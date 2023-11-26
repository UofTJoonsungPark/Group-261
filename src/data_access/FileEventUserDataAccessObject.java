package data_access;

import entity.Event;
import entity.EventFactory;
import use_case.event.EventDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileEventUserDataAccessObject implements EventDataAccessInterface {
    private final String filePath;
    private final Map<LocalDate, List<Event>> events;
    private final Map<Event, Long> eventReference;
    private final EventFactory eventFactory;
    private String username = null;

    /**
     * Initialize a new FileEventUserDataAccessObject
     *
     * @param events         The user's events
     * @param eventReference A reference to find the user's events
     * @param eventFactory   A class used to create an event
     */
    public FileEventUserDataAccessObject(Map<LocalDate, List<Event>> events,
                                         Map<Event, Long> eventReference,
                                         EventFactory eventFactory) {
        this.filePath = "DATA" + File.separator + "EventDirectory";
        this.events = events;
        this.eventReference = eventReference;
        this.eventFactory = eventFactory;
    }

    /**
     * This function makes an empty CSV file for a user in case they don't have one.
     */
    private void makeCsvFile() {
        String fileName = username + ".csv";

        // Create a File object for the folder
        File folder = new File(filePath);

        // Create a File object for the CSV file within the folder
        File csvFile = new File(folder, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Write the header to the file
            writer.write("startDate, startTime, endDate, endTime, title, location, description");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred when making the file.");
        }
    }

    public Map<LocalDate, List<Event>> getEvents(){
        return events;
    }

    public Map<Event, Long> getEventReference() {
        return eventReference;
    }

    /**
     * This method takes the given username and finds the CSV file associated with the username
     * (or calls makeCSVFile if the file does not exist). Then, it reads through the file and
     * updates the two hash maps accordingly (i.e., having "events" have its keys be the startDate
     * of the event, and the values being an ArrayList of Events that have that startDate. And having
     * "eventReference" have its keys be the events with the corresponding values being the line
     * that they can be found on in the CSV file).
     *
     * @param username The username of the user.
     */
    public void writeMaps(String username) throws RuntimeException {
        this.username = username;
        String csvFilePath = filePath + File.separator + username + ".csv";

        long lineNumber = 1;

        try {
            File file = new File(csvFilePath);

            if (!file.exists()) {
                makeCsvFile();
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String header = reader.readLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                assert header.equals("startDate, startTime, endDate, endTime, title, location, description");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    LocalDate startDate = LocalDate.parse(col[0], dateFormatter);
                    LocalDate endDate = LocalDate.parse(col[2], dateFormatter);
                    LocalTime startTime = LocalTime.parse(col[1], timeFormatter);
                    LocalTime endTime = LocalTime.parse(col[3], timeFormatter);
                    String title = col[4];
                    String location = col[5];
                    String description = col[6];

                    Event event = eventFactory.create(startDate, endDate, startTime, endTime,
                            title, description, location);

                    // UPDATE EVENTS
                    // find the days that the event happens on
                    List<LocalDate> eventDays = getDatesBetween(event.getStartDate(), event.getEndDate());

                    // iterate through the days and put them in the corresponding arraylists in events
                    for (LocalDate date : eventDays) {

                        // check if the key (date) is in events already
                        if (events.containsKey(date)) {
                            // Get the ArrayList of events and add event to it
                            List<Event> existingList = events.get(date);
                            existingList.add(event);

                        } else {
                            // If the key doesn't exist, create a new ArrayList<Event>
                            List<Event> newList = new ArrayList<>();
                            newList.add(event);
                            events.put(date, newList);
                        }
                    }

                    //UPDATE EVENT REFERENCE
                    eventReference.put(event, lineNumber);

                    lineNumber++;

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method takes a startDate and an endDate and finds the dates from the startDate
     * to the endDate, inclusive.
     *
     * @param startDate The starting date
     * @param endDate   The ending date
     * @return An array list of the date(s) between startDate and endDate.
     */
    private List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> datesInRange = new ArrayList<>();

        // find how many days are in between startDate and endDate
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);

        for (int i = 0; i <= daysBetween; i++) {
            // add "i" days to the start day until i equals the number of days in between
            // startDate and endDate (which would make startDate.plusDays(i) = endDate
            LocalDate date = startDate.plusDays(i);
            datesInRange.add(date);
        }

        return datesInRange;
    }


    /**
     * This method is used when a user logs out. This clears the current hash maps so it
     * won't contain the information of that user's events anymore. It also clears the
     * username attribute.
     */
    public void clearMaps() {
        events.clear();
        eventReference.clear();

        this.username = null;
    }

    /**
     * This method saves an event into the database.
     * @param event The event to be saved.
     */
    @Override
    public void saveEvent(Event event) throws RuntimeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // add this event to the csv file
        String title = event.getTitle();
        String description = event.getDescription();
        String location = event.getLocation();
        String startDate = event.getStartDate().format(dateFormatter);
        String endDate = event.getEndDate().format(dateFormatter);
        String startTime = event.getStartTime().format(timeFormatter);
        String endTime = event.getEndTime().format(timeFormatter);

        long lineNumber = csvAppender(startDate, endDate, startTime, endTime, title, location,
                description);

        // add this event to the events attribute
        // find the days that the event happens on
        List<LocalDate> eventDays = getDatesBetween(event.getStartDate(), event.getEndDate());

        // iterate through the days and put them in the corresponding arraylists in events
        for (LocalDate date : eventDays) {

            // check if the key (date) is in events already
            if (events.containsKey(date)) {
                // Get the ArrayList of events and add event to it
                List<Event> existingList = events.get(date);
                existingList.add(event);

            } else {
                // If the key doesn't exist, create a new ArrayList<Event>
                List<Event> newList = new ArrayList<>();
                newList.add(event);
                events.put(date, newList);
            }
        }

        // add this event to the events reference attribute
        eventReference.put(event, lineNumber);
    }

    /**
     * This is a helper method for the saveEvent method. This method saves the strings
     * into the CSV file as a new line and returns the number of the line that the
     * new line is written on.
     * @param startDate     The start date of the event.
     * @param endDate       Then end date of the event.
     * @param startTime     The start time of the event.
     * @param endTime       The end tim eof the event.
     * @param title         The title of the event.
     * @param location      The location of the event.
     * @param description   The description of the event.
     * @return the number of the line in the CSV file that the method prints the information on.
     */
    private long csvAppender(String startDate, String endDate, String startTime, String endTime,
                             String title, String location, String description) {
        String directoryPath = this.filePath + File.separator + this.username + ".csv";

        String newLine = String.join(",", startDate, startTime, endDate, endTime,
                title, location, description);

        long lineCount;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath, true))) {
            // append the event to the end of this file
            writer.newLine();
            writer.write(newLine);

            // get the number of the line that the new line is printed on
            lineCount = Files.lines(Paths.get(directoryPath)).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lineCount;
    }
    }

package data_access;

import entity.Event;
import entity.EventFactory;
import use_case.event.EventDataAccessInterface;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class FileEventUserDataAccessObject implements EventDataAccessInterface {
    private String filePath;
    private Map<LocalDate, ArrayList<Event>> events;
    private Map<Event, Integer> eventReference;
    private EventFactory eventFactory;

    /**
     * Initialize a new FileEventUserDataAccessObject
     * @param events            The user's events
     * @param eventReference    A reference to find the user's events
     * @param eventFactory      A class used to create an event
     */
    public FileEventUserDataAccessObject(Map<LocalDate, ArrayList<Event>> events,
                                         Map<Event, String> eventReference,
                                         EventFactory eventFactory) {
        this.filePath = "EventDirectory";
        this.events = events;
        this.eventReference = eventReference;
        this.eventFactory = eventFactory;
    }

    /**
     * This function makes an empty CSV file for a user in case they don't have one.
     * @param username  The username of the user.
     */
    private void makeCsvFile(String username) {
        String folderName = filePath;
        String fileName = username + ".csv";

        // Create a File object for the folder
        File folder = new File(folderName);

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

    /**
     * This function finds the file for the given username in the EventDirectory and then
     * updates the hash maps to reflect the information.
     * @param username  The username of the user.
     */
    public void writeMaps(String username) {
        String csvFilePath = filePath + username + ".csv";

        int lineNumber = 1;

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
                // Check if the key (startDate) exists
                if (events.containsKey(startDate)) {
                    // Get the ArrayList of events and add event to it
                    ArrayList<Event> existingList = events.get(startDate);
                    existingList.add(event);
                } else {
                    // If the key doesn't exist, create a new ArrayList<Event>
                    ArrayList<Event> newList = new ArrayList<>();
                    newList.add(event);
                    events.put(startDate, newList);
                }

                //UPDATE EVENT REFERENCE
                eventReference.put(event, lineNumber);

                lineNumber ++;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This function is used when a user logs out. This clears the current hash maps so it
     * won't contain the information of that user's events anymore.
     */
    public void clearMaps() {
        events.clear();
        eventReference.clear();
    }


    @Override
    public void saveEvent(Event event, String user) {

    }
}

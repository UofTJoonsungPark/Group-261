package data_access;

import entity.Event;
import entity.User;
import use_case.event.EventDataAccessInterface;

import java.io.*;
import java.util.ArrayList;

public class FileEventUserDataAccessObject implements EventDataAccessInterface {


    /**
     * Saves a new event to the user's txt file.
     *
     * @param event the event to be saved.
     * @param user  the username of the user that created the event.
     */
    @Override
    public void saveEvent(Event event, String user) {
        String printedDate = event.getStartDateAsString();
        String filePath = "EventDirectory" + File.separator + user;

        if (doesDateExist(printedDate, filePath)) {
            // There's already an array list associated with this date.
            // Append the event to the existing list.

            // Read existing events from the file
            ArrayList<EventListInfo> existingEvents = readEventsFromFile(filePath);

            // Find the event list associated with the printed date
            EventListInfo existingEventListInfo = findEventListInfo(existingEvents, printedDate);

            if (existingEventListInfo != null) {
                // Append the event to the existing list
                existingEventListInfo.getEvents().add(event.toString());
            } else {
                // This should not happen if the file is formatted correctly
                System.out.println("Error: Could not find the event list for date " + printedDate);
            }

            // Save the updated list back to the file
            saveEventsToFile(existingEvents, filePath);

        } else {
            // There is no line that refers to the date, so we make a new line with the
            // date and then create a new array list for it with the event inside it.

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                // Write the event's date followed by a comma and the event in a new array list
                writer.write(printedDate + ",[" + event.toString() + "]");
                writer.newLine();

            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception according to your needs
            }
        }
    }

    /**
     * Checks to see if the event date is already listed in the user's txt file.
     * @param dateToSearch  The date to find in the file
     * @param filePath      The user's txt file
     * @return a boolean that is true if dateToSearch is already a key in filePath. Otherwise
     * return false.
     */
    public static boolean doesDateExist(String dateToSearch, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // for each line in the file, split it into two parts: one for the event day, and the other for the list
                // of events for that specific date.
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String eventDate = parts[0].trim();

                    if (eventDate.equals(dateToSearch)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Stores all the events in the user's file.
     * @param filePath  the user's txt file.
     * @return an arraylist that contains all the event data.
     */
    private ArrayList<EventListInfo> readEventsFromFile(String filePath) {
        ArrayList<EventListInfo> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse the line to create EventListInfo objects
                EventListInfo eventListInfo = parseLine(line);
                if (eventListInfo != null) {
                    events.add(eventListInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return events;
    }

    private void saveEventsToFile(ArrayList<EventListInfo> events, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (EventListInfo eventListInfo : events) {
                // Write the event's date followed by a comma and the events in the array list
//                writer.write(eventListInfo.getPrintedDate() + "," + eventListInfo.getEventsAsString());
                // Move to the next line
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

    /**
     * This function finds the event in events that is associated with printedDate.
     * @param events        The events.
     * @param printedDate   The date we want to find the eventinfo for.
     * @return EventListInfo that contains the same date as printedDate.
     */
    private EventListInfo findEventListInfo(ArrayList<EventListInfo> events, String printedDate) {
        for (EventListInfo eventListInfo : events) {
            if (eventListInfo.getPrintedDate().equals(printedDate)) {
                return eventListInfo;
            }
        }
        return null;
    }

    private EventListInfo parseLine(String line) {
        String[] parts = line.split(",");
        if (parts.length == 2) {
            String printedDate = parts[0].trim();
            String eventsAsString = parts[1].trim();
            ArrayList<String> events = parseEvents(eventsAsString);
            return new EventListInfo(printedDate, events);
        }
        return null;
    }

    private ArrayList<String> parseEvents(String eventsAsString) {
        ArrayList<String> events = new ArrayList<>();
        // Implement the logic to parse events from the string and add them to the list
        return events;
    }

    /**
     * A private class that represents one line of the txt file (i.e., a date and its associated events).
     */
    private static class EventListInfo {
        private final String printedDate;
        private final ArrayList<String> events;

        /**
         * Initialize a new EventListInfo.
         * @param printedDate   The day of this event info.
         * @param events        The events happening on this day.
         */
        public EventListInfo(String printedDate, ArrayList<String> events) {
            this.printedDate = printedDate;
            this.events = events;
        }

        /**
         * Gets the date.
         * @return the printedDate attribute.
         */
        public String getPrintedDate() {
            return printedDate;
        }

        /**
         * Gets the events.
         *
         * @return the events attribute.
         */
        public ArrayList<String> getEvents() {
            return events;
        }

        /**
         *
         * @return
         */
//        public String getEventsAsString() {
//            StringBuilder result = new StringBuilder("[");
//            for (Event event : events) {
//                result.append(event.toString()).append(",");
//            }
//            if (result.length() > 1) {
//                result.deleteCharAt(result.length() - 1); // Remove the trailing comma
//            }
//            result.append("]");
//            return result.toString();
//        }
    }
}

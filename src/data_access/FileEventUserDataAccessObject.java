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
     * @param user  the user that created the event.
     */
    @Override
    public void saveEvent(Event event, User user) {
        String printedDate = event.getStartDateAsString();
        String filePath = "EventDirectory" + File.separator + user.getName();

        if (doesDateExist(printedDate, filePath)) {
            // There's already an array list associated with this date.
            // Append the event to the existing list.

            // Read existing events from the file
            ArrayList<EventListInfo> existingEvents = readEventsFromFile(filePath);

            // Find the event list associated with the printed date
            EventListInfo existingEventListInfo = findEventListInfo(existingEvents, printedDate);

            if (existingEventListInfo != null) {
                // Append the event to the existing list
                existingEventListInfo.getEvents().add(event);
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

    public static boolean doesDateExist(String dateToSearch, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
                writer.write(eventListInfo.getPrintedDate() + "," + eventListInfo.getEventsAsString());
                // Move to the next line
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }

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
            ArrayList<Event> events = parseEvents(eventsAsString);
            return new EventListInfo(printedDate, events);
        }
        return null;
    }

    private ArrayList<Event> parseEvents(String eventsAsString) {
        ArrayList<Event> events = new ArrayList<>();
        // Implement the logic to parse events from the string and add them to the list
        return events;
    }

    private static class EventListInfo {
        private final String printedDate;
        private final ArrayList<Event> events;

        public EventListInfo(String printedDate, ArrayList<Event> events) {
            this.printedDate = printedDate;
            this.events = events;
        }

        public String getPrintedDate() {
            return printedDate;
        }

        public ArrayList<Event> getEvents() {
            return events;
        }

        public String getEventsAsString() {
            StringBuilder result = new StringBuilder("[");
            for (Event event : events) {
                result.append(event.toString()).append(",");
            }
            if (result.length() > 1) {
                result.deleteCharAt(result.length() - 1); // Remove the trailing comma
            }
            result.append("]");
            return result.toString();
        }
    }
}

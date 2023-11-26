import data_access.FileEventUserDataAccessObject;
import entity.Event;
import entity.EventFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.event.EventDataAccessInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FileEventUserDataAccessObjectTest {

    private EventDataAccessInterface dataAccessObject;

    @Before
    public void setUp() {
        // Create mock data for testing
        Map<LocalDate, List<Event>> events = new HashMap<>();
        Map<Event, Long> eventReference = new HashMap<>();
        EventFactory eventFactory = new EventFactory();
        dataAccessObject = new FileEventUserDataAccessObject(events, eventReference, eventFactory);
    }

    @Test
    public void testWriteMapsUpdatesAttributesForUsername() {
        // Make a simple CSV file
        String csvData = "startDate, startTime, endDate, endTime, title, location, description\n" +
                "2023-11-25,01:00,2023-11-26,02:30,title,location,description";

        // The assigned username to the file
        String username = "username";

        // Set up the CSV file
        String filePath = "DATA" + File.separator + "EventDirectory" + File.separator + username + ".csv";
        createFileWithContent(filePath, csvData);

        // Call writeMaps method
        dataAccessObject.writeMaps(username);

        // Check if the attributes are updated correctly
        Map<LocalDate, List<Event>> events = ((FileEventUserDataAccessObject) dataAccessObject).getEvents();
        Map<Event, Long> eventReference = ((FileEventUserDataAccessObject) dataAccessObject).getEventReference();

        // See that there are two dates associated to the event in events, and only one reference number
        // associated with the event in eventsReference
        assertEquals(2, events.size());
        assertEquals(1, eventReference.size());

        // Clean up: delete the temporary file
        deleteFile(filePath);
    }

    private void createFileWithContent(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
        file.delete();
    }
    }
}

package Event;

import data_access.FileEventUserDataAccessObject;
import entity.Event;
import entity.EventFactory;
import interface_adapter.event.EventPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.event.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreateEventTest {
    private EventPresenter eventPresenter;
    private EventFactory eventFactory;
    private EventDataAccessInterface eventDataAccessInterface;


    @Before
    public void setUp() {
        // Set up the dependencies
        List<String> emptyList = new ArrayList<String>();
        eventPresenter = new EventPresenter();
        eventFactory = new EventFactory();
        eventDataAccessInterface = new FileEventUserDataAccessObject(new HashMap<>(), new HashMap<>(), eventFactory);
    }
    @Test
    public void createEvent() {
        // Arrange
        EventDataAccessInterface eventDataAccessInterface = new FileEventUserDataAccessObject(new HashMap<>(), new HashMap<>(),
                new EventFactory());

        EventInteractor eventInteractor = new EventInteractor(eventPresenter, eventFactory, eventDataAccessInterface);

        // test if the program successfully makes the user's csv file
        eventInteractor.initialize("testingUser");
        String filePath = "DATA/EventDirectory/testingUser.csv";
        File file = new File(filePath);

        assert file.exists();

        // test if a new event is successfully saved in the DAO
        Event event = new Event(LocalDate.now(), LocalDate.now(), LocalTime.now(), LocalTime.now(), "title", "description",
                "location");

        // create new EventInputData to put into the interactor
        EventInputData eventInputData = new EventInputData("createEvent");
        eventInputData.setEndTime(LocalTime.of(2, 0));
        eventInputData.setStartTime(LocalTime.of(0, 0));
        eventInputData.setEndDate(LocalDate.of(2023, 11, 27));
        eventInputData.setTitle("1");
        eventInputData.setLocation("2");
        eventInputData.setDescription("3");
        eventInputData.setStartDate(LocalDate.of(2023, 11, 27));

        // pass the input data into the interactor
        eventInteractor.execute(eventInputData);

        // see if the CSV file was successfully updated
        boolean result = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))  {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("2023-11-27,00:00,2023-11-27,02:00,1,2,3")) {
                    result = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert result;

        file.delete();

    }
}

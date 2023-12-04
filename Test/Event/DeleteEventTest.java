package Event;

import data_access.FileEventUserDataAccessObject;
import entity.Event;
import entity.EventFactory;
import interface_adapter.event.EventPresenter;
import org.junit.Before;
import org.junit.Test;
import use_case.event.EventDataAccessInterface;
import use_case.event.EventInputData;
import use_case.event.EventInteractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteEventTest {
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
        public void deleteEvent() {
            // Arrange
            EventDataAccessInterface eventDataAccessInterface = new FileEventUserDataAccessObject(new HashMap<>(), new HashMap<>(),
                    new EventFactory());

            EventInteractor eventInteractor = new EventInteractor(eventPresenter, eventFactory, eventDataAccessInterface);

            // add an event to the file
            eventInteractor.initialize("testingUser");
            String filePath = "DATA/EventDirectory/testingUser.csv";
            File file = new File(filePath);

            Event event = new Event(LocalDate.now(), LocalDate.now(), LocalTime.now(), LocalTime.now(), "title", "description",
                    "location");

            EventInputData setUpeventInputData = new EventInputData("createEvent");
            setUpeventInputData.setEndTime(LocalTime.of(2, 0));
            setUpeventInputData.setStartTime(LocalTime.of(0, 0));
            setUpeventInputData.setEndDate(LocalDate.of(2023, 11, 27));
            setUpeventInputData.setTitle("1");
            setUpeventInputData.setLocation("2");
            setUpeventInputData.setDescription("3");
            setUpeventInputData.setStartDate(LocalDate.of(2023, 11, 27));

            eventInteractor.execute(setUpeventInputData);

            // delete the event
            // create the input data of the event to delete (which is the event we just created)
            EventInputData eventInputData = new EventInputData("deleteEvent");
            eventInputData.setEndTime(LocalTime.of(2, 0));
            eventInputData.setStartTime(LocalTime.of(0, 0));
            eventInputData.setEndDate(LocalDate.of(2023, 11, 27));
            eventInputData.setTitle("1");
            eventInputData.setLocation("2");
            eventInputData.setDescription("3");
            eventInputData.setStartDate(LocalDate.of(2023, 11, 27));

            // pass the input data into the interactor
            eventInteractor.execute(eventInputData);

            // check to see if the event was deleted
            boolean result = true;
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("2023-11-27,00:00,2023-11-27,02:00,1,2,3")) {
                        result = false;
                        break;

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            file.delete();
            assert result;

        }
    }

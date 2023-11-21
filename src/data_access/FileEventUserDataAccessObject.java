package data_access;

import entity.Event;
import entity.EventFactory;
import use_case.event.EventDataAccessInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class FileEventUserDataAccessObject implements EventDataAccessInterface {
    private String filePath;
    private Map<LocalDate, ArrayList<Event>>  events;
    private Map<Event, String> eventReference;
    private EventFactory eventFactory;

    public FileEventUserDataAccessObject(String filePath,
                                         Map<LocalDate, ArrayList<Event>> events,
                                         Map<Event, String> eventReference,
                                         EventFactory eventFactory) {
        this.filePath = filePath;
        this.events = events;
        this.eventReference = eventReference;
        this.eventFactory = eventFactory;
    }


    @Override
    public void saveEvent(Event event, String user) {

    }
}

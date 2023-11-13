package data_access;

import entity.Event;
import use_case.event.EventDataAccessInterface;

import java.util.ArrayList;

public class FileEventUserDataAccessObject implements EventDataAccessInterface {

    // TODO: potentially change the data type that stores the events.
    private ArrayList<Event> events;

    public FileEventUserDataAccessObject(ArrayList<Event> events) {
        this.events = events;
    }

    @Override
    public void saveEvent(Event event) {
        this.events.add(event);
    }
}

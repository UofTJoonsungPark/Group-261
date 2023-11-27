package use_case.event;

import java.util.List;

public class EventOutputData {
    private final List<String> events;

    public EventOutputData(List<String> events) {
        this.events = events;
    }

    /**
     * This method is to get a list of Events
     * @return a list of events
     */
    public List<String> getEvents() {
        return events;
    }
}

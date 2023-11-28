package use_case.event;

import entity.Event;
import entity.User;

import java.time.LocalDate;
import java.util.List;

public interface EventDataAccessInterface {
    void writeMaps(String username);

    void saveEvent(Event event);

    void clearMaps();

    void deleteEvent(Event event);

    List<String> getEvents(LocalDate date);
}

package use_case.event;

import entity.Event;
import entity.User;

public interface EventDataAccessInterface {
    void writeMaps(String username);

    void saveEvent(Event event);
}

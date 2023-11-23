package use_case.event;

import entity.Event;
import entity.User;

public interface EventDataAccessInterface {
    void saveEvent(Event event, String user);

    void writeMaps(String username);
}

package interface_adapter.event;

import java.util.ArrayList;
import java.util.List;

/**
 * The EventState class is observed in EventViewModel class
 */
public class EventState {
    private final List<String> EMPTY_LIST = new ArrayList<>();
    private String username = "";
    private String useCase = "";
    private List<String> events = EMPTY_LIST;
    private String error = null;

    /**
     * Getter method for username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setting method for username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for the use case
     *
     * @return the use case of the state
     */
    public String getUseCase() {
        return useCase;
    }

    /**
     * Setter method for the use case
     *
     * @param useCase the use case of the state
     */

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }

    /**
     * This method is responsible to get an error message.
     * @return error message
     */
    public String getError() {
        return error;
    }

    /**
     * Set an error message from the interactor.
     * @param error error message
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Set event list
     * @param events a list of Events
     */
    public void setEvents(List<String> events) {
        this.events = events;
    }

    /**
     * Get event list
     * @return a list of Events
     */
    public List<String> getEvents() {
        return events;
    }
}

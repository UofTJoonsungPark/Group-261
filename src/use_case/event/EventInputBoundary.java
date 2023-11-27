package use_case.event;

public interface EventInputBoundary {
    void execute(EventInputData eventInputdata);

    /**
     * This method is used for initializing the data structure of Event
     * @param username  the logged-in username
     */
    void initialize(String username);
}

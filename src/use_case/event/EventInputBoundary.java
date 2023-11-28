package use_case.event;

import java.time.LocalDate;

public interface EventInputBoundary {
    void execute(EventInputData eventInputdata);

    /**
     * This method is used for initializing the data structure of Event
     * @param username  the logged-in username
     */
    void initialize(String username);

    /**
     * This method is used for query request with the date
     * @param date the given date by user
     */
    void query(LocalDate date);
}

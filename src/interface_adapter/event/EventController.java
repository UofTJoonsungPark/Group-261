package interface_adapter.event;

import use_case.event.EventInputBoundary;
import use_case.event.EventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventController {
    private final EventInputBoundary eventUseCaseInteractor;
    private final EventPresenter eventPresenter;

    /**
     * Initialize a new EventController.
     * @param eventUseCaseInteractor The interactor for use cases.
     * @param eventPresenter         The presenter for the interactor.
     */
    public EventController(EventInputBoundary eventUseCaseInteractor, EventPresenter eventPresenter) {
        this.eventUseCaseInteractor = eventUseCaseInteractor;
        this.eventPresenter = eventPresenter;
    }

    /**
     * Execute the given use case.
     *
     * @param useCase use case which does not interact with entities.
     * Other parameters are for createEventInputData.
     * Check the createEventInputData javadoc for detailed descriptions.
     */
    public void execute(String useCase, String title, String location, String description,
                        LocalDate startDate, LocalTime startTime,
                        LocalDate endDate, LocalTime endTime) {
        EventInputData eventInputData = createEventInputData(useCase, title, location, description,
                startDate, startTime, endDate, endTime);
        eventUseCaseInteractor.execute(eventInputData);
    }

    /**
     *  Execute the given use case.
     *
     * @param useCase use case
    */
    public void execute(String useCase) {
        if ("back".equals(useCase)) {
            eventPresenter.changeView();
        }
//        else if (useCase.equals("clear")) {
//            EventInputData eventInputData = new EventInputData(useCase);
//            eventUseCaseInteractor.execute(eventInputData);
//        }
    }

    public void initialize(String username) {
        eventUseCaseInteractor.initialize(username);
        query(LocalDate.now());
    }

    /**
     * The method is used to query data for Events
     * @param date the date given by user
     */
    public void query(LocalDate date) {
        eventUseCaseInteractor.query(date);
    }

    /**
     * The method is used to delete the selected events
     * @param date the current date in view
     * @param indices array of the selected indices in JList
     */
    public void delete(LocalDate date, int[] indices) {
        eventUseCaseInteractor.delete(date, indices);
        query(date);
    }

    /**
     * Creates an eventInputData from the parameters
     * @param useCase      use case which does not interact with entities.
     * @param title        the title(name) of the event
     * @param location     the location of where the event is held
     * @param description  the detailed description for the event
     * @param startDate    when the event starts
     * @param startTime    "
     * @param endDate      "
     * @param endTime      "
     * @return a new eventInputData
     */
    public EventInputData createEventInputData(String useCase, String title, String location, String description,
                            LocalDate startDate, LocalTime startTime,
                            LocalDate endDate, LocalTime endTime) {
        EventInputData eventInputData = new EventInputData(useCase);

        eventInputData.setTitle(title);
        eventInputData.setLocation(location);
        eventInputData.setDescription(description);
        eventInputData.setStartDate(startDate);
        eventInputData.setEndDate(endDate);
        eventInputData.setStartTime(startTime);
        eventInputData.setEndTime(endTime);
        return eventInputData;
    }
}

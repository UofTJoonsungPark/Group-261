package use_case.event;

import entity.CommonUser;
import entity.Event;
import entity.EventFactory;
import entity.User;

/**
 * This class represents the interactor for events.
 */
public class EventInteractor implements EventInputBoundary {
    private final EventOutputBoundary eventPresenter;
    private final EventFactory eventFactory;
    private final EventDataAccessInterface eventDataAccessObject;

    /**
     * Initialize a new EventInteractor.
     * @param eventPresenter        The presenter for the interactor.
     * @param eventFactory          The factory to make new events.
     * @param eventDataAccessObject The database to access events in the system.
     */
    public EventInteractor(EventOutputBoundary eventPresenter, EventFactory eventFactory, EventDataAccessInterface eventDataAccessObject) {
        this.eventPresenter = eventPresenter;
        this.eventFactory = eventFactory;
        this.eventDataAccessObject = eventDataAccessObject;
    }

    /**
     * Executes use cases with the event input data depending on what the eventInputData is (more on this
     * is in the eventInputData class).
     * @param eventInputData    The data given to the interactor.
     */
    @Override
    public void execute(EventInputData eventInputData) {
        if (eventInputData.getUseCase().equals("createEvent")) {
            /* TODO: required to check all argument is valid
            start date and time should be earlier than end date and time
            decision should be made on which text field can be empty like below:
                title should not be a empty string
                description or location can be optional

            if the given input is invalid,
                eventPresenter.prepareFailView()
            else
                eventPresenter.prepareSuccessView() (after saving the event)
             */
            Event event = eventFactory.create(eventInputData.getStartDate(), eventInputData.getEndDate(),
                    eventInputData.getStartTime(), eventInputData.getEndTime(), eventInputData.getTitle(),
                    eventInputData.getDescription(), eventInputData.getLocation());
            try {
                eventDataAccessObject.saveEvent(event);
            } catch (RuntimeException e){
                eventPresenter.prepareFailView("Failed to save data");
            }
        }
    }

    /**
     * Send a request to DAO to initialize the data structure for Event
     * @param username  the logged-in username
     */
    public void initialize(String username) {
        try{
            eventDataAccessObject.writeMaps(username);
        } catch (RuntimeException e) {
            eventPresenter.prepareFailView("Failed to load data");
        }
    }
}

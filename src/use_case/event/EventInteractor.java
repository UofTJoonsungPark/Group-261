package use_case.event;

import entity.CommonUser;
import entity.Event;
import entity.EventFactory;
import entity.User;

/**
 * This class represents the interactor for events.
 */
public class EventInteractor implements EventInputBoundary {
    final EventOutputBoundary eventPresenter;
    final EventFactory eventFactory;
    final EventDataAccessInterface eventDataAccessObject;

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
            Event event = eventFactory.create(eventInputData.getStartDate(), eventInputData.getEndDate(),
                    eventInputData.getStartTime(), eventInputData.getEndTime(), eventInputData.getTitle(),
                    eventInputData.getDescription(), eventInputData.getLocation());

            eventDataAccessObject.saveEvent(event);
        }
    }

    /**
     * Send a request to DAO to initialize the data structure for Event
     * @param username  the logged-in username
     */
    public void initialize(String username) {
        eventDataAccessObject.writeMaps(username);
    }
}

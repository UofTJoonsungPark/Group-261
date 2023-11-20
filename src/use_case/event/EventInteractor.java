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
     * is in the eventinputdata class).
     * @param eventInputdata    The data given to the interactor.
     */
    @Override
    public void execute(EventInputData eventInputdata) {
        if (eventInputdata.getUseCase().equals("createEvent")) {
            Event event = eventFactory.create(eventInputdata.getStartDate(), eventInputdata.getEndDate(),
                    eventInputdata.getStartTime(), eventInputdata.getEndTime(), eventInputdata.getTitle(),
                    eventInputdata.getDescription(), eventInputdata.getLocation());

            eventDataAccessObject.saveEvent(event, "username");
        }
    }
}

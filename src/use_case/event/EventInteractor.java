package use_case.event;

import entity.Event;
import entity.EventFactory;

public class EventInteractor implements EventInputBoundary {
    final EventOutputBoundary eventPresenter;
    final EventFactory eventFactory;
    final EventDataAccessInterface eventDataAccessObject;

    public EventInteractor(EventOutputBoundary eventPresenter, EventFactory eventFactory, EventDataAccessInterface eventDataAccessObject) {
        this.eventPresenter = eventPresenter;
        this.eventFactory = eventFactory;
        this.eventDataAccessObject = eventDataAccessObject;
    }

    @Override
    public void execute(EventInputData eventInputdata) {
        if (eventInputdata.isBack()) {
            eventPresenter.prepareSuccessView(new EventOutputData(true));
            return;
        }

        else {
            Event event = eventFactory.create(eventInputdata.getStartDate(), eventInputdata.getEndDate(),
                    eventInputdata.getStartTime(), eventInputdata.getEndTime(), eventInputdata.getTitle(),
                    eventInputdata.getDescription(), eventInputdata.getLocation());

            eventDataAccessObject.saveEvent(event);

        }

    }
}

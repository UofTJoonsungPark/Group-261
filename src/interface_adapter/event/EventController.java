package interface_adapter.event;

import use_case.event.EventInputBoundary;
import use_case.event.EventInputData;

public class EventController {
    final EventInputBoundary eventUseCaseInteractor;

    public EventController(EventInputBoundary eventUseCaseInteractor) {
        this.eventUseCaseInteractor = eventUseCaseInteractor;
    }

    public void execute(boolean back) {
        if (back) {
            eventUseCaseInteractor.execute(new EventInputData(true));
        }
    }
}

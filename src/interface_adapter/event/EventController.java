package interface_adapter.event;

import use_case.event.EventInputBoundary;
import use_case.event.EventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventController {
    final EventInputBoundary eventUseCaseInteractor;
    final EventPresenter eventPresenter;

    public EventController(EventInputBoundary eventUseCaseInteractor, EventPresenter eventPresenter) {
        this.eventUseCaseInteractor = eventUseCaseInteractor;
        this.eventPresenter = eventPresenter;
    }

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
     * @param useCase use case which does not interact with entities.
    */
    public void execute(String useCase) {
        if ("back".equals(useCase)) {
            eventPresenter.changeView();
        }
    }

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

package interface_adapter.event;

import use_case.event.EventInputBoundary;
import use_case.event.EventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public void createEvent(String title, String location, String description,
                            LocalDate startDate, LocalTime startTime,
                            LocalDate endDate, LocalTime endTime) {
        EventInputData eventInputData = new EventInputData(false);

        eventInputData.setTitle(title);
        eventInputData.setLocation(location);
        eventInputData.setDescription(description);
        eventInputData.setStartDate(startDate);
        eventInputData.setEndDate(endDate);
        eventInputData.setStartTime(startTime);
        eventInputData.setEndTime(endTime);

        eventUseCaseInteractor.execute(eventInputData);

    }
}

package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventFactory {

    public Event create(LocalDate startDate, LocalDate endDate,
                        LocalTime startTime, LocalTime endTime,
                        String title, String description, String location) {

        return new Event(startDate, endDate, startTime, endTime, title, description, location);
    }

}

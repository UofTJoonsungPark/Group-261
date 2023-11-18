package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    private String title;
    private String description;
    private String location;

    public Event(LocalDate startDate, LocalDate endDate,
                 LocalTime startTime, LocalTime endTime,
                 String title, String description, String location) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method prints out the Event by giving its time, title, and location.
     * @return A string of the Event information.
     */
    public String toString() {
        // Create a formatter for the start/end time of the event
        DateTimeFormatter hourMinute = DateTimeFormatter.ofPattern("HH:mm");

        String formattedStartTime = this.startTime.format(hourMinute);
        String formattedEndTime = this.endTime.format(hourMinute);

        return this.title + System.lineSeparator() + this.location + System.lineSeparator() + formattedStartTime + "-" + formattedEndTime;
    }
}

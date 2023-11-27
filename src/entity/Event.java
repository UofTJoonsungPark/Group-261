package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a single event. This includes the start/end date and time, event title, location
 * of the event, and a description about the Event.
 */
public class Event {
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    private String title;
    private String description;
    private String location;

    /**
     * @param startDate     The day that the event starts.
     * @param endDate       The day the event ends.
     * @param startTime     The time that the event starts.
     * @param endTime       The time theat the event ends.
     * @param title         The title of the event.
     * @param description   A description of the event.
     * @param location      The location of the event.
     */
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

    /**
     * @return the start day of the event.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @return the end day of the event.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @return the start time of the event.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @return the end time of the event.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @return the title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the location of the event.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the start date of the event to the given startDate.
     * @param startDate the start date of the event.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date of the event to the given endDate.
     * @param endDate the new end date of the event.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the start time of the event to the given startTime.
     * @param startTime the new startTime of the event.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the end time of the event to the given endTime.
     * @param endTime the new end time of the event.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the description of the Event to the given description.
     * @param description the new description of the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the location of the event as th given location.
     * @param location the new location of the event.
     */
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

    /**
     * Prints the date of the event in "yyyy-MM-dd" format.
     *
     * @return a string of the event's start date.
     */
    public String getStartDateAsString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatDate(this.startDate, dateFormat);
    }

    /**
     * A helper method for getStartDateAsString to format LocalDate into a string with the wanted format.
     *
     * @param date      The date of the event
     * @param formatter The wanted format for the date
     * @return a string of the formatted date.
     */
    private static String formatDate(LocalDate date, DateTimeFormatter formatter) {
        return date.format(formatter);
    }

    /**
     * Overrides the hashCode method
     * @return the hash value of the Event
     */
    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = result * 31 + (startTime == null ? 0 : startTime.hashCode());
        result = result * 31 + endDate.hashCode();
        result = result * 31 + (endTime == null ? 0 : endTime.hashCode());
        result = result * 31 + title.hashCode();
        result = result * 31 + description.hashCode();
        result = result * 31 + location.hashCode();
        return result;
    }

    /**
     * Overrides the equals method
     * @param obj   any object to be compared
     * @return      True iff all the attributes are identical
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        return startDate.equals(event.startDate) && startTime.equals(startTime) &&
                endDate.equals(event.endDate) && endTime.equals(event.endTime) &&
                title.equals(event.title) && description.equals(event.description) &&
                location.equals(event.location);
    }
}

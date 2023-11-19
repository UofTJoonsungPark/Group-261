package use_case.event;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class represented the input data that can be given to the interactor.
 */
public class EventInputData {
    private String useCase;
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    private String title;
    private String description;
    private String location;

    /**
     * Initializes a new EventInputData.
     * @param useCase   A string that tells the interactor what to do with the input data.
     *                  - "isBack": the user clicked the back button
     *                  - "createEvent": the user wants to create an event.
     */
    public EventInputData(String useCase) {
        this.useCase = useCase;
    }

    /**
     * @return the useCase.
     */
    public String getUseCase() {
        return useCase;
    }

    /**
     * Sets the startDate of the event input data to create a new event.
     * @param startDate the start date that the user wants to create an event for or modify an event with
     *                  this start date.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the endDate of the event input data.
     * @param endDate the end date that the user wants to create an event for or modify an event with
     *                this end date.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Sets the start time of the event input data.
     * @param startTime the start time that the user wants to create an event for or modify an event with
     *                  this start time.
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the end time of the event input data.
     * @param endTime the end time that the user wants to create an event for or modify an event with
     *                this end time.
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets the title of the event input data.
     * @param title the title that the user wants to create an event for or modify an event with
     *              this title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the description of the event input data.
     * @param description the description that the user wants to create an event for or modify an event with
     *                    this description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the location of this event input data.
     * @param location the location that the uer wants to create an event for or modify an event with
     *                 this location.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the startDate of the event input data.
     * @return the startDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the event input data.
     * @return the endDate.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Gets the start time of the event input data..
     * @return the startTime.
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the event input data.
     * @return the endTime.
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Gets the title of the event input data.
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the description of the event input data.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the location of the event input data.
     * @return the location.
     */
    public String getLocation() {
        return location;
    }
}

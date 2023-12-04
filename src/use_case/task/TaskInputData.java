
package use_case.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The TaskInputData class represents the input data for task-related actions.
 */
public class TaskInputData {
    private String title;             // Title of the task
    private String notes;             // Additional notes for the task
    private boolean completed;        // Completion status of the task
    private LocalDate dueDate;    // Due date for the task
    private String useCase;           // useCase for this input data

    /**
     * Getting the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setting the title of the task.
     *
     * @param title The new title for the task.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getting additional notes for the task.
     *
     * @return Additional notes for the task.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Setting additional notes for the task.
     *
     * @param notes The new notes for the task.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Checking if the task is completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Setting the completion status of the task.
     *
     * @param completed The new completion status for the task.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Getting the due date for the task.
     *
     * @return The due date for the task.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Setting the due date for the task.
     *
     * @param dueDate The new due date for the task.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getUseCase() {return useCase;}

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }
}
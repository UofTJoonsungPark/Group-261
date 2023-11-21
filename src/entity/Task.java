package entity;

import java.time.LocalDateTime;

/**
 * The Task class represents a task with a title, notes, completion status, and due date.
 */
public class Task {
    private String title;             // Title of the task
    private String notes;             // Additional notes for the task
    private boolean completed;        // Completion status of the task
    private LocalDateTime dueDate;    // Due date for the task

    /**
     * Constructor to initialize a Task object with the given parameters.
     *
     * @param title     The title of the task.
     * @param notes     Additional notes for the task.
     * @param completed The completion status of the task.
     * @param dueDate   The due date for the task.
     */
    public Task(String title, String notes, boolean completed, LocalDateTime dueDate) {
        this.title = title;
        this.notes = notes;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    /**
     * Gets the title of the task.
     *
     * @return The title of the task.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title The new title for the task.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets additional notes for the task.
     *
     * @return Additional notes for the task.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets additional notes for the task.
     *
     * @param notes The new notes for the task.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Checks if the task is completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param completed The new completion status for the task.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Gets the due date for the task.
     *
     * @return The due date for the task.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for the task.
     *
     * @param dueDate The new due date for the task.
     */
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the task, useful for debugging.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", notes='" + notes + '\'' +
                ", completed=" + completed +
                ", dueDate=" + dueDate +
                '}';
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = result * 31 + notes.hashCode();
        result = result * 31 + Boolean.hashCode(completed);
        result = result * 31 + dueDate.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        return title.equals(task.title) && notes.equals(task.notes) &&
                completed == task.completed && dueDate.equals(task.dueDate);
    }
}

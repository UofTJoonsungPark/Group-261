package entity;

import java.time.LocalDateTime;

/**
 * The TaskFactory class is responsible for creating instances of the Task class.
 */

public class TaskFactory {

    /**
     * Creates a new Task with default values.
     *
     * @return A new Task instance with default values.
     */
    public Task createTask() {
        return new Task("Untitled Task", "", false, LocalDateTime.now());
    }

    /**
     * Creates a new Task with specified values.
     *
     * @param title     The title of the task.
     * @param notes     Additional notes for the task.
     * @param dueDate   The due date for the task.
     * @return A new Task instance with the specified values.
     */
    public Task createTask(String title, String notes, LocalDateTime dueDate) {
        return new Task(title, notes, false, dueDate);
    }

    /**
     * Creates a completed Task with specified values.
     *
     * @param title     The title of the task.
     * @param notes     Additional notes for the task.
     * @param dueDate   The due date for the task.
     * @return A new Task instance with the specified values and marked as completed.
     */
    public Task createCompletedTask(String title, String notes, LocalDateTime dueDate) {
        Task completedTask = new Task(title, notes, true, dueDate);
        // You might want to perform additional actions for a completed task, e.g., record completion time
        return completedTask;
    }

    /**
     * Main method for testing the TaskFactory class.
     * It demonstrates how to use the TaskFactory to create different types of tasks.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        TaskFactory taskFactory = new TaskFactory();

        // Creating a task with default values
        Task defaultTask = taskFactory.createTask();
        System.out.println("Default Task: " + defaultTask);

        // Creating a task with specified values
        Task customTask = taskFactory.createTask("Custom Task", "This is a custom task", LocalDateTime.now().plusDays(3));
        System.out.println("Custom Task: " + customTask);

        // Creating a completed task with specified values
        Task completedTask = taskFactory.createCompletedTask("Completed Task", "This task is already completed", LocalDateTime.now().plusDays(7));
        System.out.println("Completed Task: " + completedTask);
    }
}
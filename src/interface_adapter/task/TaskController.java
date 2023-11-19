package interface_adapter.task;

import use_case.task.TaskInputBoundary;
import use_case.task.TaskInputData;

import java.time.LocalDateTime;

/**
 * This class handles user interface interactions related to tasks.
 */
public class TaskController {
    private final TaskInputBoundary taskUseCaseInteractor;

    /**
     * Constructor to initialize TaskController with a TaskInputBoundary.
     *
     * @param taskUseCaseInteractor The task use case interactor to handle task-related actions.
     */
    public TaskController(TaskInputBoundary taskUseCaseInteractor) {
        this.taskUseCaseInteractor = taskUseCaseInteractor;
    }

    /**
     * Executes a task-related action based on user input.
     *
     * @param back If true, indicates a request to go back.
     */
    public void execute(boolean back) {
        if (back) {
            taskUseCaseInteractor.execute(new TaskInputData());
        }
    }

    /**
     * Creates a new task with the provided details.
     *
     * @param title     The title of the task.
     * @param notes     Additional notes for the task.
     * @param completed The completion status of the task.
     * @param dueDate   The due date for the task.
     */
    public void createTask(String title, String notes, boolean completed, LocalDateTime dueDate) {
        TaskInputData taskInputData = new TaskInputData();

        taskInputData.setTitle(title);
        taskInputData.setNotes(notes);
        taskInputData.setCompleted(completed);
        taskInputData.setDueDate(dueDate);

        taskUseCaseInteractor.execute(taskInputData);
    }
}
package interface_adapter.task;

import use_case.task.TaskInputBoundary;
import use_case.task.TaskInputData;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class handles user interface interactions related to tasks.
 */
public class TaskController {
    private final TaskInputBoundary taskUseCaseInteractor;
    private final TaskPresenter taskPresenter;

    /**
     * Constructor to initialize TaskController with a TaskInputBoundary.
     *
     * @param taskUseCaseInteractor The task use case interactor to handle task-related actions.
     */
    public TaskController(TaskInputBoundary taskUseCaseInteractor, TaskPresenter taskPresenter) {
        this.taskUseCaseInteractor = taskUseCaseInteractor;
        this.taskPresenter = taskPresenter;
    }

    /**
     * Executes a task-related action based on user input.
     *
     * @param useCase use case
     */
    public void execute(String useCase) {
        if ("back".equals(useCase)) {
            taskPresenter.changeView();
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
    public void createTask(String title, String notes, boolean completed, LocalDate dueDate) {
        TaskInputData taskInputData = new TaskInputData();

        taskInputData.setTitle(title);
        taskInputData.setNotes(notes);
        taskInputData.setCompleted(completed);
        taskInputData.setDueDate(dueDate);

        taskUseCaseInteractor.execute(taskInputData);
    }
}
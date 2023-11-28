package use_case.task;

import entity.Task;
import entity.TaskFactory;
import use_case.task.TaskInputBoundary;
import use_case.task.TaskInputData;

public class TaskInteractor implements TaskInputBoundary {
    private final TaskOutputBoundary taskPresenter;
    private final TaskFactory taskFactory;
    private final TaskDataAccessInterface taskDataAccessObject;

    public TaskInteractor(TaskOutputBoundary taskPresenter, TaskFactory taskFactory,
                          TaskDataAccessInterface taskDataAccessObject) {
        this.taskPresenter = taskPresenter;
        this.taskFactory = taskFactory;
        this.taskDataAccessObject = taskDataAccessObject;
    }

    @Override
    public void execute(TaskInputData taskInputData) {
        if (taskInputData.getUseCase().equals("saveTask")) {
            Task task = taskFactory.createTask(taskInputData.getTitle(), taskInputData.getNotes(),
                    taskInputData.getDueDate());
            
            taskDataAccessObject.saveTask(task);

            // TODO: connect this use case to the presenter.

        } else if (taskInputData.getUseCase().equals("deleteTask")) {
            ;
        } else if (taskInputData.getUseCase().equals("markCompleted")) {
            ;
        }

    }
}
package app;

import data_access.FileTaskUserDataAccessObject;
import entity.TaskFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.task.TaskController;
import interface_adapter.task.TaskPresenter;
import interface_adapter.task.TaskViewModel;
import use_case.task.TaskDataAccessInterface;
import use_case.task.TaskInputBoundary;
import use_case.task.TaskInteractor;

import java.util.HashMap;

public class TaskUseCaseFactory {
    private TaskUseCaseFactory() {}

    public static TaskViewModel create(
            ViewManagerModel viewManagerModel
    ) {
        TaskController taskController = createTaskUseCase(viewManagerModel);
        TaskViewModel taskViewModel = new TaskViewModel();
        return new TaskViewModel();
    }

    private static TaskController createTaskUseCase(
            ViewManagerModel viewManagerModel
    ) {
        TaskPresenter taskPresenter = new TaskPresenter(TaskViewModel);
        TaskFactory taskFactory = new TaskFactory();
        TaskInputBoundary taskInteractor = new TaskInteractor(taskPresenter, taskFactory, taskDataAccessObject);

        TaskInputBoundary taskInteractor = new TaskInteractor();
        return new TaskController(taskInteractor, TaskInputBoundary);
    }
}

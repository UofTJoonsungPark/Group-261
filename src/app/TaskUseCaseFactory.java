package app;

import data_access.FileTaskUserDataAccessObject;
import entity.TaskFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.task.TaskController;
import interface_adapter.task.TaskPresenter;
import interface_adapter.task.TaskViewModel;
import use_case.task.TaskDataAccessInterface;
import use_case.task.TaskInputBoundary;
import use_case.task.TaskInteractor;
import use_case.task.TaskOutputBoundary;
import view.TaskView;

import java.util.HashMap;

public class TaskUseCaseFactory {
    private TaskUseCaseFactory() {}

    public static TaskView create(
            ViewManagerModel viewManagerModel,
            TaskViewModel taskViewModel,
            LoggedInViewModel loggedInViewModel
    ) {
        TaskController taskController = createTaskUseCase(viewManagerModel, taskViewModel, loggedInViewModel);
        return new TaskView(taskViewModel, taskController);
    }

    private static TaskController createTaskUseCase(
            ViewManagerModel viewManagerModel,
            TaskViewModel taskViewModel,
            LoggedInViewModel loggedInViewModel
    ) {
        TaskOutputBoundary taskOutputBoundary = new TaskPresenter(
                taskViewModel, viewManagerModel);
        TaskFactory taskFactory = new TaskFactory();
        TaskDataAccessInterface taskDataAccessObject = new FileTaskUserDataAccessObject(new HashMap<>(), taskFactory);

        TaskInputBoundary taskInputBoundary = new TaskInteractor(taskOutputBoundary, taskFactory, taskDataAccessObject);
        return new TaskController(taskInputBoundary, (TaskPresenter) taskOutputBoundary);
    }
}
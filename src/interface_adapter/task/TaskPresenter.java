package interface_adapter.task;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.task.TaskOutputBoundary;
import use_case.task.TaskOutputData;

/**
 * The TaskPresenter class is responsible for displaying the output data of the interactor on the View.
 */
public class TaskPresenter implements TaskOutputBoundary {
    private final TaskViewModel taskViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor to initialize a TaskPresenter instance.
     *
     * @param taskViewModel     Task ViewModel
     * @param loggedInViewModel LoggedIn ViewModel
     * @param viewManagerModel  ViewManager Model
     */
    public TaskPresenter(TaskViewModel taskViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.taskViewModel = taskViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Request to present success view.
     */
    @Override
    public void prepareSuccessView(TaskOutputData response) {
        // Implement how to handle success data and update the taskViewModel
    }

    /**
     * Request to change the view.
     */
    public void changeView() {
        TaskState taskState = taskViewModel.getState();
        // Implement logic based on taskState to change the view
    }

    /**
     * Request to present fail view.
     */
    @Override
    public void prepareFailView(String error) {
        TaskState taskState = taskViewModel.getState();
        taskState.setError(error);
        this.taskViewModel.firePropertyChanged();
    }
}
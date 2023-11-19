
package interface_adapter.task;

import interface_adapter.ViewManagerModel;
import use_case.task.TaskOutputBoundary;
import use_case.task.TaskOutputData;

/**
 * The TaskPresenter class is responsible for displaying the output data of the interactor on the View.
 */
public class TaskPresenter implements TaskOutputBoundary {
    private final TaskViewModel taskViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor to initialize a TaskPresenter instance.
     *
     * @param taskViewModel    Task ViewModel.
     * @param loggedInViewModel LoggedIn ViewModel.
     * @param viewManagerModel  ViewManager Model.
     */
    public TaskPresenter(TaskViewModel taskViewModel, ViewManagerModel viewManagerModel) {
        this.taskViewModel = taskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Request to present success view.
     *
     * @param taskOutputData The output data for the success view.
     */
    @Override
    public void prepareSuccessView(TaskOutputData taskOutputData) {
        // Implement logic to update the task view based on the success output data.
        // You can access taskOutputData.isSuccess() to check if the task action was successful.
    }

    /**
     * Request to change the view.
     */
    public void changeView() {
        TaskState taskState = taskViewModel.getState();
        if (taskState.getUseCase().equals("back")) {
            this.taskViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
            return;
        }
        // Add logic for handling other view changes based on taskState
    }

    /**
     * Request to present fail view.
     *
     * @param error The error message for the fail view.
     */
    @Override
    public void prepareFailView(String error) {
        // Implement logic to update the task view based on the error message.
    }
}
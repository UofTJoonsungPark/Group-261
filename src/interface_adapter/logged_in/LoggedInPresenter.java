package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.event.EventViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.task.TaskViewModel;
import interface_adapter.reward.RewardViewModel;

public class LoggedInPresenter {
    private final LoggedInViewModel loggedInViewModel;
    private final LoginViewModel loginViewModel;
    private final TaskViewModel taskViewModel;
    private final EventViewModel eventViewModel;
    private ViewManagerModel viewManagerModel;
    private final RewardViewModel rewardViewModel;

    public LoggedInPresenter(LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel,
                             EventViewModel eventViewModel, TaskViewModel taskViewModel,
                             RewardViewModel rewardViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.eventViewModel = eventViewModel;
        this.viewManagerModel = viewManagerModel;
        this.taskViewModel = taskViewModel;
        this.rewardViewModel = rewardViewModel;
    }

    public void prepareSuccessView(boolean isEvent, boolean isTask, boolean isLogout, boolean isReward) {
        if (isEvent) {
            // send a request to initialize data structure for Event
            eventViewModel.getState().setUseCase(eventViewModel.INITIALIZE_USE_CASE);

            // get username from the state
            String username = loggedInViewModel.getState().getUsername();

            // set username
            eventViewModel.getState().setUsername(username);

            // request
            eventViewModel.firePropertyChanged();

            // change the view accordingly
            viewManagerModel.setActiveView(eventViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else if (isTask) {
            // send a request to initialize data structure for Task
            taskViewModel.getState().setUseCase(taskViewModel.INITIALIZE_USE_CASE);

            // get and set username from the state
            String username = loggedInViewModel.getState().getUsername();
            taskViewModel.getState().setUsername(username);

            // request
            taskViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(taskViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else if (isLogout) {
            // clear  all the information after logout
            eventViewModel.getState().setUseCase(eventViewModel.CLEAR_USE_CASE);
            eventViewModel.firePropertyChanged();
            loginViewModel.resetState();
            loginViewModel.firePropertyChanged();

            // change the view accordingly
            viewManagerModel.setActiveView(loginViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else if (isReward) {
            String username = loggedInViewModel.getState().getUsername();
            rewardViewModel.getState().setUsername(username);
            viewManagerModel.setActiveView(rewardViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }
}

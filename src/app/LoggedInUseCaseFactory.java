package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.event.EventViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.task.TaskViewModel;
import interface_adapter.reward.RewardViewModel;
import view.LoggedInView;

public class LoggedInUseCaseFactory {

    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(
            ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel,
            EventViewModel eventViewModel, TaskViewModel taskViewModel, RewardViewModel rewardViewModel) {

        LoggedInController loggedInController = createLoggedInUseCase(viewManagerModel, loggedInViewModel,
                loginViewModel, eventViewModel, taskViewModel, rewardViewModel);

        return new LoggedInView(loggedInViewModel, loggedInController);
    }

    private static LoggedInController createLoggedInUseCase(
            ViewManagerModel viewManagerModel,
            LoggedInViewModel loggedInViewModel,
            LoginViewModel loginViewModel,
            EventViewModel eventViewModel,
            TaskViewModel taskViewModel,
            RewardViewModel rewardViewModel
    ) {
        LoggedInPresenter loggedInPresenter = new LoggedInPresenter(loggedInViewModel, loginViewModel, eventViewModel,
                taskViewModel, rewardViewModel, viewManagerModel);


        return new LoggedInController(loggedInPresenter);
    }
}

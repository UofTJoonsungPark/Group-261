package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.event.EventViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

public class LoggedInPresenter {
    private final LoggedInViewModel loggedInViewModel;
    private final LoginViewModel loginViewModel;
    private final EventViewModel eventViewModel;
    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(LoggedInViewModel loggedInViewModel, LoginViewModel loginViewModel, EventViewModel eventViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.eventViewModel = eventViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(boolean isEvent, boolean isTask, boolean isLogout) {
        if (isEvent) {
            this.viewManagerModel.setActiveView(eventViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        } else if (isTask) {

        } else if (isLogout) {
            loginViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveView(loginViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
    }
}

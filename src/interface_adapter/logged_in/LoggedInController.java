package interface_adapter.logged_in;


public class LoggedInController {
    final LoggedInPresenter loggedInPresenter;

    public LoggedInController(LoggedInPresenter loggedInPresenter) {
        this.loggedInPresenter = loggedInPresenter;
    }

    public void execute(boolean isEvent, boolean isTask, boolean isLogout, boolean isReward) {
        loggedInPresenter.prepareSuccessView(isEvent, isTask, isLogout, isReward);
    }
}

package interface_adapter.logged_in;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    public final String TITLE_LABEL = "Logged In View";
    public final String TASK_BUTTON_LABEL = "TASK";
    public final String EVENT_BUTTON_LABEL = "EVENT";
    public final String BADGES_BUTTON_LABEL = "BADGES";

    private LoggedInState state = new LoggedInState();

    public final String LOGOUT_BUTTON_LABEL = "Log out";
    private String loggedInUser;

    public LoggedInViewModel() {
        super("logged in");
    }

    public void setState(LoggedInState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoggedInState getState() {
        return state;
    }


    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}

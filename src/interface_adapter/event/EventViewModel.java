package interface_adapter.event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EventViewModel extends ViewModel {
    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String SAVE_BUTTON_LABEL = "Save";
    public static final String DELETE_BUTTON_LABEL = "Delete";
    public static final String BACK_USE_CASE = "back";
    public static final String INITIALIZE_USE_CASE = "initialize";
    public static final String CLEAR_USE_CASE = "clear";
    public static final String SAVE_USE_CASE = "createEvent";
    private EventState state = new EventState();
    public EventViewModel() {
        super("event");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public void resetState() {
        state = new EventState();
    }

}

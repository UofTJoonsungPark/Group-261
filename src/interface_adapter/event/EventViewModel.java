package interface_adapter.event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class EventViewModel extends ViewModel {
    public static final String CREATE_BUTTON_LABEL = "Create";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String SAVE_BUTTON_LABEL = "Save";
    public EventViewModel() {
        super("event");
    }
    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}

package interface_adapter.event;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class EventViewModel extends ViewModel {
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

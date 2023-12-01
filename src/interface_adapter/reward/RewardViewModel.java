package interface_adapter.reward;

import interface_adapter.ViewModel;
import interface_adapter.event.EventState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RewardViewModel extends ViewModel {
    private RewardState state;
    public RewardViewModel() {
        super("your badges");
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

    public RewardState getState() {
        return state;
    }

    public void setState(RewardState state) {
        this.state = state;
    }

    public void resetState() {
        state = new RewardState();
    }

}

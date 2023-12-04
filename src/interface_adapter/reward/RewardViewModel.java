package interface_adapter.reward;

import interface_adapter.ViewModel;
import interface_adapter.event.EventState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RewardViewModel extends ViewModel {
    private RewardState state = new RewardState();
    public static final String BACK_BUTTON_LABEL = "Back";
    public final String BACK_USE_CASE = "back";
    public final String INITIALIZE_USE_CASE = "initialize";
    public final String CLEAR_USE_CASE = "clear";
    public final String SAVE_USE_CASE = "createReward";
    public RewardViewModel() {
        super("reward");
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

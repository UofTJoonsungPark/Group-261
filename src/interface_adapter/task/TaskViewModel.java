package interface_adapter.task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The TaskViewModel class represents the ViewModel for the Task-related views.
 */
public class TaskViewModel extends ViewModel {
    public final String CREATE_BUTTON_LABEL = "Create";
    public final String BACK_BUTTON_LABEL = "Back";
    public final String BACK_USE_CASE = "back";

    private TaskState state = new TaskState();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Constructor to initialize a TaskViewModel instance.
     */
    public TaskViewModel() {
        super("task");
    }

    /**
     * Add a property change listener.
     *
     * @param listener The listener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove a property change listener.
     *
     * @param listener The listener to be removed.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Notify listeners that a property has changed.
     */
    public void firePropertyChanged() {
        propertyChangeSupport.firePropertyChange("viewModelUpdate", null, this);
    }

    public TaskState getState() {
        return state;
    }

    // Add any additional methods or properties as needed for the TaskViewModel
}
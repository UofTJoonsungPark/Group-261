package interface_adapter.task;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The TaskViewModel class represents the ViewModel for the Task-related views.
 */
public class TaskViewModel {
    private final PropertyChangeSupport propertyChangeSupport;

    /**
     * Constructor to initialize a TaskViewModel instance.
     */
    public TaskViewModel() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
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
        return getState();
    }

    // Add any additional methods or properties as needed for the TaskViewModel
}
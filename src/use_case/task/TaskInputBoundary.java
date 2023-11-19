package use_case.task;

import use_case.event.EventInputData;

/**
 * The TaskInputBoundary represents the interface for handling task-related actions.
 */
public interface TaskInputBoundary {

    /**
     * Executes a task-related action based on the input data.
     *
     * @param taskInputData The input data for the task action.
     */
    void execute(TaskInputData taskInputData);
}
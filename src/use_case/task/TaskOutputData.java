package use_case.task;

/**
 * The TaskOutputData class represents the output data for task-related actions.
 */
public class TaskOutputData {
    private boolean isSuccess;  // Represents whether the task-related action was successful

    /**
     * Constructor to initialize TaskOutputData with the success status.
     *
     * @param isSuccess Whether the task-related action was successful.
     */
    public TaskOutputData(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    /**
     * Checks if the task-related action was successful.
     *
     * @return True if the action was successful, false otherwise.
     */
    public boolean isSuccess() {
        return isSuccess;
    }
}
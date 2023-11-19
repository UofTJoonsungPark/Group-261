package use_case.task;

/**
 * The TaskOutputBoundary represents the interface for handling task-related output.
 */
public interface TaskOutputBoundary {

    /**
     * Prepares a success view based on the output data.
     *
     * @param taskOutputData The output data for the success view.
     */
    void prepareSuccessView(TaskOutputData taskOutputData);

    /**
     * Prepares a fail view with an error message.
     *
     * @param error The error message for the fail view.
     */
    void prepareFailView(String error);
}
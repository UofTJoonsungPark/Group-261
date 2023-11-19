package interface_adapter.task;

/**
 * The TaskState class is observed in TaskViewModel class.
 */
public class TaskState {
    private String useCase = null;

    /**
     * Getter method for the use case.
     *
     * @return The use case of the state.
     */
    public String getUseCase() {
        return useCase;
    }

    /**
     * Setter method for the use case.
     *
     * @param useCase The use case of the state.
     */
    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }
}
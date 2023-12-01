package interface_adapter.task;

/**
 * The TaskState class is observed in TaskViewModel class.
 */
public class TaskState {
    private String useCase = null;
    private String username = "";

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

    public String setError(String error) {
        return error;
    }

    /**
     * The method is to get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * The method is to set username
     * @param username username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }
}

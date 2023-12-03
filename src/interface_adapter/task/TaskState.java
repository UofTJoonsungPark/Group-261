package interface_adapter.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskState class is observed in TaskViewModel class.
 */
public class TaskState {
    private final List<String> EMPTY_LIST = new ArrayList<>();
    private String useCase = "";
    private String username = "";
    private String error = null;

    private List<String> tasks = EMPTY_LIST;

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

    /**
     * The method is used to set Error
     * @param error error message
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * The method is used to get Error
     * @return error message
     */
    public String getError() {
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

    /**
     * The method is to get a list of Tasks
     * @return a list of Tasks
     */
    public List<String> getTasks() {
        return tasks;
    }

    /**
     * The setter method for a list of Tasks
     * @param tasks a list of Tasks
     */
    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
}

package use_case.task;

import java.util.List;

/**
 * The TaskOutputData class represents the output data for task-related actions.
 */
public class TaskOutputData {
    private List<String> tasks;

    /**
     * Constructor to initialize TaskOutputData with the updated list of Tasks.
     *
     * @param tasks the current list of Task
     */
    public TaskOutputData(List<String> tasks) {
        this.tasks = tasks;
    }

    /**
     * The getter method for the list of Tasks
     *
     * @return the list of Tasks
     */
    public List<String> getTask() {
        return tasks;
    }

    /**
     * The setter method for the list of Tasks
     * @param tasks the current list of Tasks
     */
    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

}
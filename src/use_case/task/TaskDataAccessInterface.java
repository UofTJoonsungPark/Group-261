package use_case.task;

import entity.Task;

/**
 * The TaskDataAccessInterface represents the interface for accessing task data.
 */
public interface TaskDataAccessInterface {

    /**
     * Saves a task.
     *
     * @param task The task to be saved.
     */
    void saveTask(Task task);

    void markCompleted(Task task);

    void deleteTask(Task task);

}
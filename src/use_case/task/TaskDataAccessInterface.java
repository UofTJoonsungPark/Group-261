package use_case.task;

import entity.Task;

import java.util.List;
import java.util.Set;

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

    void deleteTask(int i);

    void writeSet(String username);

    List<Task> query();
}
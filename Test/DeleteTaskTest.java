import entity.Task;
import entity.TaskFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.task.TaskDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteTaskTest {

    private TaskDataAccessInterface dataAccess;

    @BeforeEach
    void setUp() {
        // Set up the test environment before each test method
        Map<Task, Long> tasks = new HashMap<>();
        TaskFactory taskFactory = new TaskFactory();
        dataAccess = new FileTaskUserDataAccessObject(tasks, taskFactory);
    }

    @Test
    void deleteTask_RemovesTaskFromListAndDatabase() {
        // Create a sample task and save it
        Task task = dataAccess.saveTask(new Task("Sample Task", "Sample Notes", false, null));

        // Ensure that the task is initially present in the list and the database
        assertEquals(1, dataAccess.query().size());
        assertTrue(dataAccess.query().contains(task));

        // Delete the task
        dataAccess.deleteTask(task);

        // Ensure that the task is removed from the list and the database
        assertEquals(0, dataAccess.query().size());
        assertFalse(dataAccess.query().contains(task));
    }

    @Test
    void deleteTask_WithInvalidTask_ThrowsException() {
        // Attempt to delete a task that hasn't been saved
        Task invalidTask = new Task("Invalid Task", "", false, null);

        assertThrows(RuntimeException.class, () -> dataAccess.deleteTask(invalidTask));
    }

    @Test
    void deleteTask_WithIndex_RemovesTaskFromListAndDatabase() {
        // Create a sample task and save it
        Task task = dataAccess.saveTask(new Task("Sample Task", "Sample Notes", false, null));

        // Ensure that the task is initially present in the list and the database
        assertEquals(1, dataAccess.query().size());
        assertTrue(dataAccess.query().contains(task));

        // Delete the task by index
        dataAccess.deleteTask(0);

        // Ensure that the task is removed from the list and the database
        assertEquals(0, dataAccess.query().size());
        assertFalse(dataAccess.query().contains(task));
    }

    @Test
    void deleteTask_WithInvalidIndex_ThrowsException() {
        // Attempt to delete a task with an invalid index
        int invalidIndex = 999;

        assertThrows(IndexOutOfBoundsException.class, () -> dataAccess.deleteTask(invalidIndex));
    }
}

import entity.Task;
import entity.TaskFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.task.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskTest {

    private TaskInputBoundary taskInteractor;
    private TaskOutputBoundary taskPresenter;
    private TaskDataAccessInterface taskDataAccessObject;

    @BeforeEach
    void setUp() {
        taskPresenter = new TestTaskPresenter();
        taskDataAccessObject = new TestTaskDataAccessObject();
        taskInteractor = new TaskInteractor(taskPresenter, new TaskFactory(), taskDataAccessObject);
    }

    @Test
    void testAddTask() throws IOException {
        // Arrange
        LocalDate dueDate = LocalDate.now().plusDays(1);

        TaskInputData taskInputData = new TaskInputData();
        taskInputData.setTitle("Test Task");
        taskInputData.setNotes("This is a test task");
        taskInputData.setDueDate(dueDate);
        taskInputData.setUseCase("saveTask");

        // Act
        taskInteractor.execute(taskInputData);

        // Assert
        assertEquals(1, ((TestTaskDataAccessObject) taskDataAccessObject).getSaveTaskCallCount());
        assertEquals(0, ((TestTaskPresenter) taskPresenter).getPrepareSuccessViewCallCount());
    }

    // Helper classes for testing

    static class TestTaskDataAccessObject implements TaskDataAccessInterface {
        private int saveTaskCallCount = 0;

        @Override
        public void saveTask(Task task) {
            saveTaskCallCount++;
        }

        public int getSaveTaskCallCount() {
            return saveTaskCallCount;
        }

        @Override
        public void markCompleted(Task task) {

        }

        @Override
        public void deleteTask(Task task) {

        }

        @Override
        public void deleteTask(int i) {

        }

        @Override
        public void writeSet(String username) {

        }

        @Override
        public List<Task> query() {
            return null;
        }
    }

    static class TestTaskPresenter implements TaskOutputBoundary {
        private int prepareSuccessViewCallCount = 0;

        @Override
        public void prepareSuccessView(TaskOutputData taskOutputData) {
            prepareSuccessViewCallCount++;
        }

        public int getPrepareSuccessViewCallCount() {
            return prepareSuccessViewCallCount;
        }

        @Override
        public void prepareFailView(String error) {

        }
    }
}

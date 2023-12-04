import entity.TaskFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.task.TaskDataAccessInterface;
import use_case.task.TaskInteractor;
import use_case.task.TaskInputBoundary;
import use_case.task.TaskInputData;
import use_case.task.TaskOutputBoundary;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import static javax.management.Query.times;
import static org.mockito.Mockito.*;

public class AddTaskTest {

    private TaskInputBoundary taskInteractor;
    private TaskOutputBoundary taskPresenter;
    private TaskDataAccessInterface taskDataAccessObject;

    @BeforeEach
    void setUp() {
        taskPresenter = mock(TaskOutputBoundary.class);
        taskDataAccessObject = mock(TaskDataAccessInterface.class);
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

        // Mock the behavior of DataAccessObject
        when(taskDataAccessObject.saveTask());

        // Act
        taskInteractor.execute(taskInputData);

        // Assert
        verify(taskDataAccessObject, times(1)).saveTask(anyInt()); // Change any(1) to anyInt()
        verify(taskPresenter, times(1)).prepareSuccessView(anyInt()); // Change any(1) to anyInt()
    }
}
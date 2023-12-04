//import entity.TaskFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import use_case.task.TaskDataAccessInterface;
//import use_case.task.TaskInteractor;
//import use_case.task.TaskInputBoundary;
//import use_case.task.TaskInputData;
//import use_case.task.TaskOutputBoundary;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class AddTaskTest {
//
//    private TaskInputBoundary taskInteractor;
//    private TaskOutputBoundary taskPresenter;
//    private TaskDataAccessInterface taskDataAccessObject;
//
//    @BeforeEach
//    void setUp() {
//        taskPresenter = mock(TaskOutputBoundary.class);
//        taskDataAccessObject = mock(TaskDataAccessInterface.class);
//        taskInteractor = new TaskInteractor(taskPresenter, new TaskFactory(), taskDataAccessObject);
//    }
//
//    private TaskOutputBoundary mock(Class<TaskOutputBoundary> taskOutputBoundaryClass) {
//        // I HAVE TO IMPLEMENT THIS. WHY ARE THE OTHER ONES NOT SHOWING UP???
//    }
//
//    @Test
//    void testAddTask() throws IOException {
//        // Arrange
//        LocalDateTime dueDate = LocalDateTime.now().plusDays(1);
//
//        TaskInputData taskInputData = new TaskInputData();
//        taskInputData.setTitle("Test Task");
//        taskInputData.setNotes("This is a test task");
//        taskInputData.setDueDate(dueDate);
//        taskInputData.setUseCase("saveTask");
//
//        // Mock the behavior of DataAccessObject
//        when(taskDataAccessObject.getTasks()).thenReturn(new HashMap<>());
//
//        // Act
//        taskInteractor.execute(taskInputData);
//
//        // Assert
//        verify(taskDataAccessObject, times(1)).saveTask(any());
//        verify(taskPresenter, times(1)).prepareSuccessView(any());
//
//    }
//}
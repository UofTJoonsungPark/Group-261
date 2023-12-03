import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import use_case.task.TaskDataAccessInterface;
import use_case.task.TaskInteractor;
import use_case.task.TaskOutputBoundary;
import view.TaskView;

import java.util.Collections;

import static javax.management.Query.times;
import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.mockito.Mockito.*;

public class TaskDeletionTest {

    @Mock
    private TaskOutputBoundary taskOutputBoundary;

    @Mock
    private TaskDataAccessInterface taskDataAccessObject;

    @InjectMocks
    private TaskInteractor taskInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskInteractor = new TaskInteractor(taskOutputBoundary, taskDataAccessObject);
    }

    @Test
    void testTaskDeletion() {
        // Given
        when(taskDataAccessObject.getTasks()).thenReturn(Collections.emptyList());

        // When
        taskInteractor.executeDeleteTask("Task to Delete");

        // Then
        verify(taskDataAccessObject, times(1)).deleteTask("Task to Delete");
        verify(taskOutputBoundary, times(1)).prepareSuccessView(any());
    }

    @Test
    void testTaskDeletionWithError() {
        // Given
        doThrow(new RuntimeException("Error deleting task")).when(taskDataAccessObject).deleteTask(anyString());

        // When
        taskInteractor.executeDeleteTask("Task with Error");

        // Then
        verify(taskOutputBoundary, times(1)).prepareFailView(eq("Error deleting task"));
    }
}

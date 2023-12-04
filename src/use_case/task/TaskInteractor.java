package use_case.task;

import entity.Task;
import entity.TaskFactory;
import use_case.task.TaskInputBoundary;
import use_case.task.TaskInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TaskInteractor implements TaskInputBoundary {
    private final TaskOutputBoundary taskPresenter;
    private final TaskFactory taskFactory;
    private final TaskDataAccessInterface taskDataAccessObject;

    public TaskInteractor(TaskOutputBoundary taskPresenter, TaskFactory taskFactory,
                          TaskDataAccessInterface taskDataAccessObject) {
        this.taskPresenter = taskPresenter;
        this.taskFactory = taskFactory;
        this.taskDataAccessObject = taskDataAccessObject;
    }

    @Override
    public void execute(TaskInputData taskInputData) {
            Task task = taskFactory.createTask(taskInputData.getTitle(), taskInputData.getNotes(),
                    taskInputData.isCompleted(), taskInputData.getDueDate());

            try {
                taskDataAccessObject.saveTask(task);
                query();
            } catch (RuntimeException e) {
                taskPresenter.prepareFailView("Failed to save data");
            }
    }

    public void initialize(String username) {
        try {
            taskDataAccessObject.writeSet(username);
        } catch (RuntimeException e) {
            taskPresenter.prepareFailView("Failed to load data");
        }

    }

    public void query() {
        List<Task> taskList = taskDataAccessObject.query();
        List<String> strList = new ArrayList<>();
        for (Task t : taskList) {
            strList.add(t.toString());
        }
        taskPresenter.prepareSuccessView(new TaskOutputData(strList));
    }

    public void delete(int[] indices) {
        for (int i = indices.length-1; i >= 0; i--) {
            taskDataAccessObject.deleteTask(i);
        }
    }
}
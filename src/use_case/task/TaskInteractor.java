package use_case.task;

import entity.Task;
import entity.TaskFactory;
import use_case.task.TaskInputBoundary;
import use_case.task.TaskInputData;

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
            } catch (RuntimeException e) {
                taskPresenter.prepareFailView("Failed to save data");
            }

//
//        } else if (taskInputData.getUseCase().equals("deleteTask")) {
//            Task taskToDelete = taskFactory.createTask(taskInputData.getTitle(), taskInputData.getNotes(),
//                    taskInputData.getDueDate());
//            taskDataAccessObject.deleteTask(taskToDelete);
//
//            // TODO: Connect this use case to the presenter.
//            //taskPresenter.prepareSuccessView(new TaskOutputData(taskDataAccessObject.getTasks()));
//
//        } else if (taskInputData.getUseCase().equals("markCompleted")) {
//            Task task = taskFactory.createTask(taskInputData.getTitle(), taskInputData.getNotes(),
//                    taskInputData.getDueDate());
//            taskDataAccessObject.markCompleted(task);
//
//            // TODO: Connect this use case to the presenter.
//        }
    }

    public void initialize(String username) {
        try {
            taskDataAccessObject.writeSet(username);
        } catch (RuntimeException e) {
            taskPresenter.prepareFailView("Failed to load data");
        }

    }
}
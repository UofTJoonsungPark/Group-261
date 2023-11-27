package view;

import entity.Task;
import interface_adapter.task.TaskController;
import interface_adapter.task.TaskViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The TaskView class is responsible for displaying and interacting with tasks.
 */
public class TaskView extends JPanel implements ActionListener, PropertyChangeListener  {
    private final TaskViewModel taskViewModel;
    private final TaskController taskController;

    public TaskView(TaskViewModel taskViewModel, TaskController taskController) {
        this.taskViewModel = taskViewModel;
        this.taskController = taskController;
    }

    /**
     * Displays details of a task.
     *
     * @param task The task to display.
     */
    public void displayTaskDetails(Task task) {
        System.out.println("Task Details:");
        System.out.println("Title: " + task.getTitle());
        System.out.println("Notes: " + task.getNotes());
        System.out.println("Completed: " + (task.isCompleted() ? "Yes" : "No"));
        System.out.println("Due Date: " + task.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     * Prompts the user for task input and returns a new Task instance.
     *
     * @return A new Task instance with user-provided values.
     */
    public Task promptForTaskInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Task Details:");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Notes: ");
        String notes = scanner.nextLine();

        System.out.print("Due Date (yyyy-MM-dd HH:mm): ");
        String dueDateString = scanner.nextLine();
        LocalDateTime dueDate = LocalDateTime.parse(dueDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // You can include additional prompts as needed

        // Creating and returning a new Task instance
        return new Task(title, notes, false, dueDate);
    }

    /**
     * Main method for testing the TaskView class.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
//        TaskView taskView = new TaskView();
//
//        // Testing displayTaskDetails method
//        Task sampleTask = new Task("Sample Task", "This is a sample task", false, LocalDateTime.now().plusDays(2));
//        taskView.displayTaskDetails(sampleTask);
//
//        // Testing promptForTaskInput method
//        Task userInputTask = taskView.promptForTaskInput();
//        System.out.println("User Input Task: " + userInputTask);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
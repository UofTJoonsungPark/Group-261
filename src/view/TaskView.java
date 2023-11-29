package view;

import com.github.lgooddatepicker.components.DateTimePicker;
import entity.Task;
import interface_adapter.task.TaskController;
import interface_adapter.task.TaskViewModel;

import javax.swing.*;
import java.awt.*;
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
public class TaskView extends JPanel implements ActionListener, PropertyChangeListener {
    private final static int WIDTH = 30;
    public final String viewName = "task";

    private final TaskViewModel taskViewModel;
    private final TaskController taskController;


    private final JDialog createDialog;

    private final JButton create;
    private final JButton back;
    private final JList<String> jList;


    private final JTextField title = new JTextField(WIDTH);

    private final JTextArea notes = new JTextArea(3, WIDTH+10);
    private final JCheckBox completed = new JCheckBox("completed");
    private final DateTimePicker dateTimePicker = new DateTimePicker();

    private final JButton save;

    public TaskView(TaskViewModel taskViewModel, TaskController taskController) {
        this.taskViewModel = taskViewModel;
        this.taskController = taskController;
        this.taskViewModel.addPropertyChangeListener(this);
        String[] demo = {"testTask1", "testTask2", "testTask3"};
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jList = new JList<>(demo);
        JScrollPane sp = new JScrollPane(jList);
//        sp.setPreferredSize(new Dimension(100, 100));
        JPanel buttons = new JPanel();
        create = new JButton(taskViewModel.CREATE_BUTTON_LABEL);
        back = new JButton(taskViewModel.BACK_BUTTON_LABEL);
        save = new JButton(taskViewModel.SAVE_BUTTON_LABEL);
        buttons.add(create);
        buttons.add(back);
        this.add(sp);
        this.add(buttons);

        createDialog = buildCreateDialog();

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    createDialog.setVisible(false);
                    taskViewModel.getState().setUseCsae(taskViewModel.BACK_USE_CASE);
                    taskController.execute(taskViewModel.BACK_USE_CASE);
                }
            }
        });

        create.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(create)) {
                            createDialog.setVisible(true);
                        }
                    }
                }
        );

        save.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(save) && isInputValid()) {
                            taskController.createTask();
                        }
                    }
                }
        );
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

    private JDialog buildCreateDialog() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        JDialog createDialog = new JDialog(topFrame, "Create a task");
        createDialog.setMinimumSize(new Dimension(500, 250));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel titleAndCheckBox = new JPanel();
        LabelTextPanel titleInfo = new LabelTextPanel(new JLabel("Title"), title);
        titleAndCheckBox.add(titleInfo);
        titleAndCheckBox.add(completed);

        mainPanel.add(titleAndCheckBox);

        notes.setLineWrap(true);
        notes.setWrapStyleWord(true);

        JPanel notesPanel = new JPanel();
        notesPanel.add(new JLabel("Notes"));
        JScrollPane jScrollPane = new JScrollPane(notes);
        notesPanel.add(jScrollPane);
        mainPanel.add(notesPanel);

        JPanel pickerAndButtonPanel = new JPanel();
        pickerAndButtonPanel.add(new JLabel("Due date"));
        pickerAndButtonPanel.add(dateTimePicker);
        pickerAndButtonPanel.add(save);
        mainPanel.add(pickerAndButtonPanel);

        createDialog.add(mainPanel);
        return createDialog;
    }

    private boolean isInputValid() {
        // check if title is empty
        if (title.getText().isEmpty()) {
            return false;
        }
        // check if the given due date is invalid
        // if the time for due date is provided, the date should be also provided
        if (dateTimePicker.getDatePicker().getDate() == null && dateTimePicker.timePicker.getTime() != null) {
            return false;
        }
        return true;
    }
}
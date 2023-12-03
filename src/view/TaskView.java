package view;

import com.github.lgooddatepicker.components.DatePicker;
import entity.Task;
import interface_adapter.task.TaskController;
import interface_adapter.task.TaskState;
import interface_adapter.task.TaskViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
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
    private final JButton delete;
    private final JButton back;
    private final JList<String> jList;


    private final JTextField title = new JTextField(WIDTH);

    private final JTextArea notes = new JTextArea(3, WIDTH+10);
    private final JCheckBox completed = new JCheckBox("completed");
    private final DatePicker datePicker = new DatePicker();

    private final JButton save;

    public TaskView(TaskViewModel taskViewModel, TaskController taskController) {
        this.taskViewModel = taskViewModel;
        this.taskController = taskController;
        this.taskViewModel.addPropertyChangeListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jList = new JList<>();
        JScrollPane sp = new JScrollPane(jList);
        JPanel buttons = new JPanel();

        create = new JButton(taskViewModel.CREATE_BUTTON_LABEL);
        back = new JButton(taskViewModel.BACK_BUTTON_LABEL);
        delete = new JButton(taskViewModel.DELETE_BUTTON_LABEL);
        save = new JButton(taskViewModel.SAVE_BUTTON_LABEL);

        buttons.add(create);
        buttons.add(delete);
        buttons.add(back);

        this.add(sp);
        this.add(buttons);

        createDialog = buildCreateDialog();

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(back)) {
                    createDialog.setVisible(false);
                    taskViewModel.getState().setUseCase(taskViewModel.BACK_USE_CASE);
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
                            taskController.execute(
                                    title.getText(),
                                    notes.getText(),
                                    completed.isSelected(),
                                    datePicker.getDate());
                            createDialog.setVisible(false);
                            resetField();
                        }
                    }
                }
        );

        delete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(delete) && jList.getSelectedIndices().length > 0) {
                            taskController.delete(jList.getSelectedIndices());
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TaskState state = (TaskState) evt.getNewValue();
        String useCase = state.getUseCase();
        if (state.getError() != null) {
            showErrorMessage(state.getError());
            state.setError(null);
            return;
        }
        else if (useCase.equals(taskViewModel.INITIALIZE_USE_CASE)) {
            state.setUseCase("");
            String username = taskViewModel.getState().getUsername();
            taskController.initialize(username);
        }
        updateList();
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
        pickerAndButtonPanel.add(datePicker);
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
        return true;
    }

    /**
     * This method is responsible to show error message.
     * @param message error message
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * The method is called when to refresh the list of Task
     */
    private void updateList() {
        java.util.List<String> result = taskViewModel.getState().getTasks();
        jList.setListData(result.toArray(new String[0]));
    }

    private void resetField() {
        title.setText("");
        notes.setText("");
        completed.setSelected(false);
    }
}

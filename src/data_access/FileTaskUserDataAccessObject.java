package data_access;

import entity.Task;
import entity.TaskFactory;
import use_case.task.TaskDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTaskUserDataAccessObject implements TaskDataAccessInterface {
    private final String filePath;
    private final Map<LocalDate, List<Task>> tasks;
    private final Map<Task, Long> taskReference;
    private final TaskFactory taskFactory;
    private String username = null;

    /**
     * Initialize a new FileTaskUserDataAccessObject
     *
     * @param tasks          The user's tasks
     * @param taskReference  A reference to find the user's tasks
     * @param taskFactory    A class used to create a task
     */
    public FileTaskUserDataAccessObject(Map<LocalDate, List<Task>> tasks,
                                        Map<Task, Long> taskReference,
                                        TaskFactory taskFactory) {
        this.filePath = "DATA" + File.separator + "TaskDirectory";
        this.tasks = tasks;
        this.taskReference = taskReference;
        this.taskFactory = taskFactory;
    }

    /**
     * This function makes an empty CSV file for a user in case they don't have one.
     */
    private void makeCsvFile() {
        String fileName = username + ".csv";

        // Create a File object for the folder
        File folder = new File(filePath);

        // Create a File object for the CSV file within the folder
        File csvFile = new File(folder, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Write the header to the file
            writer.write("title, notes, completed, dueDate");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred when making the file.");
        }
    }

    /**
     * This method saves a task into the database.
     *
     * @param task The task to be saved.
     */
    @Override
    public void saveTask(Task task) throws RuntimeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // add this task to the csv file
        String title = task.getTitle();
        String notes = task.getNotes();
        boolean completed = task.isCompleted();
        String dueDate = task.getDueDate().format(dateFormatter);

        long lineNumber = csvAppender(title, notes, completed, dueDate);

        // add this task to the tasks attribute
        // find the days that the task happens on (in the case of a deadline)
        List<LocalDate> taskDays = getDatesBetween(task.getDueDate(), task.getDueDate());

        // iterate through the days and put them in the corresponding arraylists in tasks
        for (LocalDate date : taskDays) {

            // check if the key (date) is in tasks already
            if (tasks.containsKey(date)) {
                // Get the ArrayList of tasks and add task to it
                List<Task> existingList = tasks.get(date);
                existingList.add(task);

            } else {
                // If the key doesn't exist, create a new ArrayList<Task>
                List<Task> newList = new ArrayList<>();
                newList.add(task);
                tasks.put(date, newList);
            }
        }

        // add this task to the task reference attribute
        taskReference.put(task, lineNumber);
    }

    // ... (Other methods remain unchanged)

    /**
     * This is a helper method for the saveTask method. This method saves the strings
     * into the CSV file as a new line and returns the number of the line that the
     * new line is written on.
     *
     * @param title     The title of the task.
     * @param notes     The notes for the task.
     * @param completed The completion status of the task.
     * @param dueDate   The due date of the task.
     * @return the number of the line in the CSV file that the method prints the information on.
     */
    private long csvAppender(String title, String notes, boolean completed, String dueDate) {
        String directoryPath = this.filePath + File.separator + this.username + ".csv";

        String newLine = String.join(",", title, notes, String.valueOf(completed), dueDate);

        long lineCount;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath, true))) {
            // append the task to the end of this file
            writer.newLine();
            writer.write(newLine);

            // get the number of the line that the new line is printed on
            lineCount = Files.lines(Paths.get(directoryPath)).count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lineCount;
    }
}
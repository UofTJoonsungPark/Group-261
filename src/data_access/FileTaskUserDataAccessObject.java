package data_access;

import entity.Task;
import entity.TaskFactory;
import use_case.task.TaskDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileTaskUserDataAccessObject implements TaskDataAccessInterface {
    private final String filePath;
    private final Map<LocalDateTime, List<Task>> tasks;
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
    public FileTaskUserDataAccessObject(Map<LocalDateTime, List<Task>> tasks,
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
        if (!folder.exists()) {
            folder.mkdirs();
        }

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

        // add this task to the csv file
        String title = task.getTitle();
        String notes = task.getNotes();
        boolean completed = task.isCompleted();
        String dueDate = task.getDueDate().format(dateFormatter);

        long lineNumber = csvAppender(title, notes, completed, dueDate);

        // add this task to the tasks attribute

        // check if the key (date) is in tasks already
        if (tasks.containsKey(task.getDueDate())) {
            // Get the ArrayList of tasks and add task to it
            List<Task> existingList = tasks.get(task.getDueDate());
            existingList.add(task);

            } else {
                // If the key doesn't exist, create a new ArrayList<Task>
                List<Task> newList = new ArrayList<>();
                newList.add(task);
                tasks.put(task.getDueDate(), newList);

        }

        // add this task to the task reference attribute
        taskReference.put(task, lineNumber);
    }

    /** This method marks the given task as completed.
     */
    public void markCompleted(Task task) {
        // find the task in the tasks attribute.
        if (tasks.containsKey(task.getDueDate())) {
            List<Task> existingList = tasks.get(task.getDueDate());

            for (Task possible_task : existingList) {
                if (possible_task.equals(task)) {
                    possible_task.setCompleted(true);
                }
            }
        }

        // find the  task in the taskReference attribute.
        List<Task> keyList = new ArrayList<>(taskReference.keySet());

        for (Task task1 : keyList) {
            if (task1.equals(task)) {
                task1.setCompleted(true);
            }
        }

        // find + modify in the task in the CSV file.
        modifyCSV(taskReference.get(task));
    }

    @Override
    public void deleteTask(Task task) {
        // delete the task from tasks
        List<Task> taskList = tasks.get(task.getDueDate());
        if (taskList != null) {
            taskList.removeIf(t -> t.equals(task));

            if (taskList.isEmpty()) {
                tasks.remove(task.getDueDate());
            }
        }

        // delete the task from taskReference
        long referenceLine = taskReference.get(task);
        taskReference.remove(task);

        // delete the task from the CSVFile
        CSVRemover(referenceLine);
    }

    private void modifyCSV(long lineNumber) {
        String fileDirectory = filePath + File.separator + username + ".csv";

        String completed = String.valueOf(true);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileDirectory));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileDirectory + ".tmp")))) {

            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;

                if (currentLine == lineNumber) {
                    String[] parts = line.split(",");

                    parts[2] = completed;

                    line = String.join(",", parts);
                }
                } writer.println(line);
            } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.move(Path.of(fileDirectory + ".tmp"), Path.of(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }


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

    // This is a helper method for updating the deleteTask method.
    private void CSVRemover(long referenceLine) {
        String fileDirectory = filePath + File.separator + username + ".csv";
        List<String> lines;

        try {
            Path path = Paths.get(fileDirectory);
            lines = Files.readAllLines(path);
            if (referenceLine > 0 && referenceLine <= lines.size()) {
                // make the specified line blank
                lines.set((int) (referenceLine - 1), "");
            } else {
                throw new RuntimeException("Invalid reference line");
            }

            // Write the updated lines back to the CSV file
            Files.write(path, lines);
        } catch (IOException e) {
            throw new RuntimeException("Error updating CSV file.");
        }
    }
}
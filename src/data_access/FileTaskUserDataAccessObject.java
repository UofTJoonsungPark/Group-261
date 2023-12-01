package data_access;

import entity.Event;
import entity.Task;
import entity.TaskFactory;
import use_case.task.TaskDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileTaskUserDataAccessObject implements TaskDataAccessInterface {
    private final String filePath;
    private final Map<Task, Long> tasks;
    private final TaskFactory taskFactory;
    private String username = null;

    /**
     * Initialize a new FileTaskUserDataAccessObject
     *
     * @param tasks          The user's tasks
     * @param taskFactory    A class used to create a task
     */
    public FileTaskUserDataAccessObject(Map<Task, Long> tasks,
                                        TaskFactory taskFactory) {
        this.filePath = "DATA" + File.separator + "TaskDirectory";
        this.tasks = tasks;
        this.taskFactory = taskFactory;
    }

    /**
     * The method is called when the user sign in to build the DS in memory.
     * @param username logged in username
     */
    public void writeSets(String username) {
        if (username.equals(this.username)) {
            return;
        }
        clear();
        this.username = username;
        String csvFilePath = filePath + File.separator + username + ".csv";
        String tempFilePath = filePath + File.separator + username + ".tmp";

        long lineNumber = 1;

        try {
            File file = new File(csvFilePath);

            if (!file.exists()) {
                makeCsvFile();
                return;
            }
            File temp = new File(tempFilePath);
            temp.createNewFile();

            // clear empty lines
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
                String row;
                while ((row = reader.readLine()) != null) {
                    if (!row.isEmpty()) {
                        writer.write(row);
                        writer.newLine();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            file.delete();
            temp.renameTo(file);

            // loading date into maps
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String header = reader.readLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                assert header.equals("title, notes, completed, dueDate");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",", 4);
                    String title = col[0];
                    String notes = col[1];
                    boolean completed = Boolean.parseBoolean(col[2]);
                    LocalDate dueDate = LocalDate.parse(col[3], dateFormatter);

                    Task task = taskFactory.createTask(title, notes, completed, dueDate);

                    //UPDATE TASK and REFERENCE
                    tasks.put(task, lineNumber);
                    lineNumber++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        // add this task to the task reference attribute
        tasks.put(task, lineNumber);
    }

    /** This method marks the given task as completed.
     */
    public void markCompleted(Task task) {
        // find the task in the tasks attribute.
//        if (tasks.containsKey(task.getDueDate())) {
//            List<Task> existingList = tasks.get(task.getDueDate());
//
//            for (Task possible_task : existingList) {
//                if (possible_task.equals(task)) {
//                    possible_task.setCompleted(true);
//                }
//            }
//        }
//
//        // find the  task in the taskReference attribute.
//        List<Task> keyList = new ArrayList<>(taskReference.keySet());
//
//        for (Task task1 : keyList) {
//            if (task1.equals(task)) {
//                task1.setCompleted(true);
//            }
//        }
//
//        // find + modify in the task in the CSV file.
//        modifyCSV(taskReference.get(task));
    }

    @Override
    public void deleteTask(Task task) {
        // delete the task from taskReference
        long referenceLine = tasks.get(task);
        tasks.remove(task);

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

    // This is a helper method for updating the deleteTask method. It is designed to update a specific line in a CSV
    // (Comma-Separated Values) file by making that line blank. This method only removes the content of a specific line
    // in the CSV file, leaving an empty line in its place. It does not completely remove the line.
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

    public void clear() {
        tasks.clear();
        this.username = null;
    }
}
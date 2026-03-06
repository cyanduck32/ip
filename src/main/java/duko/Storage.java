package duko;

import duko.task.Deadline;
import duko.task.Event;
import duko.task.Task;
import duko.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of task data to a local file.
 * This class facilitates the persistence of the TaskList between application sessions.
 */
public class Storage {
    /** The file path where task data is stored. */
    private String filePath;

    /**
     * Initializes a Storage object with a specific file path.
     * * @param filePath The relative or absolute path to the data file (e.g., "./data/duko.txt").
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the file path.
     * If the file or directory does not exist, an empty list is returned.
     * * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return loadedTasks; // return an empty list if file does not exist
        }

        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = parseTaskFromString(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return loadedTasks;
    }


    /**
     * Saves the current list of tasks to the hard disk.
     * Creates any missing parent directories before writing to the file.
     * * @param tasks The ArrayList of Task objects to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            // create directories if there is no directory
            Path path = Paths.get(filePath);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks) {
                fw.write(toFileFormat(t) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Converts a Task object into a formatted string suitable for file storage.
     * Format: TYPE | STATUS | DESCRIPTION | EXTRA_INFO
     * * @param t The task to convert.
     * @return A pipe-separated string representing the task.
     */
    private String toFileFormat(Task t) {
        String type = t instanceof Todo ? "T" : t instanceof Deadline ? "D" : "E";
        String status = t.getStatusIcon().equals("X") ? "1" : "0";
        String base = type + " | " + status + " | " + t.getDescription();

        if (t instanceof Deadline) {
            return base + " | " + ((Deadline) t).getBy();
        } else if (t instanceof Event) {
            return base + " | " + ((Event) t).getFrom() + " | " + ((Event) t).getTo();
        }
        return base;
    }

    /**
     * Parses a single line from the storage file back into a Task object.
     * * @param line The string from the data file.
     * @return The corresponding Task object (Todo, Deadline, or Event), or null if corrupted.
     */
    private Task parseTaskFromString(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String desc = parts[2];
            Task task = null;

            switch (type) {
            case "T":
                task = new Todo(desc);
                break;
            case "D":
                task = new Deadline(desc, parts[3]);
                break;
            case "E":
                task = new Event(desc, parts[3], parts[4]);
                break;
            }
            if (isDone && task != null) task.setDone();
            return task;
        } catch (Exception e) {
            return null; // skip corrupted lines
        }
    }
}
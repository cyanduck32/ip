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

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

     // loads tasks from files, handles missing directory/files
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


    // saves the whole list to the hard disk
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
            return null; // Handle corrupted lines by skipping them
        }
    }
}
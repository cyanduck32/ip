package duko;

import duko.task.Task;
import duko.exception.DukoException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws DukoException {
        validateIndex(index);
        return tasks.remove(index);
    }

    public Task getTask(int index) throws DukoException {
        validateIndex(index);
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void validateIndex(int index) throws DukoException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukoException("That task number does not exist in your list!");
        }
    }
}
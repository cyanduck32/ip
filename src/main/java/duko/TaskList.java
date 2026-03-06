package duko;

import duko.task.Task;
import duko.exception.DukoException;
import java.util.ArrayList;

/**
 * Represents the list of tasks and provides methods to manipulate the list.
 * This class handles operations such as adding, deleting, and retrieving tasks,
 * as well as validating task indices.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with an existing list of tasks.
     * * @param tasks An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     * * @param index The index of the task to be removed (0-based).
     * @return The Task object that was removed.
     * @throws DukoException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws DukoException {
        validateIndex(index);
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     * * @param index The index of the task to retrieve (0-based).
     * @return The Task object at the specified index.
     * @throws DukoException If the index is out of bounds.
     */
    public Task getTask(int index) throws DukoException {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     * * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the internal ArrayList of tasks.
     * * @return The list containing all Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Checks if the provided index is within the valid range of the task list.
     * * @param index The index to validate.
     * @throws DukoException If the index is less than 0 or greater than/equal to the list size.
     */
    private void validateIndex(int index) throws DukoException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukoException("That task number does not exist in your list!");
        }
    }
}
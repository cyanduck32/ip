package duko;

import duko.exception.DukoException;
import duko.task.*;

import java.util.*;

/**
 * Handles the logic for interpreting user input and executing commands.
 * The Parser class deciphers commands such as list, mark, unmark, delete,
 * todo, deadline, event, and find.
 */
public class Parser {
    /**
     * Parses the user input and calls the appropriate handler to execute the command.
     *
     * @param input   The full command string entered by the user.
     * @param tasks   The TaskList object containing the current tasks.
     * @param ui      The Ui object used to interact with the user.
     * @param storage The Storage object used to save task data to the disk.
     * @throws DukoException If the command is unrecognized or if the arguments are invalid.
     */
    public static void parseAndExecute(String input, TaskList tasks, Ui ui, Storage storage) throws DukoException {
        if (input.equals("list")) {
            handleList(tasks, ui);
        } else if (input.startsWith("mark")) {
            handleMark(input, tasks, ui, storage, true);
        } else if (input.startsWith("unmark")) {
            handleMark(input, tasks, ui, storage, false);
        } else if (input.startsWith("delete")) {
            handleDelete(input, tasks, ui, storage);
        } else if (input.startsWith("todo")) {
            handleTodo(input, tasks, ui, storage);
        } else if (input.startsWith("deadline")) {
            handleDeadline(input, tasks, ui, storage);
        } else if (input.startsWith("event")) {
            handleEvent(input, tasks, ui, storage);
        } else if (input.startsWith("find")){
            handleFind(input, tasks, ui);
        }else {
            throw new DukoException("I'm sorry, but I don't know what that command means.");
        }
    }

    /**
     * Searches for tasks that contain the specified keyword in their description.
     *
     * @param input The raw user input containing the keyword.
     * @param tasks The TaskList to search within.
     * @param ui    The Ui to display matching tasks.
     * @throws DukoException If the search keyword is empty.
     */
    private static void handleFind(String input, TaskList tasks, Ui ui) throws DukoException {
        String keyword = input.replaceFirst("find", "").trim();
        if (keyword.isEmpty()) {
            throw new DukoException("Please enter a keyword to search for.");
        }
        ArrayList<Task> allTasks = tasks.getTasks();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found with the keyword: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Handles the creation of a Todo task.
     *
     * @param input   The raw user input.
     * @param tasks   The task list to add to.
     * @param ui      The user interface for feedback.
     * @param storage The storage for saving the new task list.
     * @throws DukoException If the todo description is empty.
     */
    private static void handleTodo(String input, TaskList tasks, Ui ui, Storage storage) throws DukoException {
        String description = input.replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            throw new DukoException("The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(description);
        addTaskAndSave(newTask, tasks, ui, storage);
    }

    /**
     * Handles the creation of a Deadline task, including date parsing.
     *
     * @param input   The raw user input.
     * @param tasks   The task list to add to.
     * @param ui      The user interface for feedback.
     * @param storage The storage for saving.
     * @throws DukoException If description/date is missing or date format is invalid.
     */
    private static void handleDeadline(String input, TaskList tasks, Ui ui, Storage storage) throws DukoException {
        String content = input.replaceFirst("deadline", "").trim();
        if (content.isEmpty()) {
            throw new DukoException("The description of a deadline cannot be empty.");
        }
        if (!content.contains(" /by ")) {
            throw new DukoException("Deadlines must include ' /by ' followed by the date.");
        }
        String[] parts = content.split(" /by ");
        String description = parts[0].trim();
        String dateString = parts[1].trim();
        try {
            Task newTask = new Deadline(description, dateString);
            addTaskAndSave(newTask, tasks, ui, storage);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukoException("Please use the date format yyyy-mm-dd (e.g., 2026-12-25)");
        }
    }

    /**
     * Handles the creation of an Event task with start and end times.
     *
     * @param input   The raw user input.
     * @param tasks   The task list to add to.
     * @param ui      The user interface for feedback.
     * @param storage The storage for saving.
     * @throws DukoException If parameters are missing or incorrectly formatted.
     */
    private static void handleEvent(String input, TaskList tasks, Ui ui, Storage storage) throws DukoException {
        String content = input.replaceFirst("event", "").trim();
        if (!content.contains(" /from ") || !content.contains(" /to ")) {
            throw new DukoException("Events must include ' /from ' and ' /to ' timings.");
        }
        String[] parts = content.split(" /from | /to ");
        Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        addTaskAndSave(newTask, tasks, ui, storage);
    }

    /**
     * Adds a task to the list, saves the list to disk, and displays a confirmation message.
     *
     * @param task    The new task to be added.
     * @param tasks   The task list.
     * @param ui      The UI for feedback.
     * @param storage The storage for persistence.
     */
    private static void addTaskAndSave(Task task, TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n  " + task);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Updates the status (mark/unmark) of an existing task.
     *
     * @param input   The raw user input containing the task index.
     * @param tasks   The task list.
     * @param ui      The UI for feedback.
     * @param storage The storage for saving the update.
     * @param isMark  True to mark as done, false to unmark.
     * @throws DukoException If the provided index is invalid or non-numeric.
     */
    private static void handleMark(String input, TaskList tasks, Ui ui, Storage storage, boolean isMark) throws DukoException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = tasks.getTask(index);

            if (isMark) {
                task.setDone();
                ui.showMessage("Nice! I've marked this task as done:\n  " + task);
            } else {
                task.unmark();
                ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
            }
            storage.save(tasks.getTasks());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukoException("Please provide a valid task number to mark/unmark.");
        }
    }

    /**
     * Removes a task from the list based on the user-provided index.
     *
     * @param input   The raw user input containing the task index.
     * @param tasks   The task list.
     * @param ui      The UI for feedback.
     * @param storage The storage for saving the deletion.
     * @throws DukoException If the provided index is invalid or non-numeric.
     */
    private static void handleDelete(String input, TaskList tasks, Ui ui, Storage storage) throws DukoException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removedTask = tasks.deleteTask(index);

            storage.save(tasks.getTasks());
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage("  " + removedTask);
            ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukoException("Please provide a valid task number to delete.");
        }
    }

    /**
     * Displays all tasks currently in the TaskList.
     *
     * @param tasks The task list to display.
     * @param ui    The UI to handle printing.
     * @throws DukoException If the list is empty.
     */
    private static void handleList(TaskList tasks, Ui ui) throws DukoException {
        if (tasks.getSize() == 0) {
            ui.showMessage("Your list is currently empty!");
            return;
        }
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showMessage((i + 1) + "." + tasks.getTask(i));
        }
    }
}
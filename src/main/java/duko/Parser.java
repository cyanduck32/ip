package duko;

import duko.exception.DukoException;
import duko.task.*;

public class Parser {
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
        } else {
            throw new DukoException("I'm sorry, but I don't know what that command means.");
        }
    }

    private static void handleTodo(String input, TaskList tasks, Ui ui, Storage storage) throws DukoException {
        String description = input.replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            throw new DukoException("The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(description);
        addTaskAndSave(newTask, tasks, ui, storage);
    }

    private static void handleDeadline(String input, TaskList tasks, Ui ui, Storage storage) throws DukoException {
        String content = input.replaceFirst("deadline", "").trim();
        if (!content.contains(" /by ")) {
            throw new DukoException("Deadlines must include ' /by ' followed by the time.");
        }
        String[] parts = content.split(" /by ");
        Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
        addTaskAndSave(newTask, tasks, ui, storage);
    }

    private static void handleEvent(String input, TaskList tasks, Ui ui, Storage storage) throws DukoException {
        String content = input.replaceFirst("event", "").trim();
        if (!content.contains(" /from ") || !content.contains(" /to ")) {
            throw new DukoException("Events must include ' /from ' and ' /to ' timings.");
        }
        String[] parts = content.split(" /from | /to ");
        Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        addTaskAndSave(newTask, tasks, ui, storage);
    }

    private static void addTaskAndSave(Task task, TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.showMessage("Got it. I've added this task:\n  " + task);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
    }

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
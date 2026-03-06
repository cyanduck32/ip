package duko;

import duko.exception.DukoException;

/**
 * The main class for the Duko task management application.
 * Duko is a personal assistant chatbot that helps users keep track of various tasks.
 * It coordinates the {@link Ui}, {@link Storage}, {@link TaskList}, and {@link Parser}
 * to provide a command-line interface for managing tasks.
 */
public class Duko {
    /** Deals with loading tasks from the file and saving tasks in the file. */
    private Storage storage;
    /** Stores the list of tasks and provides operations to modify the list. */
    private TaskList tasks;
    /** Deals with interactions with the user (printing messages, reading input). */
    private Ui ui;

    /**
     * Initializes the Duko application with a specific file path for data storage.
     *
     * @param filePath The path to the file where task data is stored (e.g., "./data/duko.txt").
     */
    public Duko(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the main program loop.
     * Displays the welcome message and continuously reads and executes user commands
     * until the "bye" command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            if (input.equalsIgnoreCase("bye")) {
                ui.showMessage("Bye. Hope to see you again soon!");
                isExit = true;
                continue;
            }

            try {
                ui.showLine();
                Parser.parseAndExecute(input, tasks, ui, storage);
            } catch (DukoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main entry point for the Duko application.
     * Creates a new Duko instance and calls its {@link #run()} method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Duko("./data/duko.txt").run();
    }
}
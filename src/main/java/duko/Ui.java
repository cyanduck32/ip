package duko;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the Duko application.
 * This class is responsible for reading user input and displaying messages,
 * errors, and structural elements like the logo and divider lines.
 */
public class Ui {
    private final String LINE = "____________________________________________________________";
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   < | | |\n"
            + "|____/ \\__,_|_|\\_\\___/\n";

    private Scanner scanner;

    /**
     * Initializes the Ui object and its input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message, logo, and a divider line.
     */
    public void showWelcome() {
        System.out.println(LINE + "\n" + LOGO + "Hello! I'm Duko\nWhat can I do for you?\n" + LINE);
    }

    /**
     * Reads the next line of input from the user.
     * * @return The full string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal divider line to the console.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays an error message formatted with a prefix.
     * * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("oh no! " + message);
    }

    /**
     * Displays a specific error message when the task file fails to load.
     */
    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty list.");
    }

    /**
     * Prints a generic message to the console.
     * * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
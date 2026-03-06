package duko;

import java.util.Scanner;

public class Ui {
    private final String LINE = "____________________________________________________________";
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   < | | |\n"
            + "|____/ \\__,_|_|\\_\\___/\n";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(LINE + "\n" + LOGO + "Hello! I'm Duko\nWhat can I do for you?\n" + LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println("oh no! " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty list.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
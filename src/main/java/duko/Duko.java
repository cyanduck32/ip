package duko;

import duko.exception.DukoException;

public class Duko {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duko(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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

    public static void main(String[] args) {
        new Duko("./data/duko.txt").run();
    }
}
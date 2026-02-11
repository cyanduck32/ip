package duko;

import duko.exception.DukoException;
import duko.task.Deadline;
import duko.task.Event;
import duko.task.Task;
import duko.task.Todo;

import java.util.Scanner;

public class Duko {
    static String horizontalLine = "____________________________________________________________";
    public static String GREETING = "Hello! I'm Duko\nWhat can I do for you?";
    public static String CLOSING_GREETING = "Bye. Hope to see you again soon!";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;
    public static String LOGO =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   < | | |\n"
            + "|____/ \\__,_|_|\\_\\___/\n";



    public static void main(String[] args) {
        System.out.println(horizontalLine + "\n" + LOGO + GREETING + "\n" + horizontalLine);
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            try{
                if (input.equals("bye")) {
                System.out.println(horizontalLine + "\n" + CLOSING_GREETING + "\n" + horizontalLine);
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark")) {
                updateTaskStatus(input, true);
            } else if (input.startsWith("unmark")) {
                updateTaskStatus(input, false);
            } else if (input.startsWith("todo") || input.startsWith("deadline")||input.startsWith("event")){
                handleAddTask(input);
            } else {
                    throw new DukoException("I'm sorry, but I don't know what that command means.");
                }
            } catch (DukoException e){
                System.out.println(horizontalLine);
                System.out.println("oh no!" + e.getMessage());
                System.out.println(horizontalLine);
            }
        }
        scanner.close();
    }

    private static void handleAddTask(String input) throws DukoException {
        Task newTask = null;

        if (input.startsWith("todo")) {
            // "todo borrow book" results in "borrow book"
            String description = input.replaceFirst("todo", "").trim();
            if (description.isEmpty()){
                throw new DukoException("The description of a todo cannot be empty.");
            }
            newTask = new Todo(description);

        } else if (input.startsWith("deadline")) {
            // "deadline return book /by Sunday"
            String content = input.replaceFirst("deadline", "").trim();
            if (content.isEmpty()) {
                throw new DukoException("The description of a deadline cannot be empty.");
            }
            if (!content.contains(" /by ")){
                throw new DukoException("Deadlines must include ' /by ' followed by the time.");
            }
            String[] parts = content.split(" /by ");
            newTask = new Deadline(parts[0].trim(), parts[1].trim());

        } else if (input.startsWith("event")) {
            // "event meeting /from Mon 2pm /to 4pm"
            String content = input.replaceFirst("event", "").trim();
            if (content.isEmpty()){
                throw new DukoException("The description of an event cannot be empty.");
            }
            if (!content.contains(" /from ")||!content.contains(" /to ")){
                throw new DukoException("Events must include ' /from ' and  ' /to ' timings.");
            }
            String[] parts = content.split(" /from | /to");
            newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        }

        if (newTask != null) {
            tasks[taskCount] = newTask;
            taskCount++;
            System.out.println(horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTask);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            System.out.println(horizontalLine);
        }
    }

    private static void updateTaskStatus(String input, boolean isMark) throws DukoException {
        String[] parts = input.split(" ");
        if (parts.length < 2){
            throw new DukoException("Please specify a task number to mark/unmark.");
        }
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= taskCount){
                throw new DukoException("That task number does not exist in your list!");
            }
            if (isMark) {
                tasks[index].setDone();
                System.out.println(horizontalLine + "\nNice! I've marked this task as done:\n " + tasks[index] + "\n" + horizontalLine);
            } else {
                tasks[index].unmark();
                System.out.println(horizontalLine + "\nOK, I've marked this task as not done yet:\n " + tasks[index] + "\n" + horizontalLine);
            }

        } catch (NumberFormatException e){
            throw new DukoException("Please provide a valid number, not text.");
        }
    }

    private static void printList() {
        System.out.println(horizontalLine + "\nHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println(horizontalLine);
    }
}
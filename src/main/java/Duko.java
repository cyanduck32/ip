import java.util.Scanner;

public class Duko {
    static String horizontalLine = "____________________________________________________________";
    public static String greeting = "Hello! I'm Duko \nWhat can I do for you?";
    public static String closingGreeting = "Bye. Hope to see you again soon!";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   < | | |\n"
                + "|____/ \\__,_|_|\\_\\___/\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine);
        System.out.println(greeting);
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")){
                System.out.println(horizontalLine);
                System.out.println (closingGreeting);
                System.out.println(horizontalLine);
                break;
            } else if (input.equals("list")) {
                System.out.println(horizontalLine + "\n Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(horizontalLine);
            } else if (input.startsWith("mark")){
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].markAsDone();
                System.out.println(horizontalLine + "\n Nice! I've marked this task as done:\n " + tasks[index] + "\n" + horizontalLine);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[index].unmark();
                System.out.println(horizontalLine + "\n OK, I've marked this task as not done yet:\n" + tasks[index] + "\n" + horizontalLine);
            } else {
                tasks[taskCount] = new Task(input);
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(horizontalLine);
                System.out.println("added: " + input);
                System.out.println(horizontalLine);
            }
        }
        scanner.close();
    }
}

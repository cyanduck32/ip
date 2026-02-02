import java.util.Scanner;

public class Duko {
    static String horizontalLine = "____________________________________________________________";
    public static String greeting = "Hello! I'm Duko \nWhat can I do for you?";
    public static String closingGreeting = "Bye. Hope to see you again soon!";
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
            }

            System.out.println(horizontalLine);
            System.out.println(input);
            System.out.println(horizontalLine);
        }
        scanner.close();
    }
}

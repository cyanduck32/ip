public class Duko {
    public static String greeting = "Hello! I'm Duko \nWhat can I do for you?";
    public static String closingGreeting = "Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   < | | |\n"
                + "|____/ \\__,_|_|\\_\\___/\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        System.out.println(closingGreeting);
    }
}
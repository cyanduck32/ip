package duko.task;

/**
 * Represents a "Todo" type task.
 * A Todo is a basic class that only contains a description,
 * without any specific date or time constraints.
 */
public class Todo extends Task {
    /** The symbol used to identify a Todo task in the string representation. */
    private static final String TODO_SYMBOL = "[T]";

    /**
     * Initializes a Todo task with the specified description.
     * * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * The representation includes the [T] symbol followed by the task's
     * status icon and description.
     * * @return A formatted string like "[T][X] read book".
     */
    @Override
    public String toString() {

        return TODO_SYMBOL + super.toString();
    }

    /**
     * Returns the description of the Todo task.
     * * @return The task description string.
     */
    public String getDescription() {
        return description;
    }
}

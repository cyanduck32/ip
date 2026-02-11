package duko.task;

public class Todo extends Task {
    private static final String TODO_SYMBOL = "[T]";
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {

        return TODO_SYMBOL + super.toString();
    }
}

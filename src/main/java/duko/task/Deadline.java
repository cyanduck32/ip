package duko.task;

// --- duke.task.Deadline.java ---
public class Deadline extends Task {
    private static final String DEADLINE_SYMBOL= "[D]";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return DEADLINE_SYMBOL + super.toString() + " (by: " + by + ")";
    }
}

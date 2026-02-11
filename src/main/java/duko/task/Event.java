package duko.task;

// --- duke.task.Event.java ---
public class Event extends Task {
    private static final String EVENT_SYMBOL= "[E]";
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return EVENT_SYMBOL + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
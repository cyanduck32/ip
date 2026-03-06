package duko.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that needs to be completed by a specific date.
 * A Deadline object contains a description and a single due date.
 */
public class Deadline extends Task {
    /** The deadline date for this task. */
    protected LocalDate by;

    /**
     * Initialises a Deadline task with a description and a due date.
     * * @param description The description of the task.
     * @param by The due date in yyyy-mm-dd format.
     * @throws java.time.format.DateTimeParseException if the date string is not in the correct format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the deadline task.
     * The output includes the [D] symbol, description,
     * and the due date formatted as MMM dd yyyy (e.g., Dec 25 2026).
     * * @return A formatted string representation of the deadline.
     */
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Returns the due date of the task as a string in ISO-8601 format (yyyy-mm-dd).
     * This is useful for saving the task back to the storage file.
     * * @return The due date string.
     */
    public String getBy() {
        return by.toString();
    }
}
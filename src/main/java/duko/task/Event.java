package duko.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specific time frame.
 * An Event object contains a description, a start date, and an end date.
 */
public class Event extends Task {
    /** The starting date of the event. */
    protected LocalDate from;
    /** The ending date of the event. */
    protected LocalDate to;

    /**
     * Initialises an Event task with a description and time range.
     * @param description The description of the event.
     * @param from The start date in yyyy-mm-dd format.
     * @param to The end date in yyyy-mm-dd format.
     * @throws java.time.format.DateTimeParseException if the date strings are not in yyyy-mm-dd format.
     */

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns a string representation of the event task, including the
     * type icon [E], status, description and formatted dates.
     * @return A string such as "[E][] orientation (from: Aug 01 2026 to: Aug 03 2026)".
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(fmt) + " to: " + to.format(fmt) + ")";
    }

    /**
     * Returns the start date of the event as the string in ISO-8601 format.
     * @return The start date string (yyyy-mm-dd).
     */
    public String getFrom() {
        return from.toString();
    }

    /**
     * Returns the end date of the event as the string in ISO-8601 format.
     * @return The end date string (yyyy-mm-dd).
     */
    public String getTo() {
        return to.toString();
    }
}
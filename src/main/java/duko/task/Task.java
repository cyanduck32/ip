package duko.task;
/**
 * Represents a generic task in the Duko application.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     * @param description The description of the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * @return "X" if done, " " (space) otherwise.
     */
    public String getStatusIcon(){
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void setDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as uncompleted.
     */
    public void unmark(){
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including status icon and description
     * @return A formatted string like "[X] read book".
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Retrives the raw description of the task.
     * @return The text description of the task.
     */
    public String getDescription() {
        return description;
    }
}


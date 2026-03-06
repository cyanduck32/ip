package duko.exception;

/**
 * Represents exceptions specific to the Duko application.
 * This class is used to handle errors related to user input,
 * task manipulation, and file operations within the app.
 */
public class DukoException extends Exception {
    /**
     * Constructs a new DukoException with the specified detail message.
     *
     * @param message The error message to be displayed to the user.
     */
    public DukoException(String message) {
        super(message);
    }
}
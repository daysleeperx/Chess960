package exception;

/**
 * Represent Exception.
 */
public class InvalidMoveException extends Exception{
    /**
     * Class constructor.
     *
     * @param message String
     */
    public InvalidMoveException(String message) {
        super(message);
    }
}

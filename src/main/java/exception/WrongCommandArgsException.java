package exception;

/**
 * WrongCommandArgumentException is an Exception that will only be thrown when the player put in wrong input argument.
 */
public class WrongCommandArgsException extends Exception {

    public WrongCommandArgsException(String message) {
        super(message);
    }

    public WrongCommandArgsException(int argumentNumber) {
        super("This command only takes in " + argumentNumber + "argument(s)");
    }

}

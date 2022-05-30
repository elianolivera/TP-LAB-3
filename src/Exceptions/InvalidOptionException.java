package Exceptions;

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException(String message) {
        super(message);
    }
}

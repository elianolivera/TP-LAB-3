package Exceptions;

/**
 * Excepcion para cuando se elige una opcion invalida
 */

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException(String message) {
        super(message);
    }
}

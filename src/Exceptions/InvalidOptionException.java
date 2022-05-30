package Exceptions;

/**
 * Exception para cuando se selecciona una opcion invalida en los menus.
 * Lo hice asi para abstraer de la implementacion de los menus.
 * Si el que lo implementa, cuando se retorna este error, quiere hacer algo especial, puede, en vez de pedirle opcion hasta que sea valido.
 * */

public class InvalidOptionException extends RuntimeException {
    public InvalidOptionException(String message) {
        super(message);
    }
}

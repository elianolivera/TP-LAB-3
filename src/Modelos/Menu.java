package Modelos;

import Exceptions.InvalidOptionException;

import java.util.*;

public final class Menu {

    public Menu() {
    }

    public int mostrarMenuPrincipal() throws InputMismatchException {
        int opcion = -1;

        System.out.print("\n ========  BIENVENIDO USUARIO: ======== ");
        System.out.print("\n ======== MENÚ DE USUARIO : ======== ");
        System.out.print("\n 1. Consultar estado de activos. ");
        System.out.print("\n 2. Realizar una transacción.");
        System.out.print("\n 3. Transacciones pendientes.");
        System.out.print("\n 4. Validar transaccion.");
        System.out.print("\n 5. Historial de transacciones. ");
        System.out.print("\n 6. Archivo de transacciones. ");
        System.out.print("\n 7. Volver al login. ");
        System.out.print("\n 8. Salir. ");
        System.out.print("\n ==== SELECIONA OPCIÓN ===: \n");
        Scanner teclado = new Scanner(System.in);
        opcion = teclado.nextInt();

        if (opcion > 8 || opcion < 1) throw new InvalidOptionException("La opcion seleccionada no existe.");

        return opcion;
    }

    public int mostrarMenuUsuario() throws InputMismatchException {
        int opcion = -1;

        System.out.print("\n 1. Ingresar. ");
        System.out.print("\n 2. Registrarse.");
        System.out.print("\n 3. Salir.");
        System.out.print("\n ==== SELECIONA OPCIÓN ===: \n");
        Scanner teclado = new Scanner(System.in);
        opcion = teclado.nextInt();

        if (opcion > 3 || opcion < 1) throw new InvalidOptionException("La opcion seleccionada no existe.");

        return opcion;
    }

}

package Clases;

import java.util.Scanner;

public final class Menu {
    public Menu() {
    }

    public int mostrarMenuPrincipal() {
        try{
            int opcion;
            System.out.print("\n ========  BIENVENIDO USUARIO: ======== ");
            System.out.print("\n ======== MENÚ DE USUARIO : ======== ");
            System.out.print("\n 1. Consultar estado de activos. ");
            System.out.print("\n 2. Realizar una transacción.");
            System.out.print("\n 3. Transacciones pendientes.");
            System.out.print("\n 4. Historial de transacciones. ");
            System.out.print("\n 5. Archivo de transacciones. ");
            System.out.print("\n 6. Volver al login. ");
            System.out.print("\n 7. Salir. ");
            System.out.print("\n ==== SELECIONA OPCIÓN ===: \n");
            Scanner teclado = new Scanner(System.in);
            opcion = Integer.valueOf(teclado.nextLine());
            return opcion;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int mostrarMenuUsuario() {
        try{
            int opcion;
            System.out.print("\n 1. Log in. ");
            System.out.print("\n 2. Registrarse.");
            System.out.print("\n 3. Salir.");
            System.out.print("\n ==== SELECIONA OPCIÓN ===: \n");
            Scanner teclado = new Scanner(System.in);
            opcion = Integer.valueOf(teclado.nextLine());
            return opcion;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}

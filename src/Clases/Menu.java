package Clases;

import java.util.Scanner;
import java.util.UUID;

public final class Menu {
    public Menu() {
    }

    public int mostrarMenuPrincipal() {
        try{
            int opcion = -1;
            while(opcion > 7 || opcion < 1) {
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
                opcion = Integer.valueOf(teclado.nextLine());
            }
            return opcion;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int mostrarMenuUsuario() {
        try{
            int opcion = -1;
            while(opcion < 1 || opcion > 3) {
                System.out.print("\n 1. Ingresar. ");
                System.out.print("\n 2. Registrarse.");
                System.out.print("\n 3. Salir.");
                System.out.print("\n ==== SELECIONA OPCIÓN ===: \n");
                Scanner teclado = new Scanner(System.in);
                opcion = Integer.valueOf(teclado.nextLine());
            }
            return opcion;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String pedirEmail() {
        try{
            String opcion = "";
            while(opcion.isEmpty()) {
                System.out.print("1.Email: ");
                Scanner teclado = new Scanner(System.in);
                opcion = teclado.nextLine();
            }
            return opcion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String pedirPassword() {
        try{
            String opcion = "";
            while(opcion.isEmpty()) {
                System.out.print("2.Password: ");
                Scanner teclado = new Scanner(System.in);
                opcion = teclado.nextLine();
            }
            return opcion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UUID pedirUUID() {
        try{
            String opcion = "";
            while(opcion.isEmpty()) {
                System.out.print("3.ID: ");
                Scanner teclado = new Scanner(System.in);
                opcion = teclado.nextLine();
            }
            return UUID.fromString(opcion);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}

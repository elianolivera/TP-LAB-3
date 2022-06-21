package Logica;

import Modelos.Menu;

import java.util.Scanner;
import java.util.UUID;

public class MenuLogica {

    Menu modelo=new Menu();

    public int menuPrincipal() {
        return modelo.mostrarMenuPrincipal();
    }

    public int menuUsuario() {
        return modelo.mostrarMenuUsuario();
    }

    public String pedirEmail() {
        try{
            String opcion = "";
            while(opcion.isEmpty()) {
                System.out.print("Ingrese su direccion email: ");
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
                System.out.print("Ingrese su password: ");
                Scanner teclado = new Scanner(System.in);
                opcion = teclado.nextLine();
            }
            return opcion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UUID pedirUUID() throws IllegalArgumentException {
        String opcion = "";
        while(opcion.isEmpty()) {
            System.out.print("3. Ingrese su ID: ");
            Scanner teclado = new Scanner(System.in);
            opcion = teclado.nextLine();
        }

        UUID id = UUID.fromString(opcion);
        return id;
    }





}


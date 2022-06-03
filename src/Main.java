import Clases.*;
import Exceptions.InvalidOptionException;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        Sesion sesion = new Sesion();
        Menu menu = new Menu();
        correrApp(sesion, menu);
    }

    // Esta hecha en caso de que el usuario quiera desloguearse, se llama de nuevo a esta funcion.
    private static void correrApp(Sesion sesion, Menu menu) {
        handleLoginAndRegister(sesion, menu);
        userOperations(sesion,menu);
    }

     private static void handleLoginAndRegister(Sesion sesion, Menu menu) {
        while(sesion.getUsuarioActivo() == null) {
            try {
                int opcionMenuUsuario = menu.mostrarMenuUsuario();
                if(opcionMenuUsuario == 2) {
                    sesion.registrarUsuario();
                    System.out.println("Nuevo usuario resgistrado.");

                } else if(opcionMenuUsuario == 1) {
                    // Le pido los datos para loguear a el usuario.
                    String email = menu.pedirEmail();
                    String password = menu.pedirPassword();
                    UUID id = menu.pedirUUID();
                    sesion.loguearUsuario(email,password,id);
                    if(sesion.getUsuarioActivo() == null) {
                        System.out.println("\n Los datos ingresados no son correctos o no existe el usuario.");
                    }
                } else {
                    sesion.finalizarSesion();
                }
            } catch(InvalidOptionException | InputMismatchException ex) {
                System.out.println("\n" + (ex instanceof InputMismatchException ? "La opcion debe ser un numero." : ex.getMessage()));
            } }
        System.out.println("Usuario logueado satisfactoriamente.");
    }

    private static void userOperations(Sesion sesion, Menu menu) {
        try {
            int opcionMenuPrincipal = menu.mostrarMenuPrincipal();
            ///Lista de usuarios
            Billetera us1  = new Billetera("Peter","Pedro","01010101","26/07/1993","elian.lpb","123",100);
            Billetera us2  = new Billetera("Albert","Parker","10101010","26/07/1923","elian.lpb","123",100);
            Billetera us3  = new Billetera("Alan","Sanchez","11111111","26/07/1956","elian.lpb","123",100);
            List<Billetera> lista= new ArrayList<>();
            lista.add(us1);
            lista.add(us2);
            lista.add(us3);
            Transferencia ttt = new Transferencia();

            switch (opcionMenuPrincipal) {

                case 1:
                    // Consultar activos.
                    break;
                case 2:
                    // Realizar transferencia
                    System.out.println("\n ======== Lista de usuarios ======== :  \n"+lista);
                    System.out.print("\n ========  Ingrese monto a transferir  ======== :  ");
                    float monto=0;
                    Scanner teclado = new Scanner(System.in);
                    monto = teclado.nextFloat();
                    ttt=ttt.transferir(ttt,monto,lista);
                    System.out.println(us1);
                    System.out.println(us2);
                    /// MOSTRAR TRANSFERENCIAS (ESTA VA EN EL 5 CON LISTA DE TRANSFERENCIAS EN ARCHIVO)
                    System.out.print("\n ======== Comprobante : ======== ");
                    System.out.println(ttt);
                    break;
                case 3:
                    // Transacciones pendientes.

                    break;
                case 4:
                    // Validar transaccion.

                    ttt.validar(ttt);
                    break;
                case 5:
                    // Historial de transacciones.

                    System.out.print("\n ======== Comprobante : ======== ");
                    System.out.println(ttt);

                    break;
                case 6:
                    // Archivo de transacciones.
                    break;
                case 7:
                    // Volver al login
                    sesion.setUsuarioActivo(null);
                    break;
                case 8:
                    // Salir.
                    sesion.finalizarSesion();
                    break;
            }
            //aca iria el switch con las operaciones.

        } catch(InvalidOptionException | InputMismatchException ex) {
            System.out.println("\n" + (ex instanceof InputMismatchException ? "La opcion debe ser un numero." : ex.getMessage()));
        }
    }
}


import Logica.MenuLogica;
import Logica.SesionLogica;
import Logica.TransferenciaLogica;
import Modelos.*;
import Exceptions.InvalidOptionException;

import java.util.*;

public class Main {


    public static void main(String[] args) {

       SesionLogica sesion = new SesionLogica();
        MenuLogica menu = new MenuLogica();

        sesion.archivoAMapUsuarios();
        sesion.archivoAMapBilleteras();
        sesion.archivoAMapTransferencias();

        correrApp(sesion, menu);
    }

    // Esta hecha en caso de que el usuario quiera desloguearse, se llama de nuevo a esta funcion.
    private static void correrApp(SesionLogica sesion, MenuLogica menu) {
        handleLoginAndRegister(sesion, menu);
        userOperations(sesion,menu);
    }

     private static void handleLoginAndRegister(SesionLogica sesion, MenuLogica menu) {
        while(sesion.getUsuarioActivo() == null) {
            try {
                int opcionMenuUsuario = menu.menuUsuario();
                if(opcionMenuUsuario == 2) {
                    sesion.registrarUsuario();
                    System.out.println("Nuevo usuario resgistrado.");

                } else if(opcionMenuUsuario == 1) {
                    // Le pido los datos para loguear a el usuario.
                    String email = menu.pedirEmail();
                    String password = menu.pedirPassword();

                    try {
                        UUID id = menu.pedirUUID();
                        sesion.loguearUsuario(email,password,id);

                    } catch (IllegalArgumentException ex) {
                        System.out.println("\n El ID ingresado no es valido como UUID.");
                    }

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

    private static void userOperations(SesionLogica sesion, MenuLogica menu) {
        while(sesion.getUsuarioActivo() != null) {
            try {
                int opcionMenuPrincipal = menu.menuPrincipal();

                TransferenciaLogica ttt = new TransferenciaLogica();

                switch (opcionMenuPrincipal) {
                    case 1:
                        // Consultar activos.
                        System.out.println("Actualmente cuentas con: $" + sesion.consultarActivos());
                        break;
                    case 2:
                        // Realizar transferencia
                        System.out.println("\n ======== Lista de usuarios ======== :  \n" + sesion.getUsuariosLoguin());
                        System.out.print("\n ========  Ingrese monto a transferir  ======== :  ");
                        float monto = 0;
                        Scanner teclado = new Scanner(System.in);
                        monto = teclado.nextFloat();
                        Transferencia transf = ttt.transferir(sesion,ttt, monto,sesion.getUsuarioActivo(), sesion.getBilleteras(), sesion.getUsuariosLoguin());
                        System.out.print("\n ======== Comprobante : ======== ");
                        System.out.println(transf);
                        sesion.aniadirtransferencia(transf.getUUIDtransaccion(),transf);
                        sesion.guardarTransferenciaArchivo(transf);

                        break;
                    case 3:
                        // Transacciones pendientes.
                        System.out.println(" ======== Estas son las transacciones pendientes de validacion ======== : \n");
                        ttt.pendientesValidacion(sesion.getTransferencias());

                        break;
                    case 4:
                        // Validar transaccion.
                        System.out.print("\n ======== Validación de transacción   ========: ");
                       ttt.validar(sesion,sesion.getTransferencias());
                        break;
                    case 5:
                        // Historial de transacciones.
                        System.out.print("\n ======== Comprobante : ======== ");
                        System.out.println(sesion.getTransferencias());

                        break;
                    case 6:
                        // Archivo de transacciones.
                        System.out.print("\n ======== Archivo de transacciones : ======== ");
                        System.out.println("\n ======== Lista de Transferencias ======== :  \n" + sesion.getTransferencias());
                        break;
                    case 7:
                        // Volver al login
                        sesion.setUsuarioActivo(null);
                        correrApp(sesion, menu);
                        break;
                    case 8:
                        // Salir.
                        sesion.finalizarSesion();
                        break;
                }
            } catch(InvalidOptionException | InputMismatchException ex) {
                System.out.println("\n" + (ex instanceof InputMismatchException ? "La opcion debe ser un numero." : ex.getMessage()));

            }
        }
    }
}


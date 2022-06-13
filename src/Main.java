import Logica.MenuLogica;
import Logica.SesionLogica;
import Modelos.*;
import Modelos.Sesion;
import Modelos.Menu;
import Exceptions.InvalidOptionException;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        SesionLogica sesionLogica = new SesionLogica();
        MenuLogica menuLogica = new MenuLogica();
        Menu menu = new Menu();
        Sesion sesion = new Sesion();
        
        correrApp(sesion, menu, sesionLogica, menuLogica);
    }

    // Esta hecha en caso de que el usuario quiera desloguearse, se llama de nuevo a esta funcion.
    private static void correrApp(Sesion sesion, Menu menu,SesionLogica sesionLogica, MenuLogica menuLogica) {
        sesionLogica.archivoALista();
        handleLoginAndRegister(sesion, menu, sesionLogica, menuLogica);
        userOperations(sesion,menu,sesionLogica, menuLogica);
    }

     private static void handleLoginAndRegister(Sesion sesion, Menu menu,SesionLogica sesionLogica, MenuLogica menuLogica) {
        
        Billetera usuario = null;
        
        while(sesion.getIdUsuarioActivo()== null) {
            try {
                int opcionMenuUsuario = menu.mostrarMenuUsuario();
                if(opcionMenuUsuario == 2) {
                    usuario= (Billetera) SesionLogica.registrarUsuario();
                    System.out.println("Nuevo usuario resgistrado.");

                } else if(opcionMenuUsuario == 1) {
                    // Le pido los datos para loguear a el usuario.
                    String email = menuLogica.pedirEmail();
                    String password = menuLogica.pedirPassword();

                    try {
                        UUID id = menuLogica.pedirUUID();

                        SesionLogica.loguearUsuario(usuario);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("\n El ID ingresado no es valido como UUID.");
                    }

                    if(sesion.getIdUsuarioActivo() == null) {
                        System.out.println("\n Los datos ingresados no son correctos o no existe el usuario.");
                    }
                } else {
                    SesionLogica.finalizarSesion();
                }
            } catch(InvalidOptionException | InputMismatchException ex) {
                System.out.println("\n" + (ex instanceof InputMismatchException ? "La opcion debe ser un numero." : ex.getMessage()));
            } }
        System.out.println("Usuario logueado satisfactoriamente.");
    }

    private static void userOperations(Sesion sesion, Menu menu, SesionLogica sesionLogica, MenuLogica menuLogica) {
        try {
            int opcionMenuPrincipal = menu.mostrarMenuPrincipal();
            ///Lista de usuarios
            List<Transferencia> transferencias =new ArrayList<>();
            Billetera us1  = new Billetera("Peter","Pedro","01010101","26/07/1993","elian.lpb","123");
            Billetera us2  = new Billetera("Albert","Parker","10101010","26/07/1923","elian.lpb","123");
            Billetera us3  = new Billetera("Alan","Sanchez","11111111","26/07/1956","elian.lpb","123");
            Transferencia ttt = new Transferencia();

            sesionLogica.aniadirUsuario(us1.getEmail(), us1.getBilletera());
            sesionLogica.aniadirUsuario(us2.getEmail(), us2.getBilletera());
            sesionLogica.aniadirUsuario(us3.getEmail(), us3.getBilletera());

            switch (opcionMenuPrincipal) {
                case 1:
                    // Consultar activos.
                    break;
                case 2:
                    // Realizar transferencia
                    System.out.println("\n ======== Lista de usuarios ======== :  \n" + sesionLogica.getUsuariosLoguin());
                    System.out.print("\n ========  Ingrese monto a transferir  ======== :  ");
                    float monto = 0;
                    Scanner teclado = new Scanner(System.in);
                    monto = teclado.nextFloat();
                    ttt = ttt.transferir(ttt, monto, sesionLogica.getUsuariosLoguin(), sesionLogica.getTransferencias());
                    System.out.println(us1);
                    System.out.println(us2);
                    /// MOSTRAR TRANSFERENCIAS (ESTA VA EN EL 5 CON LISTA DE TRANSFERENCIAS EN ARCHIVO)
                    System.out.print("\n ======== Comprobante : ======== ");
                    System.out.println(ttt);


                    sesionLogica.aniadirTransferencia(ttt);
                    break;
                case 3:
                    // Transacciones pendientes.
                    System.out.println("Estas son las transacciones pendientes de validacion: \n");
                    for(Transferencia t : sesionLogica.getTransferencias()) {
                        if(t != null && t.getEstado() != Estado.VALIDADA) {
                            System.out.println(t);
                        }
                    }

                    break;
                case 4:
                    // Validar transaccion.

                    ttt.validar(transferencias);
                    break;
                case 5:
                    // Historial de transacciones.
                    System.out.print("\n ======== Comprobante : ======== ");
                    System.out.println(SesionLogica.getTransferencias());

                    break;
                case 6:
                    // Archivo de transacciones.
                    break;
                case 7:
                    // Volver al login
                    sesion.setIdUsuarioActivo(null);
                    break;
                case 8:
                    // Salir.
                    sesionLogica.finalizarSesion();
                    break;
            }
        } catch(InvalidOptionException | InputMismatchException ex) {
            System.out.println("\n" + (ex instanceof InputMismatchException ? "La opcion debe ser un numero." : ex.getMessage()));

        }
    }
}


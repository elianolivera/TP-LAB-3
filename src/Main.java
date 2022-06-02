import Clases.Menu;
import Clases.Sesion;
import Exceptions.InvalidOptionException;

import java.io.IOException;
import java.util.InputMismatchException;

public class Main {


    public static void main(String[] args) throws IOException {
        ///Lista de usuarios
       /* User us1  = new User("Peter","pedr.com","pw",100);
        User us2  = new User("Robert","Robertgmail.com","pw",100);
        User us3  = new User("Alan","Robertgmail.com","pw",100);
        List<User> lista= new ArrayList<>();
        lista.add(us1);
        lista.add(us2);
        lista.add(us3);
        System.out.println(lista);*/


        /// TRANSFERENCIA
        /*
        System.out.print("\n ========  Ingrese monto a transferir  ======== :  ");
        float monto=0;
        Scanner teclado = new Scanner(System.in);
        monto = teclado.nextFloat();
        Transferencia t1 = new Transferencia();///Método transferencia
        t1=t1.transferir(t1,monto,lista);
        System.out.println(us1);
        System.out.println(us2);
        /// MOSTRAR TRANSFERENCIAS
        System.out.print("\n ======== Comprobante : ======== ");
        System.out.println(t1);
       */


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
                    ///UUID id = menu.pedirUUID();
                    sesion.loguearUsuario(email,password);
                    if(sesion.getUsuarioActivo() == null) {
                        System.out.println("\n Los datos ingresados no son correctos o no existe el usuario.");
                    }
                } else {
                    sesion.finalizarSesion();
                }
            } catch(InvalidOptionException | InputMismatchException ex) {
                System.out.println("\n" + (ex instanceof InputMismatchException ? "La opcion debe ser un numero." : ex.getMessage()));
            }
        }

        System.out.println("User logueado satisfactoriamente.");
    }

    private static void userOperations(Sesion sesion, Menu menu) {
        try {
            int opcionMenuPrincipal = menu.mostrarMenuPrincipal();

            switch (opcionMenuPrincipal) {
                case 1:
                    // Consultar activos.
                    break;
                case 2:
                    // Realizar una transaccion.
                    break;
                case 3:
                    // Transacciones pendientes.
                    break;
                case 4:
                    // Validar transaccion.
                    break;
                case 5:
                    // Historial de transacciones.
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


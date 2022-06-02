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

        handleLoginAndRegister(sesion, menu);
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
}


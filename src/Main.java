import Clases.*;
import Clases.Transferencia;

import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    

    public static void main(String[] args) throws IOException {
        User us1  = new User("Pedro Gonzales","pedr.com","pw",100);
        User us2  = new User("Robert Parker","Robertgmail.com","pw",100);


        /*
        System.out.print("\n ========  Ingrese montos a transferir : ======== ");
        float monto=0;
        Scanner teclado = new Scanner(System.in);
        monto = teclado.nextFloat();
        Transferencia t1 = new Transferencia(us1,us2,1,0,monto, Estado.NOVALIDADA);
        ///Método transferencia
        t1.transferir(t1,us1,us2,monto);
        System.out.println(us1);
        System.out.println(us2);
        ///Muestra transferencia
        System.out.print("\n ======== Comprobante : ======== ");
        System.out.println(t1);
        */

        Sesion sesion = new Sesion();
        Menu menu = new Menu();

        // Este while se encarga de las operaciones de registrar y loguear.
        while(sesion.getUsuarioActivo() == null) {
            int opcionMenuUsuario = menu.mostrarMenuUsuario();

            if(opcionMenuUsuario == 2) {
                // Le pido los datos para registrar a el usuario.
                String email = menu.pedirEmail();
                String password = menu.pedirPassword();

                UUID uuidNuevoUser = sesion.registrarUsuario(email,password);
                System.out.println("This is your new generated ID: " + uuidNuevoUser + ". Save it!");
                System.in.read();
            } else if(opcionMenuUsuario == 1) {

                // Le pido los datos para loguear a el usuario.
                String email = menu.pedirEmail();
                String password = menu.pedirPassword();
                UUID id = menu.pedirUUID();

                sesion.loguearUsuario(email,password,id);
                if(sesion.getUsuarioActivo() == null) {
                    System.out.println("Bad credentials (Press any key to continue).");
                    System.in.read();
                }
            } else {
                sesion.finalizarSesion();
            }
        }

        System.out.println("User logueado");
    }
}


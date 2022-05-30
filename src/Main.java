import Clases.*;
import Clases.Transferencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {


    public static void main(String[] args) throws IOException {
        ///Lista de usuarios
        User us1  = new User("Peter","pedr.com","pw",100);
        User us2  = new User("Robert","Robertgmail.com","pw",100);
        User us3  = new User("Alan","Robertgmail.com","pw",100);
        List<User> lista= new ArrayList<>();
        lista.add(us1);
        lista.add(us2);
        lista.add(us3);
        System.out.println(lista);


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
                sesion.finalizarSesion(); }
        }
        System.out.println("User logueado");
    }
}


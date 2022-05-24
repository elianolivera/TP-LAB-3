import Clases.*;
import Clases.Transferencia;

import java.util.Scanner;

public class Main {
    

    public static void main(String[] args) {
        User us1  = new User(1,"Pedro Gonzales","pedr.com",100);
        User us2  = new User(2,"Robert Parker","Robertgmail.com",100);


        /*
        System.out.print("\n ========  Ingrese monto a transferir : ======== ");
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

        while(sesion.isActivo()) {
            int opcionMenuPrincipal = -1;
            while(opcionMenuPrincipal == -1 || (opcionMenuPrincipal < 1 || opcionMenuPrincipal > 7)) {
                opcionMenuPrincipal = menu.mostrarMenu();
            }

            if(opcionMenuPrincipal == 7) {
                sesion.finalizarSesion();
                System.out.println("Hasta luego!");
            }
        }

    }
}


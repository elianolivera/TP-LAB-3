import Clases.Menu;
import Clases.Sesion;

public class Main {

    public static void main(String[] args) {

           /* ///EJEMPLO SIMPLE DE TRANSACCION
            User us1  = new User(1,"Pedro","pedr",800);
            User us2  = new User(2,"Robert","pedr",600);
            Tranferencia t1= new Tranferencia();
            t1.transferir(t1,us1,us2,100);
            System.out.println(us1);
            System.out.println(us2);*/

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


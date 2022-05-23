import Clases.Menu;
import Clases.Sesion;

public class Main {



    public static void main(String[] args) {



/*
              System.out.print("\n ========  PROBANDO MÉTODO TRANSACCIÓN : ======== ");
            User us1  = new User(1,"Pedro","pedr",800);
            User us2  = new User(2,"Robert","pedr",600);
            Tranferencia t1= new Tranferencia();
            t1.transferir(t1,us1,us2,100);
            System.out.println(us1);
            System.out.println(us2);
*/
        Sesion sesion = new Sesion();
        Menu menu = new Menu();

        while(sesion.isActivo()) {
            int opcion = menu.mostrarMenu();

            if(opcion == 7) {
                sesion.finalizarSesion();
                System.out.println("Hasta luego!");
            }
        }

    }
}


package Practica;

import java.io.IOException;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        System.out.print("\n ========  HOLA : ======== ");
           /* ///EJEMPLO SIMPLE DE TRANSACCION
            User us1  = new User(1,"Pedro","pedr",800);
            User us2  = new User(2,"Robert","pedr",600);
            Tranferencia t1= new Tranferencia();
            t1.transferir(t1,us1,us2,100);
            System.out.println(us1);
            System.out.println(us2);*/


          ///EJEMPLO SIMPLE MENÚ
            try{
                int Opcion = 0;
                System.out.print("\n ========  BIENVENIDO USUARIO: ======== ");
                System.out.print("\n ======== MENÚ DE USUARIO : ======== ");
                String afirm = "si";
                String Scan = null;
                Scanner scanner = new Scanner(System.in);
                System.out.print("\n 1. Consultar estado de activos ");
                System.out.print("\n 2. Realizar una transacción");
                System.out.print("\n 3. Transacciones pendientes");
                System.out.print("\n 4. Historial de transacciones ");
                System.out.print("\n 5. Archivo de transacciones ");
                System.out.print("\n 6. Volver al login ");
                System.out.print("\n ==== SELECIONA OPCIÓN ===: \n");
                Scanner teclado = new Scanner(System.in);
                Opcion = teclado.nextInt();
                System.out.print("\n ==== editado ===: \n");
                System.out.println(" Mis activos \n");


                switch (Opcion) {
                    case 1:
                        System.out.println(" Mis activos \n");


                        break;
                    case 2:
                        System.out.print("Realizar Transacción:\n");

                        break;
                    case 3:
                        System.out.print("Transacciones pendientes \n");
                        break;
                    case 4:
                        System.out.print(" Historial de transacciones \n");
                        break;
                    case 5:
                        System.out.print("Archivo de tranasacciones\n");

                        break;
                    case 0:
                        System.out.print("Volver al menú\n");
                        System.exit(0);
                        break;
                    default:
                        System.out.print("Back to login\n");
                        System.exit(0);
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public final class Sesion implements Serializable {

    private static final long serialVersionUID = -6719022570919861969L;
    private List<Billetera> billeteras = new ArrayList<>();
    private List<Transferencia> transferencias = new ArrayList<>();
    private Billetera billeteraActiva;

    public Sesion() {
        this.billeteraActiva = null;
    }

    public User getBilleteraActiva() {
        return billeteraActiva;
    }

    public void setBilleteraActiva(Billetera billeteraActiva) {
        this.billeteraActiva = billeteraActiva;
    }

    public User registrarUsuario() {

        String nombre,apellido,dni,fechaDeNacimiento,email,password;
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nIngreso de Datos.");

        System.out.println("\nIngrese su Nombre: ");
        nombre=teclado.nextLine();
        System.out.println("\nIngrese su Apellido: ");
        apellido=teclado.nextLine();
        System.out.println("\nIngrese su Numero de documento: ");
        dni=teclado.nextLine();
        System.out.println("\nIngrese su Fecha de nacimiento: ");
        fechaDeNacimiento=teclado.nextLine();
        System.out.println("\nIngrese su Correo electronico: ");
        email=teclado.nextLine();
        System.out.println("\nIngrese su password: ");
        password=teclado.nextLine();

        Billetera billetera = new Billetera(nombre, apellido, dni, fechaDeNacimiento, email, password);

        System.out.println("Su ID para loguearse es: " + billetera.billetera + ". Guardalo!");

        billeteras.add(billetera);

        return billetera;
    }

    public Billetera loguearUsuario(String email, String password, UUID billetera) {
        for(Billetera u : billeteras) {
          if(email.equals(u.email) && password.equals(u.password) && billetera.equals(u.billetera)) {///Validacion de correo y contraseña
                setBilleteraActiva(u);
                return u;
            }
        }

        return null;
    }

    /*public void guardaUsuarioEnArchivo(List<User> lista) {

        File archivoUsuarios = JSON_MAPPER.readValue (new File("usuarios.json"));

        ObjectMapper mapper = new ObjectMapper();


        for (User u : lista) {
            if (u == null) {
            }
        }
    }*/

    public List<Billetera> getBilleteras() {
        return billeteras;
    }

    public List<Transferencia> getTransferencias() {
        return transferencias;
    }

    public void mostrarUsuarios(){
        for(Billetera b : billeteras){
            if(b !=null){
                System.out.println(b);
            }
        }
    }

    public List<Billetera> aniadirBilletera(Billetera b) {
        this.billeteras.add(b);
        return billeteras;
    }

    public List<Transferencia> aniadirTransferencia(Transferencia t) {
        this.transferencias.add(t);
        return transferencias;
    }

    public void finalizarSesion() {
        // Paso los nuevos usuarios registrados a el archivo.
        System.exit(0);
    }
}

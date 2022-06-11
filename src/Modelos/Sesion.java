package Modelos;

import java.io.File;
import java.io.Serializable;
import java.util.*;


public final class Sesion implements Serializable {

    private static final long serialVersionUID = -6719022570919861969L;
    HashMap<String, UUID> usuariosLoguin = new HashMap<>();
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
        if(usuariosLoguin.containsKey(dni)){
            System.out.println("El numero de Documento ya existe");
            return null;
        }

        System.out.println("\nIngrese su Fecha de nacimiento: ");
        fechaDeNacimiento=teclado.nextLine();

        System.out.println("\nIngrese su Correo electronico: ");
        email=teclado.nextLine();
        if(usuariosLoguin.containsKey(email)){
            System.out.println("El Correo electronico ya existe");
            return null;
        }

        System.out.println("\nIngrese su password: ");
        password=teclado.nextLine();

        Billetera billetera = new Billetera(nombre, apellido, dni, fechaDeNacimiento, email, password);
        UUID id=billetera.billetera;
        System.out.println("Su ID para loguearse es: " + id + ". Guardalo!");

        return billetera;
    }

    public Billetera loguearUsuario(String email, String password, UUID billetera) {
        for(Map.Entry<String,UUID> entry: usuariosLoguin.entrySet()) {
          if(email.equals(entry.getKey()) && password.equals(entry.getKey()) && billetera.equals(entry.getKey())) {///Validacion de correo y contraseña
               // setBilleteraActiva(u);
               // return u;
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

    public void aniadirUsuario(String email, UUID id) {
        this.usuariosLoguin.put(email,id);
    }

    public HashMap<String, UUID> getUsuariosLoguin() {
        return usuariosLoguin;
    }

    public List<Transferencia> aniadirTransferencia(Transferencia t) {
        this.transferencias.add(t);
        return transferencias;
    }

    public List<Transferencia> getTransferencias() {
        return transferencias;
    }

    public void finalizarSesion() {
        // Paso los nuevos usuarios registrados a el archivo.
        System.exit(0);
    }
}

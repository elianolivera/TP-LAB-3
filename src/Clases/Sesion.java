package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public final class Sesion implements Serializable {

    private static final long serialVersionUID = -6719022570919861969L;
    private List<User> usuarios = new ArrayList<>();
    private User usuarioActivo;

    public Sesion() {
        this.usuarioActivo = null;
    }

    public User getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(User usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public User registrarUsuario() {

        String nombre,apellido,dni,fechaDeNacimiento,email,password;
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nIngreso de Datos:");
        System.out.println("\nIngrese su Nombre");
        nombre=teclado.nextLine();

        System.out.println("\nIngrese su Apellido");
        apellido=teclado.nextLine();

        System.out.println("\nIngrese su Numero de documento");
        dni=teclado.nextLine();

        System.out.println("\nIngrese su Fecha de nacimiento");
        fechaDeNacimiento=teclado.nextLine();

        System.out.println("\nIngrese su Correo electronico");
        email=teclado.nextLine();

        System.out.println("\nIngrese su password");
        password=teclado.nextLine();

        System.out.println("Los datos ingresados son:");
        User usuariox = new User( nombre,  apellido,  dni,  fechaDeNacimiento, email,  password);
        System.out.println(usuariox);
        usuarios.add(usuariox);

        return usuariox;
    }

    public User loguearUsuario(String email, String password, UUID billetera) {
        for(User u : usuarios) {
          if(email.equals(u.email) && password.equals(u.password) && billetera.equals(u.billetera)) {///Validacion de correo y contraseña
                setUsuarioActivo(u);
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

    public void mostarUsuarios(List<User> lista){
        for(User u : lista){
            if(u !=null){
                System.out.println(u.toString());
            }
        }
    }

    public void finalizarSesion() {
        // Paso los nuevos usuarios registrados a el archivo.
        System.exit(0);
    }
}

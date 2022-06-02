package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// En el caso de que quiera hacer algo como agregar datos a un archivo al apretar salir me parecio mejor usar una clase sesion.
public final class Sesion {
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

    public User loguearUsuario(String email, String password) {
        for(User u : usuarios) {
          if(email.equals(u.email) && password.equals(u.password)) {
                setUsuarioActivo(u);
                return u;
            }
        }

        return null;
    }

    public void finalizarSesion() {
        // Paso los nuevos usuarios registrados a el archivo.
        System.exit(0);
    }
}

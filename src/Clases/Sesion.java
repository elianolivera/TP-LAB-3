package Clases;

import Exceptions.InvalidOptionException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

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

    public UUID registrarUsuario(String email, String password) {
        User usuariox = new User("x",email, password, 100);
        usuarios.add(usuariox);

        return usuariox.getUUID();
    }

    public User loguearUsuario(String email, String password, UUID id) {
        for(User u : usuarios) {
            if(id.equals(u.id) && email.equals(u.email) && password.equals(u.password)) {
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

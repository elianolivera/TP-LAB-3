package Clases;

// En el caso de que quiera hacer algo como agregar datos a un archivo al apretar salir me parecio mejor usar una clase sesion.
public final class Sesion {
    private boolean activo;
    private User currentUser;

    public Sesion() {
        this.activo = true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void finalizarSesion() {
        setActivo(false);
        setCurrentUser(null);
    }
}

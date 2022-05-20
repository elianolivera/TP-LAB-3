package Clases;

// En el caso de que quiera hacer algo como agregar datos a un archivo al apretar salir me parecio mejor usar una clase sesion.
public final class Sesion {
    private boolean activo;

    public Sesion() {
        this.activo = true;
    }

    public boolean isActivo() {
        return activo;
    }

    public void finalizarSesion() {
        this.activo = false;
    }
}

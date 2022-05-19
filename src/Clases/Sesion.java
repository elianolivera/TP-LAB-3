package Clases;

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

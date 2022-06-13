package Modelos;

import java.util.UUID;


public final class Sesion  {

    private UUID idUsuarioActivo;

    public Sesion() {
        this.idUsuarioActivo = null;
    }

    public UUID getIdUsuarioActivo() {
        return idUsuarioActivo;
    }

    public void setIdUsuarioActivo(UUID idUsuarioActivo) {
        this.idUsuarioActivo = idUsuarioActivo;
    }


}

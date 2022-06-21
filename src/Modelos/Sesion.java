package Modelos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public final class Sesion  {

    private static final long serialVersionUID = -6719022570919861969L;
    HashMap<String, UUID> usuariosLoguin = new HashMap<>();
    private List<Transferencia> transferencias = new ArrayList<>();



    public Sesion() {
    }

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

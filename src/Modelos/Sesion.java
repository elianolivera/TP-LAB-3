package Modelos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.io.Serializable;
import java.util.*;


public final class Sesion implements Serializable {

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

package Logica;

import Modelos.Billetera;
import Modelos.Estado;
import Modelos.Transferencia;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public class TransferenciaLogica extends  Transferencia implements Serializable {

    Transferencia modelo = new Transferencia();
    public TransferenciaLogica() {

    }
    public TransferenciaLogica(Transferencia modelo) {
        this.modelo = modelo;
    }

    public Transferencia getModelo() {
        return modelo;
    }

    public TransferenciaLogica setModelo(Transferencia modelo) {
        this.modelo = modelo;
        return this;
    }

    // Busca transferencia por UUID para validarla
    public Transferencia buscartransferencia(List<TransferenciaLogica> transferencias) {
        System.out.println("Ingrese el UUID de la transferencia a validar: \n");
        String UUIDt;
        Scanner id = new Scanner(System.in);
        UUIDt=id.nextLine();
        for (TransferenciaLogica  transf :transferencias) {
            if (transf != null && transf.getUUIDtransaccion().equals(UUIDt)) {
                return transf;
            } }
        return null;
    }

    /// VALIDAR TRANSFERENCIA
    public void validar(List<TransferenciaLogica> transferencias) {

        modelo=buscartransferencia(transferencias);
        if (modelo.getCantidadtransac() >= 3) {
            modelo.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        } else if (modelo.getCantidadtransac() <= 3) {
            modelo.setCantidadtransac(modelo.getCantidadtransac() + 1);
            /// Se añade una validación
        }
    }

    ///Busca usuario en la lista por UUID
    public Billetera buscarUsuarioPorUUID(HashMap<UUID, Billetera> usuariosLista) {
        UUID id;
        Scanner Aux = new Scanner(System.in);
        id= UUID.fromString(Aux.nextLine());
        for (Map.Entry<UUID, Billetera> entry : usuariosLista.entrySet()) {
            if (entry != null && entry.getValue().getBilletera().equals(id)) {
                return entry.getValue(); // Retorna la billetera que coincida con el UUID
            }else{
                System.out.println("el UUID  no existe");
            }
        }
        return null;
    }

    ///Transeferir de un usuario insertado por teclado a otro.
    public Transferencia transferir(TransferenciaLogica t1, float monto, HashMap<UUID, Billetera> usuariosLista, List<Transferencia> transferencias) {
        String nombre = null;
        System.out.print(" ========  Ingrese su UUID   ========: ");
        Billetera u1 = t1.buscarUsuarioPorUUID(usuariosLista);
        System.out.print(" ========  Ingrese  el UUID del destinatario ========: ");
        Billetera u2 = t1.buscarUsuarioPorUUID(usuariosLista);
        u1.setSaldo(u1.getSaldo() - monto);
        u2.setSaldo(u2.getSaldo() + monto);
        modelo.setCantidadtransac(modelo.getCantidadtransac() + 1);
        UUID id= this.modelo.UUIDtransaccion = UUID.randomUUID();
        modelo = new Transferencia(id,u1, u2,modelo.getCantidadtransac(), monto, Estado.NOVALIDADA);
        if (modelo.getCantidadtransac() >= 3) {
            modelo.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
            guardarTransferenciaArchivo(modelo);
        }
        return modelo;
    }

    /// Guarda la transferencia en el archivo
    public void guardarTransferenciaArchivo(Transferencia t) {
         File file = new File("Transferencias.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (!file.exists()) {
                file.createNewFile(); }
            mapper.writeValue(file, t);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } } }
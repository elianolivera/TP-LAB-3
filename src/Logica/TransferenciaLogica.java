package Logica;

import Modelos.Billetera;
import Modelos.Estado;
import Modelos.Transferencia;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public class TransferenciaLogica implements Serializable {

    Transferencia modelo = new Transferencia();

    // Busca transferencia por UUID para validarla
    /*public Transferencia buscartransferencia(List<TransferenciaLogica> transferencias) {
        System.out.println("Ingrese el UUID de la transferencia a validar: \n");
        String UUIDt;
        Scanner id = new Scanner(System.in);
        UUIDt=id.nextLine();
        for (TransferenciaLogica  transf :transferencias) {
            //if (transf != null && transf.getUUIDtransaccion().equals(UUIDt)) {
                //return transf;
            } }
        //return null;
    }*/

    /// VALIDAR TRANSFERENCIA
    public void validar(List<TransferenciaLogica> transferencias) {

        TransferenciaLogica t1 = new TransferenciaLogica();
        //t1= t1.buscartransferencia(transferencias);
        if (modelo.getCantidadtransac() >= 3) {
            modelo.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        } else if (modelo.getCantidadtransac() <= 3) {
            modelo.setCantidadtransac(modelo.getCantidadtransac() + 1);
            /// Se añade una validación
        }
    }

    ///Busca usuario en la lista por DNI
    public Billetera buscarUsuarioPorDNI(HashMap<UUID, Billetera> usuariosLista) {
        String dni;
        Scanner dniAux = new Scanner(System.in);
        dni = dniAux.nextLine();
        for (Map.Entry<UUID, Billetera> entry : usuariosLista.entrySet()) {
            if (entry != null && entry.getValue().getDni().equals(dni)) {
                return entry.getValue(); // Retorna la billetera que coincida con el DNI
            }
        }
        return null;
    }


    ///Transeferir de un usuario insertado por teclado a otro.
    public Transferencia transferir(TransferenciaLogica t1, float monto, HashMap<UUID, Billetera> usuariosLista, List<Transferencia> transferencias) {
        String nombre = null;
        System.out.print(" ========  Ingrese su DNI   ========: ");
        Billetera u1 = t1.buscarUsuarioPorDNI(usuariosLista);
        System.out.print(" ========  Ingrese  el DNI a quien va a transferir ========: ");
        Billetera u2 = t1.buscarUsuarioPorDNI(usuariosLista);
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
                file.createNewFile();
            }
            mapper.writeValue(file, t);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
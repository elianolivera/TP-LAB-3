package Logica;

import Modelos.Estado;
import Modelos.Transferencia;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TransferenciaLogica implements Serializable {

     Transferencia modelo = new Transferencia();

    // Busca transferencia por UUID para validarla
    public Transferencia buscartransferencia(List<TransferenciaLogica> transferencias) {
        System.out.println("Ingrese el UUID de la transferencia a validar: \n");
        String UUIDt;
        Scanner id = new Scanner(System.in);
        UUIDt=id.nextLine();
        for (TransferenciaLogica  transf :transferencias) {
            if (transf != null && transf .getUUIDtransaccion().equals(UUIDt)) {
                return transf;
            } }
        return null;
    }

    /// VALIDAR TRANSFERENCIA
    public void validar (List<TransferenciaLogica>transferencias){

        TransferenciaLogica t1= new TransferenciaLogica();
        t1= t1.buscartransferencia(transferencias);
        if (modelo.getCantidadtransac()>=3) {
            modelo.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        }else if (modelo.getCantidadtransac()<=3){
            modelo.setCantidadtransac(modelo.getCantidadtransac()+1);
            /// Se añade una validación
        }
    }

    ///Busca usuario en la lista por DNI
    /*public Usuario buscarUsuarioPorDNI(HashMap<String, UUID> usuariosLista) {
        String dni;
        Scanner dniAux = new Scanner(System.in);
        dni= dniAux.nextLine();
        for (Map.Entry<String,UUID> entry: usuariosLista.entrySet()) {
            if (entry != null && entry.getDni().equals(dni)) {
                return usuario;
            }
        }
        return null;
    }*/


    ///Transeferir de un usuario insertado por teclado a otro.
    public Transferencia transferir(TransferenciaLogica t1, float monto, HashMap<String, UUID> usuariosLista, List<Transferencia>transferencias) {
        String nombre = null;
        System.out.print(" ========  Ingrese su DNI   ========: ");
        //Billetera u1 = (Billetera) t1.buscarUsuarioPorDNI(usuariosLista);
        System.out.print(" ========  Ingrese  el DNI a quien va a transferir ========: ");
        //Billetera u2 = (Billetera) t1.buscarUsuarioPorDNI(usuariosLista);
        //u1.setSaldo(u1.getSaldo() - monto);
        //u2.setSaldo(u2.getSaldo() + monto);
        modelo.setCantidadtransac(modelo.getCantidadtransac() + 1);
        this.modelo.UUIDtransaccion = UUID.randomUUID();
        //t1 = new Transferencia(UUIDtransaccion,u1, u2, t1.getCantidadtransac(), monto, Estado.NOVALIDADA);
        if (modelo.getCantidadtransac() >= 3) {
            modelo.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        }return modelo; }
}

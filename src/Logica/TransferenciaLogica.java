package Logica;

import Modelos.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.Serializable;
import java.security.KeyStore;
import java.util.*;

public class TransferenciaLogica extends Transferencia implements Serializable {

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
    public Transferencia buscartransferencia(HashMap<UUID, Transferencia> trans) {
        UUID id;
        Scanner Aux = new Scanner(System.in);
        id = UUID.fromString(Aux.nextLine());

        Transferencia b = trans.get(id);

        return b;
    }

    /// VALIDAR TRANSFERENCIA


    public void validar(SesionLogica sesion,HashMap<UUID, Transferencia> transferencias) {

        for (Map.Entry<UUID, Transferencia> t : transferencias.entrySet()) {
            modelo = buscartransferencia(transferencias);
            if (modelo.getCantidadtransac() >= 3) {
                modelo.setEstado(Estado.VALIDADA);
                //Cuando las validaciones sean mayor a 3 se pasa al archivo de transacciones validadas.
                sesion.guardarTransferenciaArchivo(modelo);
            } else if (modelo.getCantidadtransac() <= 3) {
                modelo.setCantidadtransac(modelo.getCantidadtransac() + 1);
                /// Se añade una validación
            }
        }
    }

    ///Busca usuario en la lista por UUID
    public Billetera buscarBilleteraPorUUID(HashMap<UUID, Billetera> billeteras) {
        UUID id;
        Scanner Aux = new Scanner(System.in);
        id = UUID.fromString(Aux.nextLine());

        Billetera b = billeteras.get(id);

        return b;
    }

    ///Transeferir de un usuario insertado por teclado a otro.
    public Transferencia transferir(TransferenciaLogica t1, float monto, Usuario actualUsuario, HashMap<UUID, Billetera> billeteras, HashMap<UUID, Usuario> usuarios) {
        Billetera u1 = billeteras.get(actualUsuario.getBilletera());
        System.out.print(" ========  Ingrese  el N°de billetera (UUID) del destinatario ========: ");
        Billetera u2 = t1.buscarBilleteraPorUUID(billeteras);
        //Actualiza saldos luego de transacción , en los objetos y el saldo en el  hashmap de billeteras.
        billeteras.get(u1.getIdBilletera()).setSaldo(u1.getSaldo() - monto);
        billeteras.get(u2.getIdBilletera()).setSaldo(u2.getSaldo() + monto);
        modelo.setCantidadtransac(modelo.getCantidadtransac() + 1);
        modelo = new Transferencia(usuarios.get(u1.getIdBilletera()), usuarios.get(u2.getIdBilletera()), modelo.getCantidadtransac(), monto, Estado.NOVALIDADA);

        if (modelo.getCantidadtransac() >= 3) {
            modelo.setEstado(Estado.VALIDADA);
        } else {
            System.out.println("Transacción aún pendiente de validar");
        }
        return modelo;
    }

}
package Logica;

import Exceptions.InvalidOptionException;
import Modelos.Billetera;
import Modelos.Estado;
import Modelos.Transferencia;
import Modelos.Usuario;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class TransferenciaLogica extends Transferencia implements Serializable {

    Transferencia modelo = new Transferencia();

    public TransferenciaLogica() {}

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

        Scanner Aux = new Scanner(System.in);
        UUID id = UUID.fromString(Aux.nextLine());

        Transferencia b = trans.get(id);

        return b;
    }

    ///Busca usuario en la lista por UUID
    public Billetera buscarBilleteraPorUUID(HashMap<UUID, Billetera> billeteras) {

        Scanner Aux = new Scanner(System.in);
        UUID id = UUID.fromString(Aux.nextLine());

        Billetera b = billeteras.get(id);

        return b;
    }

    /// VALIDAR TRANSFERENCIA
///Tendria que mostrar por pantalla los datos de la transferencia
    public void validar(SesionLogica sesion,HashMap<UUID, Transferencia> transferencias) throws InvalidOptionException {
        modelo = buscartransferencia(transferencias);
        for (Map.Entry<UUID, Transferencia> t : transferencias.entrySet()) {

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

    ///Transeferir de un usuario insertado por teclado a otro.
    public Transferencia transferir(SesionLogica sesion, TransferenciaLogica t1, float monto, Usuario actualUsuario, HashMap<UUID, Billetera> billeteras, HashMap<UUID, Usuario> usuarios) throws InvalidOptionException {

        Billetera u1 = billeteras.get(actualUsuario.getBilletera());

        System.out.print(" ========  Ingrese  el N°de billetera (UUID) del destinatario ========: ");
        Billetera u2 = t1.buscarBilleteraPorUUID(billeteras);
        //Actualiza saldos luego de transacción , en los objetos y el saldo en el  hashmap de billeteras.

        if (monto>=u1.getSaldo()) {
            u1.setSaldo(u1.getSaldo() - monto);
            sesion.aniadirBilletera(u1.getIdBilletera(), u1);
        }
        else {
            System.out.println("Monto insuficiente");
        }

        u2.setSaldo(u2.getSaldo() + monto);
        sesion.aniadirBilletera(u2.getIdBilletera(), u2);
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
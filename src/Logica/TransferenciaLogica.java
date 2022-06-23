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

    ///Transeferir de un usuario insertado por teclado a otro.
    public Transferencia transferir(SesionLogica sesion, TransferenciaLogica t1, float monto, Usuario actualUsuario, HashMap<UUID, Billetera> billeteras, HashMap<UUID, Usuario> usuarios) {
        modelo = t1;
        Billetera u1 = billeteras.get(actualUsuario.getBilletera());
        System.out.print(" ========  Ingrese  el N°de billetera (UUID) del destinatario ========: ");
        Billetera u2 = t1.buscarBilleteraPorUUID(billeteras);
        //Actualiza saldos luego de transacción , en los objetos y el saldo en el  hashmap de billeteras.
        if (monto>0) {
            if (u1.getSaldo() >= monto) {
                u1.setSaldo(u1.getSaldo() - monto); sesion.aniadirBilletera(u1);
                u2.setSaldo(u2.getSaldo() + monto); sesion.aniadirBilletera(u2);
            }            else{ System.out.println("Fondos insuficientes");return null;
            }
        }  else {            System.out.println("Error de transaccion");
            return null;
        }
        modelo.setValidaciones(modelo.getValidaciones() + 1);
        modelo = new Transferencia(usuarios.get(u1.getIdBilletera()), usuarios.get(u2.getIdBilletera()), modelo.getValidaciones(), monto, Estado.NOVALIDADA);
        if (modelo.getValidaciones() >= 3) {
            modelo.setEstado(Estado.VALIDADA);
        } else {
            System.out.println("Transacción aún pendiente de validar");
        }        sesion.guardarTransferenciaArchivo(modelo);
        return modelo; }

    // Busca transferencia por UUID para validarla
    public Transferencia buscartransferencia(HashMap<UUID, Transferencia> trans) {
        System.out.println("\nIngrese el UUID de transacción que desea validar: ");
        Scanner Aux = new Scanner(System.in);
        UUID id = UUID.fromString(Aux.nextLine());

        Transferencia b = trans.get(id);
        if (id!=null) {
            return b;
        }
        else{
            System.out.println("No se encontro la transferencia");
            return null;
        }
    }

    ///Busca usuario en la lista por UUID
    public Billetera buscarBilleteraPorUUID(HashMap<UUID, Billetera> billeteras) {

        Scanner Aux = new Scanner(System.in);
        UUID id = UUID.fromString(Aux.nextLine());

        Billetera b = billeteras.get(id);
        if (id!=null) {
            System.out.println("Billetera encontrada"+b);
            return b;
        }
        else{
            System.out.println("No se encontro la billetera");
            return null;
        }
    }

    /// VALIDAR TRANSFERENCIA

        public void validar (SesionLogica sesion, HashMap < UUID, Transferencia > transferencias) throws
        InvalidOptionException {
                        modelo = buscartransferencia(transferencias);
                if (modelo.getValidaciones() >= 3) {
                    modelo.setEstado(Estado.VALIDADA);
                    System.out.println("La transacción ya está validada");
                    //Cuando las validaciones sean mayor a 3 se pasa al archivo de transacciones validadas.
                    sesion.guardarValidacionArchivo(modelo);
                } else if (modelo.getValidaciones() <= 3) {
                    modelo.setValidaciones(modelo.getValidaciones() + 1);
                    System.out.println("se ha añadido 1 validación");
                    /// Se añade una validación
                }
                sesion.guardarTransferenciaArchivo(modelo);
    }

        ///Método transacciones pendientes de validar
        public HashMap<UUID, Transferencia> pendientesValidacion (HashMap < UUID, Transferencia > transferencias){
            for (Map.Entry<UUID, Transferencia> t : transferencias.entrySet()) {
                if (t.getValue().getEstado().equals(Estado.NOVALIDADA)) {
                    System.out.println(t);
                } else {
                    System.out.println("");
                }
            }
            return transferencias;
        }
}

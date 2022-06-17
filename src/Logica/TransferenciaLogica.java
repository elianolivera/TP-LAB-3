package Logica;

import Modelos.Billetera;
import Modelos.Estado;
import Modelos.Transferencia;
import Modelos.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.Serializable;
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
    public Transferencia buscartransferencia(List<Transferencia> transferencias) {
        System.out.println("Ingrese el UUID de la transferencia a validar: \n");
        String UUIDt;
        Scanner id = new Scanner(System.in);
        UUIDt = id.nextLine();
        for (Transferencia transf : transferencias) {
            if (transf != null && transf.getUUIDtransaccion().equals(UUIDt)) {
                return transf;
            }
        }
        return null;
    }

    /// VALIDAR TRANSFERENCIA
    public void validar(List<Transferencia> transferencias) {

        modelo = buscartransferencia(transferencias);
        if (modelo.getCantidadtransac() >= 3) {
            modelo.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        } else if (modelo.getCantidadtransac() <= 3) {
            modelo.setCantidadtransac(modelo.getCantidadtransac() + 1);
            /// Se añade una validación
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
    public Transferencia transferir(TransferenciaLogica t1, float monto, Usuario actualUsuario, HashMap<UUID, Billetera> billeteras, HashMap<UUID, Usuario> usuarios, List<Transferencia> transferencias) {
        String nombre = null; //SesionLogica sesion= null;
        Billetera u1 = billeteras.get(actualUsuario.getBilletera());
        System.out.print(" ========  Ingrese  el UUID del destinatario ========: ");
        Billetera u2 = t1.buscarBilleteraPorUUID(billeteras);
        //Actualiza saldos luego de transacción , en los objetos y el hashmap.
        u1.setSaldo(u1.getSaldo() - monto); //sesion.aniadirBilletera(u1.getIdBilletera(), u1);
        u2.setSaldo(u2.getSaldo() + monto); //sesion.aniadirBilletera(u2.getIdBilletera(), u2);
        modelo.setCantidadtransac(modelo.getCantidadtransac() + 1);
        modelo = new Transferencia(usuarios.get(u1.getIdBilletera()), usuarios.get(u2.getIdBilletera()), modelo.getCantidadtransac(), monto, Estado.NOVALIDADA);
        if (modelo.getCantidadtransac() >= 3) {
            modelo.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
            guardarTransferenciaArchivo(modelo);
        } else {
            System.out.println("Transacción aún pendiente de validar");
        }
        return modelo;
    }

    /// Guarda la transferencia en el archivo
    public void guardarTransferenciaArchivo(Transferencia t) {
        File file = new File("./Transferencias.json");
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



    /// Mostrar transferencias activas
    public void mostrarTransferenciasActivas(Transferencia t) {
        File file = new File("./Transferencias.json");
        ObjectMapper mapper = new ObjectMapper();

        if (file.exists()) {
            try {
                mapper.readValue(file,Transferencia.class);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
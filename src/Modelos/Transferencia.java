package Modelos;


import java.io.File;
import java.util.*;

public class Transferencia {
    protected User us;
    protected  User receptor;
    public UUID UUIDtransaccion;
    protected  int cantidadtransac;
    protected  double monto;
    protected Estado estado;

    public Transferencia() {
    }

    public Transferencia(UUID UUIDtransaccion,User us, User receptor, int cantidadtransac, double monto, Estado estado) {
        this.UUIDtransaccion = UUID.randomUUID();
        this.us = us;
        this.receptor = receptor;
        this.cantidadtransac = cantidadtransac;
        this.monto = monto;
        this.estado = estado;
    }
    // Busca transferencia por UUID para validarla
    public Transferencia buscartransferencia(List<Transferencia>transferencias) {
        System.out.println("Ingrese el UUID de la transferencia a validar: \n");
        String UUIDt;
        Scanner id = new Scanner(System.in);
        UUIDt=id.nextLine();
        for (Transferencia  transf :transferencias) {
            if (transf != null && transf .getUUIDtransaccion().equals(UUIDt)) {
                return transf;
            } }
        return null;
    }

    /// VALIDAR TRANSFERENCIA
    public void validar (List<Transferencia>transferencias){

        Transferencia t1= new Transferencia();
        t1= t1.buscartransferencia(transferencias);
        if (t1.getCantidadtransac()>=3) {
            t1.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        }else if (t1.getCantidadtransac()<=3){
            t1.setCantidadtransac(t1.getCantidadtransac()+1);
            /// Se añade una validación
        }
    }

    ///Busca usuario en la lista por DNI
    /*public User buscarUsuarioPorDNI(HashMap<String, UUID> usuariosLista) {
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
    public Transferencia transferir(Transferencia t1, float monto, HashMap<UUID, List<String>> usuariosLista, List<Transferencia>transferencias) {
        String nombre = null;
        System.out.print(" ========  Ingrese su DNI   ========: ");
        //Billetera u1 = (Billetera) t1.buscarUsuarioPorDNI(usuariosLista);
        System.out.print(" ========  Ingrese  el DNI a quien va a transferir ========: ");
        //Billetera u2 = (Billetera) t1.buscarUsuarioPorDNI(usuariosLista);
        //u1.setSaldo(u1.getSaldo() - monto);
        //u2.setSaldo(u2.getSaldo() + monto);
        t1.setCantidadtransac(t1.getCantidadtransac() + 1);
        UUIDtransaccion = UUID.randomUUID();
        //t1 = new Transferencia(UUIDtransaccion,u1, u2, t1.getCantidadtransac(), monto, Estado.NOVALIDADA);
        if (t1.getCantidadtransac() >= 3) {
            t1.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        }return t1; }

    ///Pasar transferencia a archivo


    public Transferencia setUs(User us) {
        this.us = us;
        return this;
    }

    public User getReceptor() {
        return receptor;
    }

    public Transferencia setReceptor(User receptor) {
        this.receptor = receptor;
        return this; }

    public UUID getUUIDtransaccion() {
        return UUIDtransaccion;
    }

    public int getCantidadtransac() {
        return cantidadtransac;
    }

    public Transferencia setCantidadtransac(int cantidadtransac) {
        this.cantidadtransac = cantidadtransac;
        return this;
    }
    public double getMonto() {
        return monto;
    }

    public Transferencia setMonto(double monto) {
        this.monto = monto;
        return this;
    }

    public Estado getEstado() {
        return estado;
    }

    public Transferencia setEstado(Estado estado) {
        this.estado = estado;
        return this;
    }
    @Override
    public String toString() {
        return "Transferencia{" +
                "us=" + us.getNombre() +
                ", receptor=" + receptor.getNombre() +
                ", UUIDtransaccion=" + UUIDtransaccion +
                ", cantidadtransac=" + cantidadtransac +
                ", monto Transferido=" + monto +
                ", estado=" + estado +
                '}';
    }
}


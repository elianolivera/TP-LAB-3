package Clases;


import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Transferencia {
    protected User us;
    protected  User receptor;
    public UUID UUIDtransaccion;
    protected  int cantidadtransac;
    protected  double monto;
    protected Estado estado;

    public Transferencia() {
    }

    public Transferencia(User us, User receptor, int cantidadtransac, double monto, Estado estado) {
        this.UUIDtransaccion = UUID.randomUUID();
        this.us = us;
        this.receptor = receptor;
        this.cantidadtransac = cantidadtransac;
        this.monto = monto;
        this.estado = estado;
    }

    /// VALIDAR TRANSFERENCIA
    public void validar (Transferencia t1){
        if (t1.getCantidadtransac()>=3) {
            t1.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        }else if (t1.getCantidadtransac()<=3){
            t1.setCantidadtransac(t1.getCantidadtransac()+1);
            /// Se añade una validación
        }
    }

    ///Busca usuario en la lista por DNI
    public User buscarUsuarioPorDNI(List<User>lista) {
        String dni;
        Scanner dniAux = new Scanner(System.in);
        dni= dniAux.nextLine();
        for (User usuario : lista) {
            if (usuario != null && usuario.getDni().equals(dni)) {
                return usuario;
            }
        }
        return null;
    }

    ///Transeferir de un usuario insertado por teclado a otro.
    public Transferencia transferir(Transferencia t1, float monto,List<User>lista) {
        String nombre = null;

        System.out.print(" ========  Ingrese su DNI   ========: ");
        Billetera u1 = (Billetera) t1.buscarUsuarioPorDNI(lista);

        System.out.print(" ========  Ingrese  el DNI a quien va a transferir ========: ");
        Billetera u2 = (Billetera) t1.buscarUsuarioPorDNI(lista);

        u1.setSaldo(u1.getSaldo() - monto);
        u2.setSaldo(u2.getSaldo() + monto);
        t1.setCantidadtransac(t1.getCantidadtransac() + 1);
        ///UUIDtransaccion = UUID.randomUUID();
        t1 = new Transferencia(u1, u2, t1.getCantidadtransac(), monto, Estado.NOVALIDADA);
        if (t1.getCantidadtransac() >= 3) {
            t1.setEstado(Estado.VALIDADA);
            ///SE PASA AL ARCHIVO DE VALIDADAS
        }
        return t1; }

    public User getUs() {
        return us;
    }

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

    /*public Transferencia setUUIDtransaccion(UUID UUIDtransaccion) {
        this.UUIDtransaccion = UUIDtransaccion;
        return this;
    }*/
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


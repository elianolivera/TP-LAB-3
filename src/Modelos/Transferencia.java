package Modelos;


import java.io.Serializable;
import java.util.UUID;

public class Transferencia implements Serializable {
    protected Usuario us;
    protected Usuario receptor;
    public UUID UUIDtransaccion;
    protected  int cantidadtransac;
    protected  double monto;
    protected Estado estado;

    public Transferencia() {
    }

    public Transferencia(Usuario us, Usuario receptor, int cantidadtransac, double monto, Estado estado) {
        this.UUIDtransaccion = UUID.randomUUID();
        this.us = us;
        this.receptor = receptor;
        this.cantidadtransac = cantidadtransac;
        this.monto = monto;
        this.estado = estado;
    }


    public Usuario getUs() {
        return us;
    }

    public Transferencia setUs(Usuario us) {
        this.us = us;
        return this;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public Transferencia setReceptor(Usuario receptor) {
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


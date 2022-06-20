package Modelos;


import java.util.UUID;

public class Transferencia  {
    protected Usuario us;
    protected Usuario receptor;
    public UUID UUIDtransaccion;
    protected int validaciones;
    protected double monto;
    protected Estado estado;

    public Transferencia() {
    }

    public Transferencia(Usuario us, Usuario receptor, int validaciones, double monto, Estado estado) {
        this.UUIDtransaccion = UUID.randomUUID();
        this.us = us;
        this.receptor = receptor;
        this.validaciones = validaciones;
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

    public Transferencia setUUIDtransaccion(UUID UUIDtransaccion) {
        this.UUIDtransaccion = UUIDtransaccion;
        return this;
    }

    public int getValidaciones() {
        return validaciones;
    }

    public Transferencia setValidaciones(int validaciones) {
        this.validaciones = validaciones;
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
        return "\nTransferencia:" +
                "\n Usuario emisor=" + us.getNombre() +
                "\n Usuario receptor=" + receptor.getNombre() +
                "\n ID de la transaccion=" + UUIDtransaccion +
                "\n Validaciones=" + validaciones +
                "\n Monto Transferido=" + monto +
                "\n Estado=" + estado;
    }
}


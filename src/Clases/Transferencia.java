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

    ///busca usuario en la lista
    public User buscarCliente(String nombre, List<User>lista) {
        Scanner clientes = new Scanner(System.in);
        nombre= clientes.nextLine();
        for (User cliente : lista) {
            if (cliente != null && cliente.getNombre().equals(nombre)) {
                return cliente; }

        }
        return null;
    }

    public Transferencia(User us, User receptor, UUID UUIDtransaccion, int cantidadtransac, double monto, Estado estado) {
        this.us = us;
        this.receptor = receptor;
        this.UUIDtransaccion = UUIDtransaccion;
        this.cantidadtransac = cantidadtransac;
        this.monto = monto;
        this.estado = estado;
    }




    public User getReceptor() {
        return receptor;
    }

    public Transferencia setReceptor(User receptor) {
        this.receptor = receptor;
        return this;
    }

    public Estado getEstado() {
        return estado;
    }

    public Transferencia setEstado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public User getUs() {
        return us;
    }

    public Transferencia setUs(User us) {
        this.us = us;
        return this;
    }

    public UUID getUUIDtransaccion() {
        return UUIDtransaccion;
    }

    public Transferencia setUUIDtransaccion(UUID UUIDtransaccion) {
        this.UUIDtransaccion = UUIDtransaccion;
        return this;
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


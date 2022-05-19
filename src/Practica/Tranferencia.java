package Practica;

public class Tranferencia {
    User us;
    int UUIDtransaccion;
    int cantidadtransac;
    double monto;


    public Tranferencia( ) {


    }
    public Tranferencia(User us, int UUIDtransaccion, int cantidadtransac, double monto) {

        this.us = us;
        this.UUIDtransaccion = UUIDtransaccion;
        this.cantidadtransac = cantidadtransac;
        this.monto = monto;
    }

    public void transferir(Tranferencia t1,User u1 , User u2,float monto){

        u1.setSaldo(u1.getSaldo()-monto);
        u2.setSaldo(u2.getSaldo()+monto);
        t1.setCantidadtransac(t1.getCantidadtransac()+1);
    }



    public User getUs() {
        return us;
    }

    public Tranferencia setUs(User us) {
        this.us = us;
        return this;
    }

    public int getUUIDtransaccion() {
        return UUIDtransaccion;
    }

    public Tranferencia setUUIDtransaccion(int UUIDtransaccion) {
        this.UUIDtransaccion = UUIDtransaccion;
        return this;
    }

    public int getCantidadtransac() {
        return cantidadtransac;
    }

    public Tranferencia setCantidadtransac(int cantidadtransac) {
        this.cantidadtransac = cantidadtransac;
        return this;
    }

    public double getMonto() {
        return monto;
    }

    public Tranferencia setMonto(double monto) {
        this.monto = monto;
        return this;
    }

    @Override
    public String toString() {
        return "Tranferencia{" +
                "us=" + us +
                ", UUIDtransaccion=" + UUIDtransaccion +
                ", cantidadtransac=" + cantidadtransac +
                ", monto=" + monto +
                '}';
    }
}


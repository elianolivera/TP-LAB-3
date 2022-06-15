package Modelos;

import java.io.Serializable;

public  class Billetera extends Usuario implements Serializable {

    private double saldo;

    public Billetera() {}

    public Billetera(String nombre, String apellido, String dni, String fechaDeNacimiento, String email, String password) {
        super(nombre, apellido, dni, fechaDeNacimiento, email, password);
        this.saldo = 100;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n saldo=" + saldo;
    }

}

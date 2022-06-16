package Modelos;

import java.io.Serializable;
import java.util.UUID;

public  class Billetera implements Serializable {

    private double saldo;
    protected UUID idBilletera;

    public Billetera() {
        this.saldo = 100;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public UUID getIdBilletera() {
        return idBilletera;
    }

    public void setIdBilletera(UUID idBilletera) {
        this.idBilletera = idBilletera;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n saldo=" + saldo;
    }

}

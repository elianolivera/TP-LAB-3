package Modelos;

import java.util.UUID;

public  class Billetera {


    private double saldo;
    protected UUID idBilletera;

    public Billetera() {
        this.saldo = 100;
        this.idBilletera = idBilletera;
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
        return  "\n Saldo=" + saldo +
                "\n Id de Billetera=" + idBilletera;
    }
}

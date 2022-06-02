package Clases;

public  class Billetera extends User{

    protected double saldo;

    public Billetera(double saldo) {
        super(billetera);
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Billetera{" +
                "saldo=" + saldo +
                '}';
    }
}

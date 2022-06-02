package Clases;

public  class Billetera extends User{

    protected double saldo;

    public Billetera(String nombre, String apellido, String dni, String fechaDeNacimiento, String email, String password, double saldo) {
        super(nombre, apellido, dni, fechaDeNacimiento, email, password);
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
        return "Billetera{" + super.toString() +
                "saldo=" + saldo +
                '}';
    }
}

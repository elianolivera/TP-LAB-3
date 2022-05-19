package Practica;

public class User {
    protected int UUID;
    protected String Nombre;
    protected String email;
    protected float saldo;

    public User(int UUID, String nombre, String email, float saldo) {
        this.UUID = UUID;
        Nombre = nombre;
        this.email = email;
        this.saldo = saldo;
    }

    public int getUUID() {
        return UUID;
    }

    public User setUUID(int UUID) {
        this.UUID = UUID;
        return this;
    }

    public String getNombre() {
        return Nombre;
    }

    public User setNombre(String nombre) {
        Nombre = nombre;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public float getSaldo() {
        return saldo;
    }

    public User setSaldo(float saldo) {
        this.saldo = saldo;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "UUID=" + UUID +
                ", Nombre='" + Nombre + '\'' +
                ", email='" + email + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

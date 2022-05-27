package Clases;

import java.util.Objects;
import java.util.UUID;

public class User {
    protected UUID id;
    protected String nombre;
    protected String email;
    protected String password;
    protected float saldo;

    public User(String nombre, String email,String password, float saldo) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.saldo = saldo;
    }

    public UUID getUUID() {
        return id;
    }

    public User setUUID(UUID id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public User setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "UUID=" + id +
                ", Nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}

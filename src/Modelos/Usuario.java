package Modelos;

import java.io.Serializable;
import java.util.UUID;

// Si billetera va a heredar de esto, no deberia ser instanciada, por eso el abstract.
public class Usuario {
    protected UUID billetera;
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String fechaDeNacimiento;
    protected String email;
    protected String password;

    public Usuario() {}

    public Usuario(String nombre, String apellido, String dni, String fechaDeNacimiento, String email, String password) {
        this.billetera=UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.email = email;
        this.password = password;
    }

    public UUID getBilletera() {
        return billetera;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {

        return

                "\nN de billetera=" + billetera +
                "\n Nombre='" + nombre +
                "\n Apellido='" + apellido +
                "\n Fecha De Nacimiento='" + fechaDeNacimiento +
                "\n Correo electrónico='" + email +
                "\n Password='" + password;
    }
}

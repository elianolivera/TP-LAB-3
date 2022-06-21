package Modelos;


import java.util.UUID;

public class Usuario {
    protected UUID billetera;
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String fechaDeNacimiento;
    protected String email;
    protected String password;

    public Usuario() {}

    public Usuario(UUID billetera,String nombre, String apellido, String dni, String fechaDeNacimiento, String email, String password) {
        this.billetera= billetera;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.email = email;
        this.password = password;
    }

    public void setBilletera(UUID billetera) {
        this.billetera = billetera;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return usuario.billetera == billetera && usuario.nombre == nombre && usuario.apellido == apellido && usuario.dni == dni && usuario.fechaDeNacimiento == fechaDeNacimiento && usuario.email == email && usuario.password == password;
    }

    @Override
    public int hashCode() {
        int resultado= Short.hashCode(Short.parseShort(nombre));
        resultado=31 * resultado + Short.hashCode(Short.parseShort(apellido));
        resultado=31 * resultado + Short.hashCode(Short.parseShort(dni));
        resultado=31 * resultado + Short.hashCode(Short.parseShort(fechaDeNacimiento));
        resultado=31 * resultado + Short.hashCode(Short.parseShort(email));
        resultado=31 * resultado + Short.hashCode(Short.parseShort(password));
        return resultado;
    }

    @Override
    public String toString() {

        return  "\n N° de billetera=" + billetera +
                "\n Nombre='" + nombre +
                "\n Apellido='" + apellido +
                "\n Fecha De Nacimiento='" + fechaDeNacimiento +
                "\n Correo electrónico='" + email +
                "\n Password='" + password;
    }
}

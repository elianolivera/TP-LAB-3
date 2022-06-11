package Modelos;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.io.Serializable;
import java.util.*;


public final class Sesion implements Serializable {

    private static final long serialVersionUID = -6719022570919861969L;
    HashMap<String, UUID> usuariosLoguin = new HashMap<>();
    private List<Transferencia> transferencias = new ArrayList<>();
    private List<Billetera> billeteras = new ArrayList<>();
    private UUID idUsuarioActivo;



    public Sesion() {
        this.idUsuarioActivo = null;
    }

    public UUID getIdUsuarioActivo() {
        return idUsuarioActivo;
    }

    public void setIdUsuarioActivo(UUID idUsuarioActivo) {
        this.idUsuarioActivo = idUsuarioActivo;
    }

    public User registrarUsuario() {

        String nombre,apellido,dni,fechaDeNacimiento,email,password;
        Scanner teclado = new Scanner(System.in);

        System.out.println("\nIngreso de Datos.");
        System.out.println("\nIngrese su Nombre: ");
        nombre=teclado.nextLine();

        System.out.println("\nIngrese su Apellido: ");
        apellido=teclado.nextLine();

        System.out.println("\nIngrese su Numero de documento: ");
        dni=teclado.nextLine();
        if(usuariosLoguin.containsKey(dni)){
            System.out.println("El numero de Documento ya existe");
            return null;
        }

        System.out.println("\nIngrese su Fecha de nacimiento: ");
        fechaDeNacimiento=teclado.nextLine();

        System.out.println("\nIngrese su Correo electronico: ");
        email=teclado.nextLine();
        if(usuariosLoguin.containsKey(email)){
            System.out.println("El Correo electronico ya existe");
            return null;
        }

        System.out.println("\nIngrese su password: ");
        password=teclado.nextLine();

        Billetera billetera = new Billetera(nombre, apellido, dni, fechaDeNacimiento, email, password);
        UUID id=billetera.billetera;
        System.out.println("Su ID para loguearse es: " + id + ". Guardalo!");

        guardarBilleteraEnArchivo(billetera);

        return billetera;
    }

    public UUID loguearUsuario(String email, String password, UUID billetera) {
        for(Map.Entry<String,UUID> entry: usuariosLoguin.entrySet()) {
          if(email.equals(entry.getKey()) && password.equals(entry.getKey()) && billetera.equals(entry.getKey())) {///Validacion de correo y contraseña
               setIdUsuarioActivo(billetera);
               return billetera;
            }
        }

        return null;
    }

    public void guardarBilleteraEnArchivo(Billetera billetera) {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();
        billeteras.add(billetera);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, this.billeteras);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void archivoALista() {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();

        if(file.exists()) {
            try {
                this.billeteras = mapper.readValue(file, new TypeReference<List<Billetera>>(){});
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void aniadirUsuario(String email, UUID id) {
        this.usuariosLoguin.put(email,id);
    }

    public HashMap<String, UUID> getUsuariosLoguin() {
        return usuariosLoguin;
    }

    public List<Transferencia> aniadirTransferencia(Transferencia t) {
        this.transferencias.add(t);
        return transferencias;
    }

    public List<Transferencia> getTransferencias() {
        return transferencias;
    }

    public void finalizarSesion() {
        // Paso los nuevos usuarios registrados a el archivo.
        System.exit(0);
    }
}

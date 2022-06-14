package Modelos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.io.Serializable;
import java.util.*;


public final class Sesion implements Serializable {

    private static final long serialVersionUID = -6719022570919861969L;
    HashMap<UUID, List<String>> usuariosLoguin = new HashMap<>();

    private List<Transferencia> transferencias = new ArrayList<>();
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
        List<String> datos = new ArrayList<>();
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

        datos.add(nombre);
        datos.add(apellido);
        datos.add(dni);
        datos.add(fechaDeNacimiento);
        datos.add(email);
        datos.add(password);

        guardarBilleteraEnArchivo(id, datos);

        return billetera;
    }

    public UUID loguearUsuario(String email, String password, UUID billetera) {
        for(Map.Entry<UUID,List<String>> entry: usuariosLoguin.entrySet()) {
            List<String> values = entry.getValue();
            if(billetera.equals(entry.getKey())) {
                if(email.equals(values.get(4)) && password.equals(values.get(5))) {///Validacion de correo y contraseña
                    setIdUsuarioActivo(billetera);
                    return billetera;
                }
            }
        }
        return null;
    }

    public void guardarBilleteraEnArchivo(UUID id, List<String> datos) {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();
        aniadirUsuario(id, datos);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, this.usuariosLoguin);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void archivoALista() {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();

        if(file.exists()) {
            try {
                this.usuariosLoguin = mapper.readValue(file, new TypeReference<Map<UUID, List<String>>>(){});
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void aniadirUsuario(UUID id, List<String> datos) {
        this.usuariosLoguin.put(id,datos);
    }

    public HashMap<UUID, List<String>> getUsuariosLoguin() {
        return usuariosLoguin;
    }
    public User getUs() {
        return us;
    }

    public void guardarTransferenciaenARchivo(File file,List<Transferencia> transferencias ) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            if(!file.exists()){
                file.createNewFile();
            }   mapper.writeValue(file, transferencias);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }  }

    public List<Transferencia> aniadirTransferencia(Transferencia t,File file) {
        this.transferencias.add(t);
      guardarTransferenciaenARchivo(file,transferencias);
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

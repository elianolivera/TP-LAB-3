package Logica;


import Modelos.Billetera;
import Modelos.Sesion;
import Modelos.Transferencia;
import Modelos.Usuario;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.UUID;


public class SesionLogica implements Serializable {

    static Sesion modelo=new Sesion();
    private static final long serialVersionUID = -6719022570919861969L;
    static HashMap<String, Billetera> usuariosLoguin = new HashMap<>();
    private List<Transferencia> transferencias = new ArrayList<>();

    public static Usuario registrarUsuario() {

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
        UUID id= modelo.getIdUsuarioActivo();
        System.out.println("Su ID para loguearse es: " + id + ". Guardalo!");

        guardarBilleteraEnArchivo(billetera);//Se crea el Archivo de Usuarios

        return billetera;
    }

    public static UUID loguearUsuario(Billetera usuario) {
        for(Map.Entry<String,Billetera> entry: usuariosLoguin.entrySet()) {
            if(usuario.getEmail().equals(entry.getKey()) && usuario.getPassword().equals(entry.getKey()) && usuario.getBilletera().equals(entry.getKey())) {///Validacion de correo, contraseña e ID
                modelo.setIdUsuarioActivo(usuario.getBilletera());
                return usuario.getBilletera();
            }
        }

        return null;
    }

    public static void guardarBilleteraEnArchivo(Billetera billetera) {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();
        aniadirUsuario(billetera.getEmail(), billetera);
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
                this.usuariosLoguin = mapper.readValue(file, new TypeReference<Map<String, UUID>>(){});
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void aniadirUsuario(String email, Billetera usuario) {
        usuariosLoguin.put(email,usuario);
    }

    public HashMap<String, Billetera> getUsuariosLoguin() {
        return usuariosLoguin;
    }

    public List<Transferencia> aniadirTransferencia(Transferencia t) {
        this.transferencias.add(t);
        return transferencias;
    }

    public List<Transferencia> getTransferencias() {
        return transferencias;
    }

    public static void finalizarSesion() {
        // Paso los nuevos usuarios registrados a el archivo.
        System.exit(0);
    }
}

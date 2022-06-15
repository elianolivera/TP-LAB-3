package Logica;


import Modelos.Billetera;
import Modelos.Sesion;
import Modelos.Transferencia;
import Modelos.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.UUID;


public class SesionLogica implements Serializable {

    static Sesion modelo=new Sesion();
    private static final long serialVersionUID = -6719022570919861969L;
    static HashMap<UUID, Billetera> usuariosLoguin = new HashMap<>();
    private List<Transferencia> transferencias = new ArrayList<>();
    private Billetera billeteraActiva;

    public SesionLogica(){
        this.billeteraActiva = null;
    }

    public Billetera registrarUsuario() {

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
        UUID id= billetera.getBilletera();
        System.out.println("Su ID para loguearse es: " + id + ". Guardalo!");

        guardarBilleteraEnArchivo(billetera);//Se crea el Archivo de Usuarios

        return billetera;
    }

    public Billetera loguearUsuario(String email, String password, UUID id) {
        for(Map.Entry<UUID,Billetera> entry: usuariosLoguin.entrySet()) {
            if(id.equals(entry.getKey()) && password.equals(entry.getValue().getPassword()) && email.equals(entry.getValue().getPassword())) {
                setBilleteraActiva(entry.getValue());
                return entry.getValue();
            }
        }

        return null;
    }

    public void guardarBilleteraEnArchivo(Billetera billetera) {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();
        aniadirUsuario(billetera.getBilletera(), billetera);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, usuariosLoguin);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void archivoALista() {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();

        if(file.exists()) {
            try {
          //  this.usuariosLoguin = mapper.readValue(file, new TypeReference<Map<UUID, Billetera>>(){});
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void aniadirUsuario(UUID id, Billetera billetera) {
        usuariosLoguin.put(id,billetera);
    }

    public HashMap<UUID, Billetera> getUsuariosLoguin() {
        return usuariosLoguin;
    }

    public List<Transferencia> aniadirTransferencia(Transferencia t) {
        this.transferencias.add(t);

        return transferencias;
    }

    public List<Transferencia> getTransferencias() {
        return transferencias;
    }

    public Billetera getBilleteraActiva() {
        return billeteraActiva;
    }

    public void setBilleteraActiva(Billetera billeteraActiva) {
        this.billeteraActiva = billeteraActiva;
    }

    public static void finalizarSesion() {
        // Paso los nuevos usuarios registrados a el archivo.
        System.exit(0);
    }
}

package Logica;


import Modelos.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.UUID;


public class SesionLogica implements Serializable {

    static Sesion modelo=new Sesion();
    private static final long serialVersionUID = -6719022570919861969L;
    private HashMap<UUID, Usuario> usuarios = new HashMap<>();
    private HashMap<UUID, Billetera> billeteras = new HashMap<>();
    private HashMap<UUID, Transferencia> transferencias = new HashMap<>();
    private Usuario usuarioActivo;

    public SesionLogica(){
        this.usuarioActivo = null;
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
        if(usuarios.containsValue(dni)){
            System.out.println("El numero de Documento ya existe");
            return null;
        }

        System.out.println("\nIngrese su Fecha de nacimiento: ");
        fechaDeNacimiento=teclado.nextLine();

        System.out.println("\nIngrese su Correo electronico: ");
        email=teclado.nextLine();
        if(usuarios.containsValue(email)){
            System.out.println("El Correo electronico ya existe");
            return null;
        }

        System.out.println("\nIngrese su password: ");
        password=teclado.nextLine();

        Usuario user = new Usuario(nombre, apellido, dni, fechaDeNacimiento, email, password);
        UUID id= user.getBilletera();

        Billetera billeteraUser = new Billetera();
        billeteraUser.setIdBilletera(id);

        System.out.println("Su ID para loguearse es: " + id + ". Guardalo!");

        aniadirBilletera(billeteraUser.getIdBilletera(), billeteraUser);//Se crea el Archivo de Usuarios
        guardarUsuarioEnArchivo(user);

        return billeteraUser;
    }

    public Usuario loguearUsuario(String email, String password, UUID id) {
        Usuario user = usuarios.get(id);

        if(user != null && (user.getEmail().equals(email) && user.getPassword().equals(password))) {
            setUsuarioActivo(user);
            return user;
        }

        return null;
    }

    private void guardarBilleterasEnArchivo() {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, this.billeteras);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void guardarUsuarioEnArchivo(Usuario usuario) {
        File file = new File("./usuarios.json");
        ObjectMapper mapper=new ObjectMapper();
        aniadirUsuario(usuario.getBilletera(), usuario);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, usuarios);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void guardarTransferenciaArchivo(Transferencia transferencia) {
        File file = new File("./Transferencias.json");
        ObjectMapper mapper=new ObjectMapper();
        aniadirtransferencia(transferencia.getUUIDtransaccion(),transferencia);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, transferencias);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void archivoAMapBilleteras() {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();

        if(file.exists()) {
            try {
                this.billeteras = mapper.readValue(file, new TypeReference<Map<UUID, Billetera>>(){});
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void archivoAMapTransferencias() {
        File file = new File("./Transferencias.json");
        ObjectMapper mapper=new ObjectMapper();

        if(file.exists()) {
            try {
                this.transferencias = mapper.readValue(file, new TypeReference<Map<UUID, Transferencia>>(){});
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void archivoAMapUsuarios() {
        File file = new File("./usuarios.json");
        ObjectMapper mapper=new ObjectMapper();

        if(file.exists()) {
            try {
                this.usuarios = mapper.readValue(file, new TypeReference<Map<UUID, Usuario>>(){});
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }  }  }

    public double consultarActivos() {
        Billetera billeteraUsuarioActivo = billeteras.get(usuarioActivo.getBilletera());
        return billeteraUsuarioActivo.getSaldo();
    }

    public ArrayList<Transferencia> pendientesValidacion() {
        ArrayList<Transferencia> pendientes = new ArrayList<>();

        for (Map.Entry<UUID, Transferencia> t : transferencias.entrySet()) {
            if(t.getValue().getEstado().equals(Estado.NOVALIDADA)) {
                pendientes.add(t.getValue());
            }
        }

        return pendientes;
    }

    public void aniadirUsuario(UUID id, Usuario usuario) {
        this.usuarios.put(id,usuario);
    }

    public void aniadirBilletera(UUID id, Billetera billetera) {



        this.billeteras.put(id,billetera);
    }

    public HashMap<UUID, Usuario> getUsuariosLoguin() {
        return usuarios;
    }

    public HashMap<UUID, Billetera> getBilleteras() {
        return billeteras;
    }



    public HashMap<UUID, Transferencia> getTransferencias() {
        return transferencias;
    }
    public void aniadirtransferencia(UUID id,Transferencia transferencia) {

        this.transferencias.put(id,transferencia);
    }
    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public void finalizarSesion() {
        // Paso los nuevos usuarios registrados a el archivo.
        guardarBilleterasEnArchivo();
        System.exit(0);
    }
}

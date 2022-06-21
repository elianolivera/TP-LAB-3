package Logica;


import Exceptions.InvalidOptionException;
import Modelos.Billetera;
import Modelos.Transferencia;
import Modelos.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;


public class SesionLogica implements Serializable {

    ///static Sesion modelo=new Sesion();
    private static final long serialVersionUID = -6719022570919861969L;
    private HashMap<UUID, Usuario> usuarios = new HashMap<>();
    private HashMap<UUID, Billetera> billeteras = new HashMap<>();
    private HashMap<UUID, Transferencia> transferencias = new HashMap<>();
    private HashMap<UUID, Transferencia> transferenciasValidadas = new HashMap<>();
    private Usuario usuarioActivo;


    public SesionLogica() {
        this.usuarioActivo = null;
    }

    ///********************METODO REGISTRO DE USUARIO***********************************
    public Billetera registrarUsuario() throws InvalidOptionException {

        Usuario aux = new Usuario();
        Scanner teclado = new Scanner(System.in);
        Validator validator = new Validator();

        System.out.println("\nIngreso de Datos.");
        while(!validator.validAlphabetical(aux.getNombre())) {
            System.out.println("\nIngrese su Nombre: ");
            aux.setNombre(teclado.nextLine());
        }

        while(!validator.validAlphabetical(aux.getApellido())) {
            System.out.println("\nIngrese su Apellido: ");
            aux.setApellido(teclado.nextLine());
        }

        while(!validator.validDNI(aux.getDni())) {
            System.out.println("\nIngrese su Numero de documento: ");
            aux.setDni(teclado.nextLine());
        }

        while(!validator.validDate(aux.getFechaDeNacimiento())) {
            System.out.println("\nIngrese su Fecha de nacimiento (DD/MM/AAAA): ");
            aux.setFechaDeNacimiento(teclado.nextLine());
        }

        while(!validator.validEmail(aux.getEmail())) {
            System.out.println("\nIngrese su Correo electronico: ");
            aux.setEmail(teclado.nextLine());
        }

        while(validator.isEmpty(aux.getPassword())) {
            System.out.println("\nIngrese su password: ");
            aux.setPassword(teclado.nextLine());
        }

        System.out.println("\nDatos ingresados" + aux);

        UUID id = aux.getBilletera();

        System.out.println("Su ID para loguearse es: " + id + ". Guardalo!");
        Billetera billeteraUser = new Billetera();
        billeteraUser.setIdBilletera(id);

        aniadirBilletera(billeteraUser);
        guardarUsuarioEnArchivo(aux);

        return billeteraUser;

    }

    ///*****************************METODO LOGUEAR USUARIO********************************************
    public Usuario loguearUsuario(String email, String password, UUID id) {
        Usuario user = usuarios.get(id);

        if (user != null && (user.getEmail().equals(email) && user.getPassword().equals(password))) {
            setUsuarioActivo(user);
            return user;
        }

        return null;
    }

    ///**************************METODOS DE GUARDADO EN ARCHIVOS***************************************
    private void guardarBilleterasEnArchivo() {
        File file = new File("./billeteras.json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writeValue(file, this.billeteras);
        } catch (IOException e) {
            System.out.println("No se pudo completar la operacion." + e.getMessage());
        }
    }

    public void guardarUsuarioEnArchivo(Usuario usuario) {
        File file = new File("./usuarios.json");
        ObjectMapper mapper = new ObjectMapper();
        aniadirUsuario(usuario);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writeValue(file, usuarios);
        } catch (IOException e) {
            System.out.println("No se pudo completar la operacion." + e.getMessage());
        }
    }

    public void guardarTransferenciaArchivo (Transferencia t){
            File file = new File("./Transferencias.json");
            ObjectMapper mapper = new ObjectMapper();
            aniadirtransferencia(t.getUUIDtransaccion(), t);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                mapper.writeValue(file, transferencias);
            } catch (IOException e) {
                System.out.println("No se pudo completar la operacion." + e.getMessage());
            }
    }

    public void guardarValidacionArchivo (Transferencia t){
        File file = new File("./Validaciones.json");
        ObjectMapper mapper = new ObjectMapper();
        aniadirtransferenciaValidada(t.getUUIDtransaccion(), t);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writeValue(file, transferenciasValidadas);
        } catch (IOException e) {
            System.out.println("No se pudo completar la operacion." + e.getMessage());
        }
    }

    ///******************************************METODOS DE APERTURA DE ARCHIVOS*******************************
    public void archivoAMapBilleteras () {
            File file = new File("./billeteras.json");
            ObjectMapper mapper = new ObjectMapper();

            if (file.exists()) {
                try {
                    this.billeteras = mapper.readValue(file, new TypeReference<Map<UUID, Billetera>>(){});
                } catch (IOException e) {
                    System.out.println("No se pudo completar la operacion." + e.getMessage());
                }
            }
    }

    public void archivoAMapTransferencias () {
            File file = new File("./Transferencias.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            if (file.exists()) {
                try {
                    this.transferencias = mapper.readValue(file, new TypeReference<Map<UUID, Transferencia>>(){});
                } catch (IOException e) {
                    System.out.println("No se pudo completar la operacion." + e.getMessage());
                }
            }
    }

    public void archivoAMapUsuarios () {
            File file = new File("./usuarios.json");
            ObjectMapper mapper = new ObjectMapper();

            if (file.exists()) {
                try {
                    this.usuarios = mapper.readValue(file, new TypeReference<Map<UUID, Usuario>>(){});
                } catch (IOException e) {
                    System.out.println("No se pudo completar la operacion." + e.getMessage());
                }
            }
    }

    public void archivoAMapValidaciones () {
        File file = new File("./Validaciones.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        if (file.exists()) {
            try {
                this.transferenciasValidadas = mapper.readValue(file, new TypeReference<Map<UUID, Transferencia>>(){});
            } catch (IOException e) {
                System.out.println("No se pudo completar la operacion." + e.getMessage());
            }
        }
    }

    public double consultarActivos () {
            Billetera billeteraUsuarioActivo = billeteras.get(usuarioActivo.getBilletera());
            return billeteraUsuarioActivo.getSaldo();
    }

    public HashMap<UUID, Transferencia> getTransferenciasValidadas() {
        return transferenciasValidadas;
    }

    public void aniadirUsuario (Usuario usuario){
        this.usuarios.put(usuario.getBilletera(), usuario);
        }
        public void aniadirBilletera (Billetera aux){
        this.billeteras.put(aux.getIdBilletera(),aux);
        }

        public HashMap<UUID, Usuario> getUsuariosLoguin () {
            return usuarios;
        }

        public HashMap<UUID, Billetera> getBilleteras () {
            return billeteras;
        }

        public HashMap<UUID, Transferencia> getTransferencias () {
            return transferencias;
        }

        public void aniadirtransferencia (UUID id, Transferencia transferencia){
            this.transferencias.put(id, transferencia);
        }

        public void aniadirtransferenciaValidada (UUID id, Transferencia transferencia){
            this.transferenciasValidadas.put(id, transferencia);
        }

        public Usuario getUsuarioActivo () {
            return usuarioActivo;
        }

        public void setUsuarioActivo (Usuario usuarioActivo){
        this.usuarioActivo = usuarioActivo;
        }

        public void finalizarSesion () {
            // Paso los nuevos usuarios registrados a el archivo.
            guardarBilleterasEnArchivo();
            System.exit(0);
        }
    public void mostrarLogo(){
        if (!(new File("logo.txt")).exists()) {
            return;
        }try {        BufferedReader fEntrada = new BufferedReader(new FileReader(new File("logo.txt")));
            String linea=null;
            while ((linea=fEntrada.readLine()) != null) {
                System.out.println(linea);
            }
            fEntrada.close();
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo: " + e.getMessage());
        }}

    public void mostrarLogosaldo(){
        if (!(new File("saldo.txt")).exists()) {
            return;
        }try {        BufferedReader fEntrada = new BufferedReader(new FileReader(new File("saldo.txt")));
            String linea=null;
            while ((linea=fEntrada.readLine()) != null) {
                System.out.println(linea);
            }
            fEntrada.close();
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir en el archivo: " + e.getMessage());
        }}

}



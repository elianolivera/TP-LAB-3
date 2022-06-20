package Logica;


import Exceptions.InvalidOptionException;
import Modelos.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import Modelos.Billetera;


public class SesionLogica implements Serializable {

    ///static Sesion modelo=new Sesion();
    private static final long serialVersionUID = -6719022570919861969L;
    private HashMap<UUID, Usuario> usuarios = new HashMap<>();
    private HashMap<UUID, Billetera> billeteras = new HashMap<>();
    private HashMap<UUID, Transferencia> transferencias = new HashMap<>();
    private Usuario usuarioActivo;


    public SesionLogica(){
        this.usuarioActivo = null;
    }

///********************METODO REGISTRO DE USUARIO***********************************
    public Billetera registrarUsuario() throws InvalidOptionException {

        Usuario aux = new Usuario();
        Scanner teclado = new Scanner(System.in);
        String opcionMenuLoguin;

        while (aux==null) {
            System.out.println("\nIngreso de Datos.");
            System.out.println("\nIngrese su Nombre: ");
            aux.setNombre(teclado.nextLine());

            System.out.println("\nIngrese su Apellido: ");
            aux.setApellido(teclado.nextLine());

            System.out.println("\nIngrese su Numero de documento: ");
            aux.setDni(teclado.nextLine());
            if (usuarios.containsValue(aux.getDni())) {
                System.out.println("El numero de Documento ya existe");
                return null;
            }

            System.out.println("\nIngrese su Fecha de nacimiento: ");
            aux.setFechaDeNacimiento(teclado.nextLine());

            System.out.println("\nIngrese su Correo electronico: ");
            aux.setEmail(teclado.nextLine());
            if (usuarios.containsValue(aux.getEmail())) {
                System.out.println("El Correo electronico ya existe");
                return null;
            }

            System.out.println("\nIngrese su password: ");
            aux.setPassword(teclado.nextLine());

            System.out.println("\nDatos ingresados"+ aux);
            System.out.println("\nSon correctos?. N para corregir. Cualquier tecla para continuar");
            opcionMenuLoguin=teclado.nextLine();
            if(opcionMenuLoguin == "N"){
                    registrarUsuario();
            }

        }

        UUID id= aux.getBilletera();

        Billetera billeteraUser = new Billetera();
        billeteraUser.setIdBilletera(id);

        System.out.println("Su ID para loguearse es: " + id + ". Guardalo!");

        aniadirBilletera(billeteraUser);//Se crea el Archivo de Usuarios
        guardarUsuarioEnArchivo(user);

        return billeteraUser;
    }

    ///*****************************METODO LOGUEAR USUARIO///////////////////////////////////////////
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
        } catch(IOException e) {
            System.out.println("No se pudo completar la operacion."+e.getMessage());
        }
        finally {
            if (file!=null){
                file.close();
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
        } catch(IOException e) {
            System.out.println("No se pudo completar la operacion."+e.getMessage());
        }
        finally {
            if (file!=null){
                file.close();
            }
        }
    }

    public void guardarTransferenciaArchivo(Transferencia transferencia) {
    public void guardarTransferenciaArchivo(Transferencia t) {
        File file = new File("./Transferencias.json");
        ObjectMapper mapper=new ObjectMapper();
       aniadirtransferencia(t.getUUIDtransaccion(),t);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            mapper.writeValue(file, transferencias);
        } catch(IOException e) {
            System.out.println("No se pudo completar la operacion."+e.getMessage());
        }
        finally {
            if (file!=null){
                file.close();
            }
    }


    public void archivoAMapBilleteras() {
        File file = new File("./billeteras.json");
        ObjectMapper mapper=new ObjectMapper();

        if(file.exists()) {
            try {
                this.billeteras = mapper.readValue(file, new TypeReference<Map<UUID, Billetera>>(){});
            } catch(IOException e) {
                System.out.println("No se pudo completar la operacion."+e.getMessage());
            }
            finally {
                if (file!=null){
                    file.close();
                }
        }
    }

    public void archivoAMapTransferencias() {
        File file = new File("./Transferencias.json");

        ObjectMapper mapper=new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        if(file.exists()) {
            try {
                this.transferencias = mapper.readValue(file, new TypeReference<Map<UUID, Transferencia>>(){});
            } catch(IOException e) {
                System.out.println("No se pudo completar la operacion."+e.getMessage());
            }
            finally {
                if (file!=null){
                    file.close();
                }
        }
    }

    public void archivoAMapUsuarios() {
        File file = new File("./usuarios.json");
        ObjectMapper mapper=new ObjectMapper();

        if(file.exists()) {
            try {
                this.usuarios = mapper.readValue(file, new TypeReference<Map<UUID, Usuario>>(){});
            } catch(IOException e) {
                System.out.println("No se pudo completar la operacion."+e.getMessage());
            }
            finally {
                if (file!=null){
                    file.close();
                }
        }
    }

    public double consultarActivos() {
        Billetera billeteraUsuarioActivo = billeteras.get(usuarioActivo.getBilletera());
        return billeteraUsuarioActivo.getSaldo();
    }


    public void aniadirUsuario(UUID id, Usuario usuario) {
        this.usuarios.put(id,usuario);
    }

    public void aniadirBilletera(Billetera aux) {
            this.billeteras.put();
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

    public void aniadirtransferencia(UUID id,Transferencia transferencia) {this.transferencias.put(id,transferencia);}

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

    public void archivoAMapUsuarios() {
    }

    public void aniadirBilletera() {
    }

    public void aniadirBilletera() {
    }

    public void aniadirBilletera() {
    }

    public void finalizarSesion() {
    }
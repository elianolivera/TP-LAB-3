package Logica;


import Exceptions.InvalidOptionException;
import Modelos.Billetera;
import Modelos.Transferencia;
import Modelos.Usuario;
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


public class SesionLogica implements Serializable {

    ///static Sesion modelo=new Sesion();
    private static final long serialVersionUID = -6719022570919861969L;
    private HashMap<UUID, Usuario> usuarios = new HashMap<>();
    private HashMap<UUID, Billetera> billeteras = new HashMap<>();
    private HashMap<UUID, Transferencia> transferencias = new HashMap<>();
    private Usuario usuarioActivo;


    public SesionLogica() {
        this.usuarioActivo = null;
    }

    ///********************METODO REGISTRO DE USUARIO***********************************
    public Billetera registrarUsuario() throws InvalidOptionException {

        Usuario aux = new Usuario();
        Scanner teclado = new Scanner(System.in);

            System.out.println("\nIngreso de Datos.");
            aux.setBilletera(UUID.randomUUID());

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

            System.out.println("\nDatos ingresados" + aux);
            System.out.println("\nSon correctos?. 1 para corregir. 2 para continuar");
            int opcionMenuLoguin = teclado.nextInt();
            if (opcionMenuLoguin == 1) {
                System.exit(0);
            }
            System.out.println("Su ID para loguearse es: " + aux.getBilletera() + ". Guardalo!");

            Billetera billeteraUser = new Billetera();

            billeteraUser.setIdBilletera(aux.getBilletera());
            guardarUsuarioEnArchivo(aux);
            guardarBilleterasEnArchivo(billeteraUser);

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
    private void guardarBilleterasEnArchivo(Billetera billetera) {
        File file = new File("./billeteras.json");
        ObjectMapper mapper = new ObjectMapper();
        aniadirBilletera(billetera);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writeValue(file, this.billeteras);
        } catch (IOException e) {
            System.out.println("No se pudo completar la operacion." + e.getMessage());
        } finally {
            if (file != null) {
                ///file.close();
            }
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
        } finally {
            if (file != null) {
                ///file.close();
            }
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
            } finally {
                if (file != null) {
                    ///file.close();
                }
            }
    }

    public void guardarValidacionArchivo (Transferencia t){
        File file = new File("./Validaciones.json");
        ObjectMapper mapper = new ObjectMapper();

        if (t.getValidaciones()>0 && t.getValidaciones()<3) {
            aniadirtransferencia(t.getUUIDtransaccion(), t);
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            mapper.writeValue(file, transferencias);
        } catch (IOException e) {
            System.out.println("No se pudo completar la operacion." + e.getMessage());
        } finally {
            if (file != null) {
                ///file.close();
            }
        }
    }

    ///******************************************METODOS DE APERTURA DE ARCHIVOS*******************************
    public void archivoAMapBilleteras () {
            File file = new File("./billeteras.json");
            ObjectMapper mapper = new ObjectMapper();

            if (file.exists()) {
                try {
                    this.billeteras = (HashMap<UUID, Billetera>) mapper.readValue(file, new TypeReference<Map<UUID, Billetera>>() {
                    });
                } catch (IOException e) {
                    System.out.println("No se pudo completar la operacion." + e.getMessage());
                } finally {
                    if (file != null) {
                        ///file.close();
                    }
                }
            }
    }

    public void archivoAMapTransferencias () {
            File file = new File("./Transferencias.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            if (file.exists()) {
                try {
                    this.transferencias = (HashMap<UUID, Transferencia>) mapper.readValue(file, new TypeReference<Map<UUID, Transferencia>>() {
                    });
                } catch (IOException e) {
                    System.out.println("No se pudo completar la operacion." + e.getMessage());
                } finally {
                    if (file != null) {
                        ///file.close;
                    }
                }
            }
    }

    public void archivoAMapUsuarios () {
            File file = new File("./usuarios.json");
            ObjectMapper mapper = new ObjectMapper();

            if (file.exists()) {
                try {
                    this.usuarios = (HashMap<UUID, Usuario>) mapper.readValue(file, new TypeReference<Map<UUID, Usuario>>() {
                    });
                } catch (IOException e) {
                    System.out.println("No se pudo completar la operacion." + e.getMessage());
                } finally {
                    if (file != null) {
                       /// file.close();
                    }
                }
            }
    }

    public void archivoAMapValidaciones () {
        File file = new File("./Validaciones.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        if (file.exists()) {
            try {
                this.transferencias = (HashMap<UUID, Transferencia>) mapper.readValue(file, new TypeReference<Map<UUID, Transferencia>>() {
                });
            } catch (IOException e) {
                System.out.println("No se pudo completar la operacion." + e.getMessage());
            } finally {
                if (file != null) {
                   /// file.close();
                }
            }
        }
    }

    public double consultarActivos () {
            Billetera billeteraUsuarioActivo = billeteras.get(usuarioActivo.getBilletera());
            return billeteraUsuarioActivo.getSaldo();
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

        public Usuario getUsuarioActivo () {
            return usuarioActivo;
        }

        public void setUsuarioActivo (Usuario usuarioActivo){
        this.usuarioActivo = usuarioActivo;
        }

        public void finalizarSesion () {
            // Paso los nuevos usuarios registrados a el archivo.
            ///guardarBilleterasEnArchivo();
            System.exit(0);
        }
}



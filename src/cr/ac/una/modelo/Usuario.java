package cr.ac.una.modelo;

public class Usuario {

    private String id;
    private String clave;
    private String rol; // Administrasdor o Funcionario.

    public Usuario() { // Comstructor
    }

    public Usuario(String id, String clave, String rol) { // Constructor con Parametros
        this.id = id;
        this.clave = clave;
        this.rol = rol;
    }

    //Getters
    public String getId() {return id;}
    public String getClave() {return clave;}
    public String getRol() {return rol;}

    //Setters
    public void setId(String id) {this.id = id;}
    public void setClave(String clave) {this.clave = clave;}
    public void setRol(String rol) {this.rol = rol;}

    @Override
    public String toString() {
        return id + " (" + rol + ")";
        }
    }

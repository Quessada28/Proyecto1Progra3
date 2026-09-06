package cr.ac.una.modelo;

import java.util.Objects;

public class Usuario {

    private String id;
    private String clave;
    private Rol rol;

    public Usuario() {
    }

    public Usuario(String id, String clave, Rol rol) {
        this.id = id;
        this.clave = clave;
        this.rol = rol;
    }

    public String getId() { return id; }
    public String getClave() { return clave; }
    public Rol getRol() { return rol; }

    public void setId(String id) { this.id = id; }
    public void setClave(String clave) { this.clave = clave; }
    public void setRol(Rol rol) { this.rol = rol; }

    public boolean claveCoincide(String intento) {
        return clave != null && clave.equals(intento);
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Usuario)) {
            return false;
        }
        return Objects.equals(id, ((Usuario) otro).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

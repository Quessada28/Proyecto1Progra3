package cr.ac.una.modelo;

import java.util.Objects;

public class Recurso {

    private String id;
    private String idCategoria;
    private String descripcion;

    public Recurso() {
    }

    public Recurso(String id, String idCategoria, String descripcion) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
    }

    public String getId() { return id; }
    public String getIdCategoria() { return idCategoria; }
    public String getDescripcion() { return descripcion; }

    public void setId(String id) { this.id = id; }
    public void setIdCategoria(String idCategoria) { this.idCategoria = idCategoria; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Recurso)) {
            return false;
        }
        return Objects.equals(id, ((Recurso) otro).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return descripcion == null ? id : descripcion;
    }
}

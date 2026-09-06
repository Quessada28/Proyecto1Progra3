package cr.ac.una.modelo;

import java.util.Objects;

public class Categoria {

    private String id;
    private String descripcion;

    public Categoria() {
    }

    public Categoria(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }

    public void setId(String id) { this.id = id; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Categoria)) {
            return false;
        }
        return Objects.equals(id, ((Categoria) otro).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return descripcion == null ? "" : descripcion;
    }
}

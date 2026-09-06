package cr.ac.una.modelo;

import java.util.Objects;

public class Funcionario {

    private String id;
    private String nombre;
    private String telefono;

    public Funcionario() {
    }

    public Funcionario(String id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }

    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Funcionario)) {
            return false;
        }
        return Objects.equals(id, ((Funcionario) otro).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nombre == null ? id : nombre;
    }
}

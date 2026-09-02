package cr.ac.una.modelo;

/**
 * Funcionario de la organizacion (funcionalidad 3).
 * El id del funcionario es el mismo id de su Usuario.
 */
public class Funcionario {

    private String id;
    private String nombre;
    private String telefono;

    public Funcionario() {
    }

    public Funcionario(String id, String nombre, String telefono) {
        // TODO: asignar los atributos
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }

    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        // TODO: util para mostrarlo en combos/tablas
        return null;
    }
}

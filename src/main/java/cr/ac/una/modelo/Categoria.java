package cr.ac.una.modelo;

/**
 * Categoria de recurso (funcionalidad 4). Ejemplos: "Sala para 10 personas",
 * "Laptop windows 11". El id es autogenerado con formato CAT-000001
 * (ver util.GeneradorId).
 */
public class Categoria {

    private String id;
    private String descripcion;

    public Categoria() {
    }

    public Categoria(String id, String descripcion) {
        // TODO
    }

    public String getId() { return id; }
    public String getDescripcion() { return descripcion; }

    public void setId(String id) { this.id = id; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        // TODO: devolver la descripcion (es lo que se ve en los JComboBox)
        return null;
    }
}

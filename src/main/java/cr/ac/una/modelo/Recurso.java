package cr.ac.una.modelo;

/**
 * Unidad fisica reservable (funcionalidad 5). Ejemplo: "Laptop #238715"
 * de la categoria "Laptop windows 11".
 * El id lo digita el administrador (numero de activo), no es autogenerado.
 */
public class Recurso {

    private String id;
    private String idCategoria;
    private String descripcion;

    public Recurso() {
    }

    public Recurso(String id, String idCategoria, String descripcion) {
        // TODO
    }

    public String getId() { return id; }
    public String getIdCategoria() { return idCategoria; }
    public String getDescripcion() { return descripcion; }

    public void setId(String id) { this.id = id; }
    public void setIdCategoria(String idCategoria) { this.idCategoria = idCategoria; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        // TODO
        return null;
    }
}

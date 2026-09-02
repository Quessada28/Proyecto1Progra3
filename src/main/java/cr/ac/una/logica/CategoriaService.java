package cr.ac.una.logica;

import cr.ac.una.datos.CategoriaXmlDao;
import cr.ac.una.modelo.Categoria;

import java.util.List;

/**
 * Funcionalidad 4: mantenimiento de categorias (solo ADMIN).
 */
public class CategoriaService {

    private final CategoriaXmlDao dao;

    public CategoriaService() {
        this.dao = new CategoriaXmlDao();
    }

    public List<Categoria> listar() {
        // TODO
        return null;
    }

    public List<Categoria> buscarPorDescripcion(String texto) {
        // TODO: si el texto viene vacio, devolver todas
        return null;
    }

    /**
     * Si la categoria no trae id, generarlo (GeneradorId.siguienteCategoria).
     * Validar que la descripcion no venga vacia ni repetida.
     */
    public void guardar(Categoria categoria) {
        // TODO
    }

    /** No se debe poder borrar una categoria que tenga recursos asociados. */
    public void eliminar(String id) {
        // TODO
    }
}

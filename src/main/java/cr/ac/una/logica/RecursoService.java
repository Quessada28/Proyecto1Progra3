package cr.ac.una.logica;

import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.modelo.Recurso;

import java.util.List;

/**
 * Funcionalidad 5: mantenimiento de recursos (solo ADMIN).
 */
public class RecursoService {

    private final RecursoXmlDao dao;

    public RecursoService() {
        this.dao = new RecursoXmlDao();
    }

    public List<Recurso> listar() {
        // TODO
        return null;
    }

    /** Filtro de la pantalla: por categoria y/o por descripcion. */
    public List<Recurso> buscar(String idCategoria, String descripcion) {
        // TODO
        return null;
    }

    /** Validar: id obligatorio (numero de activo) y no repetido, categoria seleccionada. */
    public void guardar(Recurso recurso) {
        // TODO
    }

    /** No se debe poder borrar un recurso que este asignado a una reserva activa futura. */
    public void eliminar(String id) {
        // TODO
    }
}

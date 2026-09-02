package cr.ac.una.datos;

import cr.ac.una.modelo.Recurso;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Optional;

/**
 * Persistencia en datos/recursos.xml
 *
 * <recursos>
 *   <recurso><id>238715</id><idCategoria>CAT-000002</idCategoria>
 *            <descripcion>Laptop #238715</descripcion></recurso>
 * </recursos>
 */
public class RecursoXmlDao implements Dao<Recurso> {

    private static final String ARCHIVO = "recursos.xml";
    private static final String RAIZ = "recursos";
    private static final String NODO = "recurso";

    @Override
    public List<Recurso> listar() {
        // TODO
        return null;
    }

    @Override
    public Optional<Recurso> buscarPorId(String id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public void guardar(Recurso recurso) {
        // TODO
    }

    @Override
    public void eliminar(String id) {
        // TODO
    }

    /**
     * Recursos de una categoria. Se usa en el filtro de la funcionalidad 5,
     * en las columnas de la calendarizacion (func. 6) y en la busqueda de
     * disponibilidad al reservar (func. 2).
     */
    public List<Recurso> listarPorCategoria(String idCategoria) {
        // TODO
        return null;
    }

    private Recurso mapear(Element e) {
        // TODO
        return null;
    }
}

package cr.ac.una.datos;

import cr.ac.una.modelo.Categoria;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Optional;

/**
 * Persistencia en datos/categorias.xml
 *
 * <categorias>
 *   <categoria><id>CAT-000001</id><descripcion>Sala para 10 personas</descripcion></categoria>
 * </categorias>
 */
public class CategoriaXmlDao implements Dao<Categoria> {

    private static final String ARCHIVO = "categorias.xml";
    private static final String RAIZ = "categorias";
    private static final String NODO = "categoria";

    @Override
    public List<Categoria> listar() {
        // TODO
        return null;
    }

    @Override
    public Optional<Categoria> buscarPorId(String id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public void guardar(Categoria categoria) {
        // TODO
    }

    @Override
    public void eliminar(String id) {
        // TODO
    }

    /** Busqueda por descripcion (contiene) - funcionalidad 4. */
    public List<Categoria> buscarPorDescripcion(String texto) {
        // TODO
        return null;
    }

    /** Ultimo consecutivo usado, para que GeneradorId arme el siguiente CAT-00000N. */
    public int ultimoConsecutivo() {
        // TODO
        return 0;
    }

    private Categoria mapear(Element e) {
        // TODO
        return null;
    }
}

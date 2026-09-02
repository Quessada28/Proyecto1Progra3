package cr.ac.una.datos;

import cr.ac.una.modelo.Usuario;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Optional;

/**
 * Persistencia de usuarios en datos/usuarios.xml
 *
 * <usuarios>
 *   <usuario><id>admin</id><clave>admin</clave><rol>ADMIN</rol></usuario>
 * </usuarios>
 */
public class UsuarioXmlDao implements Dao<Usuario> {

    private static final String ARCHIVO = "usuarios.xml";
    private static final String RAIZ = "usuarios";
    private static final String NODO = "usuario";

    @Override
    public List<Usuario> listar() {
        // TODO: abrir el documento, recorrer getElementsByTagName(NODO) y mapear cada nodo
        return null;
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public void guardar(Usuario usuario) {
        // TODO: si ya existe el nodo con ese id -> reemplazarlo, si no -> agregarlo.
        //       Al final XmlUtil.guardar(doc, ARCHIVO)
    }

    @Override
    public void eliminar(String id) {
        // TODO
    }

    /** Element del DOM -> objeto Usuario */
    private Usuario mapear(Element e) {
        // TODO
        return null;
    }
}

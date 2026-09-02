package cr.ac.una.datos;

import cr.ac.una.modelo.Funcionario;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Optional;

/**
 * Persistencia en datos/funcionarios.xml
 *
 * <funcionarios>
 *   <funcionario><id>111</id><nombre>Juan Perez</nombre><telefono>3323</telefono></funcionario>
 * </funcionarios>
 */
public class FuncionarioXmlDao implements Dao<Funcionario> {

    private static final String ARCHIVO = "funcionarios.xml";
    private static final String RAIZ = "funcionarios";
    private static final String NODO = "funcionario";

    @Override
    public List<Funcionario> listar() {
        // TODO
        return null;
    }

    @Override
    public Optional<Funcionario> buscarPorId(String id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public void guardar(Funcionario funcionario) {
        // TODO
    }

    @Override
    public void eliminar(String id) {
        // TODO
    }

    /** Busqueda por nombre (contiene, sin distinguir mayusculas) - funcionalidad 3. */
    public List<Funcionario> buscarPorNombre(String texto) {
        // TODO
        return null;
    }

    private Funcionario mapear(Element e) {
        // TODO
        return null;
    }
}

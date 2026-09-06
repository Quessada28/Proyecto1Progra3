package cr.ac.una.soporte;

import cr.ac.una.datos.UsuarioXmlDao;
import cr.ac.una.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioDaoFalso extends UsuarioXmlDao {

    private final DaoEnMemoria<Usuario> memoria = new DaoEnMemoria<>(Usuario::getId);

    @Override
    public List<Usuario> listar() {
        return memoria.listar();
    }

    @Override
    public Optional<Usuario> buscarPorId(String id) {
        return memoria.buscarPorId(id);
    }

    @Override
    public void guardar(Usuario usuario) {
        memoria.guardar(usuario);
    }

    @Override
    public void eliminar(String id) {
        memoria.eliminar(id);
    }

    @Override
    public boolean existe(String id) {
        return memoria.existe(id);
    }

    public int cantidad() {
        return memoria.cantidad();
    }
}

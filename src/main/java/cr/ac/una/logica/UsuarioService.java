package cr.ac.una.logica;

import cr.ac.una.datos.UsuarioXmlDao;
import cr.ac.una.modelo.Rol;
import cr.ac.una.modelo.Usuario;
import cr.ac.una.util.Validador;

import java.util.Optional;

public class UsuarioService {

    private final UsuarioXmlDao dao;

    public UsuarioService() {
        this(new UsuarioXmlDao());
    }

    public UsuarioService(UsuarioXmlDao dao) {
        this.dao = dao;
    }

    public Usuario autenticar(String id, String clave) {
        Validador.requerido(id, "ID");
        Validador.requerido(clave, "Clave");
        Usuario usuario = dao.buscarPorId(id.trim())
                .orElseThrow(() -> new ServicioException("El usuario indicado no existe."));
        if (!usuario.claveCoincide(clave)) {
            throw new ServicioException("La clave es incorrecta.");
        }
        return usuario;
    }

    public void cambiarClave(String id, String claveActual, String claveNueva) {
        Validador.requerido(claveNueva, "Clave nueva");
        if (claveNueva.length() < 3) {
            throw new ServicioException("La clave nueva debe tener al menos 3 caracteres.");
        }
        Usuario usuario = autenticar(id, claveActual);
        if (claveNueva.equals(claveActual)) {
            throw new ServicioException("La clave nueva debe ser diferente a la actual.");
        }
        usuario.setClave(claveNueva);
        dao.guardar(usuario);
    }

    public void crearUsuarioDeFuncionario(String id) {
        Validador.requerido(id, "ID");
        if (dao.existe(id)) {
            return;
        }
        dao.guardar(new Usuario(id, id, Rol.FUNCIONARIO));
    }

    public void eliminar(String id) {
        dao.eliminar(id);
    }

    public Optional<Usuario> buscar(String id) {
        return dao.buscarPorId(id);
    }

    public void asegurarAdministrador() {
        if (dao.listar().stream().noneMatch(u -> u.getRol() == Rol.ADMIN)) {
            dao.guardar(new Usuario("admin", "admin", Rol.ADMIN));
        }
    }
}

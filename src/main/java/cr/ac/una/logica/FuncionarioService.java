package cr.ac.una.logica;

import cr.ac.una.datos.FuncionarioXmlDao;
import cr.ac.una.modelo.Funcionario;

import java.util.List;

/**
 * Funcionalidad 3: mantenimiento de funcionarios (solo ADMIN).
 */
public class FuncionarioService {

    private final FuncionarioXmlDao dao;
    private final UsuarioService usuarioService;

    public FuncionarioService() {
        this.dao = new FuncionarioXmlDao();
        this.usuarioService = new UsuarioService();
    }

    public List<Funcionario> listar() {
        // TODO
        return null;
    }

    /** Si el id viene vacio busca por nombre, y viceversa. */
    public List<Funcionario> buscar(String id, String nombre) {
        // TODO
        return null;
    }

    /**
     * Agrega o modifica. Al AGREGAR uno nuevo hay que crear tambien su usuario
     * con clave igual al id (usuarioService.crearUsuarioDeFuncionario).
     * Validar: id obligatorio y no repetido, nombre obligatorio, telefono numerico.
     */
    public void guardar(Funcionario funcionario) {
        // TODO
    }

    /**
     * Borra el funcionario y su usuario.
     * Decidir (y documentar en la defensa) que pasa si tiene reservas activas:
     * lo mas sano es no permitir el borrado y lanzar ServicioException.
     */
    public void eliminar(String id) {
        // TODO
    }
}

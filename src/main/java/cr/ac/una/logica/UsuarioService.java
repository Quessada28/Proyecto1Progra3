package cr.ac.una.logica;

import cr.ac.una.datos.UsuarioXmlDao;
import cr.ac.una.modelo.Usuario;

/**
 * Funcionalidad 1: ingreso y cambio de clave.
 */
public class UsuarioService {

    private final UsuarioXmlDao dao;

    public UsuarioService() {
        this.dao = new UsuarioXmlDao();
    }

    /** Constructor para pruebas: permite inyectar un DAO falso. */
    public UsuarioService(UsuarioXmlDao dao) {
        this.dao = dao;
    }

    /**
     * Devuelve el usuario si el id existe y la clave coincide.
     * Si no, lanza ServicioException con un mensaje claro.
     */
    public Usuario autenticar(String id, String clave) {
        // TODO: validar que id y clave no vengan vacios
        // TODO: dao.buscarPorId(id) -> comparar clave
        return null;
    }

    /**
     * Cambia la clave. Reglas: la clave actual debe coincidir,
     * la nueva no puede ir vacia y las dos copias de la nueva deben ser iguales
     * (esa segunda comparacion la puede hacer el controlador, ver la pantalla).
     */
    public void cambiarClave(String id, String claveActual, String claveNueva) {
        // TODO
    }

    /**
     * Crea el usuario que acompana a un funcionario nuevo.
     * Regla del enunciado: la clave inicial es igual al id, rol FUNCIONARIO.
     */
    public void crearUsuarioDeFuncionario(String id) {
        // TODO
    }

    public void eliminar(String id) {
        // TODO: se llama cuando el administrador borra un funcionario
    }
}

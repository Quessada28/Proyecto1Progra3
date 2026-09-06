package cr.ac.una.logica;

import cr.ac.una.modelo.Rol;
import cr.ac.una.modelo.Usuario;
import cr.ac.una.soporte.UsuarioDaoFalso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioServiceTest {

    private UsuarioDaoFalso dao;
    private UsuarioService servicio;

    @BeforeEach
    void prepararEscenario() {
        dao = new UsuarioDaoFalso();
        servicio = new UsuarioService(dao);
        dao.guardar(new Usuario("admin", "admin", Rol.ADMIN));
    }

    @Test
    void autenticaConIdYClaveCorrectos() {
        Usuario usuario = servicio.autenticar("admin", "admin");

        assertEquals("admin", usuario.getId());
        assertEquals(Rol.ADMIN, usuario.getRol());
    }

    @Test
    void rechazaClaveIncorrecta() {
        ServicioException error = assertThrows(ServicioException.class,
                () -> servicio.autenticar("admin", "otra"));

        assertTrue(error.getMessage().contains("clave"));
    }

    @Test
    void rechazaUsuarioQueNoExiste() {
        assertThrows(ServicioException.class, () -> servicio.autenticar("nadie", "x"));
    }

    @Test
    void cambiarClaveExigeLaClaveActualCorrecta() {
        assertThrows(ServicioException.class,
                () -> servicio.cambiarClave("admin", "equivocada", "nueva"));
    }

    @Test
    void cambiarClaveGuardaLaClaveNueva() {
        servicio.cambiarClave("admin", "admin", "secreta");

        assertEquals("secreta", dao.buscarPorId("admin").orElseThrow().getClave());
    }

    @Test
    void rechazaClaveNuevaIgualALaActual() {
        assertThrows(ServicioException.class,
                () -> servicio.cambiarClave("admin", "admin", "admin"));
    }

    @Test
    @DisplayName("El usuario nuevo de un funcionario queda con clave igual al id")
    void elUsuarioNuevoDeUnFuncionarioQuedaConClaveIgualAlId() {
        servicio.crearUsuarioDeFuncionario("111");

        Usuario creado = dao.buscarPorId("111").orElseThrow();
        assertEquals("111", creado.getClave());
        assertEquals(Rol.FUNCIONARIO, creado.getRol());
    }

    @Test
    void noSobreescribeLaClaveDeUnUsuarioQueYaExiste() {
        servicio.crearUsuarioDeFuncionario("111");
        servicio.cambiarClave("111", "111", "personal");
        servicio.crearUsuarioDeFuncionario("111");

        assertEquals("personal", dao.buscarPorId("111").orElseThrow().getClave());
    }

    @Test
    void asegurarAdministradorNoDuplicaAlQueYaExiste() {
        servicio.asegurarAdministrador();

        assertEquals(1, dao.cantidad());
    }
}

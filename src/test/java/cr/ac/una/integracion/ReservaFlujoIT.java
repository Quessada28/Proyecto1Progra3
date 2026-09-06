package cr.ac.una.integracion;

import cr.ac.una.datos.XmlUtil;
import cr.ac.una.logica.CalendarioService;
import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.EstadisticaService;
import cr.ac.una.logica.FuncionarioService;
import cr.ac.una.logica.RecursoService;
import cr.ac.una.logica.ReservaService;
import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.DatosReserva;
import cr.ac.una.modelo.Funcionario;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.ResultadoReserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaFlujoIT {

    private static final String FUNCIONARIO = "111";

    @TempDir
    Path carpetaTemporal;

    private String carpetaOriginal;
    private ReservaService reservaService;
    private CalendarioService calendarioService;
    private EstadisticaService estadisticaService;
    private String idCategoria;
    private LocalDate manana;

    @BeforeEach
    void prepararEscenario() {
        carpetaOriginal = XmlUtil.getCarpeta();
        XmlUtil.setCarpeta(carpetaTemporal.toString());

        reservaService = new ReservaService();
        calendarioService = new CalendarioService();
        estadisticaService = new EstadisticaService();
        manana = LocalDate.now().plusDays(1);

        Categoria categoria = new CategoriaService().guardar(new Categoria(null, "Sala de Juntas"));
        idCategoria = categoria.getId();
        new RecursoService().guardar(new Recurso("SALA-1", idCategoria, "Sala 1 primer piso"));
        new FuncionarioService().guardar(new Funcionario(FUNCIONARIO, "Juan Perez", "3323"));
    }

    @AfterEach
    void restaurarCarpeta() {
        XmlUtil.setCarpeta(carpetaOriginal);
    }

    private DatosReserva datos() {
        return new DatosReserva("Sesion de Junta Directiva", manana,
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of(idCategoria));
    }

    @Test
    @DisplayName("El segundo intento sobre el unico recurso falla e indica la categoria")
    void unFuncionarioReservaElUnicoRecursoYElSegundoIntentoFalla() {
        assertTrue(reservaService.crear(FUNCIONARIO, datos()).isExito());

        ResultadoReserva segunda = reservaService.crear("222", datos());

        assertFalse(segunda.isExito());
        assertEquals(List.of("Sala de Juntas"), segunda.getCategoriasNoDisponibles());
    }

    @Test
    void alCancelarLaPrimeraElSegundoIntentoAhoraSiPasa() {
        ResultadoReserva primera = reservaService.crear(FUNCIONARIO, datos());
        reservaService.cancelar(primera.getReserva().getId(), FUNCIONARIO);

        assertTrue(reservaService.crear("222", datos()).isExito());
    }

    @Test
    void laReservaApareceEnLaCalendarizacionDeEseDia() {
        reservaService.crear(FUNCIONARIO, datos());

        String[][] matriz = calendarioService.calendarioDeRecursos(manana, idCategoria);

        assertEquals("Sesion de Junta Directiva - Juan Perez", matriz[2][1]);
    }

    @Test
    void laReservaApareceEnLaProgramacionDeActividades() {
        reservaService.crear(FUNCIONARIO, datos());

        String[][] matriz = calendarioService.calendarioDeActividades(manana);
        int columnaDelDia = manana.getDayOfWeek().getValue();

        assertTrue(matriz[2][columnaDelDia].contains("Sesion de Junta Directiva"));
    }

    @Test
    void laReservaCuentaEnLasEstadisticasDelPeriodo() {
        reservaService.crear(FUNCIONARIO, datos());

        Map<String, Integer> conteo = estadisticaService.recursosPorCategoria(manana, manana);

        assertEquals(1, conteo.get("Sala de Juntas"));
    }

    @Test
    void laReservaCanceladaDejaDeContarEnLasEstadisticas() {
        ResultadoReserva reserva = reservaService.crear(FUNCIONARIO, datos());
        reservaService.cancelar(reserva.getReserva().getId(), FUNCIONARIO);

        assertTrue(estadisticaService.recursosPorCategoria(manana, manana).isEmpty());
    }

    @Test
    @DisplayName("Al crear el funcionario se le crea el usuario con clave igual al id")
    void alCrearElFuncionarioSeLeCreaSuUsuario() {
        assertTrue(new cr.ac.una.logica.UsuarioService().buscar(FUNCIONARIO).isPresent());
        assertEquals(FUNCIONARIO,
                new cr.ac.una.logica.UsuarioService().buscar(FUNCIONARIO).orElseThrow().getClave());
    }
}

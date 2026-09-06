package cr.ac.una.logica;

import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.DatosReserva;
import cr.ac.una.modelo.EstadoReserva;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.ResultadoReserva;
import cr.ac.una.soporte.CategoriaDaoFalso;
import cr.ac.una.soporte.RecursoDaoFalso;
import cr.ac.una.soporte.ReservaDaoFalso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaServiceTest {

    private static final String SALA = "CAT-000001";
    private static final String LAPTOP = "CAT-000002";
    private static final String FUNCIONARIO = "111";

    private ReservaDaoFalso reservaDao;
    private RecursoDaoFalso recursoDao;
    private CategoriaDaoFalso categoriaDao;
    private ReservaService servicio;
    private LocalDate manana;

    @BeforeEach
    void prepararEscenario() {
        reservaDao = new ReservaDaoFalso();
        recursoDao = new RecursoDaoFalso();
        categoriaDao = new CategoriaDaoFalso();
        servicio = new ReservaService(reservaDao, recursoDao, categoriaDao);
        manana = LocalDate.now().plusDays(1);

        categoriaDao.guardar(new Categoria(SALA, "Sala de Juntas"));
        categoriaDao.guardar(new Categoria(LAPTOP, "Laptop windows"));
        recursoDao.guardar(new Recurso("SALA-1", SALA, "Sala 1 primer piso"));
        recursoDao.guardar(new Recurso("238715", LAPTOP, "Laptop 238715"));
    }

    private DatosReserva datos(LocalTime inicio, LocalTime fin, String... categorias) {
        return new DatosReserva("Reunion de trabajo", manana, inicio, fin, List.of(categorias));
    }

    @Test
    @DisplayName("Reserva exitosa cuando hay una unidad libre de cada categoria")
    void crearReservaConTodasLasCategoriasDisponibles() {
        ResultadoReserva resultado = servicio.crear(FUNCIONARIO,
                datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA, LAPTOP));

        assertTrue(resultado.isExito());
        assertEquals(2, resultado.getReserva().getIdsRecursos().size());
        assertEquals(EstadoReserva.ACTIVA, resultado.getReserva().getEstado());
        assertEquals("RES-000001", resultado.getReserva().getId());
    }

    @Test
    @DisplayName("Reserva fallida indica cuales categorias no tienen disponibilidad")
    void crearReservaFallaCuandoUnaCategoriaNoTieneUnidadesLibres() {
        servicio.crear(FUNCIONARIO, datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA));

        ResultadoReserva segunda = servicio.crear("222",
                datos(LocalTime.of(9, 0), LocalTime.of(11, 0), SALA));

        assertFalse(segunda.isExito());
        assertEquals(List.of("Sala de Juntas"), segunda.getCategoriasNoDisponibles());
    }

    @Test
    @DisplayName("Una reserva fallida no guarda nada: es todo o nada")
    void reservaFallidaNoGuardaNada() {
        servicio.crear(FUNCIONARIO, datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA));

        servicio.crear("222", datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA, LAPTOP));

        assertEquals(1, reservaDao.cantidad());
    }

    @Test
    @DisplayName("Se asigna el primer recurso libre de la categoria")
    void asignaElPrimerRecursoDisponibleDeLaCategoria() {
        recursoDao.guardar(new Recurso("SALA-2", SALA, "Sala 2 segundo piso"));
        servicio.crear(FUNCIONARIO, datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA));

        ResultadoReserva segunda = servicio.crear("222",
                datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA));

        assertTrue(segunda.isExito());
        assertEquals(List.of("SALA-2"), segunda.getReserva().getIdsRecursos());
    }

    @Test
    @DisplayName("Dos categorias iguales en la misma reserva toman recursos distintos")
    void dosVecesLaMismaCategoriaNoRepiteElRecurso() {
        recursoDao.guardar(new Recurso("SALA-2", SALA, "Sala 2 segundo piso"));

        ResultadoReserva resultado = servicio.crear(FUNCIONARIO,
                datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA, SALA));

        assertTrue(resultado.isExito());
        assertEquals(List.of("SALA-1", "SALA-2"), resultado.getReserva().getIdsRecursos());
    }

    @Test
    @DisplayName("Horarios que se tocan en el extremo no chocan")
    void dosReservasEnHorariosQueNoSeTraslapanUsanElMismoRecurso() {
        servicio.crear(FUNCIONARIO, datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA));

        ResultadoReserva segunda = servicio.crear("222",
                datos(LocalTime.of(10, 0), LocalTime.of(12, 0), SALA));

        assertTrue(segunda.isExito());
        assertEquals(List.of("SALA-1"), segunda.getReserva().getIdsRecursos());
    }

    @Test
    @DisplayName("Cancelar libera los recursos para otra reserva")
    void cancelarLiberaLosRecursosParaOtraReserva() {
        ResultadoReserva primera = servicio.crear(FUNCIONARIO,
                datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA));

        servicio.cancelar(primera.getReserva().getId(), FUNCIONARIO);
        ResultadoReserva segunda = servicio.crear("222",
                datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA));

        assertTrue(segunda.isExito());
    }

    @Test
    @DisplayName("No se puede cancelar la reserva de otro funcionario")
    void noSePuedeCancelarUnaReservaDeOtroFuncionario() {
        ResultadoReserva reserva = servicio.crear(FUNCIONARIO,
                datos(LocalTime.of(8, 0), LocalTime.of(10, 0), SALA));

        ServicioException error = assertThrows(ServicioException.class,
                () -> servicio.cancelar(reserva.getReserva().getId(), "999"));

        assertTrue(error.getMessage().contains("propias"));
    }

    @Test
    void validarRechazaHoraFinMenorOIgualAHoraInicio() {
        assertThrows(ServicioException.class, () -> servicio.validar(
                datos(LocalTime.of(10, 0), LocalTime.of(10, 0), SALA)));
    }

    @Test
    void validarRechazaFechaAnteriorAHoy() {
        DatosReserva datos = new DatosReserva("Reunion", LocalDate.now().minusDays(1),
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of(SALA));

        assertThrows(ServicioException.class, () -> servicio.validar(datos));
    }

    @Test
    void validarRechazaListaDeCategoriasVacia() {
        DatosReserva datos = new DatosReserva("Reunion", manana,
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of());

        assertThrows(ServicioException.class, () -> servicio.validar(datos));
    }

    @Test
    void validarRechazaActividadVacia() {
        DatosReserva datos = new DatosReserva("  ", manana,
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of(SALA));

        assertThrows(ServicioException.class, () -> servicio.validar(datos));
    }
}

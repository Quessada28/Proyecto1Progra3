package cr.ac.una.logica;

import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.EstadoReserva;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.Reserva;
import cr.ac.una.soporte.CategoriaDaoFalso;
import cr.ac.una.soporte.RecursoDaoFalso;
import cr.ac.una.soporte.ReservaDaoFalso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EstadisticaServiceTest {

    private static final String SALA = "CAT-000001";
    private static final String LAPTOP = "CAT-000002";

    private ReservaDaoFalso reservaDao;
    private EstadisticaService servicio;

    @BeforeEach
    void prepararEscenario() {
        reservaDao = new ReservaDaoFalso();
        RecursoDaoFalso recursoDao = new RecursoDaoFalso();
        CategoriaDaoFalso categoriaDao = new CategoriaDaoFalso();
        servicio = new EstadisticaService(reservaDao, recursoDao, categoriaDao);

        categoriaDao.guardar(new Categoria(SALA, "Sala de Juntas"));
        categoriaDao.guardar(new Categoria(LAPTOP, "Laptop windows"));
        recursoDao.guardar(new Recurso("SALA-1", SALA, "Sala 1"));
        recursoDao.guardar(new Recurso("LAP-1", LAPTOP, "Laptop 1"));
        recursoDao.guardar(new Recurso("LAP-2", LAPTOP, "Laptop 2"));
    }

    private void agregarReserva(String id, LocalDate fecha, EstadoReserva estado, String... recursos) {
        reservaDao.guardar(new Reserva(id, "111", "Actividad", fecha,
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of(recursos), estado));
    }

    @Test
    @DisplayName("Una reserva con dos laptops suma dos a esa categoria")
    void cuentaLosRecursosPorCategoriaEnElPeriodo() {
        LocalDate dia = LocalDate.of(2026, 8, 5);
        agregarReserva("RES-000001", dia, EstadoReserva.ACTIVA, "LAP-1", "LAP-2");
        agregarReserva("RES-000002", dia, EstadoReserva.ACTIVA, "SALA-1");

        Map<String, Integer> conteo = servicio.recursosPorCategoria(dia, dia);

        assertEquals(2, conteo.get("Laptop windows"));
        assertEquals(1, conteo.get("Sala de Juntas"));
    }

    @Test
    void noCuentaLasReservasCanceladas() {
        LocalDate dia = LocalDate.of(2026, 8, 5);
        agregarReserva("RES-000001", dia, EstadoReserva.CANCELADA, "SALA-1");

        assertTrue(servicio.recursosPorCategoria(dia, dia).isEmpty());
    }

    @Test
    @DisplayName("Los limites Desde y Hasta son inclusivos")
    void noCuentaLasReservasFueraDelPeriodo() {
        agregarReserva("RES-000001", LocalDate.of(2026, 8, 3), EstadoReserva.ACTIVA, "SALA-1");
        agregarReserva("RES-000002", LocalDate.of(2026, 8, 10), EstadoReserva.ACTIVA, "SALA-1");
        agregarReserva("RES-000003", LocalDate.of(2026, 8, 20), EstadoReserva.ACTIVA, "SALA-1");

        Map<String, Integer> conteo = servicio.recursosPorCategoria(
                LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 10));

        assertEquals(2, conteo.get("Sala de Juntas"));
    }

    @Test
    @DisplayName("Aparecen todas las semanas del periodo aunque tengan cero")
    void listaTodasLasSemanasDelPeriodoAunqueTenganCero() {
        agregarReserva("RES-000001", LocalDate.of(2026, 8, 5), EstadoReserva.ACTIVA, "SALA-1");

        Map<String, Integer> conteo = servicio.actividadesPorSemana(
                LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 16));

        assertEquals(2, conteo.size());
        assertEquals(1, conteo.get("2026-08-03"));
        assertEquals(0, conteo.get("2026-08-10"));
    }

    @Test
    void validarPeriodoRechazaDesdeMayorQueHasta() {
        assertThrows(ServicioException.class, () -> servicio.validarPeriodo(
                LocalDate.of(2026, 8, 10), LocalDate.of(2026, 8, 3)));
    }

    @Test
    void validarPeriodoRechazaFechasNulas() {
        assertThrows(ServicioException.class,
                () -> servicio.validarPeriodo(null, LocalDate.now()));
        assertThrows(ServicioException.class,
                () -> servicio.validarPeriodo(LocalDate.now(), null));
    }
}

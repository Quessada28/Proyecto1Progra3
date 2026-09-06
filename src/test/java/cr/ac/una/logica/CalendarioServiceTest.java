package cr.ac.una.logica;

import cr.ac.una.modelo.EstadoReserva;
import cr.ac.una.modelo.Funcionario;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.Reserva;
import cr.ac.una.soporte.FuncionarioDaoFalso;
import cr.ac.una.soporte.RecursoDaoFalso;
import cr.ac.una.soporte.ReservaDaoFalso;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalendarioServiceTest {

    private static final String SALA = "CAT-000001";
    private static final LocalDate MIERCOLES = LocalDate.of(2026, 8, 5);

    private ReservaDaoFalso reservaDao;
    private RecursoDaoFalso recursoDao;
    private CalendarioService servicio;

    @BeforeEach
    void prepararEscenario() {
        reservaDao = new ReservaDaoFalso();
        recursoDao = new RecursoDaoFalso();
        FuncionarioDaoFalso funcionarioDao = new FuncionarioDaoFalso();
        servicio = new CalendarioService(reservaDao, recursoDao, funcionarioDao);

        funcionarioDao.guardar(new Funcionario("111", "Juan Perez", "3323"));
        recursoDao.guardar(new Recurso("SALA-1", SALA, "Sala 1 primer piso"));
        reservaDao.guardar(new Reserva("RES-000001", "111", "Sesion de Junta", MIERCOLES,
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of("SALA-1"), EstadoReserva.ACTIVA));
    }

    @Test
    void laMatrizDeRecursosTieneUnaFilaPorHoraYUnaColumnaPorRecurso() {
        String[] columnas = servicio.columnasDeRecursos(SALA);
        String[][] matriz = servicio.calendarioDeRecursos(MIERCOLES, SALA);

        assertEquals(2, columnas.length);
        assertEquals("Hora", columnas[0]);
        assertEquals(16, matriz.length);
        assertEquals(2, matriz[0].length);
    }

    @Test
    @DisplayName("La celda ocupada muestra actividad y funcionario")
    void laCeldaOcupadaMuestraActividadYFuncionario() {
        String[][] matriz = servicio.calendarioDeRecursos(MIERCOLES, SALA);

        assertEquals("Sesion de Junta - Juan Perez", matriz[2][1]);
    }

    @Test
    @DisplayName("Una reserva de 8 a 10 ocupa las filas 8 y 9 pero no la de las 10")
    void unaReservaDe8a10OcupaLasFilas8y9PeroNoLa10() {
        String[][] matriz = servicio.calendarioDeRecursos(MIERCOLES, SALA);

        assertEquals("08:00", matriz[2][0]);
        assertTrue(matriz[2][1].contains("Sesion de Junta"));
        assertTrue(matriz[3][1].contains("Sesion de Junta"));
        assertEquals("", matriz[4][1]);
    }

    @Test
    void lasReservasCanceladasNoAparecenEnLaMatriz() {
        reservaDao.guardar(new Reserva("RES-000001", "111", "Sesion de Junta", MIERCOLES,
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of("SALA-1"), EstadoReserva.CANCELADA));

        String[][] matriz = servicio.calendarioDeRecursos(MIERCOLES, SALA);

        assertEquals("", matriz[2][1]);
    }

    @Test
    @DisplayName("La matriz de actividades arranca en el lunes de esa semana")
    void laMatrizDeActividadesArrancaEnElLunesDeLaSemana() {
        assertEquals(LocalDate.of(2026, 8, 3), servicio.lunesDeLaSemana(MIERCOLES));

        String[] columnas = servicio.columnasDeActividades(MIERCOLES);

        assertEquals(8, columnas.length);
        assertTrue(columnas[1].contains("2026-08-03"));
        assertTrue(columnas[7].contains("2026-08-09"));
    }

    @Test
    void laActividadCaeEnLaColumnaDelDiaCorrecto() {
        String[][] matriz = servicio.calendarioDeActividades(MIERCOLES);

        assertEquals("Sesion de Junta (Juan Perez)", matriz[2][3]);
        assertEquals("", matriz[2][1]);
    }
}

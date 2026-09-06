package cr.ac.una.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservaTest {

    private static final LocalDate DIA = LocalDate.of(2026, 8, 5);

    private Reserva reservaDe8a10(EstadoReserva estado) {
        return new Reserva("RES-000001", "111", "Reunion", DIA,
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of("SALA-1"), estado);
    }

    @Test
    void ocupaLaHoraDeInicio() {
        assertTrue(reservaDe8a10(EstadoReserva.ACTIVA).ocupaHora(DIA, LocalTime.of(8, 0)));
    }

    @Test
    @DisplayName("Una reserva de 8 a 10 no ocupa las 10:00")
    void noOcupaLaHoraDeFin() {
        assertFalse(reservaDe8a10(EstadoReserva.ACTIVA).ocupaHora(DIA, LocalTime.of(10, 0)));
    }

    @Test
    void noOcupaHorasDeOtraFecha() {
        assertFalse(reservaDe8a10(EstadoReserva.ACTIVA)
                .ocupaHora(DIA.plusDays(1), LocalTime.of(8, 0)));
    }

    @Test
    void chocaCuandoLosRangosSeTraslapanParcialmente() {
        assertTrue(reservaDe8a10(EstadoReserva.ACTIVA)
                .chocaCon(DIA, LocalTime.of(9, 0), LocalTime.of(11, 0)));
    }

    @Test
    @DisplayName("Un rango que empieza justo al terminar el otro no choca")
    void noChocaCuandoUnRangoEmpiezaJustoAlTerminarElOtro() {
        assertFalse(reservaDe8a10(EstadoReserva.ACTIVA)
                .chocaCon(DIA, LocalTime.of(10, 0), LocalTime.of(12, 0)));
    }

    @Test
    void chocaCuandoUnRangoContieneAlOtro() {
        assertTrue(reservaDe8a10(EstadoReserva.ACTIVA)
                .chocaCon(DIA, LocalTime.of(7, 0), LocalTime.of(13, 0)));
    }

    @Test
    void unaReservaCanceladaNoChocaConNada() {
        assertFalse(reservaDe8a10(EstadoReserva.CANCELADA)
                .chocaCon(DIA, LocalTime.of(8, 0), LocalTime.of(10, 0)));
    }

    @Test
    void esFuturaDistingueLasReservasYaPasadas() {
        Reserva pasada = new Reserva("RES-000001", "111", "Reunion",
                LocalDate.now().minusDays(1), LocalTime.of(8, 0), LocalTime.of(10, 0),
                List.of("SALA-1"), EstadoReserva.ACTIVA);
        Reserva futura = new Reserva("RES-000002", "111", "Reunion",
                LocalDate.now().plusDays(1), LocalTime.of(8, 0), LocalTime.of(10, 0),
                List.of("SALA-1"), EstadoReserva.ACTIVA);

        assertFalse(pasada.esFutura());
        assertTrue(futura.esFutura());
    }

    @Test
    void usaRecursoReconoceLosRecursosAsignados() {
        Reserva reserva = reservaDe8a10(EstadoReserva.ACTIVA);

        assertTrue(reserva.usaRecurso("SALA-1"));
        assertFalse(reserva.usaRecurso("LAP-1"));
    }
}

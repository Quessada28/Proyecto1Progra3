package cr.ac.una.logica;

import org.junit.jupiter.api.Test;

/** PRUEBA UNITARIA de la funcionalidad 8 (Surefire). */
class EstadisticaServiceTest {

    @Test
    void cuentaLosRecursosPorCategoriaEnElPeriodo() {
        // TODO: una reserva con 2 recursos de la misma categoria suma 2, no 1
    }

    @Test
    void noCuentaLasReservasCanceladas() {
        // TODO
    }

    @Test
    void noCuentaLasReservasFueraDelPeriodo() {
        // TODO: los limites desde y hasta son INCLUSIVOS
    }

    @Test
    void listaTodasLasSemanasDelPeriodoAunqueTenganCero() {
        // TODO
    }

    @Test
    void validarPeriodoRechazaDesdeMayorQueHasta() {
        // TODO: assertThrows(ServicioException.class, ...)
    }
}

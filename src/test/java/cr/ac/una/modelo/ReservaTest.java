package cr.ac.una.modelo;

import org.junit.jupiter.api.Test;

/**
 * PRUEBA UNITARIA de las reglas de tiempo que viven en la entidad.
 * Son pocas lineas de codigo pero es donde se meten los errores de
 * "menor" contra "menor o igual", asi que conviene cubrirlas.
 */
class ReservaTest {

    @Test
    void ocupaLaHoraDeInicio() {
        // TODO: una reserva 08:00-10:00 ocupa las 08:00
    }

    @Test
    void noOcupaLaHoraDeFin() {
        // TODO: una reserva 08:00-10:00 NO ocupa las 10:00
    }

    @Test
    void noOcupaHorasDeOtraFecha() {
        // TODO
    }

    @Test
    void chocaCuandoLosRangosSeTraslapanParcialmente() {
        // TODO: 08:00-10:00 contra 09:00-11:00 -> choca
    }

    @Test
    void noChocaCuandoUnRangoEmpiezaJustoAlTerminarElOtro() {
        // TODO: 08:00-10:00 contra 10:00-12:00 -> NO choca
    }

    @Test
    void unaReservaCanceladaNoChocaConNada() {
        // TODO
    }

    @Test
    void esFuturaDistingueLasReservasYaPasadas() {
        // TODO
    }
}

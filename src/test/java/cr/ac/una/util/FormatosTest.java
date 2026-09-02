package cr.ac.una.util;

import org.junit.jupiter.api.Test;

/**
 * PRUEBA UNITARIA del formateo de fechas y horas.
 * La prueba mas util aqui es la de IDA Y VUELTA: formatear una fecha y
 * volverla a leer tiene que devolver la misma fecha. Si eso falla, la
 * pantalla va a perder datos sin avisar.
 */
class FormatosTest {

    @Test
    void formateaLaFechaEnEspanol() {
        // TODO: LocalDate.of(2026, 8, 5) -> "5 de agosto de 2026"
    }

    @Test
    void leerLoQueSeFormateoDevuelveLaMismaFecha() {
        // TODO
    }

    @Test
    void leerFechaInvalidaDevuelveNull() {
        // TODO
    }

    @Test
    void formateaLaHoraEnFormatoDe12Horas() {
        // TODO: LocalTime.of(9, 0) -> "9:00 a. m."
    }

    @Test
    void lasHorasDelDiaVanDeSeisAVeintidos() {
        // TODO
    }
}

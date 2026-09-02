package cr.ac.una.datos;

import org.junit.jupiter.api.Test;

/**
 * PRUEBA DE INTEGRACION del DAO mas complicado: la reserva tiene una LISTA
 * de recursos anidada y ademas fechas y horas.
 */
class ReservaXmlDaoIT {

    @Test
    void guardaYReleeLaListaDeRecursosCompleta() {
        // TODO: una reserva con tres recursos vuelve con los tres, en orden
    }

    @Test
    void lasFechasYHorasSobrevivenLaIdaYVuelta() {
        // TODO: aqui es donde se descubre si se guardo la fecha ya formateada
        //       en vez de en ISO
    }

    @Test
    void listarPorFechaSoloTraeLasDeEsaFecha() {
        // TODO
    }

    @Test
    void listarEntreIncluyeLosDosExtremos() {
        // TODO
    }
}

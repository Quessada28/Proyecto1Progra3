package cr.ac.una.integracion;

import org.junit.jupiter.api.Test;

/**
 * PRUEBA DE INTEGRACION del flujo completo de la funcionalidad 2, con los
 * servicios y los DAO reales trabajando juntos (sin interfaz grafica).
 *
 * Es la prueba que demuestra que las capas encajan:
 *   CategoriaService -> RecursoService -> ReservaService -> los XML
 *
 * Escenario sugerido (@BeforeEach para armarlo):
 *   1. crear la categoria "Sala de Juntas"
 *   2. crear un unico recurso "Sala 1" en esa categoria
 *   3. crear el funcionario 111
 */
class ReservaFlujoIT {

    @Test
    void unFuncionarioReservaElUnicoRecursoYElSegundoIntentoFalla() {
        // TODO: la segunda reserva en el mismo horario debe venir con
        //       exito == false y con "Sala de Juntas" en las no disponibles
    }

    @Test
    void alCancelarLaPrimeraElSegundoIntentoAhoraSiPasa() {
        // TODO
    }

    @Test
    void laReservaAparaceEnLaCalendarizacionDeEseDia() {
        // TODO: CalendarioService.calendarioDeRecursos(...) debe traer la
        //       celda con "actividad - nombre del funcionario"
    }

    @Test
    void laReservaCuentaEnLasEstadisticasDelPeriodo() {
        // TODO
    }
}

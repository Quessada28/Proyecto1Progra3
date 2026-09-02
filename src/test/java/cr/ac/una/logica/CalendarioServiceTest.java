package cr.ac.una.logica;

import org.junit.jupiter.api.Test;

/** PRUEBA UNITARIA de las funcionalidades 6 y 7 (Surefire). */
class CalendarioServiceTest {

    @Test
    void laMatrizDeRecursosTieneUnaFilaPorHoraYUnaColumnaPorRecurso() {
        // TODO: filas = HORA_FIN - HORA_INICIO, columnas = 1 + cantidad de recursos
    }

    @Test
    void laCeldaOcupadaMuestraActividadYFuncionario() {
        // TODO
    }

    @Test
    void unaReservaDe8a10OcupaLasFilas8y9PeroNoLa10() {
        // TODO: este es el error clasico, vale la pena dejarlo probado
    }

    @Test
    void laMatrizDeActividadesArrancaEnElLunesDeLaSemana() {
        // TODO: pasar un miercoles y revisar que la primera columna sea el lunes
    }
}

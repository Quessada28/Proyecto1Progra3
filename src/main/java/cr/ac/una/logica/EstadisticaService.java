package cr.ac.una.logica;

import cr.ac.una.datos.ReservaXmlDao;

import java.time.LocalDate;
import java.util.Map;

/**
 * Funcionalidad 8 (20% de la nota).
 *
 * Se devuelven Map ordenados (LinkedHashMap) porque de ahi salen las DOS cosas
 * que pide el enunciado: la tabla y el grafico de barras (JFreeChart).
 */
public class EstadisticaService {

    private final ReservaXmlDao reservaDao;

    public EstadisticaService() {
        this.reservaDao = new ReservaXmlDao();
    }

    /**
     * Recursos reservados en un periodo:
     *   clave  = descripcion de la categoria
     *   valor  = cuantos recursos de esa categoria se reservaron en el periodo
     * Se cuenta por recurso asignado, no por reserva: si una reserva pidio
     * 2 laptops, la categoria "Laptop windows" suma 2.
     */
    public Map<String, Integer> recursosPorCategoria(LocalDate desde, LocalDate hasta) {
        // TODO
        return null;
    }

    /**
     * Actividades por semana:
     *   clave  = fecha del lunes de la semana en formato ISO (ej "2026-08-03")
     *   valor  = cuantas actividades (reservas ACTIVAS) caen en esa semana
     * Deben aparecer todas las semanas del periodo, aunque tengan 0.
     */
    public Map<String, Integer> actividadesPorSemana(LocalDate desde, LocalDate hasta) {
        // TODO
        return null;
    }

    /** Validacion comun: ambas fechas obligatorias y desde <= hasta. */
    public void validarPeriodo(LocalDate desde, LocalDate hasta) {
        // TODO
    }
}

package cr.ac.una.logica;

import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.datos.ReservaXmlDao;
import cr.ac.una.modelo.Recurso;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Funcionalidades 6 y 7: las dos matrices.
 *
 * La idea es que el servicio devuelva la matriz YA ARMADA (String[][]) y que
 * la vista solo se la entregue a un DefaultTableModel. Asi la logica de
 * "que va en cada celda" queda fuera de la interfaz.
 */
public class CalendarioService {

    /** Hora en que arranca la primera fila de las matrices. */
    public static final LocalTime HORA_INICIO = LocalTime.of(6, 0);
    /** Hora en que termina la ultima fila. */
    public static final LocalTime HORA_FIN = LocalTime.of(22, 0);

    private final ReservaXmlDao reservaDao;
    private final RecursoXmlDao recursoDao;

    public CalendarioService() {
        this.reservaDao = new ReservaXmlDao();
        this.recursoDao = new RecursoXmlDao();
    }

    /** Columnas de la matriz de recursos: los recursos de la categoria escogida. */
    public List<Recurso> recursosDeCategoria(String idCategoria) {
        // TODO
        return null;
    }

    /**
     * Funcionalidad 6. Matriz [hora][recurso].
     * Columna 0 = la hora ("06:00"); las demas columnas, una por recurso.
     * Celda ocupada = "actividad - nombre del funcionario"; libre = "".
     */
    public String[][] calendarioDeRecursos(LocalDate fecha, String idCategoria) {
        // TODO: 1) traer reservas ACTIVAS de esa fecha
        //       2) para cada hora entre HORA_INICIO y HORA_FIN y cada recurso,
        //          buscar si alguna reserva lo tiene y ocupa esa hora
        return null;
    }

    /** Encabezados de columna de la matriz anterior: "Hora" + descripcion de cada recurso. */
    public String[] columnasDeRecursos(String idCategoria) {
        // TODO
        return null;
    }

    /**
     * Funcionalidad 7. Matriz [hora][dia de la semana].
     * Se recibe cualquier fecha y se calcula el lunes de esa semana
     * (fechaReferencia.with(DayOfWeek.MONDAY)).
     * Celda = "actividad (funcionario)" de todas las actividades de ese dia y hora.
     */
    public String[][] calendarioDeActividades(LocalDate fechaReferencia) {
        // TODO
        return null;
    }

    /** Encabezados: "Hora", "lun 2026-08-03", ... "dom 2026-08-09". */
    public String[] columnasDeActividades(LocalDate fechaReferencia) {
        // TODO
        return null;
    }
}

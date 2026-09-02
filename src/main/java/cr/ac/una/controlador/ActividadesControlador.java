package cr.ac.una.controlador;

import cr.ac.una.logica.CalendarioService;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.ActividadesView;

/**
 * CONTROLADOR de la pestana Actividades (funcionalidad 7).
 * Lo usan ADMIN y FUNCIONARIO.
 *
 * Igual de corto que el de Calendarizacion:
 *   - leer la fecha de referencia
 *   - servicio.columnasDeActividades(fecha) y servicio.calendarioDeActividades(fecha)
 *   - vista.cargarMatriz(columnas, datos)
 */
public class ActividadesControlador {

    private final ActividadesView vista;
    private final CalendarioService servicio;
    private final ReportePdf reportePdf;

    public ActividadesControlador(ActividadesView vista, CalendarioService servicio) {
        this.vista = vista;
        this.servicio = servicio;
        this.reportePdf = new ReportePdf();
        // TODO: listeners
    }

    /** Pone la fecha de hoy por defecto y carga la semana actual. */
    public void inicializar() {
        // TODO
    }

    private void cargar() {
        // TODO
    }

    private void imprimir() {
        // TODO
    }
}

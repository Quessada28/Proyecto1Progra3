package cr.ac.una.controlador;

import cr.ac.una.logica.CalendarioService;
import cr.ac.una.logica.CategoriaService;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.CalendarizacionView;

/**
 * CONTROLADOR de la pestana Calendarizacion (funcionalidad 6).
 * Lo usan ADMIN y FUNCIONARIO.
 *
 * Es corto porque la matriz la arma CalendarioService:
 *   cargar()
 *     - leer fecha y categoria de la vista, validar que las dos vengan
 *     - String[] columnas = servicio.columnasDeRecursos(idCategoria)
 *     - String[][] datos  = servicio.calendarioDeRecursos(fecha, idCategoria)
 *     - vista.cargarMatriz(columnas, datos)
 *
 *   imprimir()
 *     - la misma matriz que se ve en pantalla, mandada a ReportePdf.tabla(...)
 */
public class CalendarizacionControlador {

    private final CalendarizacionView vista;
    private final CalendarioService servicio;
    private final CategoriaService categoriaService;
    private final ReportePdf reportePdf;

    public CalendarizacionControlador(CalendarizacionView vista, CalendarioService servicio,
                                      CategoriaService categoriaService) {
        this.vista = vista;
        this.servicio = servicio;
        this.categoriaService = categoriaService;
        this.reportePdf = new ReportePdf();
        // TODO: listeners
    }

    /** Llena el combo de categorias y pone la fecha de hoy por defecto. */
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

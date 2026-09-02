package cr.ac.una.controlador;

import cr.ac.una.logica.EstadisticaService;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.EstadisticasView;

/**
 * CONTROLADOR de la pestana Estadisticas (funcionalidad 8, 20% de la nota).
 * Lo usan ADMIN y FUNCIONARIO.
 *
 * Son dos flujos independientes, uno por bloque:
 *
 *   cargarRecursos()
 *     - leer las dos fechas del bloque izquierdo
 *     - servicio.validarPeriodo(desde, hasta)
 *     - Map<String,Integer> datos = servicio.recursosPorCategoria(desde, hasta)
 *     - vista.mostrarRecursos(datos)   // llena tabla Y grafico
 *
 *   cargarActividades()
 *     - lo mismo con actividadesPorSemana(...)
 *
 *   imprimir()
 *     - usa ReportePdf.tablaConGrafico(...) con la imagen que devuelve
 *       vista.imagenGraficoRecursos() / imagenGraficoActividades()
 *     - si se quiere un solo PDF con los dos bloques, hay que agregar un
 *       metodo mas a ReportePdf; si no, preguntar cual bloque imprimir
 */
public class EstadisticasControlador {

    private final EstadisticasView vista;
    private final EstadisticaService servicio;
    private final ReportePdf reportePdf;

    public EstadisticasControlador(EstadisticasView vista, EstadisticaService servicio) {
        this.vista = vista;
        this.servicio = servicio;
        this.reportePdf = new ReportePdf();
        // TODO: listeners
    }

    /** Fechas por defecto: por ejemplo el mes actual en los dos bloques. */
    public void inicializar() {
        // TODO
    }

    private void cargarRecursos() {
        // TODO
    }

    private void cargarActividades() {
        // TODO
    }

    private void imprimir() {
        // TODO
    }
}

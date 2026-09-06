package cr.ac.una.controlador;

import cr.ac.una.logica.EstadisticaService;
import cr.ac.una.logica.ServicioException;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.EstadisticasView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EstadisticasControlador implements ControladorPestana {

    private final EstadisticasView vista;
    private final EstadisticaService servicio;
    private final ReportePdf reportePdf = new ReportePdf();

    private Map<String, Integer> ultimosRecursos;
    private Map<String, Integer> ultimasActividades;

    public EstadisticasControlador(EstadisticasView vista, EstadisticaService servicio) {
        this.vista = vista;
        this.servicio = servicio;

        this.vista.getBtnCargarRecursos().addActionListener(e -> cargarRecursos());
        this.vista.getBtnCargarActividades().addActionListener(e -> cargarActividades());
        this.vista.getBtnImprimirRecursos().addActionListener(e -> imprimirRecursos());
        this.vista.getBtnImprimirActividades().addActionListener(e -> imprimirActividades());
    }

    @Override
    public void inicializar() {
        cargarRecursos();
        cargarActividades();
    }

    @Override
    public void refrescar() {
    }

    private void cargarRecursos() {
        try {
            ultimosRecursos = servicio.recursosPorCategoria(
                    vista.getDesdeRecursos(), vista.getHastaRecursos());
            vista.mostrarRecursos(ultimosRecursos);
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void cargarActividades() {
        try {
            ultimasActividades = servicio.actividadesPorSemana(
                    vista.getDesdeActividades(), vista.getHastaActividades());
            vista.mostrarActividades(ultimasActividades);
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void imprimirRecursos() {
        if (ultimosRecursos == null) {
            vista.mostrarError("Primero cargue las estadisticas de recursos.");
            return;
        }
        imprimir("Recursos Usados", new String[]{"Categoria", "Cantidad"},
                ultimosRecursos, vista.imagenGraficoRecursos(), "estadisticas_recursos.pdf");
    }

    private void imprimirActividades() {
        if (ultimasActividades == null) {
            vista.mostrarError("Primero cargue las estadisticas de actividades.");
            return;
        }
        imprimir("Actividades Realizadas", new String[]{"Semana", "Cantidad"},
                ultimasActividades, vista.imagenGraficoActividades(), "estadisticas_actividades.pdf");
    }

    private void imprimir(String titulo, String[] columnas, Map<String, Integer> datos,
                          BufferedImage grafico, String nombreSugerido) {
        File destino = reportePdf.pedirDestino(vista, nombreSugerido);
        if (destino == null) {
            return;
        }
        try {
            List<String[]> filas = new ArrayList<>();
            for (Map.Entry<String, Integer> entrada : datos.entrySet()) {
                filas.add(new String[]{entrada.getKey(), String.valueOf(entrada.getValue())});
            }
            reportePdf.tablaConGrafico(titulo, columnas, filas, grafico, destino);
            reportePdf.abrir(destino);
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}

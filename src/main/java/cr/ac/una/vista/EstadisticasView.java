package cr.ac.una.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * VISTA de la pestana "Estadisticas" (funcionalidad 8, 20% de la nota).
 * La ven ADMIN y FUNCIONARIO.
 *
 * Son DOS bloques independientes, uno a la par del otro (GridLayout(1,2)):
 *
 *   IZQUIERDA - Recursos:    Desde | Hasta | Cargar
 *                            tabla Categoria | Cantidad
 *                            grafico de barras "Recursos Usados"
 *
 *   DERECHA   - Actividades: Desde | Hasta | Cargar
 *                            tabla Semana | Cantidad
 *                            grafico de barras "Actividades Realizadas"
 *
 * Cada bloque tiene sus propias fechas y su propio boton, o sea que se
 * cargan por separado.
 *
 * GRAFICOS (JFreeChart, ya esta en el pom):
 *   DefaultCategoryDataset ds = new DefaultCategoryDataset();
 *   ds.addValue(cantidad, "Recurso", nombreCategoria);
 *   JFreeChart chart = ChartFactory.createBarChart(titulo, "", "Cantidad", ds);
 *   panelGraficoRecursos.add(new ChartPanel(chart));
 * Guardar la referencia al JFreeChart en el atributo, porque el reporte PDF
 * necesita despues chart.createBufferedImage(ancho, alto).
 */
public class EstadisticasView extends JPanel {

    // Bloque de recursos
    private SelectorFecha fechaDesdeRecursos;
    private SelectorFecha fechaHastaRecursos;
    private JButton btnCargarRecursos;
    private JTable tablaRecursos;
    private DefaultTableModel modeloRecursos;
    private JPanel panelGraficoRecursos;
    private org.jfree.chart.JFreeChart graficoRecursos;

    // Bloque de actividades
    private SelectorFecha fechaDesdeActividades;
    private SelectorFecha fechaHastaActividades;
    private JButton btnCargarActividades;
    private JTable tablaActividades;
    private DefaultTableModel modeloActividades;
    private JPanel panelGraficoActividades;
    private org.jfree.chart.JFreeChart graficoActividades;

    private JButton btnImprimir;

    public EstadisticasView() {
        // TODO: GridLayout(1, 2) con armarBloqueRecursos() y armarBloqueActividades()
    }

    private JPanel armarBloqueRecursos() {
        // TODO
        return null;
    }

    private JPanel armarBloqueActividades() {
        // TODO
        return null;
    }

    /**
     * Recibe el Map que devuelve EstadisticaService.recursosPorCategoria(...)
     * y con el llena LA TABLA Y EL GRAFICO de la izquierda.
     */
    public void mostrarRecursos(Map<String, Integer> datos) {
        // TODO
    }

    /** Igual pero con actividadesPorSemana(...) y el bloque de la derecha. */
    public void mostrarActividades(Map<String, Integer> datos) {
        // TODO
    }

    /** Imagen del grafico para meterla en el PDF. */
    public BufferedImage imagenGraficoRecursos() {
        // TODO: graficoRecursos.createBufferedImage(500, 300)
        return null;
    }

    public BufferedImage imagenGraficoActividades() {
        // TODO
        return null;
    }

    public SelectorFecha getFechaDesdeRecursos() { return fechaDesdeRecursos; }
    public SelectorFecha getFechaHastaRecursos() { return fechaHastaRecursos; }
    public SelectorFecha getFechaDesdeActividades() { return fechaDesdeActividades; }
    public SelectorFecha getFechaHastaActividades() { return fechaHastaActividades; }

    public void mostrarError(String mensaje) {
        // TODO
    }

    public JButton getBtnCargarRecursos() { return btnCargarRecursos; }
    public JButton getBtnCargarActividades() { return btnCargarActividades; }
    public JButton getBtnImprimir() { return btnImprimir; }
    public JTable getTablaRecursos() { return tablaRecursos; }
    public JTable getTablaActividades() { return tablaActividades; }
}

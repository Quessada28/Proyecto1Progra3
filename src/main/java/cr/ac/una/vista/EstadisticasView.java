package cr.ac.una.vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EstadisticasView extends JPanel {

    private static final int ANCHO_GRAFICO = 500;
    private static final int ALTO_GRAFICO = 300;

    private final SelectorFecha fechaDesdeRecursos = new SelectorFecha();
    private final SelectorFecha fechaHastaRecursos = new SelectorFecha();
    private final JButton btnCargarRecursos = ComponentesUI.boton("Cargar");
    private final DefaultTableModel modeloRecursos =
            ComponentesUI.modeloDeSoloLectura(new String[]{"Categoria", "Cantidad"});
    private final JTable tablaRecursos = ComponentesUI.tablaDeSoloLectura(modeloRecursos);
    private final JPanel panelGraficoRecursos = new JPanel(new BorderLayout());
    private JFreeChart graficoRecursos;

    private final SelectorFecha fechaDesdeActividades = new SelectorFecha();
    private final SelectorFecha fechaHastaActividades = new SelectorFecha();
    private final JButton btnCargarActividades = ComponentesUI.boton("Cargar");
    private final DefaultTableModel modeloActividades =
            ComponentesUI.modeloDeSoloLectura(new String[]{"Semana", "Cantidad"});
    private final JTable tablaActividades = ComponentesUI.tablaDeSoloLectura(modeloActividades);
    private final JPanel panelGraficoActividades = new JPanel(new BorderLayout());
    private JFreeChart graficoActividades;

    private final JButton btnImprimirRecursos = ComponentesUI.boton("Imprimir");
    private final JButton btnImprimirActividades = ComponentesUI.boton("Imprimir");

    public EstadisticasView() {
        setLayout(new GridLayout(1, 2, 8, 0));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        establecerFechasPorDefecto();
        add(armarBloqueRecursos());
        add(armarBloqueActividades());
    }

    private void establecerFechasPorDefecto() {
        LocalDate hoy = LocalDate.now();
        LocalDate primero = hoy.withDayOfMonth(1);
        LocalDate ultimo = hoy.withDayOfMonth(hoy.lengthOfMonth());
        fechaDesdeRecursos.setFecha(primero);
        fechaHastaRecursos.setFecha(ultimo);
        fechaDesdeActividades.setFecha(primero);
        fechaHastaActividades.setFecha(ultimo);
    }

    private JPanel armarBloqueRecursos() {
        JPanel bloque = new JPanel(new BorderLayout(0, 6));
        bloque.setBorder(BorderFactory.createTitledBorder("Recursos"));

        JPanel filtros = ComponentesUI.panelConTitulo("Fechas Desde y Hasta");
        ComponentesUI.agregar(filtros, fechaDesdeRecursos, 0, 0);
        ComponentesUI.agregar(filtros, fechaHastaRecursos, 1, 0);
        ComponentesUI.agregar(filtros, btnCargarRecursos, 2, 0);
        ComponentesUI.agregar(filtros, btnImprimirRecursos, 3, 0);
        bloque.add(filtros, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(0, 6));
        JPanel contenedorTabla = new JPanel(new BorderLayout());
        contenedorTabla.setBorder(BorderFactory.createTitledBorder("Estadisticas"));
        contenedorTabla.add(ComponentesUI.conBarras(tablaRecursos, 150), BorderLayout.CENTER);
        centro.add(contenedorTabla, BorderLayout.NORTH);

        panelGraficoRecursos.setBorder(BorderFactory.createTitledBorder("Grafico"));
        panelGraficoRecursos.setPreferredSize(new Dimension(ANCHO_GRAFICO, ALTO_GRAFICO));
        centro.add(panelGraficoRecursos, BorderLayout.CENTER);

        bloque.add(centro, BorderLayout.CENTER);
        return bloque;
    }

    private JPanel armarBloqueActividades() {
        JPanel bloque = new JPanel(new BorderLayout(0, 6));
        bloque.setBorder(BorderFactory.createTitledBorder("Actividades"));

        JPanel filtros = ComponentesUI.panelConTitulo("Fechas Desde y Hasta");
        ComponentesUI.agregar(filtros, fechaDesdeActividades, 0, 0);
        ComponentesUI.agregar(filtros, fechaHastaActividades, 1, 0);
        ComponentesUI.agregar(filtros, btnCargarActividades, 2, 0);
        ComponentesUI.agregar(filtros, btnImprimirActividades, 3, 0);
        bloque.add(filtros, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(0, 6));
        JPanel contenedorTabla = new JPanel(new BorderLayout());
        contenedorTabla.setBorder(BorderFactory.createTitledBorder("Estadisticas"));
        contenedorTabla.add(ComponentesUI.conBarras(tablaActividades, 150), BorderLayout.CENTER);
        centro.add(contenedorTabla, BorderLayout.NORTH);

        panelGraficoActividades.setBorder(BorderFactory.createTitledBorder("Grafico"));
        panelGraficoActividades.setPreferredSize(new Dimension(ANCHO_GRAFICO, ALTO_GRAFICO));
        centro.add(panelGraficoActividades, BorderLayout.CENTER);

        bloque.add(centro, BorderLayout.CENTER);
        return bloque;
    }

    public void mostrarRecursos(Map<String, Integer> datos) {
        ComponentesUI.llenarTabla(modeloRecursos, aFilas(datos));
        graficoRecursos = construirGrafico("Recursos Usados", "Recurso", datos);
        pintar(panelGraficoRecursos, graficoRecursos);
    }

    public void mostrarActividades(Map<String, Integer> datos) {
        ComponentesUI.llenarTabla(modeloActividades, aFilas(datos));
        graficoActividades = construirGrafico("Actividades Realizadas", "Semana", datos);
        pintar(panelGraficoActividades, graficoActividades);
    }

    private List<String[]> aFilas(Map<String, Integer> datos) {
        List<String[]> filas = new ArrayList<>();
        for (Map.Entry<String, Integer> entrada : datos.entrySet()) {
            filas.add(new String[]{entrada.getKey(), String.valueOf(entrada.getValue())});
        }
        return filas;
    }

    private JFreeChart construirGrafico(String titulo, String serie, Map<String, Integer> datos) {
        DefaultCategoryDataset conjunto = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entrada : datos.entrySet()) {
            conjunto.addValue(entrada.getValue(), serie, entrada.getKey());
        }
        return ChartFactory.createBarChart(titulo, "", "Cantidad", conjunto,
                PlotOrientation.VERTICAL, true, true, false);
    }

    private void pintar(JPanel contenedor, JFreeChart grafico) {
        contenedor.removeAll();
        contenedor.add(new ChartPanel(grafico), BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();
    }

    public BufferedImage imagenGraficoRecursos() {
        return graficoRecursos == null
                ? null : graficoRecursos.createBufferedImage(ANCHO_GRAFICO, ALTO_GRAFICO);
    }

    public BufferedImage imagenGraficoActividades() {
        return graficoActividades == null
                ? null : graficoActividades.createBufferedImage(ANCHO_GRAFICO, ALTO_GRAFICO);
    }

    public LocalDate getDesdeRecursos() {
        return fechaDesdeRecursos.getFecha();
    }

    public LocalDate getHastaRecursos() {
        return fechaHastaRecursos.getFecha();
    }

    public LocalDate getDesdeActividades() {
        return fechaDesdeActividades.getFecha();
    }

    public LocalDate getHastaActividades() {
        return fechaHastaActividades.getFecha();
    }

    public void mostrarError(String mensaje) {
        ComponentesUI.error(this, mensaje);
    }

    public JButton getBtnCargarRecursos() {
        return btnCargarRecursos;
    }

    public JButton getBtnCargarActividades() {
        return btnCargarActividades;
    }

    public JButton getBtnImprimirRecursos() {
        return btnImprimirRecursos;
    }

    public JButton getBtnImprimirActividades() {
        return btnImprimirActividades;
    }

    public JTable getTablaRecursos() {
        return tablaRecursos;
    }

    public JTable getTablaActividades() {
        return tablaActividades;
    }
}

package cr.ac.una.vista;

import cr.ac.una.modelo.Categoria;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * VISTA de la pestana "Calendarizacion" (funcionalidad 6).
 * La ven ADMIN y FUNCIONARIO.
 *
 * Filtros: Fecha (SelectorFecha) + Categoria + boton Cargar + boton Imprimir.
 * Abajo la matriz: filas = horas del dia, columnas = recursos de esa categoria.
 *
 * La matriz NO se arma aqui: CalendarioService la devuelve ya lista como
 * String[][] y esta vista solo se la pasa al DefaultTableModel. Por eso el
 * modelo se vuelve a crear entero cada vez que se carga (cambian las columnas
 * al cambiar de categoria).
 *
 * Detalle visual del enunciado: las celdas ocupadas salen con fondo amarillo.
 * Eso se logra con un DefaultTableCellRenderer propio que pinta la celda
 * cuando el valor no esta vacio (ver crearRenderer()).
 */
public class CalendarizacionView extends JPanel {

    private SelectorFecha selectorFecha;
    private JComboBox<Categoria> cmbCategoria;
    private JButton btnCargar;
    private JButton btnImprimir;

    private JTable tabla;
    private DefaultTableModel modelo;

    public CalendarizacionView() {
        // TODO
    }

    private JPanel armarPanelFiltros() {
        // TODO
        return null;
    }

    public void cargarCategorias(List<Categoria> categorias) {
        // TODO
    }

    /** Reemplaza la matriz completa (columnas incluidas). */
    public void cargarMatriz(String[] columnas, String[][] datos) {
        // TODO: modelo = new DefaultTableModel(datos, columnas) con isCellEditable false,
        //       tabla.setModel(modelo) y volver a aplicar el renderer
    }

    /** Renderer que pinta de amarillo las celdas que tienen una reserva. */
    private javax.swing.table.DefaultTableCellRenderer crearRenderer() {
        // TODO
        return null;
    }

    public SelectorFecha getSelectorFecha() { return selectorFecha; }

    /** Id de la categoria escogida, o null si no hay ninguna. */
    public String getIdCategoria() {
        // TODO
        return null;
    }

    public void mostrarError(String mensaje) {
        // TODO
    }

    public JButton getBtnCargar() { return btnCargar; }
    public JButton getBtnImprimir() { return btnImprimir; }
    public JTable getTabla() { return tabla; }
}

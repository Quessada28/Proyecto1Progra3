package cr.ac.una.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * VISTA de la pestana "Actividades" (funcionalidad 7).
 * La ven ADMIN y FUNCIONARIO.
 *
 * Es la mas parecida a Calendarizacion, pero mas simple: el unico filtro es
 * una "Fecha de referencia" y el servicio calcula el lunes de esa semana.
 * Filas = horas del dia, columnas = los siete dias de la semana.
 * Celda = "actividad (funcionario)".
 *
 * Como una celda puede traer varias actividades a la misma hora, conviene
 * que las filas sean mas altas (tabla.setRowHeight(...)).
 */
public class ActividadesView extends JPanel {

    private SelectorFecha selectorFecha;
    private JButton btnCargar;
    private JButton btnImprimir;

    private JTable tabla;
    private DefaultTableModel modelo;

    public ActividadesView() {
        // TODO
    }

    private JPanel armarPanelSemana() {
        // TODO
        return null;
    }

    public void cargarMatriz(String[] columnas, String[][] datos) {
        // TODO
    }

    public SelectorFecha getSelectorFecha() { return selectorFecha; }

    public void mostrarError(String mensaje) {
        // TODO
    }

    public JButton getBtnCargar() { return btnCargar; }
    public JButton getBtnImprimir() { return btnImprimir; }
    public JTable getTabla() { return tabla; }
}

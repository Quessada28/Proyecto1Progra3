package cr.ac.una.vista;

import cr.ac.una.modelo.Categoria;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.util.List;

public class CalendarizacionView extends JPanel {

    private static final Color COLOR_OCUPADO = new Color(255, 250, 205);

    private final SelectorFecha selectorFecha = new SelectorFecha();
    private final JComboBox<Categoria> cmbCategoria = new JComboBox<>();
    private final JButton btnCargar = ComponentesUI.boton("Cargar");
    private final JButton btnImprimir = ComponentesUI.boton("Imprimir");

    private DefaultTableModel modelo = ComponentesUI.modeloDeSoloLectura(new String[]{"Hora"});
    private final JTable tabla = ComponentesUI.tablaDeSoloLectura(modelo);

    public CalendarizacionView() {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        selectorFecha.setFecha(LocalDate.now());
        tabla.setRowHeight(34);
        tabla.setDefaultRenderer(Object.class, crearRenderer());
        add(armarPanelFiltros(), BorderLayout.NORTH);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createTitledBorder("Calendarizacion de recursos"));
        contenedor.add(ComponentesUI.conBarras(tabla, 420), BorderLayout.CENTER);
        add(contenedor, BorderLayout.CENTER);
    }

    private JPanel armarPanelFiltros() {
        JPanel panel = ComponentesUI.panelConTitulo("Filtros");
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Fecha"), 0, 0);
        ComponentesUI.agregar(panel, selectorFecha, 1, 0);
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Categoria"), 2, 0);
        ComponentesUI.agregar(panel, cmbCategoria, 3, 0);
        ComponentesUI.agregar(panel, btnCargar, 4, 0);
        ComponentesUI.agregar(panel, btnImprimir, 5, 0);
        return panel;
    }

    private DefaultTableCellRenderer crearRenderer() {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                           boolean seleccionada, boolean enfocada,
                                                           int fila, int columna) {
                Component celda = super.getTableCellRendererComponent(
                        tabla, valor, seleccionada, enfocada, fila, columna);
                boolean ocupada = columna > 0 && valor != null && !valor.toString().isBlank();
                if (!seleccionada) {
                    celda.setBackground(ocupada ? COLOR_OCUPADO : Color.WHITE);
                }
                return celda;
            }
        };
    }

    public void cargarCategorias(List<Categoria> categorias) {
        cmbCategoria.removeAllItems();
        for (Categoria categoria : categorias) {
            cmbCategoria.addItem(categoria);
        }
    }

    public void cargarMatriz(String[] columnas, String[][] datos) {
        modelo = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tabla.setModel(modelo);
        tabla.setRowHeight(34);
        tabla.setDefaultRenderer(Object.class, crearRenderer());
    }

    public LocalDate getFecha() {
        return selectorFecha.getFecha();
    }

    public SelectorFecha getSelectorFecha() {
        return selectorFecha;
    }

    public String getIdCategoria() {
        Categoria seleccionada = (Categoria) cmbCategoria.getSelectedItem();
        return seleccionada == null ? null : seleccionada.getId();
    }

    public String getDescripcionCategoria() {
        Categoria seleccionada = (Categoria) cmbCategoria.getSelectedItem();
        return seleccionada == null ? "" : seleccionada.getDescripcion();
    }

    public void mostrarError(String mensaje) {
        ComponentesUI.error(this, mensaje);
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public JTable getTabla() {
        return tabla;
    }
}

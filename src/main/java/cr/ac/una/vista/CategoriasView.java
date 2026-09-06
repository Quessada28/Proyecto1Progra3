package cr.ac.una.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.util.List;

public class CategoriasView extends JPanel {

    private static final String[] COLUMNAS = {"Id", "Descripcion"};

    private final JTextField txtBuscarDescripcion = ComponentesUI.campo(24);
    private final JButton btnBuscar = ComponentesUI.boton("Buscar");
    private final JButton btnImprimir = ComponentesUI.boton("Imprimir");

    private final JTextField txtId = ComponentesUI.campo(16);
    private final JTextField txtDescripcion = ComponentesUI.campo(28);
    private final JButton btnGuardar = ComponentesUI.boton("Guardar");
    private final JButton btnBorrar = ComponentesUI.boton("Borrar");
    private final JButton btnLimpiar = ComponentesUI.boton("Limpiar");

    private final DefaultTableModel modelo = ComponentesUI.modeloDeSoloLectura(COLUMNAS);
    private final JTable tabla = ComponentesUI.tablaDeSoloLectura(modelo);

    public CategoriasView() {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        txtId.setEditable(false);
        add(armarPanelBusqueda(), BorderLayout.NORTH);
        add(armarFormulario(), BorderLayout.CENTER);
        add(armarListado(), BorderLayout.SOUTH);
    }

    private JPanel armarPanelBusqueda() {
        JPanel panel = ComponentesUI.panelConTitulo("Busqueda");
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Descripcion"), 0, 0);
        ComponentesUI.agregar(panel, txtBuscarDescripcion, 1, 0);
        ComponentesUI.agregar(panel, btnBuscar, 2, 0);
        ComponentesUI.agregar(panel, btnImprimir, 3, 0);
        return panel;
    }

    private JPanel armarFormulario() {
        JPanel panel = ComponentesUI.panelConTitulo("Categoria");
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("ID"), 0, 0);
        ComponentesUI.agregar(panel, txtId, 1, 0);
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Descripcion"), 0, 1);
        ComponentesUI.agregar(panel, txtDescripcion, 1, 1);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        botones.add(btnGuardar);
        botones.add(btnBorrar);
        botones.add(btnLimpiar);

        GridBagConstraints fila = ComponentesUI.restricciones(0, 2);
        fila.gridwidth = 2;
        panel.add(botones, fila);
        return panel;
    }

    private JPanel armarListado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Listado"));
        panel.add(ComponentesUI.conBarras(tabla, 230), BorderLayout.CENTER);
        return panel;
    }

    public void cargarTabla(List<String[]> filas) {
        ComponentesUI.llenarTabla(modelo, filas);
    }

    public void mostrarEnFormulario(String id, String descripcion) {
        txtId.setText(id);
        txtDescripcion.setText(descripcion);
    }

    public String getBuscarDescripcion() {
        return txtBuscarDescripcion.getText().trim();
    }

    public String getIdFormulario() {
        return txtId.getText().trim();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public String getIdSeleccionado() {
        int fila = tabla.getSelectedRow();
        return fila < 0 ? null : (String) modelo.getValueAt(fila, 0);
    }

    public void limpiarFormulario() {
        txtId.setText("");
        txtDescripcion.setText("");
        tabla.clearSelection();
    }

    public void mostrarError(String mensaje) {
        ComponentesUI.error(this, mensaje);
    }

    public void mostrarInfo(String mensaje) {
        ComponentesUI.info(this, mensaje);
    }

    public boolean confirmar(String mensaje) {
        return ComponentesUI.confirmar(this, mensaje);
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTable getTabla() {
        return tabla;
    }
}

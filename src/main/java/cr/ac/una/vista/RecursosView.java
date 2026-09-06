package cr.ac.una.vista;

import cr.ac.una.modelo.Categoria;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.util.List;

public class RecursosView extends JPanel {

    private static final String[] COLUMNAS = {"Id", "Categoria", "Descripcion"};
    private static final Categoria TODAS = new Categoria("", "(todas)");

    private final JComboBox<Categoria> cmbFiltroCategoria = new JComboBox<>();
    private final JTextField txtFiltroDescripcion = ComponentesUI.campo(20);
    private final JButton btnBuscar = ComponentesUI.boton("Buscar");
    private final JButton btnImprimir = ComponentesUI.boton("Imprimir");

    private final JTextField txtId = ComponentesUI.campo(14);
    private final JComboBox<Categoria> cmbCategoria = new JComboBox<>();
    private final JTextField txtDescripcion = ComponentesUI.campo(26);
    private final JButton btnGuardar = ComponentesUI.boton("Guardar");
    private final JButton btnBorrar = ComponentesUI.boton("Borrar");
    private final JButton btnLimpiar = ComponentesUI.boton("Limpiar");

    private final DefaultTableModel modelo = ComponentesUI.modeloDeSoloLectura(COLUMNAS);
    private final JTable tabla = ComponentesUI.tablaDeSoloLectura(modelo);

    public RecursosView() {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(armarPanelFiltro(), BorderLayout.NORTH);
        add(armarFormulario(), BorderLayout.CENTER);
        add(armarListado(), BorderLayout.SOUTH);
    }

    private JPanel armarPanelFiltro() {
        JPanel panel = ComponentesUI.panelConTitulo("Filtro");
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Categoria"), 0, 0);
        ComponentesUI.agregar(panel, cmbFiltroCategoria, 1, 0);
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Descripcion"), 2, 0);
        ComponentesUI.agregar(panel, txtFiltroDescripcion, 3, 0);
        ComponentesUI.agregar(panel, btnBuscar, 4, 0);
        ComponentesUI.agregar(panel, btnImprimir, 5, 0);
        return panel;
    }

    private JPanel armarFormulario() {
        JPanel panel = ComponentesUI.panelConTitulo("Recurso");
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("ID"), 0, 0);
        ComponentesUI.agregar(panel, txtId, 1, 0);
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Categoria"), 0, 1);
        ComponentesUI.agregar(panel, cmbCategoria, 1, 1);
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Descripcion"), 0, 2);
        ComponentesUI.agregar(panel, txtDescripcion, 1, 2);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        botones.add(btnGuardar);
        botones.add(btnBorrar);
        botones.add(btnLimpiar);

        GridBagConstraints fila = ComponentesUI.restricciones(0, 3);
        fila.gridwidth = 2;
        panel.add(botones, fila);
        return panel;
    }

    private JPanel armarListado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Listado"));
        panel.add(ComponentesUI.conBarras(tabla, 220), BorderLayout.CENTER);
        return panel;
    }

    public void cargarCategorias(List<Categoria> categorias) {
        cmbFiltroCategoria.removeAllItems();
        cmbCategoria.removeAllItems();
        cmbFiltroCategoria.addItem(TODAS);
        for (Categoria categoria : categorias) {
            cmbFiltroCategoria.addItem(categoria);
            cmbCategoria.addItem(categoria);
        }
    }

    public void cargarTabla(List<String[]> filas) {
        ComponentesUI.llenarTabla(modelo, filas);
    }

    public void mostrarEnFormulario(String id, String idCategoria, String descripcion) {
        txtId.setText(id);
        txtDescripcion.setText(descripcion);
        txtId.setEditable(false);
        for (int i = 0; i < cmbCategoria.getItemCount(); i++) {
            if (cmbCategoria.getItemAt(i).getId().equals(idCategoria)) {
                cmbCategoria.setSelectedIndex(i);
                return;
            }
        }
    }

    public int cantidadCategorias() {
        return cmbCategoria.getItemCount();
    }

    public String getIdCategoriaFiltro() {
        Categoria seleccionada = (Categoria) cmbFiltroCategoria.getSelectedItem();
        if (seleccionada == null || seleccionada.getId().isEmpty()) {
            return null;
        }
        return seleccionada.getId();
    }

    public String getFiltroDescripcion() {
        return txtFiltroDescripcion.getText().trim();
    }

    public String getIdFormulario() {
        return txtId.getText().trim();
    }

    public String getIdCategoria() {
        Categoria seleccionada = (Categoria) cmbCategoria.getSelectedItem();
        return seleccionada == null ? null : seleccionada.getId();
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
        txtId.setEditable(true);
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

package cr.ac.una.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * VISTA de la pestana "Categorias" (funcionalidad 4). Solo ADMIN.
 *
 * Busqueda por descripcion + formulario (el ID es autogenerado, va no
 * editable) + listado. Es la mas sencilla de las tres pantallas de
 * mantenimiento, asi que conviene hacer esta de primero y despues copiar
 * la estructura para Funcionarios y Recursos.
 */
public class CategoriasView extends JPanel {

    private JTextField txtBuscarDescripcion;
    private JButton btnBuscar;
    private JButton btnImprimir;

    private JTextField txtId;          // no editable: lo genera GeneradorId
    private JTextField txtDescripcion;
    private JButton btnGuardar;
    private JButton btnBorrar;
    private JButton btnLimpiar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public CategoriasView() {
        // TODO
    }

    private JPanel armarPanelBusqueda() {
        // TODO
        return null;
    }

    private JPanel armarFormulario() {
        // TODO
        return null;
    }

    private JPanel armarListado() {
        // TODO
        return null;
    }

    public void cargarTabla(List<String[]> filas) {
        // TODO
    }

    public void mostrarEnFormulario(String id, String descripcion) {
        // TODO
    }

    public String getBuscarDescripcion() {
        // TODO
        return null;
    }

    /** Vacio cuando es una categoria nueva; con valor cuando se esta modificando. */
    public String getIdFormulario() {
        // TODO
        return null;
    }

    public String getDescripcion() {
        // TODO
        return null;
    }

    public void limpiarFormulario() {
        // TODO
    }

    public void mostrarError(String mensaje) {
        // TODO
    }

    public void mostrarInfo(String mensaje) {
        // TODO
    }

    public boolean confirmar(String mensaje) {
        // TODO
        return false;
    }

    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnImprimir() { return btnImprimir; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnBorrar() { return btnBorrar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JTable getTabla() { return tabla; }
}

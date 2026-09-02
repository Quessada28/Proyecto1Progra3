package cr.ac.una.vista;

import cr.ac.una.modelo.Categoria;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * VISTA de la pestana "Recursos" (funcionalidad 5). Solo ADMIN.
 *
 * Dos diferencias con Categorias:
 *   - hay DOS combos de categoria: cmbFiltroCategoria (para filtrar el
 *     listado) y cmbCategoria (la categoria del recurso que se edita)
 *   - el ID lo digita el administrador (es el numero de activo), NO se genera
 *
 * Los combos se llenan con objetos Categoria y no con Strings, para poder
 * sacar el id de la categoria escogida sin tener que buscarla por descripcion.
 */
public class RecursosView extends JPanel {

    private JComboBox<Categoria> cmbFiltroCategoria;
    private JTextField txtFiltroDescripcion;
    private JButton btnBuscar;
    private JButton btnImprimir;

    private JTextField txtId;
    private JComboBox<Categoria> cmbCategoria;
    private JTextField txtDescripcion;
    private JButton btnGuardar;
    private JButton btnBorrar;
    private JButton btnLimpiar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public RecursosView() {
        // TODO
    }

    private JPanel armarPanelFiltro() {
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

    /** Llena los DOS combos. En el de filtro conviene una primera opcion "(todas)". */
    public void cargarCategorias(List<Categoria> categorias) {
        // TODO
    }

    public void cargarTabla(List<String[]> filas) {
        // TODO
    }

    public void mostrarEnFormulario(String id, String idCategoria, String descripcion) {
        // TODO: seleccionar en cmbCategoria la Categoria cuyo id coincide
    }

    /** Id de la categoria del filtro, o null si esta en "(todas)". */
    public String getIdCategoriaFiltro() {
        // TODO
        return null;
    }

    public String getFiltroDescripcion() {
        // TODO
        return null;
    }

    public String getIdFormulario() {
        // TODO
        return null;
    }

    /** Id de la categoria escogida en el formulario. */
    public String getIdCategoria() {
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

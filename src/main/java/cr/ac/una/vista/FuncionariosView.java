package cr.ac.una.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * VISTA de la pestana "Funcionarios" (funcionalidad 3). Solo ADMIN.
 *
 * Tres zonas, como en la captura:
 *   Busqueda:   ID | Nombre | Buscar | Imprimir
 *   Funcionario (formulario): ID | Nombre | Telefono | Guardar | Borrar | Limpiar
 *   Listado:    JTable con Id | Nombre | Telefono
 *
 * Detalle del enunciado: al AGREGAR un funcionario se le crea tambien su
 * usuario con clave = id. Eso lo hace el servicio, no la vista. Lo que si
 * conviene aqui es bloquear el campo ID (setEditable(false)) cuando se esta
 * modificando uno que ya existe, porque el id es la llave.
 */
public class FuncionariosView extends JPanel {

    private JTextField txtBuscarId;
    private JTextField txtBuscarNombre;
    private JButton btnBuscar;
    private JButton btnImprimir;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JButton btnGuardar;
    private JButton btnBorrar;
    private JButton btnLimpiar;

    private JTable tabla;
    private DefaultTableModel modelo;

    public FuncionariosView() {
        // TODO: BorderLayout con armarPanelBusqueda() al norte,
        //       armarFormulario() al centro y armarListado() al sur
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

    /** Vuelca las filas en la tabla. */
    public void cargarTabla(List<String[]> filas) {
        // TODO
    }

    /** Pasa los datos de la fila marcada al formulario (evento de seleccion de la tabla). */
    public void mostrarEnFormulario(String id, String nombre, String telefono) {
        // TODO: al mostrar uno existente, bloquear el campo id
    }

    public String getBuscarId() {
        // TODO
        return null;
    }

    public String getBuscarNombre() {
        // TODO
        return null;
    }

    public String getIdFormulario() {
        // TODO
        return null;
    }

    public String getNombre() {
        // TODO
        return null;
    }

    public String getTelefono() {
        // TODO
        return null;
    }

    /** Campos en blanco y campo id habilitado de nuevo. */
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

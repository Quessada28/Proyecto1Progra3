package cr.ac.una.vista;

import cr.ac.una.modelo.Categoria;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * VISTA de la pestana "Reservas" (funcionalidad 2, 25% de la nota).
 * Solo la ve un usuario tipo FUNCIONARIO.
 *
 * Se divide en dos zonas, igual que la captura del enunciado:
 *
 *   1) "Nueva reserva" (JPanel con TitledBorder)
 *      Frase + boton Extraer (IA)  |  Actividad  |  Fecha (SelectorFecha)
 *      Hora inicio / Hora fin (JComboBox con Formatos.horasDelDia())
 *      Categorias requeridas: JList con SELECCION MULTIPLE
 *      Botones: Reservar / Cancelar reserva seleccionada / Limpiar
 *
 *   2) "Mis reservas" (JTable + boton Imprimir)
 *      Columnas: Id | Actividad | Fecha | Horario | Recursos | Estado
 *
 * OJO CON EL JList DE CATEGORIAS: el usuario ve la descripcion pero el
 * controlador necesita los IDS. Por eso el modelo del JList guarda objetos
 * Categoria completos (Categoria.toString() devuelve la descripcion) y
 * getCategoriasSeleccionadas() devuelve la lista de ids.
 */
public class ReservasView extends JPanel {

    private JTextArea txtFrase;
    private JButton btnExtraer;

    private JTextField txtActividad;
    private SelectorFecha selectorFecha;
    private JComboBox<String> cmbHoraInicio;
    private JComboBox<String> cmbHoraFin;
    private JList<Categoria> listaCategorias;
    private DefaultListModel<Categoria> modeloCategorias;

    private JButton btnReservar;
    private JButton btnCancelarReserva;
    private JButton btnLimpiar;

    private JTable tablaReservas;
    private DefaultTableModel modeloReservas;
    private JButton btnImprimir;

    public ReservasView() {
        // TODO: setLayout(new BorderLayout()), armarPanelNuevaReserva() al norte,
        //       armarPanelMisReservas() al centro
    }

    private JPanel armarPanelNuevaReserva() {
        // TODO
        return null;
    }

    private JPanel armarPanelMisReservas() {
        // TODO: modeloReservas con las columnas; hacer las celdas NO editables
        //       sobreescribiendo isCellEditable(...) como en el ejemplo del profe
        return null;
    }

    // ---- Llenado (lo llama el controlador) ----

    /** Carga el JList de categorias con las que existen en el sistema. */
    public void cargarCategorias(List<Categoria> categorias) {
        // TODO
    }

    /** Vuelca las filas ya formateadas en la tabla "Mis reservas". */
    public void cargarReservas(List<String[]> filas) {
        // TODO: modeloReservas.setRowCount(0) y luego addRow por cada fila
    }

    // ---- Lectura del formulario ----

    public String getFrase() {
        // TODO
        return null;
    }

    public String getActividad() {
        // TODO
        return null;
    }

    public SelectorFecha getSelectorFecha() { return selectorFecha; }

    /** Texto de la hora escogida ("8:00 a. m."); el controlador la convierte a LocalTime. */
    public String getHoraInicio() {
        // TODO
        return null;
    }

    public String getHoraFin() {
        // TODO
        return null;
    }

    /** Ids de las categorias marcadas en el JList (seleccion multiple). */
    public List<String> getCategoriasSeleccionadas() {
        // TODO: listaCategorias.getSelectedValuesList() y sacarle el id a cada una
        return null;
    }

    /** Id de la reserva marcada en la tabla, o null si no hay ninguna marcada. */
    public String getIdReservaSeleccionada() {
        // TODO: tablaReservas.getSelectedRow() y leer la columna 0
        return null;
    }

    // ---- Escritura del formulario (la usa el boton Extraer de la IA) ----

    /** Llena actividad, fecha, horas y marca las categorias que devolvio el LLM. */
    public void llenarFormulario(String actividad, java.time.LocalDate fecha,
                                 java.time.LocalTime inicio, java.time.LocalTime fin,
                                 List<String> idsCategorias) {
        // TODO: recordar que el usuario puede corregir todo antes de reservar
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

    /** Confirmacion si/no, para "seguro que desea cancelar la reserva?". */
    public boolean confirmar(String mensaje) {
        // TODO: JOptionPane.showConfirmDialog(...) == JOptionPane.YES_OPTION
        return false;
    }

    public JButton getBtnExtraer() { return btnExtraer; }
    public JButton getBtnReservar() { return btnReservar; }
    public JButton getBtnCancelarReserva() { return btnCancelarReserva; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JButton getBtnImprimir() { return btnImprimir; }
    public JTable getTablaReservas() { return tablaReservas; }
}

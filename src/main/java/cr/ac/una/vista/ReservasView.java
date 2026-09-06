package cr.ac.una.vista;

import cr.ac.una.modelo.Categoria;
import cr.ac.una.util.Formatos;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservasView extends JPanel {

    private static final String[] COLUMNAS =
            {"Id", "Actividad", "Fecha", "Horario", "Recursos", "Estado"};

    private final JTextArea txtFrase = new JTextArea(3, 40);
    private final JButton btnExtraer = ComponentesUI.boton("Extraer IA");

    private final JTextField txtActividad = ComponentesUI.campo(30);
    private final SelectorFecha selectorFecha = new SelectorFecha();
    private final JComboBox<String> cmbHoraInicio = new JComboBox<>(Formatos.horasDelDia());
    private final JComboBox<String> cmbHoraFin = new JComboBox<>(Formatos.horasDelDia());
    private final DefaultListModel<Categoria> modeloCategorias = new DefaultListModel<>();
    private final JList<Categoria> listaCategorias = new JList<>(modeloCategorias);

    private final JButton btnReservar = ComponentesUI.boton("Reservar");
    private final JButton btnCancelarReserva = ComponentesUI.boton("Cancelar reserva seleccionada");
    private final JButton btnLimpiar = ComponentesUI.boton("Limpiar");

    private final DefaultTableModel modeloReservas = ComponentesUI.modeloDeSoloLectura(COLUMNAS);
    private final JTable tablaReservas = ComponentesUI.tablaDeSoloLectura(modeloReservas);
    private final JButton btnImprimir = ComponentesUI.boton("Imprimir");

    public ReservasView() {
        setLayout(new BorderLayout(0, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(armarPanelNuevaReserva(), BorderLayout.NORTH);
        add(armarPanelMisReservas(), BorderLayout.CENTER);
    }

    private JPanel armarPanelNuevaReserva() {
        JPanel panel = ComponentesUI.panelConTitulo("Nueva reserva");

        txtFrase.setLineWrap(true);
        txtFrase.setWrapStyleWord(true);
        JScrollPane scrollFrase = new JScrollPane(txtFrase);
        scrollFrase.setPreferredSize(new Dimension(520, 56));

        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Frase"), 0, 0);
        GridBagConstraints frase = ComponentesUI.restricciones(1, 0);
        frase.gridwidth = 3;
        panel.add(scrollFrase, frase);
        ComponentesUI.agregar(panel, btnExtraer, 4, 0);

        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Actividad"), 0, 1);
        ComponentesUI.agregar(panel, txtActividad, 1, 1, 3);

        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Fecha"), 0, 2);
        ComponentesUI.agregar(panel, selectorFecha, 1, 2);
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Hora inicio"), 2, 2);
        ComponentesUI.agregar(panel, cmbHoraInicio, 3, 2);

        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Categorias requeridas"), 0, 3);
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("(seleccion multiple)"), 1, 3);
        ComponentesUI.agregar(panel, ComponentesUI.etiqueta("Hora fin"), 2, 3);
        ComponentesUI.agregar(panel, cmbHoraFin, 3, 3);

        listaCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listaCategorias.setVisibleRowCount(4);
        JScrollPane scrollCategorias = new JScrollPane(listaCategorias);
        scrollCategorias.setPreferredSize(new Dimension(520, 92));
        scrollCategorias.setBorder(BorderFactory.createTitledBorder("Categorias"));

        GridBagConstraints categorias = ComponentesUI.restricciones(0, 4);
        categorias.gridwidth = 5;
        panel.add(scrollCategorias, categorias);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        botones.add(btnReservar);
        botones.add(btnCancelarReserva);
        botones.add(btnLimpiar);

        GridBagConstraints fila = ComponentesUI.restricciones(0, 5);
        fila.gridwidth = 5;
        panel.add(botones, fila);

        cmbHoraInicio.setSelectedItem(Formatos.hora12(LocalTime.of(8, 0)));
        cmbHoraFin.setSelectedItem(Formatos.hora12(LocalTime.of(10, 0)));
        selectorFecha.setFecha(LocalDate.now());
        return panel;
    }

    private JPanel armarPanelMisReservas() {
        JPanel panel = new JPanel(new BorderLayout(8, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Mis reservas"));
        panel.add(ComponentesUI.conBarras(tablaReservas, 200), BorderLayout.CENTER);

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
        derecha.add(btnImprimir);
        panel.add(derecha, BorderLayout.EAST);
        return panel;
    }

    public void cargarCategorias(List<Categoria> categorias) {
        modeloCategorias.clear();
        for (Categoria categoria : categorias) {
            modeloCategorias.addElement(categoria);
        }
    }

    public void cargarReservas(List<String[]> filas) {
        ComponentesUI.llenarTabla(modeloReservas, filas);
    }

    public String getFrase() {
        return txtFrase.getText().trim();
    }

    public String getActividad() {
        return txtActividad.getText().trim();
    }

    public LocalDate getFecha() {
        return selectorFecha.getFecha();
    }

    public LocalTime getHoraInicio() {
        return Formatos.leerHora12((String) cmbHoraInicio.getSelectedItem());
    }

    public LocalTime getHoraFin() {
        return Formatos.leerHora12((String) cmbHoraFin.getSelectedItem());
    }

    public List<String> getCategoriasSeleccionadas() {
        List<String> ids = new ArrayList<>();
        for (Categoria categoria : listaCategorias.getSelectedValuesList()) {
            ids.add(categoria.getId());
        }
        return ids;
    }

    public String getIdReservaSeleccionada() {
        int fila = tablaReservas.getSelectedRow();
        return fila < 0 ? null : (String) modeloReservas.getValueAt(fila, 0);
    }

    public void llenarFormulario(String actividad, LocalDate fecha, LocalTime inicio,
                                 LocalTime fin, List<String> idsCategorias) {
        if (actividad != null) {
            txtActividad.setText(actividad);
        }
        if (fecha != null) {
            selectorFecha.setFecha(fecha);
        }
        if (inicio != null) {
            cmbHoraInicio.setSelectedItem(Formatos.hora12(inicio));
        }
        if (fin != null) {
            cmbHoraFin.setSelectedItem(Formatos.hora12(fin));
        }
        seleccionarCategorias(idsCategorias);
    }

    private void seleccionarCategorias(List<String> idsCategorias) {
        listaCategorias.clearSelection();
        if (idsCategorias == null || idsCategorias.isEmpty()) {
            return;
        }
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < modeloCategorias.size(); i++) {
            if (idsCategorias.contains(modeloCategorias.get(i).getId())) {
                indices.add(i);
            }
        }
        int[] seleccion = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            seleccion[i] = indices.get(i);
        }
        listaCategorias.setSelectedIndices(seleccion);
    }

    public void limpiarFormulario() {
        txtFrase.setText("");
        txtActividad.setText("");
        selectorFecha.setFecha(LocalDate.now());
        cmbHoraInicio.setSelectedItem(Formatos.hora12(LocalTime.of(8, 0)));
        cmbHoraFin.setSelectedItem(Formatos.hora12(LocalTime.of(10, 0)));
        listaCategorias.clearSelection();
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

    public void setExtraccionEnProceso(boolean enProceso) {
        btnExtraer.setEnabled(!enProceso);
        btnExtraer.setText(enProceso ? "Extrayendo..." : "Extraer IA");
    }

    public JButton getBtnExtraer() {
        return btnExtraer;
    }

    public JButton getBtnReservar() {
        return btnReservar;
    }

    public JButton getBtnCancelarReserva() {
        return btnCancelarReserva;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnImprimir() {
        return btnImprimir;
    }

    public JTable getTablaReservas() {
        return tablaReservas;
    }
}

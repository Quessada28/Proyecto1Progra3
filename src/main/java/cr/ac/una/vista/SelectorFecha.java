package cr.ac.una.vista;

import cr.ac.una.util.Formatos;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class SelectorFecha extends JPanel {

    private final JTextField txtFecha = new JTextField(20);
    private final JButton btnCalendario = new JButton("...");

    public SelectorFecha() {
        super(new BorderLayout(4, 0));
        txtFecha.setPreferredSize(new Dimension(195, 26));
        btnCalendario.setPreferredSize(new Dimension(34, 26));
        btnCalendario.setFocusPainted(false);
        btnCalendario.addActionListener(e -> abrirCalendario());
        add(txtFecha, BorderLayout.CENTER);
        add(btnCalendario, BorderLayout.EAST);
    }

    private void abrirCalendario() {
        LocalDate actual = getFecha() == null ? LocalDate.now() : getFecha();
        SpinnerDateModel modelo = new SpinnerDateModel();
        modelo.setValue(aDate(actual));

        JSpinner spinner = new JSpinner(modelo);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));

        int opcion = JOptionPane.showConfirmDialog(this, spinner, "Seleccione la fecha",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (opcion == JOptionPane.OK_OPTION) {
            setFecha(aLocalDate((Date) spinner.getValue()));
        }
    }

    public LocalDate getFecha() {
        return Formatos.leerFechaLarga(txtFecha.getText());
    }

    public void setFecha(LocalDate fecha) {
        txtFecha.setText(fecha == null ? "" : Formatos.fechaLarga(fecha));
        txtFecha.setCaretPosition(0);
    }

    public void limpiar() {
        txtFecha.setText("");
    }

    public void setEditable(boolean editable) {
        txtFecha.setEditable(editable);
    }

    private static Date aDate(LocalDate fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(fecha.getYear(), fecha.getMonthValue() - 1, fecha.getDayOfMonth(), 0, 0, 0);
        calendario.set(Calendar.MILLISECOND, 0);
        return calendario.getTime();
    }

    private static LocalDate aLocalDate(Date fecha) {
        return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

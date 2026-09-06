package cr.ac.una.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;

public class CambiarClaveView extends JDialog {

    private final JPasswordField txtClaveActual = new JPasswordField(16);
    private final JPasswordField txtClaveNueva = new JPasswordField(16);
    private final JPasswordField txtClaveNuevaConfirmacion = new JPasswordField(16);
    private final JButton btnAceptar = ComponentesUI.boton("Aceptar");
    private final JButton btnCancelar = ComponentesUI.boton("Cancelar");

    public CambiarClaveView(Frame padre) {
        super(padre, "Cambiar Clave", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        inicializarComponentes();
        pack();
        setLocationRelativeTo(padre);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(0, 8));

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(BorderFactory.createEmptyBorder(14, 16, 6, 16));
        ComponentesUI.agregar(formulario, ComponentesUI.etiqueta("Clave Actual"), 0, 0);
        ComponentesUI.agregar(formulario, txtClaveActual, 1, 0);
        ComponentesUI.agregar(formulario, ComponentesUI.etiqueta("Clave Nueva"), 0, 1);
        ComponentesUI.agregar(formulario, txtClaveNueva, 1, 1);
        ComponentesUI.agregar(formulario, ComponentesUI.etiqueta("Confirmar Clave"), 0, 2);
        ComponentesUI.agregar(formulario, txtClaveNuevaConfirmacion, 1, 2);
        add(formulario, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));
        botones.add(btnAceptar);
        botones.add(btnCancelar);
        add(botones, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnAceptar);
    }

    public String getClaveActual() {
        return new String(txtClaveActual.getPassword());
    }

    public String getClaveNueva() {
        return new String(txtClaveNueva.getPassword());
    }

    public String getClaveNuevaConfirmacion() {
        return new String(txtClaveNuevaConfirmacion.getPassword());
    }

    public void limpiar() {
        txtClaveActual.setText("");
        txtClaveNueva.setText("");
        txtClaveNuevaConfirmacion.setText("");
        txtClaveActual.requestFocusInWindow();
    }

    public void mostrarError(String mensaje) {
        ComponentesUI.error(this, mensaje);
    }

    public void mostrarInfo(String mensaje) {
        ComponentesUI.info(this, mensaje);
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}

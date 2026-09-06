package cr.ac.una.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class LoginView extends JFrame {

    private final JTextField txtId = ComponentesUI.campo(16);
    private final JPasswordField txtClave = new JPasswordField(16);
    private final JButton btnIngresar = ComponentesUI.boton("Ingresar");
    private final JButton btnCancelar = ComponentesUI.boton("Cancelar");
    private final JButton btnCambiar = ComponentesUI.boton("Cambiar");

    public LoginView() {
        setTitle("SISTEMA DE RESERVAS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        inicializarComponentes();
        pack();
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(0, 8));

        JLabel titulo = new JLabel("Ingreso al sistema", SwingConstants.CENTER);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 16f));
        titulo.setBorder(BorderFactory.createEmptyBorder(14, 10, 4, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new java.awt.GridBagLayout());
        formulario.setBorder(BorderFactory.createEmptyBorder(6, 20, 6, 20));
        ComponentesUI.agregar(formulario, ComponentesUI.etiqueta("ID"), 0, 0);
        ComponentesUI.agregar(formulario, txtId, 1, 0);
        ComponentesUI.agregar(formulario, ComponentesUI.etiqueta("Clave"), 0, 1);
        ComponentesUI.agregar(formulario, txtClave, 1, 1);
        add(formulario, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));
        botones.add(btnIngresar);
        botones.add(btnCancelar);
        botones.add(btnCambiar);
        add(botones, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnIngresar);
        setPreferredSize(new Dimension(360, 210));
    }

    public String getId() {
        return txtId.getText().trim();
    }

    public String getClave() {
        return new String(txtClave.getPassword());
    }

    public void limpiar() {
        txtId.setText("");
        txtClave.setText("");
        txtId.requestFocusInWindow();
    }

    public void limpiarClave() {
        txtClave.setText("");
        txtClave.requestFocusInWindow();
    }

    public void mostrarError(String mensaje) {
        ComponentesUI.error(this, mensaje);
    }

    public void mostrarInfo(String mensaje) {
        ComponentesUI.info(this, mensaje);
    }

    public JButton getBtnIngresar() {
        return btnIngresar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnCambiar() {
        return btnCambiar;
    }
}

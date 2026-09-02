package cr.ac.una.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CambiarClaveView extends JDialog {

    private JPasswordField txtClaveActual;
    private JPasswordField txtClaveNueva1;
    private JPasswordField txtClaveNueva2;
    private JButton btnCambiar;
    private JButton btnCancelar;

    public CambiarClaveView(Window parent) {
        super(parent, "Cambiar Clave", ModalityType.APPLICATION_MODAL);
        setSize(350, 220);
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Clave Actual
        gbc.gridx = 0; gbc.gridy = 0;
        panelPrincipal.add(new JLabel("Clave Actual:"), gbc);

        txtClaveActual = new JPasswordField(12);
        gbc.gridx = 1;
        panelPrincipal.add(txtClaveActual, gbc);

        // Fila 1: Clave Nueva
        gbc.gridx = 0; gbc.gridy = 1;
        panelPrincipal.add(new JLabel("Clave Nueva:"), gbc);

        txtClaveNueva1 = new JPasswordField(12);
        gbc.gridx = 1;
        panelPrincipal.add(txtClaveNueva1, gbc);

        // Fila 2: Confirmar Clave
        gbc.gridx = 0; gbc.gridy = 2;
        panelPrincipal.add(new JLabel("Confirmar Clave:"), gbc);

        txtClaveNueva2 = new JPasswordField(12);
        gbc.gridx = 1;
        panelPrincipal.add(txtClaveNueva2, gbc);

        // Fila 3: Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnCambiar = new JButton("Cambiar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnCambiar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelPrincipal.add(panelBotones, gbc);

        add(panelPrincipal);
    }

    // --- GETTERS ---
    public String getClaveActual() {
        return new String(txtClaveActual.getPassword());
    }

    public String getClaveNueva1() {
        return new String(txtClaveNueva1.getPassword());
    }

    public String getClaveNueva2() {
        return new String(txtClaveNueva2.getPassword());
    }

    // --- LISTENERS Y MENSAJES ---
    public void addCambiarListener(ActionListener listener) {
        btnCambiar.addActionListener(listener);
    }

    public void addCancelarListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }

    public void mostrarError(String msj) {
        JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito(String msj) {
        JOptionPane.showMessageDialog(this, msj, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
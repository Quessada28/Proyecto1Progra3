package cr.ac.una.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField txtId;
    private JPasswordField txtContra;
    private JButton btnIngresar;
    private JButton btnCambiarClave;
    private JButton btnCancelar;

    public LoginView() { // Ventana

        setTitle("SISTEMA DE RESERVAS - ADMIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 240);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {

        // Panel principal con margen interno
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Icon iconoNativo = UIManager.getIcon("OptionPane.informationIcon");
        JLabel lblIcono = new JLabel(iconoNativo, SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(lblIcono, gbc);

        // Fila 1: Campo ID
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(new JLabel("ID:"), gbc);

        txtId = new JTextField(15);
        gbc.gridx = 1;
        panelPrincipal.add(txtId, gbc);

        // Fila 2: Campo Contraseña
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(new JLabel("Clave:"), gbc);

        txtContra = new JPasswordField(15);
        gbc.gridx = 1;
        panelPrincipal.add(txtContra, gbc);

        // Fila 3: Botones principales
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnIngresar = new JButton("Ingresar");
        btnCambiarClave = new JButton("Cambiar clave");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnIngresar);
        panelBotones.add(btnCambiarClave);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelPrincipal.add(panelBotones, gbc);

        add(panelPrincipal);

    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JPasswordField getTxtContra() {
        return txtContra;
    }

    public void addIngresarListener(ActionListener listener) {
        btnIngresar.addActionListener(listener);
    }

    public void addCambiarClaveListener(ActionListener listener) {
        btnCambiarClave.addActionListener(listener);
    }

    public void addCancelarListener(ActionListener listener) {
        btnCancelar.addActionListener(listener);
    }

    // --- MÉTODOS PARA MENSAJES DE ALERTA (JOptionPane) ---

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de autenticación", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtContra.setText("");
    }
}


package cr.ac.una.vista;

import javax.swing.*;

/**
 * VISTA del login (funcionalidad 1). Es la primera ventana que se abre.
 *
 * REGLA DEL PATRON MVC: la vista SOLO arma y muestra componentes.
 * No valida, no lee XML y no sabe que es un Usuario. Todo eso lo hace
 * LoginControlador, que se suscribe a los botones con addActionListener.
 *
 * Como armarla (estilo visto en clase, Swing a mano):
 *   setTitle("SISTEMA DE RESERVAS"); setSize(...); setLocationRelativeTo(null);
 *   setDefaultCloseOperation(EXIT_ON_CLOSE);
 *   un JPanel con GridBagLayout para las etiquetas ID / Clave
 *   y un JPanel con FlowLayout para los tres botones.
 */
public class LoginView extends JFrame {

    private JTextField txtId;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JButton btnCancelar;
    private JButton btnCambiar;

    public LoginView() {
        // TODO: configurar la ventana y llamar a inicializarComponentes()
    }

    /** Crea los componentes y los acomoda en la ventana. */
    private void inicializarComponentes() {
        // TODO
    }

    // ---- Lo que el controlador necesita LEER del formulario ----

    public String getId() {
        // TODO: txtId.getText().trim()
        return null;
    }

    public String getClave() {
        // TODO: new String(txtClave.getPassword())
        return null;
    }

    // ---- Lo que el controlador necesita ESCRIBIR en el formulario ----

    /** Deja los dos campos en blanco y el foco en el id (despues de un intento fallido). */
    public void limpiar() {
        // TODO
    }

    /** Cuadro de dialogo de error: JOptionPane.showMessageDialog(this, mensaje, ...). */
    public void mostrarError(String mensaje) {
        // TODO
    }

    // ---- Enganche de los botones (los llama el controlador) ----

    public JButton getBtnIngresar() { return btnIngresar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnCambiar() { return btnCambiar; }
}

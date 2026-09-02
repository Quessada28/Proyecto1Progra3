package cr.ac.una.vista;

import javax.swing.*;
import java.awt.Frame;

/**
 * VISTA del cambio de clave (funcionalidad 1). Es un JDialog MODAL porque
 * se abre encima del login o encima de la ventana principal y hay que
 * esperar a que el usuario termine.
 *
 * Ojo: la clave nueva se pide DOS veces; comparar las dos NO es trabajo de
 * la vista sino del controlador.
 */
public class CambiarClaveView extends JDialog {

    private JPasswordField txtClaveActual;
    private JPasswordField txtClaveNueva;
    private JPasswordField txtClaveNuevaConfirmacion;
    private JButton btnAceptar;
    private JButton btnCancelar;

    public CambiarClaveView(Frame padre) {
        super(padre, "Cambiar Clave", true); // true = modal
        // TODO: setSize(...), setLocationRelativeTo(padre), inicializarComponentes()
    }

    private void inicializarComponentes() {
        // TODO
    }

    public String getClaveActual() {
        // TODO
        return null;
    }

    public String getClaveNueva() {
        // TODO
        return null;
    }

    public String getClaveNuevaConfirmacion() {
        // TODO
        return null;
    }

    public void limpiar() {
        // TODO
    }

    public void mostrarError(String mensaje) {
        // TODO
    }

    public void mostrarInfo(String mensaje) {
        // TODO
    }

    public JButton getBtnAceptar() { return btnAceptar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}

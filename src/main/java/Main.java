import cr.ac.una.controlador.LoginControlador;
import cr.ac.una.logica.UsuarioService;
import cr.ac.una.vista.LoginView;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punto de entrada del sistema. Lo unico que hace es abrir el login;
 * de ahi en adelante manda LoginControlador.
 *
 * Toda ventana de Swing se debe crear dentro de SwingUtilities.invokeLater
 * porque los componentes no son seguros entre hilos.
 *
 *   SwingUtilities.invokeLater(() -> {
 *       LoginView vista = new LoginView();
 *       new LoginControlador(vista, new UsuarioService());
 *       vista.setVisible(true);
 *   });
 *
 * Antes de mostrar el login conviene:
 *   - poner el look and feel del sistema (UIManager.setLookAndFeel(
 *     UIManager.getSystemLookAndFeelClassName()) dentro de un try/catch)
 *   - asegurar que exista el usuario administrador inicial, para poder
 *     entrar la primera vez (ver datos/usuarios.xml)
 */
public class Main {

    public static void main(String[] args) {
        // TODO
    }

    /** Look and feel del sistema operativo, para que no se vea el gris viejo de Java. */
    private static void configurarLookAndFeel() {
        // TODO
    }

    /** Si no hay ningun usuario ADMIN en el XML, crear admin/admin. */
    private static void asegurarAdministrador() {
        // TODO
    }
}

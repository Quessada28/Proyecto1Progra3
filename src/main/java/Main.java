import cr.ac.una.controlador.LoginControlador;
import cr.ac.una.logica.UsuarioService;
import cr.ac.una.vista.LoginView;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        configurarLookAndFeel();
        UsuarioService usuarioService = new UsuarioService();
        usuarioService.asegurarAdministrador();

        SwingUtilities.invokeLater(() -> {
            LoginView vista = new LoginView();
            new LoginControlador(vista, usuarioService).mostrar();
        });
    }

    private static void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignorada) {
            UIManager.getCrossPlatformLookAndFeelClassName();
        }
    }
}

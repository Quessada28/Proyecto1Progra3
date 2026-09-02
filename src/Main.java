
import cr.ac.una.vista.LoginView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el hilo de eventos de Swing (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            try {
                // Aplica el estilo visual nativo del sistema operativo (Windows/Mac)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 1. Instanciar la vista de Login
            LoginView loginView = new LoginView();

            // 2. Probar eventos temporales para verificar la interfaz
            loginView.addIngresarListener(e -> {
                String id = loginView.getTxtId().getText();
                String clave = loginView.getTxtContra().getText();

                if (id.isEmpty() || clave.isEmpty()) {
                    loginView.mostrarMensajeError("Por favor, ingrese ID y Clave.");
                } else {
                    loginView.mostrarMensajeExito("Intento de ingreso con ID: " + id);
                }
            });

            loginView.addCambiarClaveListener(e -> {
                loginView.mostrarMensajeExito("Presionaste el botón 'Cambiar clave'");
            });

            loginView.addCancelarListener(e -> {
                loginView.mostrarMensajeExito("Se ha cancelado la acción ");
            });

            // 3. Hacer visible la ventana
            loginView.setVisible(true);
        });
    }
}
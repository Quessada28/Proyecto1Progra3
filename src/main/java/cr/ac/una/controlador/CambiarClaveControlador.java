package cr.ac.una.controlador;

import cr.ac.una.logica.ServicioException;
import cr.ac.una.logica.UsuarioService;
import cr.ac.una.vista.CambiarClaveView;

public class CambiarClaveControlador {

    private final CambiarClaveView vista;
    private final UsuarioService usuarioService;
    private final String idUsuario;

    public CambiarClaveControlador(CambiarClaveView vista, UsuarioService usuarioService, String idUsuario) {
        this.vista = vista;
        this.usuarioService = usuarioService;
        this.idUsuario = idUsuario;
        this.vista.getBtnAceptar().addActionListener(e -> aceptar());
        this.vista.getBtnCancelar().addActionListener(e -> cancelar());
    }

    public void mostrar() {
        vista.setVisible(true);
    }

    private void aceptar() {
        String actual = vista.getClaveActual();
        String nueva = vista.getClaveNueva();
        String confirmacion = vista.getClaveNuevaConfirmacion();

        if (actual.isBlank() || nueva.isBlank() || confirmacion.isBlank()) {
            vista.mostrarError("Debe llenar los tres campos.");
            return;
        }
        if (!nueva.equals(confirmacion)) {
            vista.mostrarError("La clave nueva y su confirmacion no coinciden.");
            return;
        }
        try {
            usuarioService.cambiarClave(idUsuario, actual, nueva);
            vista.mostrarInfo("La clave se cambio correctamente.");
            vista.dispose();
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
            vista.limpiar();
        }
    }

    private void cancelar() {
        vista.dispose();
    }
}

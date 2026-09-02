package cr.ac.una.controlador;

import cr.ac.una.logica.UsuarioService;
import cr.ac.una.vista.CambiarClaveView;

/**
 * CONTROLADOR del cambio de clave (funcionalidad 1).
 *
 * Validaciones que le tocan a ESTE controlador (no al servicio):
 *   - los tres campos vienen llenos
 *   - clave nueva == confirmacion
 * El resto (que la clave actual sea la correcta) lo verifica
 * UsuarioService.cambiarClave(...), que lanza ServicioException si no.
 */
public class CambiarClaveControlador {

    private final CambiarClaveView vista;
    private final UsuarioService usuarioService;
    private final String idUsuario;

    public CambiarClaveControlador(CambiarClaveView vista, UsuarioService usuarioService, String idUsuario) {
        this.vista = vista;
        this.usuarioService = usuarioService;
        this.idUsuario = idUsuario;
        // TODO: registrar listeners de Aceptar y Cancelar
    }

    /** Muestra el dialogo modal. */
    public void mostrar() {
        // TODO: vista.setVisible(true)
    }

    private void aceptar() {
        // TODO
    }

    private void cancelar() {
        // TODO: vista.dispose()
    }
}

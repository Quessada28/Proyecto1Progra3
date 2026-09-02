package cr.ac.una.controlador;

import cr.ac.una.logica.UsuarioService;
import cr.ac.una.vista.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CONTROLADOR del login (funcionalidad 1).
 *
 * ASI FUNCIONA EL MVC QUE PIDE EL ENUNCIADO, y todos los controladores de
 * este proyecto siguen el mismo molde:
 *
 *   1. El controlador recibe la VISTA y el SERVICIO en el constructor.
 *   2. En el constructor se suscribe a los botones de la vista
 *      (vista.getBtnIngresar().addActionListener(this)).
 *   3. Cuando cae un evento: lee los datos de la vista, llama al servicio,
 *      atrapa ServicioException y le pide a la vista que muestre el resultado.
 *
 * La vista nunca llama al servicio y el servicio nunca abre un JOptionPane.
 *
 * FLUJO DE ingresar():
 *   Usuario u = usuarioService.autenticar(id, clave);
 *   Sesion.iniciar(u);
 *   vista.dispose();
 *   new PrincipalControlador().mostrar();   // abre la ventana con las pestanas
 */
public class LoginControlador implements ActionListener {

    private final LoginView vista;
    private final UsuarioService usuarioService;

    public LoginControlador(LoginView vista, UsuarioService usuarioService) {
        this.vista = vista;
        this.usuarioService = usuarioService;
        // TODO: registrar los listeners de los tres botones
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: segun e.getSource() llamar a ingresar(), cambiarClave() o cancelar()
    }

    /** Autentica y, si todo bien, abre la ventana principal segun el rol. */
    private void ingresar() {
        // TODO
    }

    /** Abre el dialogo de cambio de clave sin haber entrado al sistema. */
    private void cambiarClave() {
        // TODO: pide el id primero (no hay sesion todavia) y abre CambiarClaveView
    }

    private void cancelar() {
        // TODO: System.exit(0)
    }
}

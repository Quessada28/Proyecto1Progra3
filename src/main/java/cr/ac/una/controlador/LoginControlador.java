package cr.ac.una.controlador;

import cr.ac.una.logica.ServicioException;
import cr.ac.una.logica.UsuarioService;
import cr.ac.una.modelo.Usuario;
import cr.ac.una.util.Sesion;
import cr.ac.una.vista.CambiarClaveView;
import cr.ac.una.vista.LoginView;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginControlador implements ActionListener {

    private final LoginView vista;
    private final UsuarioService usuarioService;

    public LoginControlador(LoginView vista, UsuarioService usuarioService) {
        this.vista = vista;
        this.usuarioService = usuarioService;
        this.vista.getBtnIngresar().addActionListener(this);
        this.vista.getBtnCancelar().addActionListener(this);
        this.vista.getBtnCambiar().addActionListener(this);
    }

    public void mostrar() {
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Object origen = evento.getSource();
        if (origen == vista.getBtnIngresar()) {
            ingresar();
        } else if (origen == vista.getBtnCambiar()) {
            cambiarClave();
        } else if (origen == vista.getBtnCancelar()) {
            cancelar();
        }
    }

    private void ingresar() {
        try {
            Usuario usuario = usuarioService.autenticar(vista.getId(), vista.getClave());
            Sesion.iniciar(usuario);
            vista.dispose();
            new PrincipalControlador().mostrar();
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
            vista.limpiarClave();
        }
    }

    private void cambiarClave() {
        String id = vista.getId();
        if (id.isEmpty()) {
            id = JOptionPane.showInputDialog(vista, "Digite su ID de usuario:",
                    "Cambiar clave", JOptionPane.QUESTION_MESSAGE);
        }
        if (id == null || id.isBlank()) {
            return;
        }
        if (usuarioService.buscar(id.trim()).isEmpty()) {
            vista.mostrarError("El usuario indicado no existe.");
            return;
        }
        CambiarClaveView dialogo = new CambiarClaveView(vista);
        new CambiarClaveControlador(dialogo, usuarioService, id.trim()).mostrar();
    }

    private void cancelar() {
        vista.dispose();
        System.exit(0);
    }
}

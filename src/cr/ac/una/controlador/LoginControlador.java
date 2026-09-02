//package cr.ac.una.controlador;
//
//import cr.ac.una.modelo.Usuario;
//import cr.ac.una.modelo.UsuarioService;
//import cr.ac.una.vista.CambiarClaveView;
//import cr.ac.una.vista.LoginView;
//
//public class LoginController {
//
//    private LoginView vista;
//    private UsuarioService modelo;
//
//    public LoginController(LoginView vista, UsuarioService modelo) {
//        this.vista = vista;
//        this.modelo = modelo;
//
//        // Registrar los eventos de los botones de LoginView
//        this.vista.addIngresarListener(e -> ingresar());
//        this.vista.addCambiarClaveListener(e -> abrirCambiarClave());
//    }
//
//    private void ingresar() {
//        String id = vista.getId();
//        String clave = vista.getClave();
//
//        if (id.isEmpty() || clave.isEmpty()) {
//            vista.mostrarMensajeError("Por favor llene todos los campos.");
//            return;
//        }
//
//        Usuario usuarioAutenticado = modelo.autenticar(id, clave);
//
//        if (usuarioAutenticado != null) {
//            vista.mostrarMensajeExito("Bienvenido " + usuarioAutenticado.getId() + " (" + usuarioAutenticado.getRol() + ")");[cite: 1]
//            vista.dispose(); // Cerrar la ventana de Login
//
//            // TODO: Abrir el Dashboard correspondiente según el rol (ADMINISTRADOR o FUNCIONARIO)[cite: 1]
//        } else {
//            vista.mostrarMensajeError("Credenciales incorrectas.");
//        }
//    }
//
//    private void abrirCambiarClave() {
//        CambiarClaveView vistaCambiar = new CambiarClaveView(vista);
//        new CambiarClaveController(vistaCambiar, modelo);
//        vistaCambiar.setVisible(true);
//    }
//}
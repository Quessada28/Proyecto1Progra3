//package cr.ac.una.controlador;
//
//import cr.ac.una.modelo.UsuarioService;
//import cr.ac.una.vista.CambiarClaveView;
//
//public class CambiarClaveController {
//
//    private CambiarClaveView vista;
//    private UsuarioService modelo;
//
//    public CambiarClaveController(CambiarClaveView vista, UsuarioService modelo) {
//        this.vista = vista;
//        this.modelo = modelo;
//
//        this.vista.addCambiarListener(e -> procesarCambioClave());
//        this.vista.addCancelarListener(e -> vista.dispose());
//    }
//
//    private void procesarCambioClave() {
//        String claveActual = vista.getClaveActual();
//        String claveNueva1 = vista.getClaveNueva1();
//        String claveNueva2 = vista.getClaveNueva2();
//
//        if (claveActual.isEmpty() || claveNueva1.isEmpty() || claveNueva2.isEmpty()) {
//            vista.mostrarError("Debe llenar todos los campos.");
//            return;
//        }
//
//        if (!claveNueva1.equals(claveNueva2)) {
//            vista.mostrarError("Las contraseñas nuevas no coinciden.");
//            return;
//        }
//
//        // Para este flujo se solicita el cambio usando el id ingresado o buscando coincidencia de clave actual
//        // Aquí asumimos que la validación valida primero la clave actual registrada en el servicio
//        boolean exito = modelo.cambiarClave("admin", claveActual, claveNueva1); // Se valida id/clave
//
//        if (exito) {
//            vista.mostrarExito("Contraseña actualizada con éxito.");
//            vista.dispose();
//        } else {
//            vista.mostrarError("La contraseña actual es incorrecta o el usuario no existe.");
//        }
//    }
//}
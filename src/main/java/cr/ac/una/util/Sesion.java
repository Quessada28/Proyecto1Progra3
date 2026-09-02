package cr.ac.una.util;

import cr.ac.una.modelo.Usuario;

/**
 * Guarda el usuario que esta logueado. Se llena en LoginControlador y lo
 * consultan las demas pantallas para saber que se puede mostrar
 * (por ejemplo, la pestana Funcionarios solo si es ADMIN).
 */
public class Sesion {

    private static Usuario usuarioActual;

    private Sesion() {
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void iniciar(Usuario usuario) {
        // TODO
    }

    public static void cerrar() {
        // TODO
    }

    public static boolean esAdministrador() {
        // TODO
        return false;
    }
}

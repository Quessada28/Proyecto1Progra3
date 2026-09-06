package cr.ac.una.util;

import cr.ac.una.modelo.Rol;
import cr.ac.una.modelo.Usuario;

public class Sesion {

    private static Usuario usuarioActual;

    private Sesion() {
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void iniciar(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void cerrar() {
        usuarioActual = null;
    }

    public static boolean haySesion() {
        return usuarioActual != null;
    }

    public static boolean esAdministrador() {
        return usuarioActual != null && usuarioActual.getRol() == Rol.ADMIN;
    }

    public static String idUsuarioActual() {
        return usuarioActual == null ? null : usuarioActual.getId();
    }
}

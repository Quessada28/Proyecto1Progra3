package cr.ac.una.util;

/**
 * Validaciones basicas y reutilizables. La regla del enunciado es
 * "todos los datos deberan validarse y reportar adecuadamente cualquier error",
 * y eso se revisa en la defensa: conviene que los mensajes salgan de un solo lugar.
 */
public class Validador {

    private Validador() {
    }

    public static boolean vacio(String texto) {
        // TODO: null, "" o solo espacios
        return false;
    }

    /** Lanza ServicioException con el mensaje si el texto viene vacio. */
    public static void requerido(String texto, String nombreCampo) {
        // TODO
    }

    public static boolean esNumero(String texto) {
        // TODO
        return false;
    }

    /** Telefono: solo digitos, entre 8 y 12 caracteres. */
    public static boolean esTelefono(String texto) {
        // TODO
        return false;
    }
}

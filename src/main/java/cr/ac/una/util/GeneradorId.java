package cr.ac.una.util;

/**
 * Ids autogenerados con el formato del enunciado: CAT-000003, RES-000001.
 * El consecutivo se saca del ultimo id que exista en el XML
 * (ver ultimoConsecutivo() en los DAO), no de un contador en memoria:
 * si no, al reiniciar el programa se repetirian los ids.
 */
public class GeneradorId {

    private static final String PREFIJO_CATEGORIA = "CAT-";
    private static final String PREFIJO_RESERVA = "RES-";

    private GeneradorId() {
    }

    /** Ej: siguiente("CAT-", 2) devuelve "CAT-000003". */
    public static String siguiente(String prefijo, int ultimoConsecutivo) {
        // TODO: String.format("%s%06d", prefijo, ultimoConsecutivo + 1)
        return null;
    }

    public static String siguienteCategoria(int ultimoConsecutivo) {
        // TODO
        return null;
    }

    public static String siguienteReserva(int ultimoConsecutivo) {
        // TODO
        return null;
    }

    /** De "CAT-000003" saca el 3. Devuelve 0 si el id no tiene el formato. */
    public static int consecutivoDe(String id) {
        // TODO
        return 0;
    }
}

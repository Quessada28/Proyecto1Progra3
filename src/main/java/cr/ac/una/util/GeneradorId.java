package cr.ac.una.util;

public class GeneradorId {

    public static final String PREFIJO_CATEGORIA = "CAT-";
    public static final String PREFIJO_RESERVA = "RES-";

    private GeneradorId() {
    }

    public static String siguiente(String prefijo, int ultimoConsecutivo) {
        return String.format("%s%06d", prefijo, ultimoConsecutivo + 1);
    }

    public static String siguienteCategoria(int ultimoConsecutivo) {
        return siguiente(PREFIJO_CATEGORIA, ultimoConsecutivo);
    }

    public static String siguienteReserva(int ultimoConsecutivo) {
        return siguiente(PREFIJO_RESERVA, ultimoConsecutivo);
    }

    public static int consecutivoDe(String id) {
        if (id == null) {
            return 0;
        }
        int guion = id.indexOf('-');
        if (guion < 0 || guion == id.length() - 1) {
            return 0;
        }
        try {
            return Integer.parseInt(id.substring(guion + 1));
        } catch (NumberFormatException ignorada) {
            return 0;
        }
    }
}

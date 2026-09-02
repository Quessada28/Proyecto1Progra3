package cr.ac.una.util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Conversion entre lo que se GUARDA (ISO: 2026-08-05, 09:00) y lo que se
 * MUESTRA en pantalla (5 de agosto de 2026, 9:00 a. m.).
 * Tener esto en un solo lugar evita el error tipico de guardar en el XML
 * la fecha ya formateada y despues no poder volver a leerla.
 */
public class Formatos {

    private Formatos() {
    }

    /** LocalDate -> "5 de agosto de 2026" (Locale espanol). */
    public static String fechaLarga(LocalDate fecha) {
        // TODO: DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es"))
        return null;
    }

    /** "5 de agosto de 2026" -> LocalDate. Devuelve null si no se puede interpretar. */
    public static LocalDate leerFechaLarga(String texto) {
        // TODO
        return null;
    }

    /** LocalTime -> "9:00 a. m." */
    public static String hora12(LocalTime hora) {
        // TODO
        return null;
    }

    /** Horas para llenar los JComboBox de hora inicio / hora fin. */
    public static String[] horasDelDia() {
        // TODO: de 06:00 a 22:00 en punto, usando hora12(...)
        return null;
    }
}

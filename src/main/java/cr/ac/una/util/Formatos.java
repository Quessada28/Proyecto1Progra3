package cr.ac.una.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Formatos {

    public static final Locale ESPANOL = Locale.forLanguageTag("es");
    public static final LocalTime PRIMERA_HORA = LocalTime.of(6, 0);
    public static final LocalTime ULTIMA_HORA = LocalTime.of(22, 0);

    private static final DateTimeFormatter FECHA_LARGA =
            DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", ESPANOL);
    private static final DateTimeFormatter HORA_12 =
            DateTimeFormatter.ofPattern("h:mm a", ESPANOL);
    private static final DateTimeFormatter HORA_24 =
            DateTimeFormatter.ofPattern("HH:mm");

    private Formatos() {
    }

    public static String fechaLarga(LocalDate fecha) {
        return fecha == null ? "" : fecha.format(FECHA_LARGA);
    }

    public static LocalDate leerFechaLarga(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        String limpio = texto.trim();
        try {
            return LocalDate.parse(limpio, FECHA_LARGA);
        } catch (RuntimeException ignorada) {
            try {
                return LocalDate.parse(limpio);
            } catch (RuntimeException tampoco) {
                return null;
            }
        }
    }

    public static String hora12(LocalTime hora) {
        return hora == null ? "" : hora.format(HORA_12);
    }

    public static LocalTime leerHora12(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        String limpio = texto.trim();
        try {
            return LocalTime.parse(limpio, HORA_12);
        } catch (RuntimeException ignorada) {
            try {
                return LocalTime.parse(limpio);
            } catch (RuntimeException tampoco) {
                return null;
            }
        }
    }

    public static String hora24(LocalTime hora) {
        return hora == null ? "" : hora.format(HORA_24);
    }

    public static String[] horasDelDia() {
        List<String> horas = new ArrayList<>();
        for (LocalTime h = PRIMERA_HORA; !h.isAfter(ULTIMA_HORA); h = h.plusHours(1)) {
            horas.add(hora12(h));
        }
        return horas.toArray(new String[0]);
    }

    public static String rangoHorario(LocalTime inicio, LocalTime fin) {
        return hora24(inicio) + " - " + hora24(fin);
    }
}

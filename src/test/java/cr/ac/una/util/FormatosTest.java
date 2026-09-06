package cr.ac.una.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormatosTest {

    @Test
    void formateaLaFechaEnEspanol() {
        assertEquals("5 de agosto de 2026", Formatos.fechaLarga(LocalDate.of(2026, 8, 5)));
    }

    @Test
    @DisplayName("Formatear y volver a leer devuelve la misma fecha")
    void leerLoQueSeFormateoDevuelveLaMismaFecha() {
        LocalDate original = LocalDate.of(2026, 12, 31);

        assertEquals(original, Formatos.leerFechaLarga(Formatos.fechaLarga(original)));
    }

    @Test
    void tambienEntiendeElFormatoIso() {
        assertEquals(LocalDate.of(2026, 8, 5), Formatos.leerFechaLarga("2026-08-05"));
    }

    @Test
    void leerFechaInvalidaDevuelveNull() {
        assertNull(Formatos.leerFechaLarga("cualquier cosa"));
        assertNull(Formatos.leerFechaLarga(""));
        assertNull(Formatos.leerFechaLarga(null));
    }

    @Test
    @DisplayName("Formatear y volver a leer devuelve la misma hora")
    void laHoraSobreviveLaIdaYVuelta() {
        LocalTime original = LocalTime.of(9, 0);

        assertEquals(original, Formatos.leerHora12(Formatos.hora12(original)));
    }

    @Test
    void laHoraDeLaTardeNoSeConfundeConLaDeLaManana() {
        LocalTime tarde = LocalTime.of(15, 30);

        assertEquals(tarde, Formatos.leerHora12(Formatos.hora12(tarde)));
    }

    @Test
    void hora24UsaDosDigitos() {
        assertEquals("08:00", Formatos.hora24(LocalTime.of(8, 0)));
    }

    @Test
    void lasHorasDelDiaVanDeSeisAVeintidos() {
        String[] horas = Formatos.horasDelDia();

        assertEquals(17, horas.length);
        assertNotNull(horas[0]);
        assertEquals(LocalTime.of(6, 0), Formatos.leerHora12(horas[0]));
        assertEquals(LocalTime.of(22, 0), Formatos.leerHora12(horas[horas.length - 1]));
    }

    @Test
    void rangoHorarioSeMuestraEnFormatoDe24Horas() {
        assertTrue(Formatos.rangoHorario(LocalTime.of(8, 0), LocalTime.of(10, 0))
                .equals("08:00 - 10:00"));
    }
}

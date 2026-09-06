package cr.ac.una.util;

import cr.ac.una.logica.ServicioException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorTest {

    @Test
    void detectaTextoVacioNuloYDeSoloEspacios() {
        assertTrue(Validador.vacio(null));
        assertTrue(Validador.vacio(""));
        assertTrue(Validador.vacio("   "));
        assertFalse(Validador.vacio("dato"));
    }

    @Test
    void requeridoLanzaExcepcionConElNombreDelCampo() {
        ServicioException error = assertThrows(ServicioException.class,
                () -> Validador.requerido("", "Nombre"));

        assertTrue(error.getMessage().contains("Nombre"));
    }

    @Test
    void requeridoNoLanzaCuandoElCampoTieneValor() {
        Validador.requerido("Juan", "Nombre");
    }

    @Test
    void reconoceNumeros() {
        assertTrue(Validador.esNumero("12345"));
        assertFalse(Validador.esNumero("12a45"));
        assertFalse(Validador.esNumero(""));
    }

    @Test
    void reconoceTelefonosConSeparadores() {
        assertTrue(Validador.esTelefono("8888-8888"));
        assertTrue(Validador.esTelefono("(506) 2277 3000"));
        assertTrue(Validador.esTelefono("3323"));
        assertFalse(Validador.esTelefono("123"));
        assertFalse(Validador.esTelefono("telefono"));
    }

    @Test
    void telefonoValidoLanzaCuandoElFormatoEsIncorrecto() {
        assertThrows(ServicioException.class,
                () -> Validador.telefonoValido("abc", "Telefono"));
    }
}

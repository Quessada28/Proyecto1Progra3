package cr.ac.una.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneradorIdTest {

    @Test
    void generaElSiguienteConSeisDigitos() {
        assertEquals("CAT-000003", GeneradorId.siguienteCategoria(2));
    }

    @Test
    void elPrimeroEsElUno() {
        assertEquals("RES-000001", GeneradorId.siguienteReserva(0));
    }

    @Test
    void extraeElConsecutivoDeUnId() {
        assertEquals(3, GeneradorId.consecutivoDe("CAT-000003"));
        assertEquals(15, GeneradorId.consecutivoDe("RES-000015"));
    }

    @Test
    void devuelveCeroSiElIdNoTieneElFormato() {
        assertEquals(0, GeneradorId.consecutivoDe("hola"));
        assertEquals(0, GeneradorId.consecutivoDe(null));
        assertEquals(0, GeneradorId.consecutivoDe("CAT-"));
        assertEquals(0, GeneradorId.consecutivoDe("238715"));
    }
}

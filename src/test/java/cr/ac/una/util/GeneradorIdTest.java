package cr.ac.una.util;

import org.junit.jupiter.api.Test;

/** PRUEBA UNITARIA del formato de los ids autogenerados. */
class GeneradorIdTest {

    @Test
    void generaElSiguienteConSeisDigitos() {
        // TODO: siguienteCategoria(2) -> "CAT-000003"
    }

    @Test
    void elPrimeroEsElUno() {
        // TODO: siguienteReserva(0) -> "RES-000001"
    }

    @Test
    void extraeElConsecutivoDeUnId() {
        // TODO: consecutivoDe("CAT-000003") -> 3
    }

    @Test
    void devuelveCeroSiElIdNoTieneElFormato() {
        // TODO: consecutivoDe("hola") y consecutivoDe(null) -> 0
    }
}

package cr.ac.una.integracion;

import org.junit.jupiter.api.Test;

/**
 * PRUEBA DE INTEGRACION del generador de PDF (Failsafe).
 * No se puede comparar el contenido visual, pero si se puede comprobar que
 * el archivo se creo, que no quedo vacio y que arranca con la firma de un
 * PDF ("%PDF").
 */
class ReportePdfIT {

    @Test
    void generaUnArchivoPdfValido() {
        // TODO: usar @TempDir para el archivo destino
    }

    @Test
    void elReporteConTablaVaciaTampocoTruena() {
        // TODO
    }
}

package cr.ac.una.datos;

import org.junit.jupiter.api.Test;

/**
 * PRUEBA DE INTEGRACION (la corre Failsafe con "mvn verify" porque el
 * nombre termina en IT).
 *
 * Diferencia con las pruebas unitarias: esta SI toca el disco. Prueba que
 * lo que se escribe en el XML se pueda volver a leer igual.
 *
 * Para no ensuciar la carpeta datos/ del proyecto conviene usar el
 * directorio temporal de JUnit:
 *
 *   @TempDir static Path carpetaTemporal;
 *
 * y hacer que XmlUtil.CARPETA se pueda cambiar (por ejemplo volviendola una
 * variable con setter, o leyendola de una propiedad del sistema). Es un
 * cambio chiquito en XmlUtil y hace que estas pruebas sean repetibles.
 */
class CategoriaXmlDaoIT {

    @Test
    void loQueSeGuardaSePuedeVolverALeer() {
        // TODO: guardar una categoria, crear un DAO nuevo y buscarla por id
    }

    @Test
    void guardarDosVecesElMismoIdActualizaEnVezDeDuplicar() {
        // TODO: listar() debe devolver un solo elemento
    }

    @Test
    void eliminarQuitaElNodoDelArchivo() {
        // TODO
    }

    @Test
    void elArchivoSeCreaSolitoLaPrimeraVez() {
        // TODO: sin archivo previo, listar() devuelve lista vacia y no truena
    }

    @Test
    void ultimoConsecutivoDevuelveElMayorIdGuardado() {
        // TODO
    }
}

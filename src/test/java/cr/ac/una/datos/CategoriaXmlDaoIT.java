package cr.ac.una.datos;

import cr.ac.una.modelo.Categoria;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoriaXmlDaoIT {

    @TempDir
    Path carpetaTemporal;

    private String carpetaOriginal;
    private CategoriaXmlDao dao;

    @BeforeEach
    void apuntarAlDirectorioTemporal() {
        carpetaOriginal = XmlUtil.getCarpeta();
        XmlUtil.setCarpeta(carpetaTemporal.toString());
        dao = new CategoriaXmlDao();
    }

    @AfterEach
    void restaurarCarpeta() {
        XmlUtil.setCarpeta(carpetaOriginal);
    }

    @Test
    @DisplayName("Lo que se guarda se puede volver a leer con un DAO nuevo")
    void loQueSeGuardaSePuedeVolverALeer() {
        dao.guardar(new Categoria("CAT-000001", "Sala para 10 personas"));

        Categoria leida = new CategoriaXmlDao().buscarPorId("CAT-000001").orElseThrow();

        assertEquals("Sala para 10 personas", leida.getDescripcion());
    }

    @Test
    void guardarDosVecesElMismoIdActualizaEnVezDeDuplicar() {
        dao.guardar(new Categoria("CAT-000001", "Sala pequena"));
        dao.guardar(new Categoria("CAT-000001", "Sala grande"));

        assertEquals(1, dao.listar().size());
        assertEquals("Sala grande", dao.buscarPorId("CAT-000001").orElseThrow().getDescripcion());
    }

    @Test
    void eliminarQuitaElNodoDelArchivo() {
        dao.guardar(new Categoria("CAT-000001", "Sala de Juntas"));
        dao.guardar(new Categoria("CAT-000002", "Laptop windows"));

        dao.eliminar("CAT-000001");

        assertEquals(1, dao.listar().size());
        assertTrue(dao.buscarPorId("CAT-000001").isEmpty());
    }

    @Test
    @DisplayName("Sin archivo previo listar devuelve vacio y no truena")
    void elArchivoSeCreaSolitoLaPrimeraVez() {
        assertTrue(dao.listar().isEmpty());
    }

    @Test
    void ultimoConsecutivoDevuelveElMayorIdGuardado() {
        dao.guardar(new Categoria("CAT-000001", "Uno"));
        dao.guardar(new Categoria("CAT-000007", "Siete"));
        dao.guardar(new Categoria("CAT-000003", "Tres"));

        assertEquals(7, dao.ultimoConsecutivo());
    }

    @Test
    void buscarPorDescripcionEsInsensibleAMayusculas() {
        dao.guardar(new Categoria("CAT-000001", "Sala para 10 personas"));
        dao.guardar(new Categoria("CAT-000002", "Laptop windows"));

        assertEquals(1, dao.buscarPorDescripcion("SALA").size());
        assertEquals(2, dao.buscarPorDescripcion("").size());
    }

    @Test
    @DisplayName("Guardar muchas veces no acumula lineas en blanco en el archivo")
    void guardarVariasVecesNoInflaElArchivo() throws Exception {
        dao.guardar(new Categoria("CAT-000001", "Sala de Juntas"));
        long despuesDelPrimero = XmlUtil.archivo("categorias.xml").length();

        for (int i = 0; i < 10; i++) {
            dao.guardar(new Categoria("CAT-000001", "Sala de Juntas"));
        }

        assertEquals(despuesDelPrimero, XmlUtil.archivo("categorias.xml").length());
    }

    @Test
    void guardaCaracteresEspecialesSinRomperElXml() {
        dao.guardar(new Categoria("CAT-000001", "Sala A & B <principal>"));

        assertEquals("Sala A & B <principal>",
                new CategoriaXmlDao().buscarPorId("CAT-000001").orElseThrow().getDescripcion());
    }
}

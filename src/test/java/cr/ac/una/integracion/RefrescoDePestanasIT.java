package cr.ac.una.integracion;

import cr.ac.una.controlador.RecursosControlador;
import cr.ac.una.datos.XmlUtil;
import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.RecursoService;
import cr.ac.una.modelo.Categoria;
import cr.ac.una.vista.RecursosView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RefrescoDePestanasIT {

    @TempDir
    Path carpetaTemporal;

    private String carpetaOriginal;

    @BeforeEach
    void apuntarAlDirectorioTemporal() {
        carpetaOriginal = XmlUtil.getCarpeta();
        XmlUtil.setCarpeta(carpetaTemporal.toString());
    }

    @AfterEach
    void restaurarCarpeta() {
        XmlUtil.setCarpeta(carpetaOriginal);
    }

    @Test
    @DisplayName("El combo de Recursos toma las categorias creadas despues de abrir la ventana")
    void elComboDeRecursosVeLasCategoriasCreadasDespues() {
        RecursosView vista = new RecursosView();
        RecursosControlador controlador =
                new RecursosControlador(vista, new RecursoService(), new CategoriaService());

        controlador.inicializar();
        assertEquals(null, vista.getIdCategoria());

        new CategoriaService().guardar(new Categoria(null, "Sala de Juntas"));
        controlador.refrescar();

        assertNotNull(vista.getIdCategoria());
        assertEquals("CAT-000001", vista.getIdCategoria());
    }

    @Test
    void elComboRecogeCadaCategoriaNueva() {
        RecursosView vista = new RecursosView();
        RecursosControlador controlador =
                new RecursosControlador(vista, new RecursoService(), new CategoriaService());
        controlador.inicializar();

        new CategoriaService().guardar(new Categoria(null, "Sala de Juntas"));
        controlador.refrescar();
        new CategoriaService().guardar(new Categoria(null, "Laptop windows"));
        controlador.refrescar();

        assertEquals(2, vista.cantidadCategorias());
    }
}

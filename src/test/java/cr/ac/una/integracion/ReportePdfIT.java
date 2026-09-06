package cr.ac.una.integracion;

import cr.ac.una.reporte.ReportePdf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportePdfIT {

    @TempDir
    Path carpetaTemporal;

    private static final String[] COLUMNAS = {"Id", "Descripcion"};

    @Test
    @DisplayName("El archivo generado existe y empieza con la firma de un PDF")
    void generaUnArchivoPdfValido() throws Exception {
        File destino = carpetaTemporal.resolve("categorias.pdf").toFile();
        List<String[]> filas = List.of(
                new String[]{"CAT-000001", "Sala para 10 personas"},
                new String[]{"CAT-000002", "Laptop windows"});

        new ReportePdf().tabla("Listado de Categorias", COLUMNAS, filas, destino);

        assertTrue(destino.exists());
        assertTrue(destino.length() > 0);
        assertTrue(new String(Files.readAllBytes(destino.toPath()), 0, 4).startsWith("%PDF"));
    }

    @Test
    void elReporteConTablaVaciaTampocoTruena() {
        File destino = carpetaTemporal.resolve("vacio.pdf").toFile();

        new ReportePdf().tabla("Listado vacio", COLUMNAS, List.of(), destino);

        assertTrue(destino.exists());
        assertTrue(destino.length() > 0);
    }

    @Test
    void elReporteConGraficoIncluyeLaImagen() {
        File sinGrafico = carpetaTemporal.resolve("sin_grafico.pdf").toFile();
        File conGrafico = carpetaTemporal.resolve("con_grafico.pdf").toFile();
        List<String[]> filas = List.<String[]>of(new String[]{"Sala de Juntas", "2"});

        ReportePdf reporte = new ReportePdf();
        reporte.tabla("Recursos Usados", COLUMNAS, filas, sinGrafico);
        reporte.tablaConGrafico("Recursos Usados", COLUMNAS, filas,
                new java.awt.image.BufferedImage(200, 120,
                        java.awt.image.BufferedImage.TYPE_INT_RGB), conGrafico);

        assertTrue(conGrafico.length() > sinGrafico.length());
    }
}

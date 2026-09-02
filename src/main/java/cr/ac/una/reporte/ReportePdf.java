package cr.ac.una.reporte;

import javax.swing.JTable;
import java.io.File;
import java.util.List;

/**
 * Generacion de reportes PDF con iText 5 (dependencia com.itextpdf:itextpdf).
 * La rubrica pide el boton "Imprimir" en TODAS las funcionalidades, asi que
 * conviene una sola clase generica y reutilizable en vez de una por pantalla.
 *
 * Clases de iText que se necesitan:
 *   com.itextpdf.text.Document, Paragraph, Font, FontFactory
 *   com.itextpdf.text.pdf.PdfWriter, PdfPTable, PdfPCell
 *   com.itextpdf.text.Image  (para el grafico)
 */
public class ReportePdf {

    /**
     * Reporte generico de tabla, como el ejemplo "Listado de Categorias" del enunciado.
     *
     * @param titulo      titulo centrado arriba
     * @param columnas    encabezados (fila roja con letra blanca)
     * @param filas       cada arreglo es una fila
     * @param destino     archivo .pdf a generar
     */
    public void tabla(String titulo, String[] columnas, List<String[]> filas, File destino) {
        // TODO: Document doc = new Document(); PdfWriter.getInstance(doc, new FileOutputStream(destino));
        //       doc.open(); agregar titulo; armar PdfPTable; doc.close();
    }

    /**
     * Atajo comodo: toma lo que ya se ve en un JTable (respetando el orden y el
     * filtro visibles) y lo manda a tabla(...). Evita duplicar el armado de datos
     * en cada controlador.
     */
    public void deJTable(String titulo, JTable tabla, File destino) {
        // TODO
    }

    /**
     * Reporte de la pestana Estadisticas: la tabla mas la imagen del grafico.
     * El grafico de JFreeChart se convierte a imagen con chart.createBufferedImage(w, h)
     * y esa imagen se inserta con com.itextpdf.text.Image.getInstance(...).
     */
    public void tablaConGrafico(String titulo, String[] columnas, List<String[]> filas,
                                java.awt.image.BufferedImage grafico, File destino) {
        // TODO
    }

    /**
     * Muestra el JFileChooser para escoger donde guardar y devuelve el archivo
     * (o null si el usuario cancelo). Se llama desde los controladores.
     */
    public File pedirDestino(java.awt.Component padre, String nombreSugerido) {
        // TODO
        return null;
    }

    /** Abre el PDF recien generado con el visor del sistema (Desktop.getDesktop().open). */
    public void abrir(File archivo) {
        // TODO
    }
}

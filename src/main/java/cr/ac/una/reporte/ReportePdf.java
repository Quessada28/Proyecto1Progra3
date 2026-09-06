package cr.ac.una.reporte;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import cr.ac.una.logica.ServicioException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReportePdf {

    private static final Font FUENTE_TITULO =
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
    private static final Font FUENTE_ENCABEZADO =
            FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE);
    private static final Font FUENTE_CELDA =
            FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
    private static final BaseColor COLOR_ENCABEZADO = new BaseColor(200, 0, 0);
    private static final int COLUMNAS_PARA_HOJA_ANCHA = 6;

    public void tabla(String titulo, String[] columnas, List<String[]> filas, File destino) {
        tablaConGrafico(titulo, columnas, filas, null, destino);
    }

    public void tablaConGrafico(String titulo, String[] columnas, List<String[]> filas,
                                BufferedImage grafico, File destino) {
        Document documento = new Document(
                columnas.length > COLUMNAS_PARA_HOJA_ANCHA ? PageSize.A4.rotate() : PageSize.A4);
        try (FileOutputStream salida = new FileOutputStream(destino)) {
            PdfWriter.getInstance(documento, salida);
            documento.open();

            Paragraph encabezado = new Paragraph(titulo, FUENTE_TITULO);
            encabezado.setAlignment(Element.ALIGN_CENTER);
            encabezado.setSpacingAfter(18f);
            documento.add(encabezado);

            documento.add(construirTabla(columnas, filas));

            if (grafico != null) {
                documento.add(imagen(grafico));
            }
            documento.close();
        } catch (Exception e) {
            if (documento.isOpen()) {
                documento.close();
            }
            throw new ServicioException("No se pudo generar el reporte PDF: " + e.getMessage());
        }
    }

    public void deJTable(String titulo, JTable tabla, File destino) {
        int totalColumnas = tabla.getColumnCount();
        String[] columnas = new String[totalColumnas];
        for (int i = 0; i < totalColumnas; i++) {
            columnas[i] = tabla.getColumnName(i);
        }

        List<String[]> filas = new ArrayList<>();
        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            String[] valores = new String[totalColumnas];
            for (int columna = 0; columna < totalColumnas; columna++) {
                Object valor = tabla.getValueAt(fila, columna);
                valores[columna] = valor == null ? "" : valor.toString();
            }
            filas.add(valores);
        }
        tabla(titulo, columnas, filas, destino);
    }

    private PdfPTable construirTabla(String[] columnas, List<String[]> filas) {
        PdfPTable tabla = new PdfPTable(columnas.length);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(10f);
        tabla.setSpacingAfter(18f);
        tabla.setHeaderRows(1);

        for (String columna : columnas) {
            PdfPCell celda = new PdfPCell(new Phrase(columna, FUENTE_ENCABEZADO));
            celda.setBackgroundColor(COLOR_ENCABEZADO);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(6f);
            tabla.addCell(celda);
        }

        for (String[] fila : filas) {
            for (int i = 0; i < columnas.length; i++) {
                String valor = i < fila.length && fila[i] != null ? fila[i] : "";
                PdfPCell celda = new PdfPCell(new Phrase(valor, FUENTE_CELDA));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(5f);
                tabla.addCell(celda);
            }
        }
        return tabla;
    }

    private Image imagen(BufferedImage grafico) throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        ImageIO.write(grafico, "png", buffer);
        Image imagen = Image.getInstance(buffer.toByteArray());
        imagen.setAlignment(Element.ALIGN_CENTER);
        imagen.scaleToFit(480f, 320f);
        return imagen;
    }

    public File pedirDestino(Component padre, String nombreSugerido) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar reporte");
        selector.setSelectedFile(new File(nombreSugerido));
        selector.setFileFilter(new FileNameExtensionFilter("Documento PDF", "pdf"));
        if (selector.showSaveDialog(padre) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File elegido = selector.getSelectedFile();
        if (!elegido.getName().toLowerCase().endsWith(".pdf")) {
            elegido = new File(elegido.getParentFile(), elegido.getName() + ".pdf");
        }
        return elegido;
    }

    public void abrir(File archivo) {
        try {
            if (archivo != null && archivo.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(archivo);
            }
        } catch (Exception ignorada) {
            throw new ServicioException(
                    "El reporte se genero pero no se pudo abrir automaticamente.");
        }
    }
}

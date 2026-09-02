package cr.ac.una.datos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

/**
 * Utilitario compartido por todos los DAO: abre, crea y guarda documentos XML
 * usando DOM (javax.xml.parsers / javax.xml.transform, ya vienen en el JDK).
 *
 * Aqui se concentra TODO el manejo de archivos: los DAO solo traducen
 * entre Elementos del DOM y objetos del modelo.
 */
public class XmlUtil {

    /** Carpeta donde viven los archivos .xml (ver /datos en la raiz del proyecto). */
    public static final String CARPETA = "datos";

    /**
     * Devuelve el Document del archivo. Si el archivo no existe, crea uno nuevo
     * con el elemento raiz indicado (ej: "categorias") y lo devuelve vacio.
     */
    public static Document abrirOCrear(String nombreArchivo, String nombreRaiz) {
        // TODO: DocumentBuilderFactory.newInstance().newDocumentBuilder()
        //       si el archivo existe -> builder.parse(archivo)
        //       si no existe        -> builder.newDocument() + appendChild(createElement(nombreRaiz))
        return null;
    }

    /** Escribe el Document de vuelta al archivo (con indentacion para poder leerlo). */
    public static void guardar(Document doc, String nombreArchivo) {
        // TODO: TransformerFactory -> Transformer -> transform(DOMSource, StreamResult)
    }

    /** File apuntando a CARPETA/nombreArchivo, creando la carpeta si hace falta. */
    public static File archivo(String nombreArchivo) {
        // TODO
        return null;
    }

    /** Lee el texto de un hijo directo: de <categoria><descripcion>X</descripcion> devuelve X. */
    public static String texto(Element padre, String nombreHijo) {
        // TODO
        return null;
    }

    /** Crea y agrega un hijo con texto. Es el inverso de texto(...). */
    public static void agregarHijo(Document doc, Element padre, String nombreHijo, String valor) {
        // TODO
    }
}

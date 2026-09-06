package cr.ac.una.datos;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlUtil {

    private static String carpeta = "datos";

    private XmlUtil() {
    }

    public static String getCarpeta() {
        return carpeta;
    }

    public static void setCarpeta(String nuevaCarpeta) {
        carpeta = nuevaCarpeta;
    }

    public static File archivo(String nombreArchivo) {
        File directorio = new File(carpeta);
        if (!directorio.exists() && !directorio.mkdirs()) {
            throw new DatosException("No se pudo crear la carpeta de datos: " + carpeta);
        }
        return new File(directorio, nombreArchivo);
    }

    public static Document abrirOCrear(String nombreArchivo, String nombreRaiz) {
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setIgnoringComments(true);
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            File origen = archivo(nombreArchivo);
            if (origen.exists() && origen.length() > 0) {
                Document doc = constructor.parse(origen);
                doc.getDocumentElement().normalize();
                quitarEspaciosEnBlanco(doc.getDocumentElement());
                return doc;
            }
            Document doc = constructor.newDocument();
            doc.appendChild(doc.createElement(nombreRaiz));
            return doc;
        } catch (Exception e) {
            throw new DatosException("No se pudo leer el archivo " + nombreArchivo, e);
        }
    }

    private static void quitarEspaciosEnBlanco(Node nodo) {
        NodeList hijos = nodo.getChildNodes();
        for (int i = hijos.getLength() - 1; i >= 0; i--) {
            Node hijo = hijos.item(i);
            if (hijo.getNodeType() == Node.TEXT_NODE && hijo.getTextContent().isBlank()) {
                nodo.removeChild(hijo);
            } else if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                quitarEspaciosEnBlanco(hijo);
            }
        }
    }

    public static void guardar(Document doc, String nombreArchivo) {
        try {
            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformador.transform(new DOMSource(doc), new StreamResult(archivo(nombreArchivo)));
        } catch (Exception e) {
            throw new DatosException("No se pudo guardar el archivo " + nombreArchivo, e);
        }
    }

    public static String texto(Element padre, String nombreHijo) {
        NodeList hijos = padre.getElementsByTagName(nombreHijo);
        for (int i = 0; i < hijos.getLength(); i++) {
            Node hijo = hijos.item(i);
            if (hijo.getParentNode() == padre) {
                String contenido = hijo.getTextContent();
                return contenido == null ? "" : contenido.trim();
            }
        }
        return "";
    }

    public static void agregarHijo(Document doc, Element padre, String nombreHijo, String valor) {
        Element hijo = doc.createElement(nombreHijo);
        hijo.setTextContent(valor == null ? "" : valor);
        padre.appendChild(hijo);
    }

    public static List<Element> hijosDirectos(Element padre, String nombreHijo) {
        List<Element> encontrados = new ArrayList<>();
        NodeList hijos = padre.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node hijo = hijos.item(i);
            if (hijo.getNodeType() == Node.ELEMENT_NODE && hijo.getNodeName().equals(nombreHijo)) {
                encontrados.add((Element) hijo);
            }
        }
        return encontrados;
    }

    public static List<Element> nodos(Document doc, String nombreNodo) {
        return hijosDirectos(doc.getDocumentElement(), nombreNodo);
    }

    public static void eliminarNodoConId(Document doc, String nombreNodo, String id) {
        Element raiz = doc.getDocumentElement();
        for (Element nodo : hijosDirectos(raiz, nombreNodo)) {
            if (texto(nodo, "id").equals(id)) {
                raiz.removeChild(nodo);
            }
        }
    }
}

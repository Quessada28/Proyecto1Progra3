package cr.ac.una.datos;

/**
 * Error de la capa de datos (archivo no encontrado, XML mal formado, etc.).
 * Se lanza envolviendo la excepcion original para que las capas de arriba
 * no dependan de las clases de javax.xml.
 */
public class DatosException extends RuntimeException {

    public DatosException(String mensaje) {
        super(mensaje);
    }

    public DatosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}

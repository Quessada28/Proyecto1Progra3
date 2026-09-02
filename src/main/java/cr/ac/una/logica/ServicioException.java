package cr.ac.una.logica;

/**
 * Error de negocio o de validacion: "la clave actual no coincide",
 * "la hora de fin debe ser mayor que la de inicio", etc.
 * El controlador la atrapa y la muestra con JOptionPane.
 */
public class ServicioException extends RuntimeException {

    public ServicioException(String mensaje) {
        super(mensaje);
    }
}

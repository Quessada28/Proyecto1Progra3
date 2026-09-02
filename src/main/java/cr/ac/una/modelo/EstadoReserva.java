package cr.ac.una.modelo;

/**
 * Estado de una reserva. Al cancelar NO se borra la reserva: se cambia
 * el estado a CANCELADA y se liberan los recursos asignados.
 */
public enum EstadoReserva {
    ACTIVA,
    CANCELADA
}

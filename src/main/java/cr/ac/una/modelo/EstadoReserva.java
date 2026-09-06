package cr.ac.una.modelo;

public enum EstadoReserva {

    ACTIVA,
    CANCELADA;

    public static EstadoReserva desdeTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            return ACTIVA;
        }
        try {
            return EstadoReserva.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException ignorada) {
            return ACTIVA;
        }
    }
}

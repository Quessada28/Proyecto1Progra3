package cr.ac.una.modelo;

import java.util.List;

/**
 * Respuesta de ReservaService.crear(...).
 * - Si exito == true  -> reserva trae la reserva ya guardada.
 * - Si exito == false -> categoriasNoDisponibles trae las descripciones de las
 *   categorias que no tenian ninguna unidad libre, para mostrarselas al usuario.
 */
public class ResultadoReserva {

    private boolean exito;
    private Reserva reserva;
    private List<String> categoriasNoDisponibles;

    public ResultadoReserva() {
    }

    /** Fabrica para el caso exitoso. */
    public static ResultadoReserva ok(Reserva reserva) {
        // TODO
        return null;
    }

    /** Fabrica para el caso fallido. */
    public static ResultadoReserva fallo(List<String> categoriasNoDisponibles) {
        // TODO
        return null;
    }

    public boolean isExito() { return exito; }
    public Reserva getReserva() { return reserva; }
    public List<String> getCategoriasNoDisponibles() { return categoriasNoDisponibles; }

    public void setExito(boolean exito) { this.exito = exito; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }
    public void setCategoriasNoDisponibles(List<String> c) { this.categoriasNoDisponibles = c; }
}

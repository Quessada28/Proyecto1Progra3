package cr.ac.una.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * DTO con los datos que el usuario llena en el formulario "Nueva reserva",
 * ANTES de que exista una Reserva real.
 * Tambien es lo que devuelve la extraccion con IA (ver paquete ia).
 */
public class DatosReserva {

    private String actividad;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<String> idsCategorias;

    public DatosReserva() {
    }

    public DatosReserva(String actividad, LocalDate fecha, LocalTime horaInicio,
                        LocalTime horaFin, List<String> idsCategorias) {
        // TODO
    }

    public String getActividad() { return actividad; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public List<String> getIdsCategorias() { return idsCategorias; }

    public void setActividad(String actividad) { this.actividad = actividad; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public void setIdsCategorias(List<String> idsCategorias) { this.idsCategorias = idsCategorias; }
}

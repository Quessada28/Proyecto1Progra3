package cr.ac.una.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DatosReserva {

    private String actividad;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<String> idsCategorias = new ArrayList<>();

    public DatosReserva() {
    }

    public DatosReserva(String actividad, LocalDate fecha, LocalTime horaInicio,
                        LocalTime horaFin, List<String> idsCategorias) {
        this.actividad = actividad;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idsCategorias = idsCategorias == null ? new ArrayList<>() : new ArrayList<>(idsCategorias);
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

    public void setIdsCategorias(List<String> idsCategorias) {
        this.idsCategorias = idsCategorias == null ? new ArrayList<>() : new ArrayList<>(idsCategorias);
    }
}

package cr.ac.una.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reserva {

    private String id;
    private String idFuncionario;
    private String actividad;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<String> idsRecursos = new ArrayList<>();
    private EstadoReserva estado = EstadoReserva.ACTIVA;

    public Reserva() {
    }

    public Reserva(String id, String idFuncionario, String actividad,
                   LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
                   List<String> idsRecursos, EstadoReserva estado) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.actividad = actividad;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.idsRecursos = idsRecursos == null ? new ArrayList<>() : new ArrayList<>(idsRecursos);
        this.estado = estado == null ? EstadoReserva.ACTIVA : estado;
    }

    public String getId() { return id; }
    public String getIdFuncionario() { return idFuncionario; }
    public String getActividad() { return actividad; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public List<String> getIdsRecursos() { return idsRecursos; }
    public EstadoReserva getEstado() { return estado; }

    public void setId(String id) { this.id = id; }
    public void setIdFuncionario(String idFuncionario) { this.idFuncionario = idFuncionario; }
    public void setActividad(String actividad) { this.actividad = actividad; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    public void setIdsRecursos(List<String> idsRecursos) {
        this.idsRecursos = idsRecursos == null ? new ArrayList<>() : new ArrayList<>(idsRecursos);
    }

    public boolean estaActiva() {
        return estado == EstadoReserva.ACTIVA;
    }

    public boolean usaRecurso(String idRecurso) {
        return idsRecursos != null && idsRecursos.contains(idRecurso);
    }

    public boolean ocupaHora(LocalDate dia, LocalTime hora) {
        if (!estaActiva() || fecha == null || !fecha.equals(dia)) {
            return false;
        }
        if (horaInicio == null || horaFin == null || hora == null) {
            return false;
        }
        return !hora.isBefore(horaInicio) && hora.isBefore(horaFin);
    }

    public boolean chocaCon(LocalDate dia, LocalTime inicio, LocalTime fin) {
        if (!estaActiva() || fecha == null || !fecha.equals(dia)) {
            return false;
        }
        if (horaInicio == null || horaFin == null || inicio == null || fin == null) {
            return false;
        }
        return inicio.isBefore(horaFin) && horaInicio.isBefore(fin);
    }

    public boolean esFutura() {
        if (fecha == null || horaInicio == null) {
            return false;
        }
        return LocalDateTime.of(fecha, horaInicio).isAfter(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Reserva)) {
            return false;
        }
        return Objects.equals(id, ((Reserva) otro).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return actividad == null ? id : actividad;
    }
}

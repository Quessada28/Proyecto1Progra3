package cr.ac.una.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Reserva de recursos para una actividad (funcionalidad 2).
 * Guarda los ids de los recursos YA ASIGNADOS (uno por cada categoria pedida),
 * no las categorias: eso permite liberar exactamente esos recursos al cancelar.
 * El id es autogenerado con formato RES-000001.
 */
public class Reserva {

    private String id;
    private String idFuncionario;
    private String actividad;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<String> idsRecursos;
    private EstadoReserva estado;

    public Reserva() {
    }

    public Reserva(String id, String idFuncionario, String actividad,
                   LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
                   List<String> idsRecursos, EstadoReserva estado) {
        // TODO
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
    public void setIdsRecursos(List<String> idsRecursos) { this.idsRecursos = idsRecursos; }
    public void setEstado(EstadoReserva estado) { this.estado = estado; }

    /**
     * Indica si esta reserva ocupa la hora indicada de esa fecha.
     * Se usa en la calendarizacion (funcionalidad 6 y 7) y para detectar choques.
     * Regla sugerida: ocupa la hora H si  horaInicio <= H  &&  H < horaFin.
     */
    public boolean ocupaHora(LocalDate dia, LocalTime hora) {
        // TODO
        return false;
    }

    /**
     * Indica si esta reserva se traslapa en el tiempo con el rango recibido.
     * Regla sugerida: hay traslape si  inicio < this.horaFin  &&  this.horaInicio < fin
     * (y ademas es la misma fecha y la reserva esta ACTIVA).
     */
    public boolean chocaCon(LocalDate dia, LocalTime inicio, LocalTime fin) {
        // TODO
        return false;
    }

    /** Reserva cuya fecha/hora de inicio aun no ha pasado (solo esas se pueden cancelar). */
    public boolean esFutura() {
        // TODO
        return false;
    }
}

package cr.ac.una.datos;

import cr.ac.una.modelo.Reserva;
import org.w3c.dom.Element;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Persistencia en datos/reservas.xml
 *
 * <reservas>
 *   <reserva>
 *     <id>RES-000001</id>
 *     <idFuncionario>111</idFuncionario>
 *     <actividad>Reunion clientes</actividad>
 *     <fecha>2026-07-31</fecha>
 *     <horaInicio>08:00</horaInicio>
 *     <horaFin>10:00</horaFin>
 *     <estado>ACTIVA</estado>
 *     <recursos>
 *       <idRecurso>238715</idRecurso>
 *       <idRecurso>34343</idRecurso>
 *     </recursos>
 *   </reserva>
 * </reservas>
 *
 * OJO con fechas y horas: guardarlas siempre en formato ISO
 * (LocalDate.toString() da 2026-07-31, LocalTime.toString() da 08:00) y
 * releerlas con LocalDate.parse / LocalTime.parse. El formato bonito
 * "5 de agosto de 2026" es SOLO para mostrar en pantalla (ver util.Formatos).
 */
public class ReservaXmlDao implements Dao<Reserva> {

    private static final String ARCHIVO = "reservas.xml";
    private static final String RAIZ = "reservas";
    private static final String NODO = "reserva";

    @Override
    public List<Reserva> listar() {
        // TODO
        return null;
    }

    @Override
    public Optional<Reserva> buscarPorId(String id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public void guardar(Reserva reserva) {
        // TODO
    }

    @Override
    public void eliminar(String id) {
        // TODO
    }

    /** Reservas de un funcionario, para la tabla "Mis reservas". */
    public List<Reserva> listarPorFuncionario(String idFuncionario) {
        // TODO
        return null;
    }

    /** Reservas ACTIVAS de una fecha: base de la calendarizacion y de la disponibilidad. */
    public List<Reserva> listarPorFecha(LocalDate fecha) {
        // TODO
        return null;
    }

    /** Reservas ACTIVAS dentro de un rango: estadisticas y semana de actividades. */
    public List<Reserva> listarEntre(LocalDate desde, LocalDate hasta) {
        // TODO
        return null;
    }

    /** Ultimo consecutivo usado, para generar el siguiente RES-00000N. */
    public int ultimoConsecutivo() {
        // TODO
        return 0;
    }

    private Reserva mapear(Element e) {
        // TODO: no olvidar leer la lista de <recursos>
        return null;
    }
}

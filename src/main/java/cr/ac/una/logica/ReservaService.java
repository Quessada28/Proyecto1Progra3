package cr.ac.una.logica;

import cr.ac.una.datos.CategoriaXmlDao;
import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.datos.ReservaXmlDao;
import cr.ac.una.modelo.DatosReserva;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.Reserva;
import cr.ac.una.modelo.ResultadoReserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Funcionalidad 2 (25% de la nota): el corazon del sistema.
 *
 * Algoritmo de crear(...):
 *   1. Validar los datos (ver validar()).
 *   2. Para CADA categoria pedida, buscar el PRIMER recurso libre en ese rango.
 *   3. Si alguna categoria se quedo sin recurso libre -> ResultadoReserva.fallo(...)
 *      con la lista de descripciones de esas categorias. NO se guarda nada.
 *   4. Si todas tienen recurso -> armar la Reserva con esos ids, id nuevo,
 *      estado ACTIVA, guardarla y devolver ResultadoReserva.ok(...).
 *
 * Es "todo o nada": no se guardan reservas parciales.
 */
public class ReservaService {

    private final ReservaXmlDao reservaDao;
    private final RecursoXmlDao recursoDao;
    private final CategoriaXmlDao categoriaDao;

    public ReservaService() {
        this.reservaDao = new ReservaXmlDao();
        this.recursoDao = new RecursoXmlDao();
        this.categoriaDao = new CategoriaXmlDao();
    }

    /** Constructor para pruebas unitarias con DAOs falsos. */
    public ReservaService(ReservaXmlDao reservaDao, RecursoXmlDao recursoDao, CategoriaXmlDao categoriaDao) {
        this.reservaDao = reservaDao;
        this.recursoDao = recursoDao;
        this.categoriaDao = categoriaDao;
    }

    /** Reservas del funcionario logueado (tabla "Mis reservas"). */
    public List<Reserva> misReservas(String idFuncionario) {
        // TODO
        return null;
    }

    /** Intenta registrar la reserva. Ver el algoritmo descrito arriba. */
    public ResultadoReserva crear(String idFuncionario, DatosReserva datos) {
        // TODO
        return null;
    }

    /**
     * Cancela una reserva futura: cambia el estado a CANCELADA.
     * Al quedar CANCELADA, sus recursos dejan de contar como ocupados, o sea
     * quedan liberados automaticamente (por eso estaDisponible solo mira ACTIVAS).
     * Validar: la reserva existe, es del funcionario que la pide, es futura y esta ACTIVA.
     */
    public void cancelar(String idReserva, String idFuncionario) {
        // TODO
    }

    /**
     * Primer recurso de la categoria que no choque con ninguna reserva ACTIVA
     * en esa fecha y rango de horas. Optional.empty() si no hay ninguno libre.
     */
    public Optional<Recurso> primerRecursoDisponible(String idCategoria, LocalDate fecha,
                                                     LocalTime inicio, LocalTime fin) {
        // TODO: recursoDao.listarPorCategoria(...) y para cada uno preguntar estaDisponible(...)
        return Optional.empty();
    }

    /**
     * Un recurso esta disponible si NINGUNA reserva ACTIVA de esa fecha que lo
     * tenga asignado se traslapa con el rango [inicio, fin).
     * Regla de traslape:  inicio < otra.horaFin  &&  otra.horaInicio < fin
     */
    public boolean estaDisponible(String idRecurso, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        // TODO
        return false;
    }

    /**
     * Validaciones del formulario. Lanza ServicioException con el mensaje exacto
     * que se le va a mostrar al usuario:
     *  - actividad obligatoria
     *  - fecha obligatoria y no anterior a hoy
     *  - horaInicio y horaFin obligatorias, horaFin > horaInicio
     *  - al menos una categoria seleccionada
     */
    public void validar(DatosReserva datos) {
        // TODO
    }
}

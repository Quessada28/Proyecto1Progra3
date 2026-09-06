package cr.ac.una.logica;

import cr.ac.una.datos.CategoriaXmlDao;
import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.datos.ReservaXmlDao;
import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.DatosReserva;
import cr.ac.una.modelo.EstadoReserva;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.Reserva;
import cr.ac.una.modelo.ResultadoReserva;
import cr.ac.una.util.GeneradorId;
import cr.ac.una.util.Validador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaService {

    private final ReservaXmlDao reservaDao;
    private final RecursoXmlDao recursoDao;
    private final CategoriaXmlDao categoriaDao;

    public ReservaService() {
        this(new ReservaXmlDao(), new RecursoXmlDao(), new CategoriaXmlDao());
    }

    public ReservaService(ReservaXmlDao reservaDao, RecursoXmlDao recursoDao, CategoriaXmlDao categoriaDao) {
        this.reservaDao = reservaDao;
        this.recursoDao = recursoDao;
        this.categoriaDao = categoriaDao;
    }

    public List<Reserva> misReservas(String idFuncionario) {
        List<Reserva> reservas = reservaDao.listarPorFuncionario(idFuncionario);
        reservas.sort((a, b) -> {
            int porFecha = b.getFecha().compareTo(a.getFecha());
            return porFecha != 0 ? porFecha : b.getHoraInicio().compareTo(a.getHoraInicio());
        });
        return reservas;
    }

    public ResultadoReserva crear(String idFuncionario, DatosReserva datos) {
        Validador.requerido(idFuncionario, "Funcionario");
        validar(datos);

        List<String> asignados = new ArrayList<>();
        List<String> noDisponibles = new ArrayList<>();

        for (String idCategoria : datos.getIdsCategorias()) {
            Optional<Recurso> libre = primerRecursoDisponible(
                    idCategoria, datos.getFecha(), datos.getHoraInicio(), datos.getHoraFin(), asignados);
            if (libre.isPresent()) {
                asignados.add(libre.get().getId());
            } else {
                noDisponibles.add(descripcionCategoria(idCategoria));
            }
        }

        if (!noDisponibles.isEmpty()) {
            return ResultadoReserva.fallo(noDisponibles);
        }

        Reserva reserva = new Reserva(
                GeneradorId.siguienteReserva(reservaDao.ultimoConsecutivo()),
                idFuncionario,
                datos.getActividad().trim(),
                datos.getFecha(),
                datos.getHoraInicio(),
                datos.getHoraFin(),
                asignados,
                EstadoReserva.ACTIVA);
        reservaDao.guardar(reserva);
        return ResultadoReserva.ok(reserva);
    }

    public void cancelar(String idReserva, String idFuncionario) {
        Reserva reserva = reservaDao.buscarPorId(idReserva)
                .orElseThrow(() -> new ServicioException("La reserva indicada no existe."));
        if (!reserva.getIdFuncionario().equals(idFuncionario)) {
            throw new ServicioException("Solo se pueden cancelar las reservas propias.");
        }
        if (!reserva.estaActiva()) {
            throw new ServicioException("La reserva ya estaba cancelada.");
        }
        if (!reserva.esFutura()) {
            throw new ServicioException("Solo se pueden cancelar reservas futuras.");
        }
        reserva.setEstado(EstadoReserva.CANCELADA);
        reservaDao.guardar(reserva);
    }

    public Optional<Recurso> primerRecursoDisponible(String idCategoria, LocalDate fecha,
                                                     LocalTime inicio, LocalTime fin) {
        return primerRecursoDisponible(idCategoria, fecha, inicio, fin, List.of());
    }

    private Optional<Recurso> primerRecursoDisponible(String idCategoria, LocalDate fecha,
                                                      LocalTime inicio, LocalTime fin,
                                                      List<String> yaAsignados) {
        return recursoDao.listarPorCategoria(idCategoria).stream()
                .filter(r -> !yaAsignados.contains(r.getId()))
                .filter(r -> estaDisponible(r.getId(), fecha, inicio, fin))
                .findFirst();
    }

    public boolean estaDisponible(String idRecurso, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        return reservaDao.listarActivasPorFecha(fecha).stream()
                .filter(r -> r.usaRecurso(idRecurso))
                .noneMatch(r -> r.chocaCon(fecha, inicio, fin));
    }

    public void validar(DatosReserva datos) {
        if (datos == null) {
            throw new ServicioException("No hay datos de reserva.");
        }
        Validador.requerido(datos.getActividad(), "Actividad");
        if (datos.getFecha() == null) {
            throw new ServicioException("El campo Fecha es obligatorio.");
        }
        if (datos.getFecha().isBefore(LocalDate.now())) {
            throw new ServicioException("La fecha no puede ser anterior a hoy.");
        }
        if (datos.getHoraInicio() == null) {
            throw new ServicioException("El campo Hora inicio es obligatorio.");
        }
        if (datos.getHoraFin() == null) {
            throw new ServicioException("El campo Hora fin es obligatorio.");
        }
        if (!datos.getHoraFin().isAfter(datos.getHoraInicio())) {
            throw new ServicioException("La hora de fin debe ser posterior a la hora de inicio.");
        }
        if (datos.getIdsCategorias() == null || datos.getIdsCategorias().isEmpty()) {
            throw new ServicioException("Debe seleccionar al menos una categoria de recurso.");
        }
    }

    private String descripcionCategoria(String idCategoria) {
        return categoriaDao.buscarPorId(idCategoria)
                .map(Categoria::getDescripcion)
                .orElse(idCategoria);
    }
}

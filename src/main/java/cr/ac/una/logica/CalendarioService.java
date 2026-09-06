package cr.ac.una.logica;

import cr.ac.una.datos.FuncionarioXmlDao;
import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.datos.ReservaXmlDao;
import cr.ac.una.modelo.Funcionario;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.Reserva;
import cr.ac.una.util.Formatos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarioService {

    public static final LocalTime HORA_INICIO = LocalTime.of(6, 0);
    public static final LocalTime HORA_FIN = LocalTime.of(22, 0);
    public static final int DIAS_SEMANA = 7;

    private static final DateTimeFormatter ENCABEZADO_DIA =
            DateTimeFormatter.ofPattern("EEE yyyy-MM-dd", Formatos.ESPANOL);

    private final ReservaXmlDao reservaDao;
    private final RecursoXmlDao recursoDao;
    private final FuncionarioXmlDao funcionarioDao;

    public CalendarioService() {
        this(new ReservaXmlDao(), new RecursoXmlDao(), new FuncionarioXmlDao());
    }

    public CalendarioService(ReservaXmlDao reservaDao, RecursoXmlDao recursoDao, FuncionarioXmlDao funcionarioDao) {
        this.reservaDao = reservaDao;
        this.recursoDao = recursoDao;
        this.funcionarioDao = funcionarioDao;
    }

    public int cantidadFilas() {
        return HORA_FIN.getHour() - HORA_INICIO.getHour();
    }

    public List<Recurso> recursosDeCategoria(String idCategoria) {
        return recursoDao.listarPorCategoria(idCategoria);
    }

    public String[] columnasDeRecursos(String idCategoria) {
        List<Recurso> recursos = recursosDeCategoria(idCategoria);
        String[] columnas = new String[recursos.size() + 1];
        columnas[0] = "Hora";
        for (int i = 0; i < recursos.size(); i++) {
            columnas[i + 1] = recursos.get(i).getDescripcion();
        }
        return columnas;
    }

    public String[][] calendarioDeRecursos(LocalDate fecha, String idCategoria) {
        List<Recurso> recursos = recursosDeCategoria(idCategoria);
        List<Reserva> reservas = reservaDao.listarActivasPorFecha(fecha);
        String[][] matriz = new String[cantidadFilas()][recursos.size() + 1];

        for (int fila = 0; fila < cantidadFilas(); fila++) {
            LocalTime hora = HORA_INICIO.plusHours(fila);
            matriz[fila][0] = Formatos.hora24(hora);
            for (int columna = 0; columna < recursos.size(); columna++) {
                matriz[fila][columna + 1] = celdaRecurso(reservas, recursos.get(columna), fecha, hora);
            }
        }
        return matriz;
    }

    private String celdaRecurso(List<Reserva> reservas, Recurso recurso, LocalDate fecha, LocalTime hora) {
        for (Reserva reserva : reservas) {
            if (reserva.usaRecurso(recurso.getId()) && reserva.ocupaHora(fecha, hora)) {
                return reserva.getActividad() + " - " + nombreFuncionario(reserva.getIdFuncionario());
            }
        }
        return "";
    }

    public LocalDate lunesDeLaSemana(LocalDate fechaReferencia) {
        return fechaReferencia.with(DayOfWeek.MONDAY);
    }

    public String[] columnasDeActividades(LocalDate fechaReferencia) {
        LocalDate lunes = lunesDeLaSemana(fechaReferencia);
        String[] columnas = new String[DIAS_SEMANA + 1];
        columnas[0] = "Hora";
        for (int i = 0; i < DIAS_SEMANA; i++) {
            columnas[i + 1] = lunes.plusDays(i).format(ENCABEZADO_DIA);
        }
        return columnas;
    }

    public String[][] calendarioDeActividades(LocalDate fechaReferencia) {
        LocalDate lunes = lunesDeLaSemana(fechaReferencia);
        String[][] matriz = new String[cantidadFilas()][DIAS_SEMANA + 1];

        for (int fila = 0; fila < cantidadFilas(); fila++) {
            LocalTime hora = HORA_INICIO.plusHours(fila);
            matriz[fila][0] = Formatos.hora24(hora);
            for (int dia = 0; dia < DIAS_SEMANA; dia++) {
                matriz[fila][dia + 1] = celdaActividades(lunes.plusDays(dia), hora);
            }
        }
        return matriz;
    }

    private String celdaActividades(LocalDate dia, LocalTime hora) {
        List<String> actividades = new ArrayList<>();
        for (Reserva reserva : reservaDao.listarActivasPorFecha(dia)) {
            if (reserva.ocupaHora(dia, hora)) {
                String texto = reserva.getActividad()
                        + " (" + nombreFuncionario(reserva.getIdFuncionario()) + ")";
                if (!actividades.contains(texto)) {
                    actividades.add(texto);
                }
            }
        }
        return String.join(" | ", actividades);
    }

    private String nombreFuncionario(String idFuncionario) {
        return funcionarioDao.buscarPorId(idFuncionario)
                .map(Funcionario::getNombre)
                .orElse(idFuncionario);
    }
}

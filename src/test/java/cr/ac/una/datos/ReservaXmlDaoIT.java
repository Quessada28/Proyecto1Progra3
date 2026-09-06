package cr.ac.una.datos;

import cr.ac.una.modelo.EstadoReserva;
import cr.ac.una.modelo.Reserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaXmlDaoIT {

    @TempDir
    Path carpetaTemporal;

    private String carpetaOriginal;
    private ReservaXmlDao dao;

    @BeforeEach
    void apuntarAlDirectorioTemporal() {
        carpetaOriginal = XmlUtil.getCarpeta();
        XmlUtil.setCarpeta(carpetaTemporal.toString());
        dao = new ReservaXmlDao();
    }

    @AfterEach
    void restaurarCarpeta() {
        XmlUtil.setCarpeta(carpetaOriginal);
    }

    private Reserva reserva(String id, LocalDate fecha, String... recursos) {
        return new Reserva(id, "111", "Reunion clientes", fecha,
                LocalTime.of(8, 0), LocalTime.of(10, 0), List.of(recursos), EstadoReserva.ACTIVA);
    }

    @Test
    void guardaYReleeLaListaDeRecursosCompleta() {
        dao.guardar(reserva("RES-000001", LocalDate.of(2026, 7, 31), "238715", "34343", "452784"));

        Reserva leida = new ReservaXmlDao().buscarPorId("RES-000001").orElseThrow();

        assertEquals(List.of("238715", "34343", "452784"), leida.getIdsRecursos());
    }

    @Test
    @DisplayName("Las fechas y horas sobreviven la ida y vuelta al XML")
    void lasFechasYHorasSobrevivenLaIdaYVuelta() {
        LocalDate fecha = LocalDate.of(2026, 7, 31);
        dao.guardar(reserva("RES-000001", fecha, "238715"));

        Reserva leida = new ReservaXmlDao().buscarPorId("RES-000001").orElseThrow();

        assertEquals(fecha, leida.getFecha());
        assertEquals(LocalTime.of(8, 0), leida.getHoraInicio());
        assertEquals(LocalTime.of(10, 0), leida.getHoraFin());
    }

    @Test
    void conservaElEstadoDeLaReserva() {
        Reserva cancelada = reserva("RES-000001", LocalDate.of(2026, 7, 31), "238715");
        cancelada.setEstado(EstadoReserva.CANCELADA);
        dao.guardar(cancelada);

        assertEquals(EstadoReserva.CANCELADA,
                new ReservaXmlDao().buscarPorId("RES-000001").orElseThrow().getEstado());
    }

    @Test
    void listarPorFechaSoloTraeLasDeEsaFecha() {
        dao.guardar(reserva("RES-000001", LocalDate.of(2026, 7, 31), "238715"));
        dao.guardar(reserva("RES-000002", LocalDate.of(2026, 8, 1), "238715"));

        assertEquals(1, dao.listarPorFecha(LocalDate.of(2026, 7, 31)).size());
    }

    @Test
    void listarEntreIncluyeLosDosExtremos() {
        dao.guardar(reserva("RES-000001", LocalDate.of(2026, 8, 3), "238715"));
        dao.guardar(reserva("RES-000002", LocalDate.of(2026, 8, 6), "238715"));
        dao.guardar(reserva("RES-000003", LocalDate.of(2026, 8, 10), "238715"));
        dao.guardar(reserva("RES-000004", LocalDate.of(2026, 8, 15), "238715"));

        assertEquals(3, dao.listarEntre(LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 10)).size());
    }

    @Test
    void listarPorFuncionarioFiltraPorDuenoDeLaReserva() {
        dao.guardar(reserva("RES-000001", LocalDate.of(2026, 8, 3), "238715"));
        Reserva deOtro = reserva("RES-000002", LocalDate.of(2026, 8, 3), "34343");
        deOtro.setIdFuncionario("222");
        dao.guardar(deOtro);

        assertEquals(1, dao.listarPorFuncionario("111").size());
        assertEquals("RES-000002", dao.listarPorFuncionario("222").get(0).getId());
    }

    @Test
    void ultimoConsecutivoSirveParaGenerarElSiguienteId() {
        dao.guardar(reserva("RES-000001", LocalDate.of(2026, 8, 3), "238715"));
        dao.guardar(reserva("RES-000005", LocalDate.of(2026, 8, 4), "238715"));

        assertEquals(5, dao.ultimoConsecutivo());
    }
}

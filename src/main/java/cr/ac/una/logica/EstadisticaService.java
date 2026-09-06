package cr.ac.una.logica;

import cr.ac.una.datos.CategoriaXmlDao;
import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.datos.ReservaXmlDao;
import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.Reserva;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class EstadisticaService {

    private final ReservaXmlDao reservaDao;
    private final RecursoXmlDao recursoDao;
    private final CategoriaXmlDao categoriaDao;

    public EstadisticaService() {
        this(new ReservaXmlDao(), new RecursoXmlDao(), new CategoriaXmlDao());
    }

    public EstadisticaService(ReservaXmlDao reservaDao, RecursoXmlDao recursoDao, CategoriaXmlDao categoriaDao) {
        this.reservaDao = reservaDao;
        this.recursoDao = recursoDao;
        this.categoriaDao = categoriaDao;
    }

    public Map<String, Integer> recursosPorCategoria(LocalDate desde, LocalDate hasta) {
        validarPeriodo(desde, hasta);
        Map<String, Integer> conteo = new LinkedHashMap<>();
        for (Reserva reserva : reservaDao.listarEntre(desde, hasta)) {
            if (!reserva.estaActiva()) {
                continue;
            }
            for (String idRecurso : reserva.getIdsRecursos()) {
                String categoria = categoriaDeRecurso(idRecurso);
                conteo.merge(categoria, 1, Integer::sum);
            }
        }
        return conteo;
    }

    public Map<String, Integer> actividadesPorSemana(LocalDate desde, LocalDate hasta) {
        validarPeriodo(desde, hasta);
        Map<String, Integer> conteo = new LinkedHashMap<>();

        LocalDate lunes = desde.with(DayOfWeek.MONDAY);
        while (!lunes.isAfter(hasta)) {
            conteo.put(lunes.toString(), 0);
            lunes = lunes.plusWeeks(1);
        }

        for (Reserva reserva : reservaDao.listarEntre(desde, hasta)) {
            if (!reserva.estaActiva()) {
                continue;
            }
            String clave = reserva.getFecha().with(DayOfWeek.MONDAY).toString();
            conteo.merge(clave, 1, Integer::sum);
        }
        return conteo;
    }

    public void validarPeriodo(LocalDate desde, LocalDate hasta) {
        if (desde == null) {
            throw new ServicioException("La fecha Desde es obligatoria.");
        }
        if (hasta == null) {
            throw new ServicioException("La fecha Hasta es obligatoria.");
        }
        if (desde.isAfter(hasta)) {
            throw new ServicioException("La fecha Desde no puede ser posterior a la fecha Hasta.");
        }
    }

    private String categoriaDeRecurso(String idRecurso) {
        return recursoDao.buscarPorId(idRecurso)
                .map(Recurso::getIdCategoria)
                .flatMap(categoriaDao::buscarPorId)
                .map(Categoria::getDescripcion)
                .orElse("Sin categoria");
    }
}

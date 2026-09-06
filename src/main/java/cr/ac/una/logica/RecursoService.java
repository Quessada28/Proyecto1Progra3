package cr.ac.una.logica;

import cr.ac.una.datos.CategoriaXmlDao;
import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.datos.ReservaXmlDao;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.util.Validador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecursoService {

    private final RecursoXmlDao dao;
    private final CategoriaXmlDao categoriaDao;
    private final ReservaXmlDao reservaDao;

    public RecursoService() {
        this(new RecursoXmlDao(), new CategoriaXmlDao(), new ReservaXmlDao());
    }

    public RecursoService(RecursoXmlDao dao, CategoriaXmlDao categoriaDao, ReservaXmlDao reservaDao) {
        this.dao = dao;
        this.categoriaDao = categoriaDao;
        this.reservaDao = reservaDao;
    }

    public List<Recurso> listar() {
        return dao.listar();
    }

    public Optional<Recurso> buscarPorId(String id) {
        return dao.buscarPorId(id);
    }

    public List<Recurso> listarPorCategoria(String idCategoria) {
        return dao.listarPorCategoria(idCategoria);
    }

    public List<Recurso> buscar(String idCategoria, String descripcion) {
        String texto = descripcion == null ? "" : descripcion.trim().toLowerCase();
        return dao.listarPorCategoria(idCategoria).stream()
                .filter(r -> texto.isEmpty()
                        || (r.getDescripcion() != null
                            && r.getDescripcion().toLowerCase().contains(texto)))
                .collect(Collectors.toList());
    }

    public Recurso guardar(Recurso recurso) {
        Validador.requerido(recurso.getId(), "ID");
        Validador.requerido(recurso.getIdCategoria(), "Categoria");
        Validador.requerido(recurso.getDescripcion(), "Descripcion");

        recurso.setId(recurso.getId().trim());
        recurso.setDescripcion(recurso.getDescripcion().trim());

        if (categoriaDao.buscarPorId(recurso.getIdCategoria()).isEmpty()) {
            throw new ServicioException("La categoria seleccionada no existe.");
        }
        dao.guardar(recurso);
        return recurso;
    }

    public void eliminar(String id) {
        Validador.requerido(id, "ID");
        boolean tieneReservasActivas = reservaDao.listar().stream()
                .anyMatch(r -> r.estaActiva() && r.usaRecurso(id) && r.esFutura());
        if (tieneReservasActivas) {
            throw new ServicioException(
                    "No se puede borrar el recurso porque tiene reservas activas a futuro.");
        }
        dao.eliminar(id);
    }
}

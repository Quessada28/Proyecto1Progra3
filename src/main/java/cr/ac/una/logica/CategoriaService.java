package cr.ac.una.logica;

import cr.ac.una.datos.CategoriaXmlDao;
import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.modelo.Categoria;
import cr.ac.una.util.GeneradorId;
import cr.ac.una.util.Validador;

import java.util.List;
import java.util.Optional;

public class CategoriaService {

    private final CategoriaXmlDao dao;
    private final RecursoXmlDao recursoDao;

    public CategoriaService() {
        this(new CategoriaXmlDao(), new RecursoXmlDao());
    }

    public CategoriaService(CategoriaXmlDao dao, RecursoXmlDao recursoDao) {
        this.dao = dao;
        this.recursoDao = recursoDao;
    }

    public List<Categoria> listar() {
        return dao.listar();
    }

    public Optional<Categoria> buscarPorId(String id) {
        return dao.buscarPorId(id);
    }

    public List<Categoria> buscarPorDescripcion(String texto) {
        return dao.buscarPorDescripcion(texto);
    }

    public Categoria guardar(Categoria categoria) {
        Validador.requerido(categoria.getDescripcion(), "Descripcion");
        String descripcion = categoria.getDescripcion().trim();
        categoria.setDescripcion(descripcion);

        if (categoria.getId() == null || categoria.getId().isBlank()) {
            categoria.setId(GeneradorId.siguienteCategoria(dao.ultimoConsecutivo()));
        }
        boolean repetida = dao.listar().stream()
                .anyMatch(c -> c.getDescripcion().equalsIgnoreCase(descripcion)
                        && !c.getId().equals(categoria.getId()));
        if (repetida) {
            throw new ServicioException("Ya existe una categoria con esa descripcion.");
        }
        dao.guardar(categoria);
        return categoria;
    }

    public void eliminar(String id) {
        Validador.requerido(id, "ID");
        if (!recursoDao.listarPorCategoria(id).isEmpty()) {
            throw new ServicioException(
                    "No se puede borrar la categoria porque tiene recursos asociados.");
        }
        dao.eliminar(id);
    }

    public String descripcionDe(String idCategoria) {
        return dao.buscarPorId(idCategoria).map(Categoria::getDescripcion).orElse(idCategoria);
    }
}

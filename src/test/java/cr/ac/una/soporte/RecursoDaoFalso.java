package cr.ac.una.soporte;

import cr.ac.una.datos.RecursoXmlDao;
import cr.ac.una.modelo.Recurso;

import java.util.List;
import java.util.Optional;

public class RecursoDaoFalso extends RecursoXmlDao {

    private final DaoEnMemoria<Recurso> memoria = new DaoEnMemoria<>(Recurso::getId);

    @Override
    public List<Recurso> listar() {
        return memoria.listar();
    }

    @Override
    public Optional<Recurso> buscarPorId(String id) {
        return memoria.buscarPorId(id);
    }

    @Override
    public void guardar(Recurso recurso) {
        memoria.guardar(recurso);
    }

    @Override
    public void eliminar(String id) {
        memoria.eliminar(id);
    }

    @Override
    public boolean existe(String id) {
        return memoria.existe(id);
    }

    public int cantidad() {
        return memoria.cantidad();
    }
}

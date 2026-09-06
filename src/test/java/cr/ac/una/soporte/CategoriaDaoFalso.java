package cr.ac.una.soporte;

import cr.ac.una.datos.CategoriaXmlDao;
import cr.ac.una.modelo.Categoria;

import java.util.List;
import java.util.Optional;

public class CategoriaDaoFalso extends CategoriaXmlDao {

    private final DaoEnMemoria<Categoria> memoria = new DaoEnMemoria<>(Categoria::getId);

    @Override
    public List<Categoria> listar() {
        return memoria.listar();
    }

    @Override
    public Optional<Categoria> buscarPorId(String id) {
        return memoria.buscarPorId(id);
    }

    @Override
    public void guardar(Categoria categoria) {
        memoria.guardar(categoria);
    }

    @Override
    public void eliminar(String id) {
        memoria.eliminar(id);
    }

    @Override
    public boolean existe(String id) {
        return memoria.existe(id);
    }

    @Override
    public int ultimoConsecutivo() {
        return memoria.ultimoConsecutivo();
    }

    public int cantidad() {
        return memoria.cantidad();
    }
}

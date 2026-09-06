package cr.ac.una.soporte;

import cr.ac.una.datos.FuncionarioXmlDao;
import cr.ac.una.modelo.Funcionario;

import java.util.List;
import java.util.Optional;

public class FuncionarioDaoFalso extends FuncionarioXmlDao {

    private final DaoEnMemoria<Funcionario> memoria = new DaoEnMemoria<>(Funcionario::getId);

    @Override
    public List<Funcionario> listar() {
        return memoria.listar();
    }

    @Override
    public Optional<Funcionario> buscarPorId(String id) {
        return memoria.buscarPorId(id);
    }

    @Override
    public void guardar(Funcionario funcionario) {
        memoria.guardar(funcionario);
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

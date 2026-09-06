package cr.ac.una.logica;

import cr.ac.una.datos.FuncionarioXmlDao;
import cr.ac.una.datos.ReservaXmlDao;
import cr.ac.una.modelo.Funcionario;
import cr.ac.una.util.Validador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FuncionarioService {

    private final FuncionarioXmlDao dao;
    private final UsuarioService usuarioService;
    private final ReservaXmlDao reservaDao;

    public FuncionarioService() {
        this(new FuncionarioXmlDao(), new UsuarioService(), new ReservaXmlDao());
    }

    public FuncionarioService(FuncionarioXmlDao dao, UsuarioService usuarioService, ReservaXmlDao reservaDao) {
        this.dao = dao;
        this.usuarioService = usuarioService;
        this.reservaDao = reservaDao;
    }

    public List<Funcionario> listar() {
        return dao.listar();
    }

    public Optional<Funcionario> buscarPorId(String id) {
        return dao.buscarPorId(id);
    }

    public List<Funcionario> buscar(String id, String nombre) {
        String idBuscado = id == null ? "" : id.trim().toLowerCase();
        String nombreBuscado = nombre == null ? "" : nombre.trim().toLowerCase();
        return dao.listar().stream()
                .filter(f -> idBuscado.isEmpty()
                        || f.getId().toLowerCase().contains(idBuscado))
                .filter(f -> nombreBuscado.isEmpty()
                        || (f.getNombre() != null
                            && f.getNombre().toLowerCase().contains(nombreBuscado)))
                .collect(Collectors.toList());
    }

    public Funcionario guardar(Funcionario funcionario) {
        Validador.requerido(funcionario.getId(), "ID");
        Validador.requerido(funcionario.getNombre(), "Nombre");
        Validador.telefonoValido(funcionario.getTelefono(), "Telefono");

        funcionario.setId(funcionario.getId().trim());
        funcionario.setNombre(funcionario.getNombre().trim());
        funcionario.setTelefono(funcionario.getTelefono().trim());

        boolean esNuevo = dao.buscarPorId(funcionario.getId()).isEmpty();
        dao.guardar(funcionario);
        if (esNuevo) {
            usuarioService.crearUsuarioDeFuncionario(funcionario.getId());
        }
        return funcionario;
    }

    public void eliminar(String id) {
        Validador.requerido(id, "ID");
        boolean tieneReservas = reservaDao.listarPorFuncionario(id).stream()
                .anyMatch(r -> r.estaActiva() && r.esFutura());
        if (tieneReservas) {
            throw new ServicioException(
                    "No se puede borrar el funcionario porque tiene reservas activas a futuro.");
        }
        dao.eliminar(id);
        usuarioService.eliminar(id);
    }

    public String nombreDe(String idFuncionario) {
        return dao.buscarPorId(idFuncionario).map(Funcionario::getNombre).orElse(idFuncionario);
    }
}

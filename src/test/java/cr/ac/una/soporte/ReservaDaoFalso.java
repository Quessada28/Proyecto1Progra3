package cr.ac.una.soporte;

import cr.ac.una.datos.ReservaXmlDao;
import cr.ac.una.modelo.Reserva;

import java.util.List;
import java.util.Optional;

public class ReservaDaoFalso extends ReservaXmlDao {

    private final DaoEnMemoria<Reserva> memoria = new DaoEnMemoria<>(Reserva::getId);

    @Override
    public List<Reserva> listar() {
        return memoria.listar();
    }

    @Override
    public Optional<Reserva> buscarPorId(String id) {
        return memoria.buscarPorId(id);
    }

    @Override
    public void guardar(Reserva reserva) {
        memoria.guardar(reserva);
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

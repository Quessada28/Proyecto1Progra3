package cr.ac.una.datos;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> listar();

    Optional<T> buscarPorId(String id);

    void guardar(T entidad);

    void eliminar(String id);
}

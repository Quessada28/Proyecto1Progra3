package cr.ac.una.datos;

import java.util.List;
import java.util.Optional;

/**
 * Contrato comun de acceso a datos. T es la entidad del modelo.
 * Trabajar contra esta interfaz permite que los servicios se puedan
 * probar con un DAO falso en memoria (ver las pruebas unitarias).
 */
public interface Dao<T> {

    List<T> listar();

    Optional<T> buscarPorId(String id);

    /** Inserta si el id no existe, actualiza si ya existe. */
    void guardar(T entidad);

    void eliminar(String id);
}

package cr.ac.una.soporte;

import cr.ac.una.util.GeneradorId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DaoEnMemoria<T> {

    private final List<T> registros = new ArrayList<>();
    private final Function<T, String> extractorDeId;

    public DaoEnMemoria(Function<T, String> extractorDeId) {
        this.extractorDeId = extractorDeId;
    }

    public List<T> listar() {
        return new ArrayList<>(registros);
    }

    public Optional<T> buscarPorId(String id) {
        return registros.stream()
                .filter(r -> extractorDeId.apply(r).equals(id))
                .findFirst();
    }

    public void guardar(T entidad) {
        String id = extractorDeId.apply(entidad);
        registros.removeIf(r -> extractorDeId.apply(r).equals(id));
        registros.add(entidad);
    }

    public void eliminar(String id) {
        registros.removeIf(r -> extractorDeId.apply(r).equals(id));
    }

    public boolean existe(String id) {
        return buscarPorId(id).isPresent();
    }

    public int ultimoConsecutivo() {
        int mayor = 0;
        for (T entidad : registros) {
            int consecutivo = GeneradorId.consecutivoDe(extractorDeId.apply(entidad));
            if (consecutivo > mayor) {
                mayor = consecutivo;
            }
        }
        return mayor;
    }

    public int cantidad() {
        return registros.size();
    }
}

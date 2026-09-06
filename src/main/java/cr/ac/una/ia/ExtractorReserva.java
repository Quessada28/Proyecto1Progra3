package cr.ac.una.ia;

import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.DatosReserva;

import java.util.List;

public interface ExtractorReserva {

    DatosReserva extraer(String frase, List<Categoria> categorias);
}

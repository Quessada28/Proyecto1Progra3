package cr.ac.una.ia;

import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.DatosReserva;

import java.util.List;

/**
 * Contrato de la extraccion con IA (funcionalidad 2, boton "Extraer").
 *
 * Se define como interfaz a proposito: asi la vista y el controlador no
 * dependen del proveedor del modelo de lenguaje, y en las pruebas unitarias
 * se puede usar una implementacion falsa que devuelve datos fijos, sin red.
 */
public interface ExtractorReserva {

    /**
     * Convierte una frase en lenguaje natural en los datos del formulario.
     *
     * @param frase       lo que el usuario escribio, por ejemplo
     *                    "necesito una reunion de trabajo el 14 de agosto de 8am
     *                     a 10am en una sala y usando una laptop"
     * @param categorias  las categorias que existen en el sistema; se le pasan al
     *                    modelo para que escoja ids reales y no invente
     * @return los datos extraidos (los campos que no aparezcan en la frase van en null)
     */
    DatosReserva extraer(String frase, List<Categoria> categorias);
}

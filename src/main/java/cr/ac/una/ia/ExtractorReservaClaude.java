package cr.ac.una.ia;

import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.DatosReserva;

import java.util.List;

/**
 * Implementacion de ExtractorReserva usando el SDK de Anthropic
 * (dependencia com.anthropic:anthropic-java, ya esta en el pom.xml).
 *
 * COMO ARMARLO
 * ------------
 * 1. La llave NO se escribe en el codigo ni se sube a GitHub. Se lee de la
 *    variable de ambiente ANTHROPIC_API_KEY:
 *        AnthropicClient client = AnthropicOkHttpClient.fromEnv();
 *
 * 2. El prompt debe llevar:
 *    - la frase del usuario
 *    - la lista de categorias existentes (id + descripcion)
 *    - la fecha de HOY, para que pueda resolver "el proximo 14 de agosto"
 *    - la instruccion de devolver los ids de categoria tal cual, sin inventar
 *
 * 3. Para que la respuesta venga estructurada y no haya que parsear texto libre,
 *    conviene usar salida estructurada: se declara un record con los campos que
 *    se esperan y se pasa .outputConfig(RespuestaIA.class) al construir los
 *    parametros; el SDK deriva el esquema JSON y devuelve el objeto ya tipado.
 *
 * 4. Modelo recomendado: "claude-opus-5".
 *
 * 5. Si falla la red o la llave no esta configurada, lanzar ServicioException
 *    con un mensaje entendible: el usuario siempre puede llenar el formulario a mano.
 */
public class ExtractorReservaClaude implements ExtractorReserva {

    private static final String MODELO = "claude-opus-5";

    /**
     * Estructura que se le pide al modelo. Las horas y la fecha se piden como
     * texto ISO (fecha "2026-08-14", horas "08:00") y se convierten aqui con
     * LocalDate.parse / LocalTime.parse.
     */
    public record RespuestaIA(String actividad, String fecha, String horaInicio,
                              String horaFin, List<String> idsCategorias) {
    }

    @Override
    public DatosReserva extraer(String frase, List<Categoria> categorias) {
        // TODO: 1) construir el prompt con la frase, las categorias y la fecha de hoy
        //       2) llamar al modelo pidiendo la salida estructurada RespuestaIA
        //       3) convertir RespuestaIA -> DatosReserva (parseando fecha y horas)
        return null;
    }

    /** Arma el texto del prompt. Separado para poder probarlo sin llamar a la red. */
    String construirPrompt(String frase, List<Categoria> categorias) {
        // TODO
        return null;
    }
}

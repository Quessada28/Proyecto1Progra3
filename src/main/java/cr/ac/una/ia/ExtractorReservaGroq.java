package cr.ac.una.ia;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cr.ac.una.logica.ServicioException;
import cr.ac.una.modelo.Categoria;
import cr.ac.una.modelo.DatosReserva;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ExtractorReservaGroq implements ExtractorReserva {

    public static final String VARIABLE_LLAVE = "GROQ_API_KEY";
    public static final String VARIABLE_MODELO = "GROQ_MODEL";

    private static final String MODELO_POR_DEFECTO = "llama-3.3-70b-versatile";
    private static final String ENDPOINT = "https://api.groq.com/openai/v1/chat/completions";

    private final HttpClient cliente;
    private final Gson gson = new Gson();

    public ExtractorReservaGroq() {
        this.cliente = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    @Override
    public DatosReserva extraer(String frase, List<Categoria> categorias) {
        if (frase == null || frase.isBlank()) {
            throw new ServicioException("Escriba una frase que describa la reserva.");
        }
        String llave = System.getenv(VARIABLE_LLAVE);
        if (llave == null || llave.isBlank()) {
            throw new ServicioException(
                    "No hay llave de Groq configurada. Defina la variable de ambiente "
                            + VARIABLE_LLAVE + " o llene el formulario a mano.");
        }
        return convertir(invocar(llave, construirPrompt(frase, categorias)), categorias);
    }

    String construirPrompt(String frase, List<Categoria> categorias) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Sos un asistente que extrae los datos de una reserva de recursos ")
              .append("a partir de una frase en lenguaje natural escrita en espanol.\n\n")
              .append("La fecha de hoy es ").append(LocalDate.now()).append(".\n")
              .append("Usala para resolver expresiones relativas como manana o el proximo 14 de agosto.\n\n")
              .append("Categorias de recurso disponibles en el sistema:\n");
        for (Categoria categoria : categorias) {
            prompt.append("- id=").append(categoria.getId())
                  .append(" descripcion=").append(categoria.getDescripcion())
                  .append("\n");
        }
        prompt.append("\nResponde unicamente con un objeto JSON con esta forma exacta:\n")
              .append("{\n")
              .append("  \"actividad\": \"texto o null\",\n")
              .append("  \"fecha\": \"yyyy-MM-dd o null\",\n")
              .append("  \"horaInicio\": \"HH:mm o null\",\n")
              .append("  \"horaFin\": \"HH:mm o null\",\n")
              .append("  \"idsCategorias\": [\"id\", \"id\"]\n")
              .append("}\n\n")
              .append("Reglas:\n")
              .append("1. Devolve unicamente ids de categoria que aparezcan en la lista anterior.\n")
              .append("2. Si la frase no menciona un dato, devolve null en ese campo.\n")
              .append("3. La fecha va en formato ISO yyyy-MM-dd.\n")
              .append("4. Las horas van en formato ISO de 24 horas HH:mm.\n")
              .append("5. La actividad es una descripcion corta, sin fechas ni horas.\n")
              .append("6. No agregues texto fuera del JSON.\n\n")
              .append("Frase del usuario:\n")
              .append(frase);
        return prompt.toString();
    }

    String modelo() {
        String configurado = System.getenv(VARIABLE_MODELO);
        return configurado == null || configurado.isBlank()
                ? MODELO_POR_DEFECTO : configurado.trim();
    }

    private String invocar(String llave, String prompt) {
        HttpResponse<String> respuesta = enviar(llave, cuerpo(prompt, true));

        if (respuesta.statusCode() == 400 && rechazaElEsquema(respuesta.body())) {
            respuesta = enviar(llave, cuerpo(prompt, false));
        }
        if (respuesta.statusCode() != 200) {
            throw new ServicioException("Groq respondio con el codigo "
                    + respuesta.statusCode() + " usando el modelo " + modelo() + ".\n\n"
                    + detalleDelError(respuesta.body())
                    + "\n\nPuede llenar el formulario a mano.");
        }
        return textoDeLaRespuesta(respuesta.body());
    }

    private HttpResponse<String> enviar(String llave, String cuerpo) {
        try {
            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(ENDPOINT))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + llave)
                    .timeout(Duration.ofSeconds(45))
                    .POST(HttpRequest.BodyPublishers.ofString(cuerpo, StandardCharsets.UTF_8))
                    .build();
            return cliente.send(peticion, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServicioException("La consulta a Groq fue interrumpida.");
        } catch (Exception e) {
            throw new ServicioException("No se pudo consultar a Groq: " + e.getMessage()
                    + ". Puede llenar el formulario a mano.");
        }
    }

    private boolean rechazaElEsquema(String cuerpo) {
        if (cuerpo == null) {
            return false;
        }
        String texto = cuerpo.toLowerCase();
        return texto.contains("json_schema") || texto.contains("response_format");
    }

    private String cuerpo(String prompt, boolean conEsquema) {
        JsonObject mensaje = new JsonObject();
        mensaje.addProperty("role", "user");
        mensaje.addProperty("content", prompt);

        JsonArray mensajes = new JsonArray();
        mensajes.add(mensaje);

        JsonObject formato = new JsonObject();
        if (conEsquema) {
            formato.addProperty("type", "json_schema");
            formato.add("json_schema", esquema());
        } else {
            formato.addProperty("type", "json_object");
        }

        JsonObject raiz = new JsonObject();
        raiz.addProperty("model", modelo());
        raiz.addProperty("temperature", 0);
        raiz.add("messages", mensajes);
        raiz.add("response_format", formato);
        return gson.toJson(raiz);
    }

    private JsonObject esquema() {
        JsonObject elementos = new JsonObject();
        elementos.addProperty("type", "string");

        JsonObject lista = new JsonObject();
        lista.addProperty("type", "array");
        lista.add("items", elementos);

        JsonObject propiedades = new JsonObject();
        propiedades.add("actividad", cadenaNulable());
        propiedades.add("fecha", cadenaNulable());
        propiedades.add("horaInicio", cadenaNulable());
        propiedades.add("horaFin", cadenaNulable());
        propiedades.add("idsCategorias", lista);

        JsonArray requeridos = new JsonArray();
        requeridos.add("actividad");
        requeridos.add("fecha");
        requeridos.add("horaInicio");
        requeridos.add("horaFin");
        requeridos.add("idsCategorias");

        JsonObject definicion = new JsonObject();
        definicion.addProperty("type", "object");
        definicion.add("properties", propiedades);
        definicion.add("required", requeridos);
        definicion.addProperty("additionalProperties", false);

        JsonObject esquema = new JsonObject();
        esquema.addProperty("name", "datos_reserva");
        esquema.addProperty("strict", true);
        esquema.add("schema", definicion);
        return esquema;
    }

    private JsonObject cadenaNulable() {
        JsonArray tipos = new JsonArray();
        tipos.add("string");
        tipos.add("null");

        JsonObject cadena = new JsonObject();
        cadena.add("type", tipos);
        return cadena;
    }

    private String detalleDelError(String cuerpo) {
        if (cuerpo == null || cuerpo.isBlank()) {
            return "La respuesta no trajo detalle.";
        }
        try {
            JsonObject error = JsonParser.parseString(cuerpo).getAsJsonObject()
                    .getAsJsonObject("error");
            if (error != null && error.has("message")) {
                return error.get("message").getAsString();
            }
        } catch (RuntimeException ignorada) {
            return recortar(cuerpo);
        }
        return recortar(cuerpo);
    }

    private String recortar(String texto) {
        return texto.length() > 400 ? texto.substring(0, 400) : texto;
    }

    private String textoDeLaRespuesta(String respuestaJson) {
        JsonObject raiz = JsonParser.parseString(respuestaJson).getAsJsonObject();
        JsonArray opciones = raiz.getAsJsonArray("choices");
        if (opciones == null || opciones.isEmpty()) {
            throw new ServicioException("Groq no devolvio ningun resultado.");
        }
        return opciones.get(0).getAsJsonObject()
                .getAsJsonObject("message")
                .get("content").getAsString();
    }

    private DatosReserva convertir(String json, List<Categoria> categorias) {
        JsonObject objeto = JsonParser.parseString(json).getAsJsonObject();
        DatosReserva datos = new DatosReserva();
        datos.setActividad(cadena(objeto, "actividad"));
        datos.setFecha(fecha(cadena(objeto, "fecha")));
        datos.setHoraInicio(hora(cadena(objeto, "horaInicio")));
        datos.setHoraFin(hora(cadena(objeto, "horaFin")));
        datos.setIdsCategorias(idsValidos(objeto, categorias));
        return datos;
    }

    private List<String> idsValidos(JsonObject objeto, List<Categoria> categorias) {
        List<String> ids = new ArrayList<>();
        if (!objeto.has("idsCategorias") || objeto.get("idsCategorias").isJsonNull()) {
            return ids;
        }
        for (JsonElement elemento : objeto.getAsJsonArray("idsCategorias")) {
            String id = elemento.getAsString();
            boolean existe = categorias.stream().anyMatch(c -> c.getId().equals(id));
            if (existe && !ids.contains(id)) {
                ids.add(id);
            }
        }
        return ids;
    }

    private static String cadena(JsonObject objeto, String campo) {
        if (!objeto.has(campo) || objeto.get(campo).isJsonNull()) {
            return null;
        }
        String valor = objeto.get(campo).getAsString();
        if (valor.isBlank() || "null".equalsIgnoreCase(valor.trim())) {
            return null;
        }
        return valor.trim();
    }

    private static LocalDate fecha(String texto) {
        try {
            return texto == null ? null : LocalDate.parse(texto);
        } catch (RuntimeException ignorada) {
            return null;
        }
    }

    private static LocalTime hora(String texto) {
        try {
            return texto == null ? null : LocalTime.parse(texto);
        } catch (RuntimeException ignorada) {
            return null;
        }
    }
}

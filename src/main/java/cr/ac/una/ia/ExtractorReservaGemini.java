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

public class ExtractorReservaGemini implements ExtractorReserva {

    public static final String VARIABLE_LLAVE = "GEMINI_API_KEY";
    public static final String VARIABLE_MODELO = "GEMINI_MODEL";

    private static final String MODELO_POR_DEFECTO = "gemini-3.6-flash";
    private static final String ENDPOINT =
            "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent";

    private final HttpClient cliente;
    private final Gson gson = new Gson();

    public ExtractorReservaGemini() {
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
                    "No hay llave de Gemini configurada. Defina la variable de ambiente "
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
        prompt.append("\nReglas:\n")
              .append("1. Devolve unicamente ids de categoria que aparezcan en la lista anterior.\n")
              .append("2. Si la frase no menciona un dato, devolve null en ese campo.\n")
              .append("3. La fecha va en formato ISO yyyy-MM-dd.\n")
              .append("4. Las horas van en formato ISO de 24 horas HH:mm.\n")
              .append("5. La actividad es una descripcion corta, sin fechas ni horas.\n\n")
              .append("Frase del usuario:\n")
              .append(frase);
        return prompt.toString();
    }

    private String invocar(String llave, String prompt) {
        try {
            HttpRequest peticion = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(ENDPOINT, modelo())))
                    .header("Content-Type", "application/json")
                    .header("x-goog-api-key", llave)
                    .timeout(Duration.ofSeconds(45))
                    .POST(HttpRequest.BodyPublishers.ofString(cuerpo(prompt), StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> respuesta =
                    cliente.send(peticion, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            if (respuesta.statusCode() != 200) {
                throw new ServicioException("Gemini respondio con el codigo "
                        + respuesta.statusCode() + " usando el modelo " + modelo() + ".\n\n"
                        + detalleDelError(respuesta.body())
                        + "\n\nPuede llenar el formulario a mano.");
            }
            return textoDeLaRespuesta(respuesta.body());
        } catch (ServicioException e) {
            throw e;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServicioException("La consulta a Gemini fue interrumpida.");
        } catch (Exception e) {
            throw new ServicioException("No se pudo consultar a Gemini: " + e.getMessage()
                    + ". Puede llenar el formulario a mano.");
        }
    }

    private String cuerpo(String prompt) {
        JsonObject parte = new JsonObject();
        parte.addProperty("text", prompt);

        JsonArray partes = new JsonArray();
        partes.add(parte);

        JsonObject contenido = new JsonObject();
        contenido.add("parts", partes);

        JsonArray contenidos = new JsonArray();
        contenidos.add(contenido);

        JsonObject configuracion = new JsonObject();
        configuracion.addProperty("responseMimeType", "application/json");
        configuracion.add("responseSchema", esquema());
        configuracion.addProperty("temperature", 0);

        JsonObject raiz = new JsonObject();
        raiz.add("contents", contenidos);
        raiz.add("generationConfig", configuracion);
        return gson.toJson(raiz);
    }

    private JsonObject esquema() {
        JsonObject elementos = new JsonObject();
        elementos.addProperty("type", "STRING");

        JsonObject lista = new JsonObject();
        lista.addProperty("type", "ARRAY");
        lista.add("items", elementos);

        JsonObject propiedades = new JsonObject();
        propiedades.add("actividad", cadenaNulable());
        propiedades.add("fecha", cadenaNulable());
        propiedades.add("horaInicio", cadenaNulable());
        propiedades.add("horaFin", cadenaNulable());
        propiedades.add("idsCategorias", lista);

        JsonObject esquema = new JsonObject();
        esquema.addProperty("type", "OBJECT");
        esquema.add("properties", propiedades);
        return esquema;
    }

    private JsonObject cadenaNulable() {
        JsonObject cadena = new JsonObject();
        cadena.addProperty("type", "STRING");
        cadena.addProperty("nullable", true);
        return cadena;
    }

    String modelo() {
        String configurado = System.getenv(VARIABLE_MODELO);
        return configurado == null || configurado.isBlank()
                ? MODELO_POR_DEFECTO : configurado.trim();
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
            return cuerpo.length() > 400 ? cuerpo.substring(0, 400) : cuerpo;
        }
        return cuerpo.length() > 400 ? cuerpo.substring(0, 400) : cuerpo;
    }

    private String textoDeLaRespuesta(String respuestaJson) {
        JsonObject raiz = JsonParser.parseString(respuestaJson).getAsJsonObject();
        JsonArray candidatos = raiz.getAsJsonArray("candidates");
        if (candidatos == null || candidatos.isEmpty()) {
            throw new ServicioException("Gemini no devolvio ningun resultado.");
        }
        return candidatos.get(0).getAsJsonObject()
                .getAsJsonObject("content")
                .getAsJsonArray("parts")
                .get(0).getAsJsonObject()
                .get("text").getAsString();
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
        return valor.isBlank() ? null : valor.trim();
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

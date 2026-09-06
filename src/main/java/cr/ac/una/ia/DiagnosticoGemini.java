package cr.ac.una.ia;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class DiagnosticoGemini {

    private static final String LISTADO =
            "https://generativelanguage.googleapis.com/v1beta/models";

    public static void main(String[] args) throws Exception {
        String llave = System.getenv(ExtractorReservaGemini.VARIABLE_LLAVE);
        if (llave == null || llave.isBlank()) {
            System.out.println("No hay llave. Defina la variable de ambiente "
                    + ExtractorReservaGemini.VARIABLE_LLAVE);
            return;
        }

        System.out.println("Llave detectada: " + enmascarar(llave));
        System.out.println("Consultando modelos disponibles para esta llave...");
        System.out.println();

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(LISTADO))
                .header("x-goog-api-key", llave)
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();

        HttpResponse<String> respuesta = HttpClient.newHttpClient()
                .send(peticion, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (respuesta.statusCode() != 200) {
            System.out.println("La API respondio con el codigo " + respuesta.statusCode());
            System.out.println(respuesta.body());
            System.out.println();
            System.out.println("Si aqui tambien sale 403, el bloqueo es de la cuenta o del");
            System.out.println("proyecto de Google, no del programa.");
            return;
        }

        JsonArray modelos = JsonParser.parseString(respuesta.body())
                .getAsJsonObject().getAsJsonArray("models");

        System.out.println("Modelos que aceptan generateContent:");
        int encontrados = 0;
        for (JsonElement elemento : modelos) {
            JsonObject modelo = elemento.getAsJsonObject();
            if (!soportaGenerateContent(modelo)) {
                continue;
            }
            String nombre = modelo.get("name").getAsString().replace("models/", "");
            System.out.println("  " + nombre);
            encontrados++;
        }

        System.out.println();
        if (encontrados == 0) {
            System.out.println("Ningun modelo disponible para esta llave.");
        } else {
            System.out.println("Escoja uno y pongalo en la variable de ambiente "
                    + ExtractorReservaGemini.VARIABLE_MODELO);
        }
    }

    private static boolean soportaGenerateContent(JsonObject modelo) {
        if (!modelo.has("supportedGenerationMethods")) {
            return false;
        }
        for (JsonElement metodo : modelo.getAsJsonArray("supportedGenerationMethods")) {
            if ("generateContent".equals(metodo.getAsString())) {
                return true;
            }
        }
        return false;
    }

    private static String enmascarar(String llave) {
        if (llave.length() <= 8) {
            return "****";
        }
        return llave.substring(0, 4) + "..." + llave.substring(llave.length() - 4);
    }
}

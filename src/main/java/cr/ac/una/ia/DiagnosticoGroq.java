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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiagnosticoGroq {

    private static final String LISTADO = "https://api.groq.com/openai/v1/models";

    public static void main(String[] args) throws Exception {
        String llave = System.getenv(ExtractorReservaGroq.VARIABLE_LLAVE);
        if (llave == null || llave.isBlank()) {
            System.out.println("No hay llave. Defina la variable de ambiente "
                    + ExtractorReservaGroq.VARIABLE_LLAVE);
            return;
        }

        System.out.println("Llave detectada: " + enmascarar(llave));
        System.out.println("Consultando modelos disponibles para esta llave...");
        System.out.println();

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(LISTADO))
                .header("Authorization", "Bearer " + llave)
                .timeout(Duration.ofSeconds(30))
                .GET()
                .build();

        HttpResponse<String> respuesta = HttpClient.newHttpClient()
                .send(peticion, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (respuesta.statusCode() != 200) {
            System.out.println("La API respondio con el codigo " + respuesta.statusCode());
            System.out.println(respuesta.body());
            System.out.println();
            if (respuesta.statusCode() == 401) {
                System.out.println("401 significa que la llave es invalida o fue revocada.");
            }
            if (respuesta.statusCode() == 429) {
                System.out.println("429 significa que se paso el limite de peticiones por minuto.");
            }
            return;
        }

        JsonArray modelos = JsonParser.parseString(respuesta.body())
                .getAsJsonObject().getAsJsonArray("data");

        List<String> nombres = new ArrayList<>();
        for (JsonElement elemento : modelos) {
            JsonObject modelo = elemento.getAsJsonObject();
            if (modelo.has("id")) {
                nombres.add(modelo.get("id").getAsString());
            }
        }
        Collections.sort(nombres);

        System.out.println("Modelos disponibles para esta llave:");
        for (String nombre : nombres) {
            System.out.println("  " + nombre);
        }

        System.out.println();
        String actual = new ExtractorReservaGroq().modelo();
        System.out.println("El programa usa " + actual);
        if (!nombres.contains(actual)) {
            System.out.println("ESE MODELO NO ESTA EN LA LISTA. Escoja uno de arriba y pongalo en "
                    + ExtractorReservaGroq.VARIABLE_MODELO);
        } else {
            System.out.println("El modelo esta disponible. Todo listo.");
        }
    }

    private static String enmascarar(String llave) {
        if (llave.length() <= 8) {
            return "****";
        }
        return llave.substring(0, 4) + "..." + llave.substring(llave.length() - 4);
    }
}

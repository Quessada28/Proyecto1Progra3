package cr.ac.una.util;

import cr.ac.una.logica.ServicioException;

public class Validador {

    private static final String SEPARADORES_TELEFONO = " -()+";

    private Validador() {
    }

    public static boolean vacio(String texto) {
        return texto == null || texto.isBlank();
    }

    public static void requerido(String texto, String nombreCampo) {
        if (vacio(texto)) {
            throw new ServicioException("El campo " + nombreCampo + " es obligatorio.");
        }
    }

    public static boolean esNumero(String texto) {
        if (vacio(texto)) {
            return false;
        }
        return texto.trim().chars().allMatch(Character::isDigit);
    }

    public static boolean esTelefono(String texto) {
        if (vacio(texto)) {
            return false;
        }
        StringBuilder limpio = new StringBuilder();
        for (char caracter : texto.toCharArray()) {
            if (Character.isDigit(caracter)) {
                limpio.append(caracter);
            } else if (!SEPARADORES_TELEFONO.contains(String.valueOf(caracter))) {
                return false;
            }
        }
        return limpio.length() >= 4 && limpio.length() <= 15;
    }

    public static void telefonoValido(String texto, String nombreCampo) {
        requerido(texto, nombreCampo);
        if (!esTelefono(texto)) {
            throw new ServicioException("El campo " + nombreCampo + " debe ser un numero de telefono valido.");
        }
    }
}

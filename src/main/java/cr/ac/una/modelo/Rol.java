package cr.ac.una.modelo;

public enum Rol {

    ADMIN,
    FUNCIONARIO;

    public static Rol desdeTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            return FUNCIONARIO;
        }
        try {
            return Rol.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException ignorada) {
            return FUNCIONARIO;
        }
    }
}

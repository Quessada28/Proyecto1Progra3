package cr.ac.una.modelo;

import java.util.ArrayList;
import java.util.List;

public class ResultadoReserva {

    private boolean exito;
    private Reserva reserva;
    private List<String> categoriasNoDisponibles = new ArrayList<>();

    public ResultadoReserva() {
    }

    public static ResultadoReserva ok(Reserva reserva) {
        ResultadoReserva resultado = new ResultadoReserva();
        resultado.exito = true;
        resultado.reserva = reserva;
        return resultado;
    }

    public static ResultadoReserva fallo(List<String> categoriasNoDisponibles) {
        ResultadoReserva resultado = new ResultadoReserva();
        resultado.exito = false;
        resultado.categoriasNoDisponibles =
                categoriasNoDisponibles == null ? new ArrayList<>() : new ArrayList<>(categoriasNoDisponibles);
        return resultado;
    }

    public boolean isExito() { return exito; }
    public Reserva getReserva() { return reserva; }
    public List<String> getCategoriasNoDisponibles() { return categoriasNoDisponibles; }

    public void setExito(boolean exito) { this.exito = exito; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public void setCategoriasNoDisponibles(List<String> categorias) {
        this.categoriasNoDisponibles = categorias == null ? new ArrayList<>() : new ArrayList<>(categorias);
    }

    public String mensajeDeFallo() {
        return "No hay disponibilidad para: " + String.join(", ", categoriasNoDisponibles);
    }
}

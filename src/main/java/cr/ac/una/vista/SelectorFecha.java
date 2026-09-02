package cr.ac.una.vista;

import javax.swing.*;
import java.time.LocalDate;

/**
 * Componente reutilizable: el campo de fecha con el boton "..." que aparece
 * en varias pantallas del enunciado (Reservas, Calendarizacion, Actividades,
 * Estadisticas).
 *
 * Vale la pena hacerlo una sola vez aqui en vez de repetir el mismo par
 * campo+boton en cinco vistas distintas.
 *
 * Es un JPanel con:
 *   - un JTextField que muestra la fecha en formato largo (util.Formatos.fechaLarga)
 *   - un JButton "..." que abre un JSpinner con SpinnerDateModel dentro de un
 *     JOptionPane (Swing no trae calendario propio; con eso basta)
 */
public class SelectorFecha extends JPanel {

    private JTextField txtFecha;
    private JButton btnCalendario;
    private LocalDate valor;

    public SelectorFecha() {
        // TODO: BorderLayout, txtFecha al centro, btnCalendario al este,
        //       y el listener del boton llama a abrirCalendario()
    }

    /** Abre el dialogo de seleccion y actualiza el campo si el usuario acepta. */
    private void abrirCalendario() {
        // TODO
    }

    /** Fecha actual del campo, o null si esta vacio o mal escrito. */
    public LocalDate getFecha() {
        // TODO: si el usuario escribio a mano, releer con Formatos.leerFechaLarga(...)
        return null;
    }

    public void setFecha(LocalDate fecha) {
        // TODO: guardar en valor y mostrar Formatos.fechaLarga(fecha) en el campo
    }

    public void limpiar() {
        // TODO
    }
}

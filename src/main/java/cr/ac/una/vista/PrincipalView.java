package cr.ac.una.vista;

import javax.swing.*;

/**
 * VISTA principal: la ventana con el JTabbedPane que se ve en todas las
 * capturas del enunciado.
 *
 * QUE PESTANAS SE MUESTRAN DEPENDE DEL ROL (esto lo decide el controlador):
 *   ADMIN       -> Funcionarios, Categorias, Recursos, Calendarizacion, Actividades, Estadisticas
 *   FUNCIONARIO -> Reservas, Calendarizacion, Actividades, Estadisticas
 *
 * El titulo tambien cambia: "SISTEMA DE RESERVAS - admin (ADMIN)".
 *
 * Esta clase NO crea las pestanas por su cuenta: recibe los JPanel ya
 * construidos con agregarPestana(...). Asi la ventana no depende de las
 * ocho vistas concretas y se puede probar por partes.
 */
public class PrincipalView extends JFrame {

    private JTabbedPane pestanas;
    private JMenuItem itemCambiarClave;
    private JMenuItem itemSalir;

    public PrincipalView(String titulo) {
        // TODO: setTitle(titulo), setSize(...), setLocationRelativeTo(null),
        //       setDefaultCloseOperation(EXIT_ON_CLOSE), inicializarComponentes()
    }

    private void inicializarComponentes() {
        // TODO: crear el JTabbedPane y agregarlo al centro.
        //       Opcional: una barra de menu con "Cambiar clave" y "Salir".
    }

    /** Agrega una pestana con su titulo (y su icono si se quiere). */
    public void agregarPestana(String titulo, JPanel panel) {
        // TODO: pestanas.addTab(titulo, panel)
    }

    public JTabbedPane getPestanas() { return pestanas; }
    public JMenuItem getItemCambiarClave() { return itemCambiarClave; }
    public JMenuItem getItemSalir() { return itemSalir; }
}

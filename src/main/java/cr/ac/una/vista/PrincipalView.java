package cr.ac.una.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class PrincipalView extends JFrame {

    private final JTabbedPane pestanas = new JTabbedPane();
    private final JMenuItem itemCambiarClave = new JMenuItem("Cambiar clave");
    private final JMenuItem itemCerrarSesion = new JMenuItem("Cerrar sesion");
    private final JMenuItem itemSalir = new JMenuItem("Salir");

    public PrincipalView(String titulo) {
        setTitle(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        inicializarComponentes();
        setPreferredSize(new Dimension(1000, 650));
        pack();
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        JMenu menu = new JMenu("Cuenta");
        menu.add(itemCambiarClave);
        menu.addSeparator();
        menu.add(itemCerrarSesion);
        menu.add(itemSalir);

        JMenuBar barra = new JMenuBar();
        barra.add(menu);
        setJMenuBar(barra);

        add(pestanas, BorderLayout.CENTER);
    }

    public void agregarPestana(String titulo, JPanel panel) {
        pestanas.addTab(titulo, panel);
    }

    public JTabbedPane getPestanas() {
        return pestanas;
    }

    public JMenuItem getItemCambiarClave() {
        return itemCambiarClave;
    }

    public JMenuItem getItemCerrarSesion() {
        return itemCerrarSesion;
    }

    public JMenuItem getItemSalir() {
        return itemSalir;
    }
}

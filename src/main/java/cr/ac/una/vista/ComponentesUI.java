package cr.ac.una.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

public final class ComponentesUI {

    public static final Insets MARGEN = new Insets(4, 6, 4, 6);

    private ComponentesUI() {
    }

    public static JPanel panelConTitulo(String titulo) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        return panel;
    }

    public static GridBagConstraints restricciones(int columna, int fila) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = columna;
        c.gridy = fila;
        c.insets = MARGEN;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        return c;
    }

    public static void agregar(JPanel panel, Component componente, int columna, int fila) {
        panel.add(componente, restricciones(columna, fila));
    }

    public static void agregar(JPanel panel, Component componente, int columna, int fila, int ancho) {
        GridBagConstraints c = restricciones(columna, fila);
        c.gridwidth = ancho;
        panel.add(componente, c);
    }

    public static JLabel etiqueta(String texto) {
        return new JLabel(texto);
    }

    public static JTextField campo(int columnas) {
        JTextField campo = new JTextField(columnas);
        campo.setPreferredSize(new Dimension(campo.getPreferredSize().width, 26));
        return campo;
    }

    public static JButton boton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        return boton;
    }

    public static JTable tablaDeSoloLectura(DefaultTableModel modelo) {
        JTable tabla = new JTable(modelo) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(24);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setFont(tabla.getFont().deriveFont(Font.BOLD));
        return tabla;
    }

    public static DefaultTableModel modeloDeSoloLectura(String[] columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
    }

    public static JScrollPane conBarras(Component componente, int alto) {
        JScrollPane scroll = new JScrollPane(componente);
        scroll.setPreferredSize(new Dimension(scroll.getPreferredSize().width, alto));
        return scroll;
    }

    public static void llenarTabla(DefaultTableModel modelo, List<String[]> filas) {
        modelo.setRowCount(0);
        for (String[] fila : filas) {
            modelo.addRow(fila);
        }
    }

    public static void error(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void info(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirmar(Component padre, String mensaje) {
        return JOptionPane.showConfirmDialog(padre, mensaje, "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }
}

package cr.ac.una.controlador;

import cr.ac.una.ia.ExtractorReservaGroq;
import cr.ac.una.logica.CalendarioService;
import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.EstadisticaService;
import cr.ac.una.logica.FuncionarioService;
import cr.ac.una.logica.RecursoService;
import cr.ac.una.logica.ReservaService;
import cr.ac.una.logica.UsuarioService;
import cr.ac.una.util.Sesion;
import cr.ac.una.vista.ActividadesView;
import cr.ac.una.vista.CalendarizacionView;
import cr.ac.una.vista.CambiarClaveView;
import cr.ac.una.vista.CategoriasView;
import cr.ac.una.vista.EstadisticasView;
import cr.ac.una.vista.FuncionariosView;
import cr.ac.una.vista.LoginView;
import cr.ac.una.vista.PrincipalView;
import cr.ac.una.vista.RecursosView;
import cr.ac.una.vista.ReservasView;

import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public class PrincipalControlador {

    private final PrincipalView vista;
    private final UsuarioService usuarioService = new UsuarioService();
    private final List<ControladorPestana> controladores = new ArrayList<>();

    public PrincipalControlador() {
        this.vista = new PrincipalView(titulo());
        this.vista.getItemCambiarClave().addActionListener(e -> cambiarClave());
        this.vista.getItemCerrarSesion().addActionListener(e -> cerrarSesion());
        this.vista.getItemSalir().addActionListener(e -> System.exit(0));
    }

    private String titulo() {
        return "SISTEMA DE RESERVAS - " + Sesion.idUsuarioActual()
                + " (" + Sesion.getUsuarioActual().getRol().name() + ")";
    }

    public void mostrar() {
        if (Sesion.esAdministrador()) {
            agregarPestanasDeAdministrador();
        } else {
            agregarPestanaDeReservas();
        }
        agregarPestanasComunes();
        vista.getPestanas().addChangeListener(e -> refrescarPestanaActual());
        vista.setVisible(true);
    }

    private void agregarPestana(String titulo, JPanel panel, ControladorPestana controlador) {
        controlador.inicializar();
        controladores.add(controlador);
        vista.agregarPestana(titulo, panel);
    }

    private void refrescarPestanaActual() {
        int indice = vista.getPestanas().getSelectedIndex();
        if (indice >= 0 && indice < controladores.size()) {
            controladores.get(indice).refrescar();
        }
    }

    private void agregarPestanasDeAdministrador() {
        FuncionariosView funcionarios = new FuncionariosView();
        agregarPestana("Funcionarios", funcionarios,
                new FuncionariosControlador(funcionarios, new FuncionarioService()));

        CategoriasView categorias = new CategoriasView();
        agregarPestana("Categorias", categorias,
                new CategoriasControlador(categorias, new CategoriaService()));

        RecursosView recursos = new RecursosView();
        agregarPestana("Recursos", recursos,
                new RecursosControlador(recursos, new RecursoService(), new CategoriaService()));
    }

    private void agregarPestanaDeReservas() {
        ReservasView reservas = new ReservasView();
        agregarPestana("Reservas", reservas,
                new ReservasControlador(reservas, new ReservaService(), new CategoriaService(),
                        new RecursoService(), new ExtractorReservaGroq()));
    }

    private void agregarPestanasComunes() {
        CalendarizacionView calendarizacion = new CalendarizacionView();
        agregarPestana("Calendarizacion", calendarizacion,
                new CalendarizacionControlador(calendarizacion, new CalendarioService(),
                        new CategoriaService()));

        ActividadesView actividades = new ActividadesView();
        agregarPestana("Actividades", actividades,
                new ActividadesControlador(actividades, new CalendarioService()));

        EstadisticasView estadisticas = new EstadisticasView();
        agregarPestana("Estadisticas", estadisticas,
                new EstadisticasControlador(estadisticas, new EstadisticaService()));
    }

    private void cambiarClave() {
        CambiarClaveView dialogo = new CambiarClaveView(vista);
        new CambiarClaveControlador(dialogo, usuarioService, Sesion.idUsuarioActual()).mostrar();
    }

    private void cerrarSesion() {
        Sesion.cerrar();
        vista.dispose();
        LoginView login = new LoginView();
        new LoginControlador(login, usuarioService).mostrar();
    }
}

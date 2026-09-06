package cr.ac.una.controlador;

import cr.ac.una.logica.FuncionarioService;
import cr.ac.una.logica.ServicioException;
import cr.ac.una.modelo.Funcionario;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.FuncionariosView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FuncionariosControlador implements ControladorPestana {

    private final FuncionariosView vista;
    private final FuncionarioService servicio;
    private final ReportePdf reportePdf = new ReportePdf();

    public FuncionariosControlador(FuncionariosView vista, FuncionarioService servicio) {
        this.vista = vista;
        this.servicio = servicio;

        this.vista.getBtnBuscar().addActionListener(e -> buscar());
        this.vista.getBtnGuardar().addActionListener(e -> guardar());
        this.vista.getBtnBorrar().addActionListener(e -> borrar());
        this.vista.getBtnLimpiar().addActionListener(e -> vista.limpiarFormulario());
        this.vista.getBtnImprimir().addActionListener(e -> imprimir());
        this.vista.getTabla().getSelectionModel().addListSelectionListener(evento -> {
            if (!evento.getValueIsAdjusting()) {
                seleccionarDeTabla();
            }
        });
    }

    @Override
    public void inicializar() {
        refrescar();
    }

    @Override
    public void refrescar() {
        cargarTabla(servicio.listar());
    }

    private void buscar() {
        cargarTabla(servicio.buscar(vista.getBuscarId(), vista.getBuscarNombre()));
    }

    private void guardar() {
        try {
            servicio.guardar(new Funcionario(
                    vista.getIdFormulario(), vista.getNombre(), vista.getTelefono()));
            vista.mostrarInfo("El funcionario se guardo correctamente.");
            vista.limpiarFormulario();
            inicializar();
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void borrar() {
        String id = vista.getIdFormulario();
        if (id.isEmpty()) {
            vista.mostrarError("Seleccione un funcionario del listado.");
            return;
        }
        if (!vista.confirmar("Desea borrar el funcionario " + id + "?")) {
            return;
        }
        try {
            servicio.eliminar(id);
            vista.mostrarInfo("El funcionario se borro correctamente.");
            vista.limpiarFormulario();
            inicializar();
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void imprimir() {
        File destino = reportePdf.pedirDestino(vista, "funcionarios.pdf");
        if (destino == null) {
            return;
        }
        try {
            reportePdf.deJTable("Listado de Funcionarios", vista.getTabla(), destino);
            reportePdf.abrir(destino);
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void seleccionarDeTabla() {
        String id = vista.getIdSeleccionado();
        if (id == null) {
            return;
        }
        servicio.buscarPorId(id).ifPresent(funcionario -> vista.mostrarEnFormulario(
                funcionario.getId(), funcionario.getNombre(), funcionario.getTelefono()));
    }

    private void cargarTabla(List<Funcionario> funcionarios) {
        List<String[]> filas = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            filas.add(new String[]{
                    funcionario.getId(), funcionario.getNombre(), funcionario.getTelefono()});
        }
        vista.cargarTabla(filas);
    }
}

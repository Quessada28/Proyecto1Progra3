package cr.ac.una.controlador;

import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.RecursoService;
import cr.ac.una.logica.ServicioException;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.RecursosView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecursosControlador implements ControladorPestana {

    private final RecursosView vista;
    private final RecursoService servicio;
    private final CategoriaService categoriaService;
    private final ReportePdf reportePdf = new ReportePdf();

    public RecursosControlador(RecursosView vista, RecursoService servicio,
                               CategoriaService categoriaService) {
        this.vista = vista;
        this.servicio = servicio;
        this.categoriaService = categoriaService;

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
        vista.cargarCategorias(categoriaService.listar());
        cargarTabla(servicio.listar());
    }

    private void buscar() {
        cargarTabla(servicio.buscar(vista.getIdCategoriaFiltro(), vista.getFiltroDescripcion()));
    }

    private void guardar() {
        try {
            servicio.guardar(new Recurso(
                    vista.getIdFormulario(), vista.getIdCategoria(), vista.getDescripcion()));
            vista.mostrarInfo("El recurso se guardo correctamente.");
            vista.limpiarFormulario();
            cargarTabla(servicio.listar());
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void borrar() {
        String id = vista.getIdFormulario();
        if (id.isEmpty()) {
            vista.mostrarError("Seleccione un recurso del listado.");
            return;
        }
        if (!vista.confirmar("Desea borrar el recurso " + id + "?")) {
            return;
        }
        try {
            servicio.eliminar(id);
            vista.mostrarInfo("El recurso se borro correctamente.");
            vista.limpiarFormulario();
            cargarTabla(servicio.listar());
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void imprimir() {
        File destino = reportePdf.pedirDestino(vista, "recursos.pdf");
        if (destino == null) {
            return;
        }
        try {
            reportePdf.deJTable("Listado de Recursos", vista.getTabla(), destino);
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
        servicio.buscarPorId(id).ifPresent(recurso -> vista.mostrarEnFormulario(
                recurso.getId(), recurso.getIdCategoria(), recurso.getDescripcion()));
    }

    private void cargarTabla(List<Recurso> recursos) {
        List<String[]> filas = new ArrayList<>();
        for (Recurso recurso : recursos) {
            filas.add(new String[]{
                    recurso.getId(),
                    categoriaService.descripcionDe(recurso.getIdCategoria()),
                    recurso.getDescripcion()});
        }
        vista.cargarTabla(filas);
    }
}

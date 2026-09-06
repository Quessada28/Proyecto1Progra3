package cr.ac.una.controlador;

import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.ServicioException;
import cr.ac.una.modelo.Categoria;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.CategoriasView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CategoriasControlador implements ControladorPestana {

    private final CategoriasView vista;
    private final CategoriaService servicio;
    private final ReportePdf reportePdf = new ReportePdf();

    public CategoriasControlador(CategoriasView vista, CategoriaService servicio) {
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
        cargarTabla(servicio.buscarPorDescripcion(vista.getBuscarDescripcion()));
    }

    private void guardar() {
        try {
            String id = vista.getIdFormulario();
            servicio.guardar(new Categoria(id.isEmpty() ? null : id, vista.getDescripcion()));
            vista.mostrarInfo("La categoria se guardo correctamente.");
            vista.limpiarFormulario();
            inicializar();
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void borrar() {
        String id = vista.getIdFormulario();
        if (id.isEmpty()) {
            vista.mostrarError("Seleccione una categoria del listado.");
            return;
        }
        if (!vista.confirmar("Desea borrar la categoria " + id + "?")) {
            return;
        }
        try {
            servicio.eliminar(id);
            vista.mostrarInfo("La categoria se borro correctamente.");
            vista.limpiarFormulario();
            inicializar();
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void imprimir() {
        File destino = reportePdf.pedirDestino(vista, "categorias.pdf");
        if (destino == null) {
            return;
        }
        try {
            reportePdf.deJTable("Listado de Categorias", vista.getTabla(), destino);
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
        servicio.buscarPorId(id).ifPresent(
                categoria -> vista.mostrarEnFormulario(categoria.getId(), categoria.getDescripcion()));
    }

    private void cargarTabla(List<Categoria> categorias) {
        List<String[]> filas = new ArrayList<>();
        for (Categoria categoria : categorias) {
            filas.add(new String[]{categoria.getId(), categoria.getDescripcion()});
        }
        vista.cargarTabla(filas);
    }
}

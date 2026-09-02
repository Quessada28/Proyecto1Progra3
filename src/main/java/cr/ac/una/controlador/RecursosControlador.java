package cr.ac.una.controlador;

import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.RecursoService;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.RecursosView;

import java.util.List;

/**
 * CONTROLADOR de la pestana Recursos (funcionalidad 5). Solo ADMIN.
 *
 * Necesita DOS servicios: RecursoService para el CRUD y CategoriaService
 * para llenar los dos combos de categoria.
 *
 * Cuidado al borrar un recurso que ya tiene reservas ACTIVAS: hay que
 * decidir si se impide o si solo se avisa, y dejarlo claro en el mensaje.
 */
public class RecursosControlador {

    private final RecursosView vista;
    private final RecursoService servicio;
    private final CategoriaService categoriaService;
    private final ReportePdf reportePdf;

    public RecursosControlador(RecursosView vista, RecursoService servicio,
                               CategoriaService categoriaService) {
        this.vista = vista;
        this.servicio = servicio;
        this.categoriaService = categoriaService;
        this.reportePdf = new ReportePdf();
        // TODO: listeners
    }

    /** Llena los combos de categoria y carga la tabla completa. */
    public void inicializar() {
        // TODO
    }

    private void buscar() {
        // TODO: filtrar por la categoria del combo y por descripcion
    }

    private void guardar() {
        // TODO
    }

    private void borrar() {
        // TODO
    }

    private void limpiar() {
        // TODO
    }

    private void imprimir() {
        // TODO
    }

    private void seleccionarDeTabla() {
        // TODO
    }

    private void cargarTabla(List<Recurso> recursos) {
        // TODO: en la columna Categoria va la DESCRIPCION, no el id
    }
}

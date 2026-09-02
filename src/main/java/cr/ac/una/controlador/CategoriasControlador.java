package cr.ac.una.controlador;

import cr.ac.una.logica.CategoriaService;
import cr.ac.una.modelo.Categoria;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.CategoriasView;

import java.util.List;

/**
 * CONTROLADOR de la pestana Categorias (funcionalidad 4). Solo ADMIN.
 * Mismo molde que FuncionariosControlador.
 *
 * Diferencia: el id es autogenerado. Cuando el campo id de la vista viene
 * vacio es un ALTA (el servicio le pide el id a GeneradorId); cuando trae
 * valor es una MODIFICACION.
 *
 * Antes de borrar conviene revisar si la categoria tiene recursos asociados,
 * porque si no quedan recursos huerfanos apuntando a una categoria que ya
 * no existe.
 */
public class CategoriasControlador {

    private final CategoriasView vista;
    private final CategoriaService servicio;
    private final ReportePdf reportePdf;

    public CategoriasControlador(CategoriasView vista, CategoriaService servicio) {
        this.vista = vista;
        this.servicio = servicio;
        this.reportePdf = new ReportePdf();
        // TODO: listeners
    }

    public void inicializar() {
        // TODO
    }

    private void buscar() {
        // TODO
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
        // TODO: reportePdf.deJTable("Listado de Categorias", vista.getTabla(), destino)
    }

    private void seleccionarDeTabla() {
        // TODO
    }

    private void cargarTabla(List<Categoria> categorias) {
        // TODO
    }
}

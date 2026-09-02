package cr.ac.una.controlador;

import cr.ac.una.logica.FuncionarioService;
import cr.ac.una.modelo.Funcionario;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.FuncionariosView;

import java.util.List;

/**
 * CONTROLADOR de la pestana Funcionarios (funcionalidad 3). Solo ADMIN.
 *
 * Este es el molde de los tres mantenimientos (Funcionarios, Categorias y
 * Recursos): buscar / guardar / borrar / limpiar / imprimir, mas el evento
 * de seleccion de la tabla que baja la fila al formulario.
 *
 * OJO con el detalle del enunciado: al agregar un funcionario NUEVO hay que
 * crearle tambien su Usuario con clave = id y rol FUNCIONARIO. Eso lo
 * resuelve FuncionarioService.guardar(...) por dentro; el controlador solo
 * lo llama.
 */
public class FuncionariosControlador {

    private final FuncionariosView vista;
    private final FuncionarioService servicio;
    private final ReportePdf reportePdf;

    public FuncionariosControlador(FuncionariosView vista, FuncionarioService servicio) {
        this.vista = vista;
        this.servicio = servicio;
        this.reportePdf = new ReportePdf();
        // TODO: listeners de los botones y listener de seleccion de la tabla
        //       vista.getTabla().getSelectionModel().addListSelectionListener(...)
    }

    /** Carga inicial de la tabla con todos los funcionarios. */
    public void inicializar() {
        // TODO
    }

    private void buscar() {
        // TODO
    }

    private void guardar() {
        // TODO: validar, servicio.guardar(...), avisar, limpiar y recargar
    }

    private void borrar() {
        // TODO: pedir confirmacion antes
    }

    private void limpiar() {
        // TODO
    }

    private void imprimir() {
        // TODO
    }

    /** Pasa la fila marcada de la tabla al formulario. */
    private void seleccionarDeTabla() {
        // TODO
    }

    private void cargarTabla(List<Funcionario> funcionarios) {
        // TODO
    }
}

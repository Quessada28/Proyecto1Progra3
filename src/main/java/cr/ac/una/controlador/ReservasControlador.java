package cr.ac.una.controlador;

import cr.ac.una.ia.ExtractorReserva;
import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.ReservaService;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.ReservasView;

/**
 * CONTROLADOR de la pestana Reservas (funcionalidad 2, 25% de la nota).
 * Es el controlador mas cargado del proyecto; conviene hacerlo de ultimo,
 * cuando ReservaService ya este probado con JUnit.
 *
 * ACCIONES:
 *
 *  reservar()
 *     - arma un DatosReserva con lo que hay en pantalla (convirtiendo el
 *       texto de las horas a LocalTime con util.Formatos)
 *     - ResultadoReserva r = reservaService.crear(idFuncionario, datos)
 *     - si r.isExito()  -> mensaje de exito, limpiar formulario, recargar tabla
 *     - si no           -> mostrar LAS CATEGORIAS NO DISPONIBLES y DEJAR EL
 *                          FORMULARIO TAL CUAL, porque el enunciado dice que
 *                          el funcionario puede corregir e intentar de nuevo
 *     - atrapar ServicioException (datos invalidos) y mostrar el mensaje
 *
 *  cancelarReserva()
 *     - toma el id de la fila marcada, pide confirmacion,
 *       reservaService.cancelar(id, idFuncionario) y recarga la tabla
 *
 *  extraerConIA()
 *     - DatosReserva d = extractor.extraer(vista.getFrase(), categoriaService.listar())
 *     - vista.llenarFormulario(...) con lo que vino
 *     - la llamada al LLM tarda: conviene hacerla en un SwingWorker para que
 *       no se congele la ventana, y deshabilitar el boton mientras corre
 *     - si falla la red o no hay llave, mostrar el error y ya: el usuario
 *       siempre puede llenar el formulario a mano
 *
 *  imprimir()
 *     - reportePdf.deJTable("Mis Reservas", vista.getTablaReservas(), destino)
 */
public class ReservasControlador {

    private final ReservasView vista;
    private final ReservaService reservaService;
    private final CategoriaService categoriaService;
    private final ExtractorReserva extractor;
    private final ReportePdf reportePdf;

    public ReservasControlador(ReservasView vista, ReservaService reservaService,
                               CategoriaService categoriaService, ExtractorReserva extractor) {
        this.vista = vista;
        this.reservaService = reservaService;
        this.categoriaService = categoriaService;
        this.extractor = extractor;
        this.reportePdf = new ReportePdf();
        // TODO: registrar los listeners de los cinco botones
    }

    /** Carga inicial: categorias en el JList y las reservas del funcionario en la tabla. */
    public void inicializar() {
        // TODO
    }

    private void reservar() {
        // TODO
    }

    private void cancelarReserva() {
        // TODO
    }

    private void extraerConIA() {
        // TODO
    }

    private void limpiar() {
        // TODO
    }

    private void imprimir() {
        // TODO
    }

    /** Relee las reservas del funcionario logueado y las pasa a la tabla. */
    private void cargarTabla() {
        // TODO: convertir cada Reserva a String[] {id, actividad, fecha, horario, recursos, estado}
    }
}

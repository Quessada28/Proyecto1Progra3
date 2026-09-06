package cr.ac.una.controlador;

import cr.ac.una.ia.ExtractorReserva;
import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.RecursoService;
import cr.ac.una.logica.ReservaService;
import cr.ac.una.logica.ServicioException;
import cr.ac.una.modelo.DatosReserva;
import cr.ac.una.modelo.Recurso;
import cr.ac.una.modelo.Reserva;
import cr.ac.una.modelo.ResultadoReserva;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.util.Formatos;
import cr.ac.una.util.Sesion;
import cr.ac.una.vista.ReservasView;

import javax.swing.SwingWorker;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ReservasControlador implements ControladorPestana {

    private final ReservasView vista;
    private final ReservaService reservaService;
    private final CategoriaService categoriaService;
    private final RecursoService recursoService;
    private final ExtractorReserva extractor;
    private final ReportePdf reportePdf = new ReportePdf();

    public ReservasControlador(ReservasView vista, ReservaService reservaService,
                               CategoriaService categoriaService, RecursoService recursoService,
                               ExtractorReserva extractor) {
        this.vista = vista;
        this.reservaService = reservaService;
        this.categoriaService = categoriaService;
        this.recursoService = recursoService;
        this.extractor = extractor;

        this.vista.getBtnReservar().addActionListener(e -> reservar());
        this.vista.getBtnCancelarReserva().addActionListener(e -> cancelarReserva());
        this.vista.getBtnLimpiar().addActionListener(e -> vista.limpiarFormulario());
        this.vista.getBtnExtraer().addActionListener(e -> extraerConIA());
        this.vista.getBtnImprimir().addActionListener(e -> imprimir());
    }

    @Override
    public void inicializar() {
        refrescar();
    }

    @Override
    public void refrescar() {
        vista.cargarCategorias(categoriaService.listar());
        cargarTabla();
    }

    private void reservar() {
        try {
            DatosReserva datos = new DatosReserva(
                    vista.getActividad(),
                    vista.getFecha(),
                    vista.getHoraInicio(),
                    vista.getHoraFin(),
                    vista.getCategoriasSeleccionadas());

            ResultadoReserva resultado = reservaService.crear(Sesion.idUsuarioActual(), datos);
            if (resultado.isExito()) {
                vista.mostrarInfo("Reserva " + resultado.getReserva().getId()
                        + " registrada correctamente.");
                vista.limpiarFormulario();
                cargarTabla();
            } else {
                vista.mostrarError(resultado.mensajeDeFallo()
                        + "\n\nPuede modificar la reserva e intentar de nuevo.");
            }
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void cancelarReserva() {
        String idReserva = vista.getIdReservaSeleccionada();
        if (idReserva == null) {
            vista.mostrarError("Seleccione una reserva de la tabla.");
            return;
        }
        if (!vista.confirmar("Desea cancelar la reserva " + idReserva + "?")) {
            return;
        }
        try {
            reservaService.cancelar(idReserva, Sesion.idUsuarioActual());
            vista.mostrarInfo("La reserva se cancelo y sus recursos quedaron liberados.");
            cargarTabla();
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void extraerConIA() {
        String frase = vista.getFrase();
        if (frase.isEmpty()) {
            vista.mostrarError("Escriba una frase que describa la reserva.");
            return;
        }
        vista.setExtraccionEnProceso(true);

        new SwingWorker<DatosReserva, Void>() {
            @Override
            protected DatosReserva doInBackground() {
                return extractor.extraer(frase, categoriaService.listar());
            }

            @Override
            protected void done() {
                vista.setExtraccionEnProceso(false);
                try {
                    DatosReserva datos = get();
                    vista.llenarFormulario(datos.getActividad(), datos.getFecha(),
                            datos.getHoraInicio(), datos.getHoraFin(), datos.getIdsCategorias());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                    vista.mostrarError(mensajeDeError(e));
                }
            }
        }.execute();
    }

    private String mensajeDeError(ExecutionException e) {
        Throwable causa = e.getCause();
        return causa == null ? e.getMessage() : causa.getMessage();
    }

    private void imprimir() {
        File destino = reportePdf.pedirDestino(vista, "mis_reservas.pdf");
        if (destino == null) {
            return;
        }
        try {
            reportePdf.deJTable("Mis Reservas", vista.getTablaReservas(), destino);
            reportePdf.abrir(destino);
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void cargarTabla() {
        List<String[]> filas = new ArrayList<>();
        for (Reserva reserva : reservaService.misReservas(Sesion.idUsuarioActual())) {
            filas.add(new String[]{
                    reserva.getId(),
                    reserva.getActividad(),
                    reserva.getFecha().toString(),
                    Formatos.rangoHorario(reserva.getHoraInicio(), reserva.getHoraFin()),
                    descripcionRecursos(reserva),
                    reserva.getEstado().name()
            });
        }
        vista.cargarReservas(filas);
    }

    private String descripcionRecursos(Reserva reserva) {
        return reserva.getIdsRecursos().stream()
                .map(id -> recursoService.buscarPorId(id).map(Recurso::getId).orElse(id))
                .collect(Collectors.joining(", "));
    }
}

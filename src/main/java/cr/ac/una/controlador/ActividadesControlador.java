package cr.ac.una.controlador;

import cr.ac.una.logica.CalendarioService;
import cr.ac.una.logica.ServicioException;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.vista.ActividadesView;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActividadesControlador implements ControladorPestana {

    private final ActividadesView vista;
    private final CalendarioService servicio;
    private final ReportePdf reportePdf = new ReportePdf();

    private String[] ultimasColumnas = new String[0];
    private String[][] ultimosDatos = new String[0][0];

    public ActividadesControlador(ActividadesView vista, CalendarioService servicio) {
        this.vista = vista;
        this.servicio = servicio;

        this.vista.getBtnCargar().addActionListener(e -> cargar());
        this.vista.getBtnImprimir().addActionListener(e -> imprimir());
    }

    @Override
    public void inicializar() {
        cargar();
    }

    @Override
    public void refrescar() {
    }

    private void cargar() {
        LocalDate referencia = vista.getFechaReferencia();
        if (referencia == null) {
            vista.mostrarError("Seleccione una fecha de referencia valida.");
            return;
        }
        ultimasColumnas = servicio.columnasDeActividades(referencia);
        ultimosDatos = servicio.calendarioDeActividades(referencia);
        vista.cargarMatriz(ultimasColumnas, ultimosDatos);
    }

    private void imprimir() {
        if (ultimasColumnas.length == 0) {
            vista.mostrarError("Primero cargue la semana.");
            return;
        }
        File destino = reportePdf.pedirDestino(vista, "actividades.pdf");
        if (destino == null) {
            return;
        }
        try {
            String titulo = "Actividades de la semana del "
                    + servicio.lunesDeLaSemana(vista.getFechaReferencia());
            List<String[]> filas = new ArrayList<>(List.of(ultimosDatos));
            reportePdf.tabla(titulo, ultimasColumnas, filas, destino);
            reportePdf.abrir(destino);
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}

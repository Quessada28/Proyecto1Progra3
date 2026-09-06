package cr.ac.una.controlador;

import cr.ac.una.logica.CalendarioService;
import cr.ac.una.logica.CategoriaService;
import cr.ac.una.logica.ServicioException;
import cr.ac.una.reporte.ReportePdf;
import cr.ac.una.util.Formatos;
import cr.ac.una.vista.CalendarizacionView;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarizacionControlador implements ControladorPestana {

    private final CalendarizacionView vista;
    private final CalendarioService servicio;
    private final CategoriaService categoriaService;
    private final ReportePdf reportePdf = new ReportePdf();

    private String[] ultimasColumnas = new String[0];
    private String[][] ultimosDatos = new String[0][0];

    public CalendarizacionControlador(CalendarizacionView vista, CalendarioService servicio,
                                      CategoriaService categoriaService) {
        this.vista = vista;
        this.servicio = servicio;
        this.categoriaService = categoriaService;

        this.vista.getBtnCargar().addActionListener(e -> cargar());
        this.vista.getBtnImprimir().addActionListener(e -> imprimir());
    }

    @Override
    public void inicializar() {
        refrescar();
    }

    @Override
    public void refrescar() {
        vista.cargarCategorias(categoriaService.listar());
    }

    private void cargar() {
        LocalDate fecha = vista.getFecha();
        String idCategoria = vista.getIdCategoria();

        if (fecha == null) {
            vista.mostrarError("Seleccione una fecha valida.");
            return;
        }
        if (idCategoria == null) {
            vista.mostrarError("Seleccione una categoria de recurso.");
            return;
        }
        ultimasColumnas = servicio.columnasDeRecursos(idCategoria);
        ultimosDatos = servicio.calendarioDeRecursos(fecha, idCategoria);
        vista.cargarMatriz(ultimasColumnas, ultimosDatos);
    }

    private void imprimir() {
        if (ultimasColumnas.length == 0) {
            vista.mostrarError("Primero cargue la calendarizacion.");
            return;
        }
        File destino = reportePdf.pedirDestino(vista, "calendarizacion.pdf");
        if (destino == null) {
            return;
        }
        try {
            String titulo = "Calendarizacion de " + vista.getDescripcionCategoria()
                    + " - " + Formatos.fechaLarga(vista.getFecha());
            List<String[]> filas = new ArrayList<>(List.of(ultimosDatos));
            reportePdf.tabla(titulo, ultimasColumnas, filas, destino);
            reportePdf.abrir(destino);
        } catch (ServicioException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}

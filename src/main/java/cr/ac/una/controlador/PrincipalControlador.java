package cr.ac.una.controlador;

import cr.ac.una.vista.PrincipalView;

/**
 * CONTROLADOR de la ventana principal. Es el "armador" de la aplicacion:
 * decide QUE PESTANAS existen segun el rol del usuario de la sesion y
 * conecta cada vista con su controlador.
 *
 *   if (Sesion.esAdministrador()) {
 *       Funcionarios, Categorias, Recursos, Calendarizacion, Actividades, Estadisticas
 *   } else {
 *       Reservas, Calendarizacion, Actividades, Estadisticas
 *   }
 *
 * Aqui es donde se ve claro por que conviene el MVC: cada pestana se crea
 * en tres lineas (vista nueva, controlador nuevo, agregarPestana) y ninguna
 * sabe de la existencia de las otras.
 *
 * IMPORTANTE: el control de acceso por rol se hace aqui, NO escondiendo
 * botones. Un funcionario simplemente nunca ve la pestana de Funcionarios.
 */
public class PrincipalControlador {

    private final PrincipalView vista;

    public PrincipalControlador() {
        // TODO: armar el titulo "SISTEMA DE RESERVAS - <id> (<rol>)" con Sesion
        this.vista = null;
    }

    /** Crea las pestanas que corresponden al rol y muestra la ventana. */
    public void mostrar() {
        // TODO
    }

    private void agregarPestanasDeAdministrador() {
        // TODO
    }

    private void agregarPestanasDeFuncionario() {
        // TODO
    }

    /** Pestanas comunes a los dos roles: Calendarizacion, Actividades, Estadisticas. */
    private void agregarPestanasComunes() {
        // TODO
    }

    private void cerrarSesion() {
        // TODO: Sesion.cerrar(), cerrar esta ventana y volver a mostrar el login
    }
}

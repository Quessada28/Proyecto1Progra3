package cr.ac.una.logica;

import org.junit.jupiter.api.Test;

/**
 * PRUEBA UNITARIA (la corre Surefire con "mvn test" porque el nombre
 * termina en Test).
 *
 * Esta es LA clase de pruebas importante del proyecto: la funcionalidad 2
 * vale 25% y su logica se puede probar entera sin abrir una sola ventana.
 *
 * COMO PROBAR SIN TOCAR LOS XML
 * -----------------------------
 * ReservaService tiene un segundo constructor que recibe los tres DAO.
 * En la prueba se le pasan DAOs falsos: clases que EXTIENDEN al DAO real y
 * le sobreescriben los metodos para devolver listas en memoria.
 *
 *   class RecursoDaoFalso extends RecursoXmlDao {
 *       private final List<Recurso> datos = new ArrayList<>();
 *       @Override public List<Recurso> listarPorCategoria(String id) { ... }
 *   }
 *
 * Asi las pruebas corren rapido y no dependen del disco.
 *
 * Metodos que se pueden marcar con @BeforeEach para no repetir el armado.
 */
class ReservaServiceTest {

    @Test
    void crearReservaConTodasLasCategoriasDisponibles() {
        // TODO: dado 1 recurso libre en cada categoria pedida,
        //       crear(...) devuelve exito y la reserva trae un id por categoria
    }

    @Test
    void crearReservaFallaCuandoUnaCategoriaNoTieneUnidadesLibres() {
        // TODO: el resultado debe venir con exito == false y con la
        //       DESCRIPCION de la categoria que fallo en categoriasNoDisponibles
    }

    @Test
    void reservaFallidaNoGuardaNada() {
        // TODO: comprobar que el DAO de reservas quedo vacio (es todo o nada)
    }

    @Test
    void asignaElPrimerRecursoDisponibleDeLaCategoria() {
        // TODO: con dos recursos en la categoria y el primero ocupado,
        //       la reserva debe quedar con el segundo
    }

    @Test
    void dosReservasEnHorariosQueNoSeTraslapanUsanElMismoRecurso() {
        // TODO: 08:00-10:00 y 10:00-12:00 NO chocan (el limite es abierto por la derecha)
    }

    @Test
    void cancelarLiberaLosRecursosParaOtraReserva() {
        // TODO: reservar, cancelar y volver a reservar el mismo horario -> exito
    }

    @Test
    void noSePuedeCancelarUnaReservaDeOtroFuncionario() {
        // TODO: assertThrows(ServicioException.class, () -> ...)
    }

    @Test
    void validarRechazaHoraFinMenorOIgualAHoraInicio() {
        // TODO
    }

    @Test
    void validarRechazaFechaAnteriorAHoy() {
        // TODO
    }

    @Test
    void validarRechazaListaDeCategoriasVacia() {
        // TODO
    }
}

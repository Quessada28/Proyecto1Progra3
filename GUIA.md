# Proyecto 1 - Sistema de Reserva de Recursos (EIF206)

Esqueleto del proyecto. **Ninguna clase esta programada**: todas tienen sus
metodos con la firma correcta, el javadoc que explica que debe hacer cada uno
y un `// TODO` donde va el codigo.

## Arquitectura por capas

```
cr.ac.una.vista        Swing puro (JFrame/JPanel). Solo arma y muestra.       <- MVC
cr.ac.una.controlador  Escucha los botones, llama a logica, actualiza vista.  <- MVC
cr.ac.una.logica       Reglas del negocio. Lanza ServicioException.
cr.ac.una.datos        DAO sobre XML (DOM). Lanza DatosException.
cr.ac.una.modelo       Entidades: Usuario, Funcionario, Categoria, Recurso, Reserva.
cr.ac.una.util         Formatos, GeneradorId, Sesion, Validador.
cr.ac.una.ia           Extraccion de la reserva desde lenguaje natural (LLM).
cr.ac.una.reporte      Reportes PDF (iText).
```

Reglas que no se rompen:

- La **vista** nunca llama a un DAO ni abre un XML.
- El **servicio** nunca abre un `JOptionPane`.
- El **controlador** es el unico que conoce a los dos.

## Orden sugerido para programarlo

Cada paso deja algo que ya se puede correr y enseñar.

| # | Que hacer | Archivos |
|---|-----------|----------|
| 1 | Utilidades y modelo | `util/*`, `modelo/*` |
| 2 | `XmlUtil` y un DAO completo (`CategoriaXmlDao`) | `datos/` |
| 3 | Los demas DAO copiando el molde | `datos/` |
| 4 | Login + cambio de clave (5%) | `UsuarioService`, `LoginView/Controlador` |
| 5 | Categorias (10%) - el CRUD mas simple, es el molde | `Categorias*` |
| 6 | Recursos y Funcionarios (20%) | `Recursos*`, `Funcionarios*` |
| 7 | **Reservas (25%)** - hacerlo con las pruebas JUnit primero | `ReservaService`, `Reservas*` |
| 8 | Calendarizacion y Actividades (20%) | `CalendarioService`, las dos vistas |
| 9 | Estadisticas (20%) | `EstadisticaService`, `EstadisticasView` |
| 10 | PDF en todas las pantallas | `ReportePdf` |
| 11 | Extraccion con IA | `ExtractorReservaClaude` |

`ReservaService` es lo que mas vale y es logica pura: se puede escribir y
probar con JUnit **antes** de tener la pantalla lista.

## Pruebas

```
mvn test      # unitarias  -> Surefire, corre las clases *Test
mvn verify    # integracion -> Failsafe, corre las clases *IT
```

Las unitarias usan DAO falsos (una subclase del DAO real que devuelve listas
en memoria) y no tocan el disco. Las de integracion si escriben archivos, por
eso conviene `@TempDir`.

## Datos

Los XML viven en `datos/` en la raiz del proyecto. Ya esta `usuarios.xml` con
el administrador inicial:

```
id: admin    clave: admin    rol: ADMIN
```

Los otros cuatro archivos estan creados y vacios; los DAO los llenan solos.

## La llave del LLM

No se escribe en el codigo ni se sube a GitHub. Se pone como variable de
ambiente `ANTHROPIC_API_KEY` (en IntelliJ: Run > Edit Configurations >
Environment variables).

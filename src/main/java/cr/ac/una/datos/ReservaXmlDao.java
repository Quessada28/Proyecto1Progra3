package cr.ac.una.datos;

import cr.ac.una.modelo.EstadoReserva;
import cr.ac.una.modelo.Reserva;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservaXmlDao extends XmlDao<Reserva> {

    private static final String NODO_RECURSOS = "recursos";
    private static final String NODO_ID_RECURSO = "idRecurso";

    public ReservaXmlDao() {
        super("reservas.xml", "reservas", "reserva");
    }

    @Override
    protected Reserva mapear(Element elemento) {
        Reserva reserva = new Reserva();
        reserva.setId(XmlUtil.texto(elemento, "id"));
        reserva.setIdFuncionario(XmlUtil.texto(elemento, "idFuncionario"));
        reserva.setActividad(XmlUtil.texto(elemento, "actividad"));
        reserva.setFecha(leerFecha(XmlUtil.texto(elemento, "fecha")));
        reserva.setHoraInicio(leerHora(XmlUtil.texto(elemento, "horaInicio")));
        reserva.setHoraFin(leerHora(XmlUtil.texto(elemento, "horaFin")));
        reserva.setEstado(EstadoReserva.desdeTexto(XmlUtil.texto(elemento, "estado")));
        reserva.setIdsRecursos(leerRecursos(elemento));
        return reserva;
    }

    @Override
    protected void escribir(Document doc, Element destino, Reserva reserva) {
        XmlUtil.agregarHijo(doc, destino, "idFuncionario", reserva.getIdFuncionario());
        XmlUtil.agregarHijo(doc, destino, "actividad", reserva.getActividad());
        XmlUtil.agregarHijo(doc, destino, "fecha", textoDe(reserva.getFecha()));
        XmlUtil.agregarHijo(doc, destino, "horaInicio", textoDe(reserva.getHoraInicio()));
        XmlUtil.agregarHijo(doc, destino, "horaFin", textoDe(reserva.getHoraFin()));
        XmlUtil.agregarHijo(doc, destino, "estado", reserva.getEstado().name());

        Element recursos = doc.createElement(NODO_RECURSOS);
        for (String idRecurso : reserva.getIdsRecursos()) {
            XmlUtil.agregarHijo(doc, recursos, NODO_ID_RECURSO, idRecurso);
        }
        destino.appendChild(recursos);
    }

    @Override
    protected String idDe(Reserva reserva) {
        return reserva.getId();
    }

    private List<String> leerRecursos(Element elemento) {
        List<String> ids = new ArrayList<>();
        for (Element contenedor : XmlUtil.hijosDirectos(elemento, NODO_RECURSOS)) {
            for (Element item : XmlUtil.hijosDirectos(contenedor, NODO_ID_RECURSO)) {
                String valor = item.getTextContent();
                if (valor != null && !valor.isBlank()) {
                    ids.add(valor.trim());
                }
            }
        }
        return ids;
    }

    private static LocalDate leerFecha(String texto) {
        return texto == null || texto.isBlank() ? null : LocalDate.parse(texto);
    }

    private static LocalTime leerHora(String texto) {
        return texto == null || texto.isBlank() ? null : LocalTime.parse(texto);
    }

    private static String textoDe(Object valor) {
        return valor == null ? "" : valor.toString();
    }

    public List<Reserva> listarPorFuncionario(String idFuncionario) {
        return listar().stream()
                .filter(r -> r.getIdFuncionario() != null
                        && r.getIdFuncionario().equals(idFuncionario))
                .collect(Collectors.toList());
    }

    public List<Reserva> listarPorFecha(LocalDate fecha) {
        return listar().stream()
                .filter(r -> fecha != null && fecha.equals(r.getFecha()))
                .collect(Collectors.toList());
    }

    public List<Reserva> listarActivasPorFecha(LocalDate fecha) {
        return listarPorFecha(fecha).stream()
                .filter(Reserva::estaActiva)
                .collect(Collectors.toList());
    }

    public List<Reserva> listarEntre(LocalDate desde, LocalDate hasta) {
        return listar().stream()
                .filter(r -> r.getFecha() != null
                        && !r.getFecha().isBefore(desde)
                        && !r.getFecha().isAfter(hasta))
                .collect(Collectors.toList());
    }
}

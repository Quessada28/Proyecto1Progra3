package cr.ac.una.datos;

import cr.ac.una.modelo.Recurso;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;
import java.util.stream.Collectors;

public class RecursoXmlDao extends XmlDao<Recurso> {

    public RecursoXmlDao() {
        super("recursos.xml", "recursos", "recurso");
    }

    @Override
    protected Recurso mapear(Element elemento) {
        return new Recurso(
                XmlUtil.texto(elemento, "id"),
                XmlUtil.texto(elemento, "idCategoria"),
                XmlUtil.texto(elemento, "descripcion"));
    }

    @Override
    protected void escribir(Document doc, Element destino, Recurso recurso) {
        XmlUtil.agregarHijo(doc, destino, "idCategoria", recurso.getIdCategoria());
        XmlUtil.agregarHijo(doc, destino, "descripcion", recurso.getDescripcion());
    }

    @Override
    protected String idDe(Recurso recurso) {
        return recurso.getId();
    }

    public List<Recurso> listarPorCategoria(String idCategoria) {
        if (idCategoria == null || idCategoria.isBlank()) {
            return listar();
        }
        return listar().stream()
                .filter(r -> idCategoria.equals(r.getIdCategoria()))
                .collect(Collectors.toList());
    }
}

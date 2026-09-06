package cr.ac.una.datos;

import cr.ac.una.modelo.Categoria;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaXmlDao extends XmlDao<Categoria> {

    public CategoriaXmlDao() {
        super("categorias.xml", "categorias", "categoria");
    }

    @Override
    protected Categoria mapear(Element elemento) {
        return new Categoria(
                XmlUtil.texto(elemento, "id"),
                XmlUtil.texto(elemento, "descripcion"));
    }

    @Override
    protected void escribir(Document doc, Element destino, Categoria categoria) {
        XmlUtil.agregarHijo(doc, destino, "descripcion", categoria.getDescripcion());
    }

    @Override
    protected String idDe(Categoria categoria) {
        return categoria.getId();
    }

    public List<Categoria> buscarPorDescripcion(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        String buscado = texto.trim().toLowerCase();
        return listar().stream()
                .filter(c -> c.getDescripcion() != null
                        && c.getDescripcion().toLowerCase().contains(buscado))
                .collect(Collectors.toList());
    }
}

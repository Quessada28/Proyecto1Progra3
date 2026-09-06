package cr.ac.una.datos;

import cr.ac.una.modelo.Funcionario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioXmlDao extends XmlDao<Funcionario> {

    public FuncionarioXmlDao() {
        super("funcionarios.xml", "funcionarios", "funcionario");
    }

    @Override
    protected Funcionario mapear(Element elemento) {
        return new Funcionario(
                XmlUtil.texto(elemento, "id"),
                XmlUtil.texto(elemento, "nombre"),
                XmlUtil.texto(elemento, "telefono"));
    }

    @Override
    protected void escribir(Document doc, Element destino, Funcionario funcionario) {
        XmlUtil.agregarHijo(doc, destino, "nombre", funcionario.getNombre());
        XmlUtil.agregarHijo(doc, destino, "telefono", funcionario.getTelefono());
    }

    @Override
    protected String idDe(Funcionario funcionario) {
        return funcionario.getId();
    }

    public List<Funcionario> buscarPorNombre(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        String buscado = texto.trim().toLowerCase();
        return listar().stream()
                .filter(f -> f.getNombre() != null
                        && f.getNombre().toLowerCase().contains(buscado))
                .collect(Collectors.toList());
    }
}

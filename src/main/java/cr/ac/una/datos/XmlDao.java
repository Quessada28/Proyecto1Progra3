package cr.ac.una.datos;

import cr.ac.una.util.GeneradorId;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class XmlDao<T> implements Dao<T> {

    protected final String archivo;
    protected final String raiz;
    protected final String nodo;

    protected XmlDao(String archivo, String raiz, String nodo) {
        this.archivo = archivo;
        this.raiz = raiz;
        this.nodo = nodo;
    }

    protected abstract T mapear(Element elemento);

    protected abstract void escribir(Document doc, Element destino, T entidad);

    protected abstract String idDe(T entidad);

    protected Document documento() {
        return XmlUtil.abrirOCrear(archivo, raiz);
    }

    @Override
    public List<T> listar() {
        List<T> resultado = new ArrayList<>();
        for (Element elemento : XmlUtil.nodos(documento(), nodo)) {
            resultado.add(mapear(elemento));
        }
        return resultado;
    }

    @Override
    public Optional<T> buscarPorId(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return listar().stream().filter(e -> id.equals(idDe(e))).findFirst();
    }

    @Override
    public void guardar(T entidad) {
        String id = idDe(entidad);
        if (id == null || id.isBlank()) {
            throw new DatosException("No se puede guardar un registro sin id.");
        }
        Document doc = documento();
        Element nuevo = doc.createElement(nodo);
        XmlUtil.agregarHijo(doc, nuevo, "id", id);
        escribir(doc, nuevo, entidad);

        Element raizDoc = doc.getDocumentElement();
        Element existente = null;
        for (Element candidato : XmlUtil.hijosDirectos(raizDoc, nodo)) {
            if (XmlUtil.texto(candidato, "id").equals(id)) {
                existente = candidato;
                break;
            }
        }
        if (existente == null) {
            raizDoc.appendChild(nuevo);
        } else {
            raizDoc.replaceChild(nuevo, existente);
        }
        XmlUtil.guardar(doc, archivo);
    }

    @Override
    public void eliminar(String id) {
        Document doc = documento();
        XmlUtil.eliminarNodoConId(doc, nodo, id);
        XmlUtil.guardar(doc, archivo);
    }

    public boolean existe(String id) {
        return buscarPorId(id).isPresent();
    }

    public int ultimoConsecutivo() {
        int mayor = 0;
        for (T entidad : listar()) {
            int consecutivo = GeneradorId.consecutivoDe(idDe(entidad));
            if (consecutivo > mayor) {
                mayor = consecutivo;
            }
        }
        return mayor;
    }
}

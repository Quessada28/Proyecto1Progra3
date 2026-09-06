package cr.ac.una.datos;

import cr.ac.una.modelo.Rol;
import cr.ac.una.modelo.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UsuarioXmlDao extends XmlDao<Usuario> {

    public UsuarioXmlDao() {
        super("usuarios.xml", "usuarios", "usuario");
    }

    @Override
    protected Usuario mapear(Element elemento) {
        return new Usuario(
                XmlUtil.texto(elemento, "id"),
                XmlUtil.texto(elemento, "clave"),
                Rol.desdeTexto(XmlUtil.texto(elemento, "rol")));
    }

    @Override
    protected void escribir(Document doc, Element destino, Usuario usuario) {
        XmlUtil.agregarHijo(doc, destino, "clave", usuario.getClave());
        XmlUtil.agregarHijo(doc, destino, "rol", usuario.getRol().name());
    }

    @Override
    protected String idDe(Usuario usuario) {
        return usuario.getId();
    }
}

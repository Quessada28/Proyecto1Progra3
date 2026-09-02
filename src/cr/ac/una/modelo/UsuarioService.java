//package cr.ac.una.modelo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class UsuarioService {
//
//    private List<Usuario> usuarios;
//
//    public UsuarioService() {
//        this.usuarios = new ArrayList<>();
//        // Cargar usuarios desde el XML al instanciar el servicio[cite: 1]
//        cargarXML();
//    }
//
//    public Usuario autenticar(String id, String clave) {
//        for (Usuario u : usuarios) {
//            if (u.getId().equalsIgnoreCase(id) && u.getClave().equals(clave)) {
//                return u; // Retorna el usuario autenticado (con su rol)[cite: 1]
//            }
//        }
//        return null; // Credenciales incorrectas
//    }
//
//    public boolean cambiarClave(String id, String claveActual, String nuevaClave) {
//        Usuario u = autenticar(id, claveActual);
//        if (u != null) {
//            u.setClave(nuevaClave);
//            guardarXML(); // Guardar cambios persistentes en el XML[cite: 1]
//            return true;
//        }
//        return false; // Clave actual incorrecta o usuario no existe
//    }
//
//    public void cargarXML() {
//        // TODO: Aquí usarás DOM, SAX, JAXB o Jackson para leer el archivo XML[cite: 1]
//        // Datos quemados de prueba mientras integras el XML:
//        if (usuarios.isEmpty()) {
//            usuarios.add(new Usuario("admin", "1234", "ADMINISTRADOR"));[cite: 1]
//            usuarios.add(new Usuario("111", "111", "FUNCIONARIO"));[cite: 1]
//        }
//    }
//
//    public void guardarXML() {
//        // TODO: Aquí escribirás la lista de usuarios en el archivo XML[cite: 1]
//    }
//}
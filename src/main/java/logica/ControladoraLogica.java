package logica;

import java.util.List;
import persistencia.ControladoraPersistencia;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraLogica {
    
    ControladoraPersistencia control = new ControladoraPersistencia();

    //Odontologo
    public void crearOdontologo(Odontologo odonto) {
        control.crearOdontologo(odonto);
    }

    //Usuario
    public void crearUsuario(Usuario usuario) {
        control.crearUsuario(usuario);
    }
    
    public List<Usuario> listaUsuarios() {
        return control.listaUsuarios();
    }
    
    public void eliminarUsuario(int id) throws NonexistentEntityException {
        control.eliminarUsuario(id);
    }

    public Usuario encontrarUsuario(int id) {
        return control.encontrarUsuario(id);
    }

    public void editarUsuario(Usuario usu) throws Exception {
        control.editarUsuario(usu);
    }
}

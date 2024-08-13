package persistencia;

import java.util.List;
import logica.Odontologo;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    HorarioJpaController horarioJpa = new HorarioJpaController();
    OdontologoJpaController odontoJpa = new OdontologoJpaController();
    PacienteJpaController pacienteJpa = new PacienteJpaController();
    ResponsableJpaController responsableJpa = new ResponsableJpaController();
    SecretarioJpaController secretarioJpa = new SecretarioJpaController();
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();

    //Odontologo
    public void crearOdontologo(Odontologo odonto) {
        odontoJpa.create(odonto);
    }

    //Usuarios
    public void crearUsuario(Usuario usuario) {
        usuarioJpa.create(usuario);
    }

    public List<Usuario> listaUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    public void editarUsuario(Usuario usuario) throws Exception {
        usuarioJpa.edit(usuario);
    }

    public void eliminarUsuario(int id) throws NonexistentEntityException {
        usuarioJpa.destroy(id);
    }

    public Usuario encontrarUsuario(int id) {
        return usuarioJpa.findUsuario(id);
    }
}

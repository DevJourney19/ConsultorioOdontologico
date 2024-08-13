package logica;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Secretario extends Persona implements Serializable {

    private String planta;
    @OneToOne
    private Usuario usuario;

    public Secretario() {
    }

    public Secretario(String planta, Usuario usuario, int id, String dni, String nombre, String apellido, String telefono, String direccion, LocalDate fechaNac) {
        super(id, dni, nombre, apellido, telefono, direccion, fechaNac);
        this.planta = planta;
        this.usuario = usuario;
    }
    
    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

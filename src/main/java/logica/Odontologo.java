package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Odontologo extends Persona implements Serializable {

    private String especialidad;
    @OneToOne
    private Horario horario;
    @OneToMany(mappedBy = "odontologo")
    private List<Turno> turno;
    @OneToOne
    private Usuario usuario;

    public Odontologo() {
    }

    public Odontologo(String especialidad, Horario horario, List<Turno> turno, Usuario usuario, int id, String dni, String nombre, String apellido, String telefono, String direccion, LocalDate fechaNac) {
        super(id, dni, nombre, apellido, telefono, direccion, fechaNac);
        this.especialidad = especialidad;
        this.horario = horario;
        this.turno = turno;
        this.usuario = usuario;
    }

    

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Turno> getTurno() {
        return turno;
    }

    public void setTurno(List<Turno> turno) {
        this.turno = turno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

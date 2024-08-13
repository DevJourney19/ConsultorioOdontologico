package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Paciente extends Persona implements Serializable {

    private String tipo_Sangre;
    private String con_OS;
    private String malestar;
    @OneToMany(mappedBy = "paciente")
    private List<Turno> turnos;
    @OneToOne
    private Responsable res;

    public Paciente() {
    }

    public Paciente(String tipo_Sangre, String con_OS, String malestar, List<Turno> turnos, Responsable res, int id, String dni, String nombre, String apellido, String telefono, String direccion, LocalDate fechaNac) {
        super(id, dni, nombre, apellido, telefono, direccion, fechaNac);
        this.tipo_Sangre = tipo_Sangre;
        this.con_OS = con_OS;
        this.malestar = malestar;
        this.turnos = turnos;
        this.res = res;
    }

    

    public String getTipo_Sangre() {
        return tipo_Sangre;
    }

    public void setTipo_Sangre(String tipo_Sangre) {
        this.tipo_Sangre = tipo_Sangre;
    }

    public String getCon_OS() {
        return con_OS;
    }

    public void setCon_OS(String con_OS) {
        this.con_OS = con_OS;
    }

    public String getMalestar() {
        return malestar;
    }

    public void setMalestar(String malestar) {
        this.malestar = malestar;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    public Responsable getRes() {
        return res;
    }

    public void setRes(Responsable res) {
        this.res = res;
    }

}

package logica;

import java.time.LocalDate;
import javax.persistence.Entity;

@Entity
public class Responsable extends Persona {

    private String tipo;

    public Responsable() {
    }

    public Responsable(String tipo, int id, String dni, String nombre, String apellido, String telefono, String direccion, LocalDate fechaNac) {
        super(id, dni, nombre, apellido, telefono, direccion, fechaNac);
        this.tipo = tipo;
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}

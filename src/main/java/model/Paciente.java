package model;

import java.util.Date;
import lombok.Data;

@Data
public class Paciente {

    int codigo;
    String nombre;
    String apellido;
    String sexo;
    Date fecha;
    String dni;
    String movil;
    String correo;
    String ubigeo;
    String dirreccion;
    String grupo;
    String estado;
    String civil;

    public void clear() {
        codigo = 0;
        nombre = null;
        apellido = null;
        fecha = null;
        dni = null;
        movil = null;
        correo = null;
        ubigeo = null;
        dirreccion = null;
    }

}

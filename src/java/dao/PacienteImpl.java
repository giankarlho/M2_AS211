package dao;

import java.util.List;
import model.Paciente;
import java.sql.PreparedStatement;
import java.util.Date;

public class PacienteImpl extends Conexion implements ICRUD<Paciente> {

    @Override
    public void guardar(Paciente paciente) throws Exception {
          try {
            String sql = "insert into paciente"
                    + " (NOMPAC,APEPAC,SEXPAC,FNPAC,DNIPAC,TELFPAC,EMAILPAC,NUMUBI,DIRPAC,GSPAC,ESTPAC)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getSexo());
            ps.setDate(4, (java.sql.Date) (Date) paciente.getFecha());
            ps.setString(5, paciente.getDni());
            ps.setString(6, paciente.getMovil());
            ps.setString(7, paciente.getCorreo());
            ps.setString(8, paciente.getUbigeo());
            ps.setString(9, paciente.getDirreccion());
            ps.setString(10, paciente.getGrupo());
            ps.setString(11, paciente.getEstado());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/registrar: " + e.getMessage());
        }

    }

    @Override
    public void modificar(Paciente gen) throws Exception {
        
    }

    @Override
    public void eliminar(Paciente gen) throws Exception {
        
    }

    @Override
    public List<Paciente> listarTodos() throws Exception {
        
    }

   
    
}

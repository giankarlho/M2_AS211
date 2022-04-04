package dao;

import java.util.List;
import model.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    public void modificar(Paciente paciente) throws Exception {
        try {
            String sql = "update paciente set NOMPAC=?,APEPAC=?,SEXPAC=?,FNPAC=?,DNIPAC=?,TELFPAC=?,"
                    + "EMAILPAC=?,NUMUBI=?,DIRPAC=?,GSPAC=?,ESTPAC=? where NUMPAC=?";
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
            ps.setInt(12, paciente.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Paciente paciente) throws Exception {
        try {
            String sql = "delete paciente where numpac=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, paciente.getCodigo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/eliminar: " + e.getMessage());
        }
    }

    // NUMPAC  NOMPAC    APEPAC  SEXPAC FNPAC  DNIPAC   TELFPAC EMAILPAC NUMUBI DIRPAC    GSPAC HCPAC       ESTPAC
    @Override
    public List<Paciente> listarTodos() throws Exception {
        List<Paciente> lista = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from paciente";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Paciente pac = new Paciente();
                pac.setCodigo(rs.getInt("NUMPAC"));
                pac.setNombre(rs.getString("NOMPAC"));
                pac.setApellido(rs.getString("APEPAC"));
                pac.setSexo(rs.getString("SEXPAC"));
                pac.setFecha(rs.getDate("FNPAC"));
                pac.setDni(rs.getString("DNIPAC"));
                pac.setMovil(rs.getString("TELFPAC"));
                pac.setCorreo(rs.getString("EMAILPAC"));
                pac.setUbigeo(rs.getString("NUMUBI"));
                pac.setDirreccion(rs.getString("DIRPAC"));
                pac.setGrupo(rs.getString("GSPAC"));
                pac.setEstado(rs.getString("ESTPAC"));
                lista.add(pac);
            }            
        } catch (Exception e) {
            System.out.println("");
        } finally{
            this.cerrarCnx();
        }
        return lista;
    }

}

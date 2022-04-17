package dao;

import java.util.List;
import model.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import services.UtilToSql;

public class PacienteImpl extends Conexion implements ICRUD<Paciente> {

    @Override
    public void guardar(Paciente paciente) throws Exception {
        Date fecha = (Date) paciente.getFecha();
        System.out.println("Fecha directa :" + UtilToSql.convert(paciente.getFecha()));
        System.out.println("Fecha con (Date):" + UtilToSql.convert(fecha));

        try {
            String sql = "insert into paciente"
                    + " (NOMPAC,APEPAC,SEXPAC,FNPAC,DNIPAC,NUMUBI,DIRPAC,ESTPAC)"
                    + " values (?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, String.valueOf(paciente.getSexo()));
//            ps.setDate(4,fecha);
            ps.setDate(4, UtilToSql.convert(paciente.getFecha()));
            ps.setString(5, paciente.getDni());
            ps.setString(6, paciente.getUbigeo());
            ps.setString(7, paciente.getDirreccion());
            ps.setString(8, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/registrar: " + e.getMessage());
        }
    }

    @Override
    public void modificar(Paciente paciente) throws Exception {
        try {
            String sql = "update paciente set NOMPAC=?,APEPAC=?,SEXPAC=?,FNPAC=?,DNIPAC=?,NUMUBI=?,DIRPAC=?,ESTPAC=? where NUMPAC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getSexo());
            ps.setDate(4, UtilToSql.convert(paciente.getFecha()));
            ps.setString(5, paciente.getDni());
            ps.setString(6, paciente.getUbigeo());
            ps.setString(7, paciente.getDirreccion());
            ps.setString(8, "A");
            ps.setInt(9, paciente.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/modificar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Paciente paciente) throws Exception {
        try {
            String sql = "delete from paciente where numpac=?";
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
        String sql = "select * from paciente order by NUMPAC desc";
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
                pac.setUbigeo(rs.getString("NUMUBI"));
                pac.setDirreccion(rs.getString("DIRPAC"));
                pac.setEstado(rs.getString("ESTPAC"));
                lista.add(pac);
            }
        } catch (Exception e) {
            System.out.println("");
        } finally {
            this.cerrarCnx();
        }
        return lista;
    }

    public void cambiarEstado(Paciente paciente) throws Exception {
        try {
            String sql = "update paciente set ESTPAC=? where NUMPAC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, paciente.getEstado());
            ps.setInt(2, paciente.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/cambiarEstado: " + e.getMessage());
        }
    }

    public List<String> autoCompleteUbigeo1(String consulta, String departamento) throws Exception {
        List<String> lista = new ArrayList<>();
        // SQL Server
//        String sql = "select top 10 concat(DISUBI, ', ', PROUBI) AS UBIGEODESC from UBIGEO WHERE DPTUBI = ? AND DISUBI LIKE ?";
        // MySQL
        String sql = "select concat(DISUBI, ', ', PROUBI) AS UBIGEODESC from UBIGEO WHERE DPTUBI = ? AND DISUBI LIKE ? limit 10";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, departamento);
            ps.setString(2, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("UBIGEODESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/autoCompleteUbigeoDao" + e.getMessage());
        }
        return lista;
    }
    
    public String obtenerCodigoUbigeo1(String cadenaUbi) throws Exception {
        String sql = "select CODUBI FROM UBIGEO WHERE concat(DISUBI, ', ', PROUBI) = ? or DISUBI = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaUbi);
            ps.setString(2, cadenaUbi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("CODUBI");
            }
            return rs.getString("CODUBI");
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoUbigeo " + e.getMessage());
            throw e;
        }
    }

    public List<String> autoCompleteUbigeo2(String consulta) throws Exception {
        List<String> lista = new ArrayList<>();
        // SQL Server
//        String sql = "select top 10 concat(DISUBI, ', ', PROUBI, ', ',DPTUBI) AS UBIGEODESC from UBIGEO WHERE DISUBI LIKE ?";
        // MySQL
        String sql = "select concat(DISUBI, ', ', PROUBI, ', ',DPTUBI) AS UBIGEODESC from UBIGEO WHERE DISUBI LIKE ? limit 10";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("UBIGEODESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/autocompleteUbigeoDao" + e.getMessage());
        }
        return lista;
    }

    public String obtenerCodigoUbigeo2(String cadenaUbi) throws Exception {
        String sql = "select CODUBI FROM UBIGEO WHERE concat(DISUBI, ', ', PROUBI, ', ',DPTUBI) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaUbi);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("CODUBI");
            }
            return rs.getString("CODUBI");
        } catch (Exception e) {
            System.out.println("Error en PacienteImpl/obtenerCodigoUbigeo " + e.getMessage());
            throw e;
        }
    }
   
}


/*


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
 */

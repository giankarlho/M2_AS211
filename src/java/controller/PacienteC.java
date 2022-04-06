package controller;

import dao.PacienteImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.Paciente;

@Named(value="pacienteC")
@SessionScoped
public class PacienteC implements Serializable {

    private Paciente paciente;
    PacienteImpl dao;
    private List<Paciente> lstPaciente;
    
    public PacienteC() {
        paciente = new Paciente();
        dao = new PacienteImpl();
    }

    public void registrar() throws Exception{
        try {
            dao.guardar(paciente);
        } catch (Exception e) {
            System.out.println("Error en registrar PacienteC/registrar: " + e.getMessage());
        }
    }
    
    public void modificar() throws Exception{
        try {
            dao.modificar(paciente);
        } catch (Exception e) {
            System.out.println("Error en modificar PacienteC/modificar: " + e.getMessage());
        }
    }
    
    public void eliminar() throws Exception{
        try {
            dao.eliminar(paciente);
        } catch (Exception e) {
            System.out.println("Error en eliminar PacienteC/modificar: " + e.getMessage());
        }
    }
    
    public void listar() throws Exception{
        try {
          lstPaciente = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listar PacienteC/modificar: " + e.getMessage());
        }
    }   
    
    
    // Getter y Setter
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Paciente> getLstPaciente() {
        return lstPaciente;
    }

    public void setLstPaciente(List<Paciente> lstPaciente) {
        this.lstPaciente = lstPaciente;
    }
    
    
    
    
    
}

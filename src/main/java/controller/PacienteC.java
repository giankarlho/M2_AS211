package controller;

import dao.PacienteImpl;
import dao.UbigeoD;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Paciente;

@Named(value = "pacienteC")
@SessionScoped
public class PacienteC implements Serializable {

    private String departamento;
    private Paciente paciente;
    PacienteImpl dao;
    private List<Paciente> lstPaciente;
    private List<String> listaDpto;

    public PacienteC() {
        paciente = new Paciente();
        dao = new PacienteImpl();
    }

    public void registrar() throws Exception {
        try {
            paciente.setUbigeo(dao.obtenerCodigoUbigeo1(paciente.getUbigeo()));
            dao.guardar(paciente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en registrar PacienteC/registrar: " + e.getMessage());
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(paciente);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado", "Registrado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en modificar PacienteC/modificar: " + e.getMessage());
        }
    }

    public void eliminar(Paciente pac) throws Exception {
        try {
//            pac.setEstado("I");
//            dao.cambiarEstado(pac);
            dao.eliminar(pac);
            listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado", "Eliminado con éxito"));
        } catch (Exception e) {
            System.out.println("Error en eliminar PacienteC/modificar: " + e.getMessage());
        }
    }

    public void listar() throws Exception {
        try {
            lstPaciente = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en listar PacienteC/modificar: " + e.getMessage());
        }
    }

    public List<String> completeTextUbigeo1(String query) throws Exception {        
        try {
            return dao.autoCompleteUbigeo1(query,departamento);
        } catch (Exception e) {
            System.out.println("Error en listar PacienteC/completeTextUbigeo1: " + e.getMessage());
            throw e;
        }
    }    
    
    public List<String> completeTextUbigeo2(String query) throws Exception {        
        try {
            return dao.autoCompleteUbigeo2(query);
        } catch (Exception e) {
            System.out.println("Error en listar PacienteC/completeTextUbigeo2: " + e.getMessage());
            throw e;
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

//    public Paciente getPacienteSeleccionado() {
//        return pacienteSeleccionado;
//    }
//
//    public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
//        this.pacienteSeleccionado = pacienteSeleccionado;
//    }
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}

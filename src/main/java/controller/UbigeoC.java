package controller;

import dao.UbigeoD;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Ubigeo;

@Named(value = "ubigeoC")
@SessionScoped
public class UbigeoC implements Serializable {

    Ubigeo ubigeo;
    UbigeoD dao;
    private List<String> listadoDpto;

    public UbigeoC(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public UbigeoC() {
        ubigeo = new Ubigeo();
        dao = new UbigeoD();
    }

    @PostConstruct
    public void inicio() {
        try {
            listarDpto();
        } catch (Exception e) {
        }
    }

    public void listarDpto() throws Exception {
        try {
            listadoDpto = dao.listaDpto();
        } catch (Exception e) {
            
        }        
    }

    // Getter y Setter
    public List<String> getListadoDpto() {
        return listadoDpto;
    }

    public void setListadoDpto(List<String> listadoDpto) {
        this.listadoDpto = listadoDpto;
    }
    
    
    

}

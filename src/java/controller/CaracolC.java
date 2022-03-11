package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import model.Caracol;
import services.CaracolS;

@Named(value = "caracolC")
@SessionScoped
public class CaracolC implements Serializable {

    private Caracol caracol;    
   
    public CaracolC() {
        caracol = new Caracol();                        
    }
    
    public void calcular(){
        caracol.setMensaje(CaracolS.calcular(caracol.getAltura(), caracol.getSube(),caracol.getResbala()));
    }
    
    public void limpiar(){
        caracol = new Caracol();
    }

    public Caracol getCaracol() {
        return caracol;
    }

    public void setCaracol(Caracol caracol) {
        this.caracol = caracol;
    }

    
    
}

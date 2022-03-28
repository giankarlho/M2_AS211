package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import model.Cajero;
import services.CajeroS;

@Named(value = "cajeroC")
@SessionScoped
public class CajeroC implements Serializable {

    private Cajero cajero;
            
    public CajeroC() {
        cajero = new Cajero();
    }
    
    public void calcular(){
        CajeroS.calcular(cajero.getTope(),cajero.getMonto());
        cajero.setMensaje(CajeroS.mensaje);
    }
    
    // Getter y Setter

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }
    
    
    
}

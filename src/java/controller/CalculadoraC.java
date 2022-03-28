package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import model.Calculadora;
import services.CalculadoraS;

@Named(value = "calculadoraC")
@SessionScoped
public class CalculadoraC implements Serializable {

    private Calculadora modelo;
    private CalculadoraS servicio;
    
    public CalculadoraC() {
        modelo = new Calculadora();
        servicio = new CalculadoraS();
    }

    public void calcular(){
        servicio.calcular(modelo.getNro1(),modelo.getOperador(),modelo.getNro2());
        modelo.setStrResultado(CalculadoraS.StrResultado);
    } 
    
    public void limpiar(){
        modelo = new Calculadora();
    }
    
    // Getter y Setter
    public Calculadora getModelo() {
        return modelo;
    }

    public void setModelo(Calculadora modelo) {
        this.modelo = modelo;
    }

    public CalculadoraS getServicio() {
        return servicio;
    }

    public void setServicio(CalculadoraS servicio) {
        this.servicio = servicio;
    }
    
    
    
    
}

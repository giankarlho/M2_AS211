package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import services.CalculadoraS;

@Named(value = "calculadoraC")
@SessionScoped
public class CalculadoraC implements Serializable {

    private int nro1;
    private int nro2;
    private String resultado="";
    private char operador;
    
    public CalculadoraC() {
    }
    
    public void calcular(){
        resultado = CalculadoraS.calcular(nro1, operador, nro2);
    }

    public void limpiar(){
        setNro1(0);
        setNro2(0);
        setResultado("");
        setOperador('0');
    }
    
    public int getNro1() {
        return nro1;
    }

    public void setNro1(int nro1) {
        this.nro1 = nro1;
    }

    public int getNro2() {
        return nro2;
    }

    public void setNro2(int nro2) {
        this.nro2 = nro2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public char getOperador() {
        return operador;
    }

    public void setOperador(char operador) {
        this.operador = operador;
    }

    
}

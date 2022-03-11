package model;

public class Calculadora {
    
    private Double nro1;
    private Double nro2;
    private char operador;    
//    private Double DouResultado;
    private String StrResultado;

    // Encapsulamiento
    // Getter y Setter
    public Double getNro1() {
        return nro1;
    }

    public void setNro1(Double nro1) {
        this.nro1 = nro1;
    }

    public Double getNro2() {
        return nro2;
    }

    public void setNro2(Double nro2) {
        this.nro2 = nro2;
    }

    public char getOperador() {
        return operador;
    }

    public void setOperador(char operador) {
        this.operador = operador;
    }

//    public Double getDouResultado() {
//        return DouResultado;
//    }
//
//    public void setDouResultado(Double DouResultado) {
//        this.DouResultado = DouResultado;
//    }

    public String getStrResultado() {
        return StrResultado;
    }

    public void setStrResultado(String StrResultado) {
        this.StrResultado = StrResultado;
    }
    
    
}


package services;


public class CalculadoraS {   
   
    public static String StrResultado = "";
    
    public static void calcular(double nro1, char operador, double nro2){
        Double DouResultado = 0.0;
        switch(operador){
            case '+': DouResultado = nro1 + nro2; break;
            case '-': DouResultado = nro1 - nro2; break;
            case '*': DouResultado = nro1 * nro2; break;
            case '/': DouResultado = nro1 / nro2; break;            
        }  
        StrResultado = String.valueOf(DouResultado);
    }
    public String validar(double nro2){
        StrResultado = "";
        if (nro2 == 0) StrResultado = "No hay división por cero";        
        return StrResultado;
    }  
    
}

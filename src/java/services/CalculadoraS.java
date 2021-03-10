package services;

public class CalculadoraS {

    public static String calcular(double nro1, char operador, double nro2) {
        double resultado = 0.0;
        switch (operador) {
            case '+':
                resultado = nro1 + nro2;
                break;
            case '-':
                resultado = nro1 - nro2;
                break;
            case '*':
                resultado = nro1 * nro2;
                break;
            case '/':
                resultado = nro1 / nro2;
                break;
            case '^':
                resultado = Math.pow(nro1, nro2);
        }
        return String.valueOf(resultado);
    }

}

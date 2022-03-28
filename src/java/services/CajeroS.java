package services;

public class CajeroS {
    
    public static String mensaje ="";
    
    public static void calcular(int tope, int monto){
        int[] tipoBilletes ={200,100,50,20,10};
        int billete=0;
        for (int x:tipoBilletes){
//            System.out.println("tipoBilletes" + x);
            if (monto<x){
                continue;
            }
            billete = monto / x;
            monto = monto % x;
            mensaje = mensaje + billete + " de " + x + " soles \n";
        }        
    }    

    /* Validaciones
    1. El monto no debe ser mayor que el tope
    2. El monto no debe ser negativo
    3. El monto no debe tener fracción   
    */
}

package services;

public class CajeroS {
 
    public static String mensaje="";
    
    public static void calcular(int tope, int monto){
      int[]  tipoBilletes ={200,100,50,20,10};
      int billete =0;
      for (int x: tipoBilletes){
          if (monto<x){
              continue;
          }
          billete = monto / x;
          monto = monto % x;          
          // 5 de 100 soles
          mensaje = mensaje + billete + " de " + x + " soles \n";
      }
    }
}

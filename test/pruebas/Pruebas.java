/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import controller.PacienteC;

/**
 *
 * @author Joaquin
 */
public class Pruebas {
    public static void main(String[] args) throws Exception {
        PacienteC controller = new PacienteC();
        controller.listar();
        System.out.println("Listado: " + controller.getLstPaciente() );
    }
    
}

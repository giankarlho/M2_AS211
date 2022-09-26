"use strict";

/* 
- Mínimo de 3 caracteres por apellido
- Detectar un espacio vacio como mínimo en la cadena
 * */

function validarApellido(){
    // trim() -> quitar los espacios al inicio y final de mi cadena
    // Por defecto el prependId="true" en el formulario y tengo que poner un id al form
    var apellido = String(document.getElementById('formId:apeId').value).trim();
    
    // Poner el prependId="false" en el formulario y no es necesario poner un id al form
//    var apellido = document.getElementById('apeId').value;    
//    console.log("Tamaño de mi cadena con espacios: " + apellido.length);
//    console.log("Tamaño de mi cadena sin espacios: " + apellido.trim().length);
        
    var spaceCount = apellido.split(" ").length;    
    console.log(spaceCount);
    if (spaceCount > 1){
        PF('wbtnRegistrar').enable();
    } else{
        PF('wbtnRegistrar').disable();
    }    
}


/**
 *Aula.java
 *@author Laura Lozano
 *@version 1.0
 */

package tema2.ejercicio7;

import java.util.ArrayList;
import java.util.List;

/**
 *  @descrition Clase que describe un Aula de Alumnos para los ejercicios
 *	@author Laura
 *  @date 26/3/2015
 *  @version 1.0
 *  @license GPLv3
 */
public class Aula {
    private List<Alumno> alumnos;
    

    /**
     * Constructor del Almac�n con un determinado tamano
     * @param tamano
     */
    public Aula(){
      
    }
    
    public Aula(int tamano){
        alumnos=new ArrayList<Alumno>(tamano);
      
    }
   
    /**
     * Anade un nuevo elemento al almac�n si hay sitio
     * @param valor a anadir al almac�n
     */
    public void add(Alumno alumno){        
            alumnos.add(alumno);            
    }
    /**
     * Elimina un elemento del almac�n si est� en el almacen
     * @param valor
     * @return true si elimina el elemento, false en caso contrario
     */
    public boolean eliminar(Alumno alumno){
    	return alumnos.remove(alumno);
        
    }
   
   
    /**
     * Imprime por pantalla los elementos del almac�n
     */
    public void informacionAlumnos(){
        System.out.println("El aula tiene los siguientes alumnos:");
        for (int j=0;j<alumnos.size();j++){
             System.out.println(alumnos.get(j).toString()+" ");
         }
    }
}

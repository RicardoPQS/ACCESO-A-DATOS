package pruebasJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Apartado2_2 {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try (FileReader reader = new FileReader("json.txt")) {
            // Deserializamos el JSON al objeto Empleado
            Empleado empleado = gson.fromJson(reader, Empleado.class);
            
            // Mostramos el objeto para verificar los datos
            System.out.println("ID: " + empleado.getId());
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Título: " + empleado.getTitulo());
            System.out.println("Activo: " + empleado.isActivo());
            System.out.println("Número de Empleado: " + empleado.getNumeroEmpl());
            System.out.println("Fecha de Alta: " + empleado.getFechaAlta());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

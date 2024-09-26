package properties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Prueba3 {

    public static void main(String[] args) {
        List<Coche> coches = new ArrayList<>();
        coches.add(new Coche("2920MJO", 0, 150000));
        coches.add(new Coche("2324GTR", 15000, 257000));
        coches.add(new Coche("1443GTY", 0, 150000));

        // Guardar la lista de coches en un archivo CSV
        guardarCochesEnCSV(coches, "cochesSer.csv");

        // Leer la lista de coches desde un archivo CSV
        List<Coche> cochesLeidos = leerCochesDesdeCSV("cochesSer.csv");
        for (Coche coche : cochesLeidos) {
            System.out.println(coche);
        }
    }

    // Método para guardar la lista de coches en un archivo CSV
    public static void guardarCochesEnCSV(List<Coche> coches, String nombreArchivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cochesSer.csv"))) {
            // Escribir los encabezados del CSV
            out.writeObject(coches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer la lista de coches desde un archivo CSV
    public static List<Coche> leerCochesDesdeCSV(String nombreArchivo) {
        List<Coche> coches = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("cochesSer.csv"))) {
            String linea;
            // Omitimos la primera línea de encabezados

            coches = (List<Coche>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return coches;
    }
}

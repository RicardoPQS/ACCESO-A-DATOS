package properties;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Prueba2 {

    public static void main(String[] args) {
        List<Coche> coches = new ArrayList<>();
        coches.add(new Coche("2920MJO", 0, 150000));
        coches.add(new Coche("2324GTR", 15000, 257000));
        coches.add(new Coche("1443GTY", 0, 150000));

        // Guardar la lista de coches en un archivo CSV
        guardarCochesEnCSV(coches, "coches.csv");

        // Leer la lista de coches desde un archivo CSV
        List<Coche> cochesLeidos = leerCochesDesdeCSV("coches.csv");
        for (Coche coche : cochesLeidos) {
            System.out.println(coche);
        }
    }

    // Método para guardar la lista de coches en un archivo CSV
    public static void guardarCochesEnCSV(List<Coche> coches, String nombreArchivo) {
        try (PrintWriter pw = new PrintWriter("coches.csv")) {
            // Escribir los encabezados del CSV
            pw.println("Matricula,Kilometraje,Precio");
            for (Coche coche : coches) {
                pw.println(coche.aCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer la lista de coches desde un archivo CSV
    public static List<Coche> leerCochesDesdeCSV(String nombreArchivo) {
        List<Coche> coches = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("coches.csv"))) {
            String linea;
            // Omitimos la primera línea de encabezados
            br.readLine();
            while ((linea = br.readLine()) != null) {
                Coche coche = Coche.paraCSV(linea);
                coches.add(coche);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coches;
    }
}

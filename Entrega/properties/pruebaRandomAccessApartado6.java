package properties;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class pruebaRandomAccessApartado6 {

    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>(20);
        cambioDePosiciones(lista);
    }

    // Método para leer el archivo binario y cargarlo en la lista
    public static void leer(List<Integer> lista) {
        lista.clear(); // Limpiamos la lista para evitar duplicados
        try (DataInputStream in = new DataInputStream(new FileInputStream("datos.bin"))) {
            while (in.available() > 0) {
                lista.add(in.readInt());
            }
            // Si la lista tiene menos de 20 elementos, rellenamos con ceros
            while (lista.size() < 20) {
                lista.add(0);
            }

        } catch (IOException e) {
            // Si no existe el archivo, inicializar con 20 ceros
            for (int i = 0; i < 20; i++) {
                lista.add(0);
            }
            System.out.println("Archivo no encontrado. Lista inicializada con ceros.");
        }
    }

    // Método para modificar solo una posición en el archivo
    public static void guardarPosicion(int posicion, int valor) {
        try (RandomAccessFile archivo = new RandomAccessFile("datos.bin", "rw")) {
            // Posicionarse en el byte correspondiente a la posición deseada
            archivo.seek(posicion * 4); // Cada int ocupa 4 bytes
            archivo.writeInt(valor); // Escribimos el nuevo valor en esa posición
            System.out.println("Valor " + valor + " guardado en la posición " + posicion + " del archivo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para permitir al usuario modificar valores de la lista
    public static void cambioDePosiciones(List<Integer> lista) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            // Leer y mostrar la lista antes de modificar
            leer(lista);

            System.out.print("Digite posición a modificar (o un número negativo para salir): ");
            int posicion = sc.nextInt();

            if (posicion < 0) {
                salir = true;
                System.out.println("Saliendo...");
            } else if (posicion >= 0 && posicion < lista.size()) {
                System.out.print("Digite nuevo valor: ");
                int valor = sc.nextInt();

                lista.set(posicion, valor);

                // Guardar solo la posición modificada en el archivo
                guardarPosicion(posicion, valor);
                
                System.out.println("Lista después de la modificación:");
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println("Posición " + i + ": " + lista.get(i));
                }
            } else {
                System.out.println("Posición inválida. Por favor, ingrese una posición entre 0 y " + (lista.size() - 1));
            }
        }

        sc.close();
    }
}

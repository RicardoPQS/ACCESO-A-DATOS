package properties;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class pruebaRandoAccessFile {

	public static void main(String[] args) {

		List<Integer> lista = new ArrayList<Integer>(20);
		cambioDePosiciones(lista);
	}

	public static void leer(List<Integer> lista) {
		//borra el contenido de la lista para luego leer el archivo actualizado con el nuevo valor y evitamos duplicidad
		lista.clear();
		try (DataInputStream in = new DataInputStream(new FileInputStream("datos.bin"));
				RandomAccessFile rnd = new RandomAccessFile("datos.bin", "rwd")) {

			rnd.seek(0);
			rnd.writeInt(999);

			while (in.available() > 0) {
				lista.add(in.readInt());
			}

			while (lista.size() < 20) {
				lista.add(0);
			}

			for (int i = 0; i < lista.size(); i++) {
				System.out.println("Posición " + i + ": " + lista.get(i));
			}

		} catch (IOException e) {
			for (int i = 0; i < 20; i++) {
				lista.add(0);
			}
			System.out.println("no se encuentra");

		}

	}

	public static void guardar(List<Integer> lista) {

		try (DataOutputStream out = new DataOutputStream(new FileOutputStream("datos.bin"))) {

			for (int i = 0; i < lista.size(); i++) {
				out.writeInt(lista.get(i));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cambioDePosiciones(List<Integer> lista) {
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			leer(lista);
			System.out.print("digite posición a modificar: ");
			int posicion = sc.nextInt();
			if (posicion < 0) {
				salir = true;
				System.out.println("saliendo...");
			} else if (posicion >= 0 && posicion < lista.size()) {
				System.out.print("Digite valor nuevo: ");
				int valor = sc.nextInt();
				lista.set(posicion, valor); // Modificamos la lista en memoria
				guardar(lista); // Guardamos la lista modificada en el archivo

				// Imprimir la lista actualizada
				System.out.println("Lista después de la modificación:");
				for (int i = 0; i < lista.size(); i++) {
					System.out.println("Posición " + i + ": " + lista.get(i));
				}
			} else {
				System.out.println("Posición inválida. Por favor, ingrese una posición entre 0 y " + (lista.size() - 1));
			}

		}

	}
}

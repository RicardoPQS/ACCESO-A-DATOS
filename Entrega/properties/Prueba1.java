package properties;

import java.io.*;
import java.util.Properties;

public class Prueba1 {

	public static void main(String[] args) {

		escribir();
		leer();

	}

	public static void escribir() {
		Properties p = new Properties();
		p.setProperty("1", "a");
		p.setProperty("2", "b");
		p.setProperty("3", "c");

		try (FileOutputStream out = new FileOutputStream("fichero1.txt");
				FileOutputStream out2 = new FileOutputStream("ficheroXML.xml")) {

			p.store(out, null);
			p.storeToXML(out2, null);

			System.out.println("correcto");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void leer() {
		
		Properties p1 = new Properties();
		Properties p2 = new Properties();
		
		
		try (FileInputStream in = new FileInputStream("fichero1.txt");
				FileInputStream in2 = new FileInputStream("ficheroXML.xml")) {

			p1.load(in);
			p2.loadFromXML(in2);
			
			System.out.println("contenido de fichero de texto: "+p1);
			System.out.println("contenido de fichero XML: "+p2);
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	

}

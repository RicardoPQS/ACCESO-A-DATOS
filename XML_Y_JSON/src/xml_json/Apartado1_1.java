package xml_json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;



public class Apartado1_1 {
	public static void main(String[] args) {
		
		System.out.println("APARTADO 1");
		System.out.println();
		apartado1_1();
	}
	
	public static void apartado1_1() {
		guardarCoche();
		Coche coche = leerCoche();
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		System.out.println(x.toXML(coche));
	}
	
	public static void guardarCoche() {
		Coche coche = new Coche("1234ABC", 12000.5, 15000.0);
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		String xml = x.toXML(coche);
		x.alias("coche", Coche.class);

		try (PrintWriter pw = new PrintWriter("XStream.txt")) {
			pw.print(xml);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Coche leerCoche() {
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		Coche coche = null;

		try (BufferedReader bf = new BufferedReader(new FileReader("XStream.txt"))) {
			String linea;
			String contenido = "";

			while ((linea = bf.readLine()) != null) {
				contenido += linea + "\n";
			}

			coche =(Coche)x.fromXML(contenido);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return coche;
	}
}

package xml_json;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;


public class Apartado1_2 {
	public static void main(String[] args) {

		System.out.println("APARTADO 2");
		System.out.println();
		apartado1_2();
	}
	
	
	public static void apartado1_2() {
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		Coche coche = null;

		try (BufferedReader bf = new BufferedReader(new FileReader("XStream.txt"))) {
			String linea;
			String contenido = "";

			while ((linea = bf.readLine()) != null) {
				contenido += linea + "\n";
			}

			coche=(Coche)x.fromXML(contenido);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		x.alias("cocheCambiado", Coche.class);
		System.out.println(x.toXML(coche));
	}
	
	
}

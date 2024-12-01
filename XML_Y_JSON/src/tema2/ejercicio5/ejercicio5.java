package tema2.ejercicio5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import tema2.ejercicio4.Persona;
import tema2.ejercicio4.Telefono;

public class ejercicio5 {

	public static void main(String[] args) {
		guardar();
		leer();
	}

	private static void guardar() {
		Telefono tel = new Telefono();
		tel.setCodigo(34);
		tel.setNumero(666666666);

		Persona per = new Persona();
		per.setApellido("Pepe");
		per.setNombre("Garcia");
		per.setTelefono(tel);

		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);

		
		try(OutputStream out = new FileOutputStream("Persona.xml")){
			x.toXML(per, out);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void leer() {
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		
		try(InputStream in = new FileInputStream("Persona.xml")){

			Persona persona = (Persona) x.fromXML(in);
			System.out.println(x.toXML(persona));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}

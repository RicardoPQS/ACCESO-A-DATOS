package tema2.ejercicio2;

import java.io.File;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;
import com.thoughtworks.xstream.security.AnyTypePermission;


import java.io.*;

public class Ejercicio2 {

	public static void main(String[] args) {
		formaA();
		fromaBGuardar();
		formaBLeer();
	}

	private static void formaBLeer() {
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		
		try(InputStream in = new FileInputStream("cafe.xml")){

			Cafe cafe = (Cafe) x.fromXML(in);
			System.out.println(x.toXML(cafe));

			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void fromaBGuardar() {
		Cafe cafe = new Cafe();

		cafe.setNombre("peruano");
		cafe.setPrecio(500);
		cafe.setProveedorId(1);
		cafe.setTotal(1500);
		cafe.setVentas(5);

		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		try(OutputStream out = new FileOutputStream("cafe.xml")){
			x.toXML(cafe, out);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void formaA() {
		
		try {
	        XStream x = new XStream(new DomDriver());
	        x.addPermission(AnyTypePermission.ANY);
	        
	        PersistenceStrategy strategy = new FilePersistenceStrategy(new File("xmlPersistenteCafe"), x);
	        XmlArrayList lista = new XmlArrayList(strategy);
	        
	        Cafe cafe = new Cafe();
	        cafe.setNombre("late");
	        cafe.setPrecio(500);
	        cafe.setProveedorId(1);
	        cafe.setTotal(1500);
	        cafe.setVentas(5);
	        
	        lista.add(cafe);
	        System.out.println(cafe);
	        
	        for (Iterator it = lista.iterator(); it.hasNext(); ) {
	            Cafe cafe2 = (Cafe) it.next();
	            System.out.println(cafe2);
	        }
	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	
	}

}

package tema2.ejercicio6;

import java.io.File;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;
import com.thoughtworks.xstream.security.AnyTypePermission;


public class ejercicio6 {

	public static void main(String[] args) {

		ejercicio6();

	}

	private static void ejercicio6() {
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		
		Libro libro = new Libro(123456789, "El Quijote", "Editorial Planeta", 864, "Miguel de Cervantes", 10);

		x.alias("ejemplar", Libro.class);
		System.out.println(x.toXML(libro));

		try {
			//esto en windows
			String userHome = System.getProperty("user.home");
			File documentos = new File(userHome, "Documents");

			//para guardarlo en documentos en esta carpeta deberemos crearla manualmente
			PersistenceStrategy strategy = new FilePersistenceStrategy(new File(documentos, "xmlPersistente2"), x);
			XmlArrayList lista = new XmlArrayList(strategy);
			
			lista.add(libro);

			// RESTAURAMOS
			for (Iterator it = lista.iterator(); it.hasNext();) {
				Libro libro2 = (Libro) it.next();
				System.out.println(libro2);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}

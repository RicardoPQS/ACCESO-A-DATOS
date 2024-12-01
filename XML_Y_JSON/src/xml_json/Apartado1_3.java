package xml_json;

import java.io.File;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

public class Apartado1_3 {
	public static void main(String[] args) {

		System.out.println("APARTADO 3");
		System.out.println();
		apartado1_3();
	}

	public static void apartado1_3() {
		// ACTIVIDAD 13 DEL DOCUMENTO DE LAS TRANSPARENCIAS DE LAURA
		try {

			XStream x = new XStream(new DomDriver());
			x.addPermission(AnyTypePermission.ANY);

			PersistenceStrategy strategy = new FilePersistenceStrategy(new File("xmlPersistente"), x);
			XmlArrayList lista = new XmlArrayList(strategy);
			lista.add(new Coche("1234ABC", 12000, 15000));
			lista.add(new Coche("5678DEF", 80000, 7500));
			lista.add(new Coche("9012GHI", 2500, 22000));

			if (!lista.isEmpty()) {
				// Si la lista no está vacía, entonces sí iteramos y mostramos los coches
				for (Iterator it = lista.iterator(); it.hasNext();) {
					Coche coche = (Coche) it.next();
					System.out.println(coche);
				}
			} else {
				// Si la lista está vacía, mostramos un mensaje indicando que no hay coches
				// guardados
				System.out.println("No se encontraron coches guardados en el archivo.");
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

}

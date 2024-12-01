package tema2.ejercicio8;

import java.io.File;
import java.util.Iterator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;
import com.thoughtworks.xstream.security.AnyTypePermission;

import tema2.ejercicio7.*;

public class ejercicio8 {

	public static void main(String[] args) {
		try {
	        XStream x = new XStream(new DomDriver());
	        x.addPermission(AnyTypePermission.ANY);
	        
	        PersistenceStrategy strategy = new FilePersistenceStrategy(new File("xmlPersistenteAula"), x);
	        XmlArrayList lista = new XmlArrayList(strategy);
	        Alumno al = new Alumno("pepe", "garcia", 1986, "rosario", 8);
			Alumno al2 = new Alumno("juan", "lopez", 1991, "miranda", 7);
			Aula aula = new Aula(2);
			aula.add(al);
			aula.add(al2);
	        lista.add(aula);
	        
	        for (Iterator it = lista.iterator(); it.hasNext(); ) {
	            Aula aula2 = (Aula) it.next();
	            System.out.println(aula2);
	        }
	    } catch (Exception e) {
	        System.err.println("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
	

	}

}

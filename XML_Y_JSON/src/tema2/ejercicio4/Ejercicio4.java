package tema2.ejercicio4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;


public class Ejercicio4 {

	public static void main(String[] args) {
		alias();
	}

	private static void alias() {
		Telefono tel = new Telefono();
		tel.setCodigo(34);
		tel.setNumero(666666666);
		
		Persona per = new Persona();
		per.setApellido("Pepe");
		per.setNombre("Garcia");
		per.setTelefono(tel);
		
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);

		x.alias("persona", Persona.class);
		x.aliasField("prefijo", Telefono.class, "codigo");
		
		System.out.println(x.toXML(per));
	}

}

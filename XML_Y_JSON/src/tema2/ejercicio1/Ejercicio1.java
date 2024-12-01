package tema2.ejercicio1;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import xml_json.Coche;

public class Ejercicio1 {

	public static void main(String[] args) {
		Cafe cafe = new Cafe();

		cafe.setNombre("late");
		cafe.setPrecio(500);
		cafe.setProveedorId(1);
		cafe.setTotal(1500);
		cafe.setVentas(5);

		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);

		x.omitField(Cafe.class, "proveedorId");

		x.alias("cafe", Cafe.class);
		x.aliasField("marca", Cafe.class, "nombre");
		System.out.println(x.toXML(cafe));

	}

}

package tema2_ejercicios2.ejercicio1;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		Proveedor proveedor = new Proveedor();

		Cafe cafe = new Cafe();
		Cafe cafe2 = new Cafe();
		
		
		cafe.setNombre("late");
		cafe.setPrecio(500);
		cafe.setProveedorId(1);
		cafe.setTotal(1500);
		cafe.setVentas(5);

		cafe2.setNombre("manchado");
		cafe2.setPrecio(5020);
		cafe2.setProveedorId(1);
		cafe2.setTotal(15200);
		cafe2.setVentas(52);

		
		// Configurar los datos del proveedor
		proveedor.setIdentificador(1);
		proveedor.setNombre("Café Supremo");
		proveedor.setCalle("Calle Principal 123");
		proveedor.setCiudad("Ciudad Café");
		proveedor.setPais("Cafetería");
		proveedor.setCp(12345);
		proveedor.setEsNacional(true);
		proveedor.setCif("150");
		proveedor.setEmpresa("mi empresa");
		

		
		proveedor.addCafe(cafe);
		proveedor.addCafe(cafe2);

		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);
		
		x.alias("proveedor", Proveedor.class);
		x.alias("cafe", Cafe.class);
		x.aliasAttribute(Proveedor.class, "cif", "cif");
		x.aliasAttribute(Proveedor.class, "empresa", "empresa");
		System.out.println(x.toXML(proveedor));

	}

}

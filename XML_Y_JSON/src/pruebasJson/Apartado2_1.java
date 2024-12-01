package pruebasJson;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class Apartado2_1 {

	public static void main(String[] args) {
		guardarJson();
	}

	private static void guardarJson() {
		Empleado empleado = new Empleado();

		empleado.setId(1);
		empleado.setNombre("Laura");
		empleado.setTitulo("Desarrolladora Senior");
		empleado.setActivo(true);
		empleado.setNumeroEmpl(1001);
		empleado.setFechaAlta(new Date());

		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("empleado", Empleado.class);

		String json = xstream.toXML(empleado);

		try (PrintWriter pw = new PrintWriter("json.txt")) {
			pw.print(json);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

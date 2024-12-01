package tema2.ejercicio7;

import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import tema2.ejercicio1.Cafe;
import tema2.ejercicio4.Persona;
import tema2.ejercicio4.Telefono;

public class ejercicio7 {

	public static void main(String[] args) {
		sinEntradaEstandar();
		System.out.println();
		conEntradaEstandar();
	}

	private static void conEntradaEstandar() {
		Scanner sc = new Scanner(System.in);

		Aula aula = new Aula(2);
		for (int i = 0; i < 2; i++) {
			System.out.println("nombre: ");
			String nombre = sc.nextLine();
			System.out.println("apellido: ");
			String apellido = sc.nextLine();
			System.out.println("fecha nacimiento: ");
			int fecha_nac = sc.nextInt();
			System.out.println("calle: ");
			String calle = sc.nextLine();
			sc.nextLine();
			System.out.println("numero: ");
			int numero = sc.nextInt();
			System.out.println("alumno creado");
			aula.add(new Alumno(nombre, apellido, fecha_nac, calle, numero));
			sc.nextLine();
		}
		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);

		x.alias("aula", Aula.class);
		x.alias("alumno", Alumno.class);
		x.aliasField("domicilio", Alumno.class, "direccion");
		System.out.println(x.toXML(aula));
	}

	private static void sinEntradaEstandar() {
		Alumno al = new Alumno("pepe", "garcia", 1986, "rosario", 8);
		Alumno al2 = new Alumno("juan", "lopez", 1991, "miranda", 7);
		Aula aula = new Aula(2);
		aula.add(al);
		aula.add(al2);

		XStream x = new XStream(new DomDriver());
		x.addPermission(AnyTypePermission.ANY);

		x.alias("aula", Aula.class);
		x.alias("alumno", Alumno.class);
		x.aliasField("domicilio", Alumno.class, "direccion");
		System.out.println(x.toXML(aula));
	}

}

package org.apache.maven.hibernate202501;

import database.Continents;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        ContinentsRepositorio repositorio = new ContinentsRepositorio(sessionFactory);

        // 1. Nombres de continentes
        System.out.println("Consulta 1 - Listado de Continentes:");
        List<String> nombresContinentes = repositorio.obtenerNombresContinentes();
        nombresContinentes.forEach(nombre -> System.out.println("Continent: " + nombre));

        // 2. Filtrar por nombre "Asia"
        System.out.println("\nConsulta 2 - Continentes con nombre 'Asia':");
        List<Continents> asia = repositorio.buscarPorNombre("Asia");
        asia.forEach(continent -> System.out.println("Continent: " + continent.getName()));

        // 3. Continentes ordenados
        System.out.println("\nConsulta 3 - Continentes ordenados:");
        List<Continents> continentesOrdenados = repositorio.obtenerContinentesOrdenados();
        continentesOrdenados.forEach(continent -> System.out.println("Continent: " + continent.getName()));

        // 4. Continentes cuyo nombre empieza con "A"
        System.out.println("\nConsulta 4 - Continentes que empiezan con 'A':");
        List<Continents> continentesPorA = repositorio.buscarPorInicial("A");
        continentesPorA.forEach(continent -> System.out.println("Continent: " + continent.getName()));

        // 5
        System.out.println("\nConsulta 5");
        

        // 6
        System.out.println("\nConsulta 6 ");
        

        // 7. Continentes con sus Regiones
        System.out.println("\nConsulta 7 - Continentes y sus Regiones:");
        List<Object[]> continetntesYRegiones = repositorio.obtenerContinentesConRegiones();
        continetntesYRegiones.forEach(result -> System.out.println(result[0] + " - Region: " + result[1]));

        sessionFactory.close();
    }
}

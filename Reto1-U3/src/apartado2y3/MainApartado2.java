package apartado2y3;

import java.util.Arrays;
import java.util.Scanner;

public class MainApartado2 {
    public static void main(String[] args) {
        ContinenteDAO dao = new ContinenteDAO();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n---- Menú CRUD ----");
            System.out.println("1. Crear Continente con Regiones");
            System.out.println("2. Leer Continente");
            System.out.println("3. Actualizar Continente");
            System.out.println("4. Eliminar Continente");
            System.out.println("5. Agregar Región a un Continente");
            System.out.println("6. Eliminar Región");
            System.out.println("0. Salir");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> crearContinenteConRegiones(dao);
                case 2 -> leerContinente(dao);
                case 3 -> actualizarContinente(dao);
                case 4 -> eliminarContinente(dao);
                case 5 -> agregarRegion(dao);
                case 6 -> eliminarRegion(dao);
                case 0 -> {
                    sc.close();
                    System.out.println("ADIOS");
                    salir= true;
                }
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void crearContinenteConRegiones(ContinenteDAO dao) {
        Continente europa = new Continente("Europa");
        Region occidental = new Region("Europa Occidental", europa);
        Region este = new Region("Europa del Este", europa);
        europa.setRegiones(Arrays.asList(occidental, este));
        dao.crearContinente(europa);
        System.out.println("Continente y regiones creados exitosamente.");
    }

    private static void leerContinente(ContinenteDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del continente a leer: ");
        int id = sc.nextInt();
        Continente continente = dao.leerContinente(id);
        if (continente != null) {
            System.out.println("Continente: " + continente.getNombre());
            System.out.println("Regiones:");
            for (Region region : continente.getRegiones()) {
                System.out.println("- " + region.getNombre());
            }
        } else {
            System.out.println("No se encontró un continente con ese ID.");
        }
    }

    private static void actualizarContinente(ContinenteDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del continente a actualizar: ");
        int id = sc.nextInt();
        Continente continente = dao.leerContinente(id);
        if (continente != null) {
            System.out.print("Nuevo nombre para el continente: ");
            sc.nextLine();
            String nuevoNombre = sc.nextLine();
            continente.setNombre(nuevoNombre);
            dao.actualizarContinente(continente);
            System.out.println("Continente actualizado correctamente.");
        } else {
            System.out.println("No se encontró un continente con ese ID.");
        }
    }

    private static void eliminarContinente(ContinenteDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del continente a eliminar: ");
        int id = sc.nextInt();
        dao.eliminarContinente(id);
        System.out.println("Continente eliminado correctamente.");
    }

    private static void agregarRegion(ContinenteDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID del continente al que desea agregar una región: ");
        int continenteId = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre de la nueva región: ");
        String nombreRegion = sc.nextLine();
        Region region = new Region(nombreRegion, null);
        dao.agregarRegion(continenteId, region);
        System.out.println("Región agregada correctamente.");
    }

    private static void eliminarRegion(ContinenteDAO dao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el ID de la región a eliminar: ");
        int regionId = sc.nextInt();
        dao.eliminarRegion(regionId);
        System.out.println("Región eliminada correctamente.");
    }
}

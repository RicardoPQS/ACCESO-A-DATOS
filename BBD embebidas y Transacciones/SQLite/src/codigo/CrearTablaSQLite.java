package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablaSQLite {

    public static void main(String[] args) {
        final String urlBD = "jdbc:sqlite:/home/alumno/bbdd/sqlite/contadores.db"; // Ruta válida a la base de datos SQLite

        try (Connection connection = DriverManager.getConnection(urlBD);
             Statement statement = connection.createStatement()) {

            // Crear la tabla 'contadores' si no existe
            String crearTablaSQL = "CREATE TABLE IF NOT EXISTS contadores (" +
                                   "nombre TEXT PRIMARY KEY, " +
                                   "cuenta INTEGER DEFAULT 0);";
            statement.execute(crearTablaSQL);

            // Insertar el contador inicial solo si no existe
            String insertarSQL = "INSERT OR IGNORE INTO contadores (nombre, cuenta) VALUES ('contador1', 0);";
            statement.executeUpdate(insertarSQL);
            System.out.println("Creado");

        } catch (SQLException e) {
            System.out.println("Error al crear la tabla o insertar el contador: " + e.getMessage());
        }
    }
}

package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Eliminar {
    public static void main(String[] args) throws SQLException {
        // Conexión a la base de datos H2 en el archivo especificado
        Connection connection = DriverManager.getConnection("jdbc:h2:/home/alumno/bbdd/h2/contadores");
        Statement statement = connection.createStatement();
        
        try {
            // Eliminar la tabla 'contadores' si ya existe
            statement.execute("DROP TABLE IF EXISTS contadores;");
            System.out.println("Tabla 'contadores' eliminada.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos para evitar fugas de memoria
            statement.close();
            connection.close();
        }
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContadorEnBDsoloUpdateSql_SQLITE {

    public static void main(String[] args) {
        final String claveContador = "contador1";
        // La actualización en el propio SQL sí es atómica:
        final String sqlActualización = "UPDATE contador SET cuenta=cuenta+1 WHERE nombre=?;";

        try {
            // Usar el controlador correcto para SQLite
            Class.forName("org.sqlite.JDBC");  
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/alumno/bbdd/sqlite/contadores.db");

            PreparedStatement actualización = connection.prepareStatement(sqlActualización);
            
            actualización.setString(1, claveContador);
            
            // Ejecutar la actualización 1000 veces
            for (int i = 0; i < 1000; i++) {
                int filasAfectadas = actualización.executeUpdate();
                
                // Si no se actualizó ninguna fila, significa que no se encontró el contador con ese nombre
                if (filasAfectadas != 1) {
                    System.out.println("Error: No se encontró el contador con nombre '" + claveContador + "'");
                    break;
                }
            }

            System.out.println("Actualización completada.");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

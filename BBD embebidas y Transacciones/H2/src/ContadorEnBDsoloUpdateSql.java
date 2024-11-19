import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContadorEnBDsoloUpdateSql {

    public static void main(String[] args) {
        final String claveContador = "contador1";
        // La actualización en el propio SQL sí es atómica:
        final String sqlActualización = "UPDATE contadores SET cuenta=cuenta+1 WHERE nombre='" + claveContador + "';";

        try {
            // Conexión a la base de datos H2
            Connection connection = DriverManager.getConnection(
                    "jdbc:h2:/home/alumno/bbdd/h2/contadores");  
            
            PreparedStatement actualización = connection.prepareStatement(sqlActualización);
            for (int i = 0; i < 1000; i++) {
                if (actualización.executeUpdate() != 1) break;
            }
            System.out.println("Actualización completada.");

            actualización.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // main
} // class

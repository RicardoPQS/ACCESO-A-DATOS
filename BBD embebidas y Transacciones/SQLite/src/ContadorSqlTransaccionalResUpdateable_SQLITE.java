import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContadorSqlTransaccionalResUpdateable_SQLITE {

    public static void main(String[] args) throws ClassNotFoundException {
        // Prueba de concepto de transacción con bloqueo de fila para lectura
        // Sería más fácil en el propio sql poner un set cuenta=cuenta+1 pero ilustramos
        // aquí el problema de concurrencia entre varios procesos.
        // con el for update + transacción conseguimos el bloque de fila y atomicidad
        String sqlConsulta = "SELECT nombre, cuenta FROM contadores WHERE nombre='contador1';";
        String sqlActualiza = "UPDATE contadores SET cuenta = cuenta + 1 WHERE nombre='contador1';";

        Class.forName("org.sqlite.JDBC");

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/alumno/bbdd/sqlite/contadores.db")) {
            connection.setAutoCommit(false); // Iniciar la transacción

            // Aquí usamos solo la consulta SELECT para leer el valor
            PreparedStatement consulta = connection.prepareStatement(sqlConsulta);
            PreparedStatement actualiza = connection.prepareStatement(sqlActualiza);

            for (int i = 0; i < 1000; i++) {
                // Comenzar la transacción
                connection.setAutoCommit(false);
                // Ejecutar la consulta para leer el valor
                var resultSet = consulta.executeQuery();
                if (resultSet.next()) {
                    int cuenta = resultSet.getInt("cuenta");
                    cuenta++; // Incrementar el valor
                } else {
                    System.out.println("No se encontró el contador con nombre 'contador1'");
                    break;
                }

                // Ejecutar la actualización para incrementar la cuenta
                actualiza.executeUpdate();
                connection.commit(); // Hacer commit para hacer efectiva la actualización
            }

            System.out.println("Proceso completado.");

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

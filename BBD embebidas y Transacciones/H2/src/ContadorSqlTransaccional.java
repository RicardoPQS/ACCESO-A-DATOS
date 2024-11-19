import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContadorSqlTransaccional {

    public static void main(String[] args) {
        String sqlConsulta = "SELECT nombre, cuenta FROM contadores WHERE nombre='contador1' FOR UPDATE;";
        String sqlActualización = "UPDATE contadores SET cuenta=? WHERE nombre='contador1';";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:h2:/home/alumno/bbdd/h2/contadores")) {

            // Desactivar auto-commit para manejar manualmente las transacciones
            connection.setAutoCommit(false);

            // Preparar las sentencias SQL
            PreparedStatement consulta = connection.prepareStatement(sqlConsulta);
            PreparedStatement actualización = connection.prepareStatement(sqlActualización);
            int cuenta = 0;

            for (int i = 0; i < 1000; i++) {
                try {
                    // Ejecutar la consulta con bloqueo de fila
                    ResultSet res = consulta.executeQuery();
                    if (res.next()) {
                        cuenta = res.getInt("cuenta") + 1;
                        actualización.setInt(1, cuenta);
                        actualización.executeUpdate();
                    } else {
                        System.out.println("No se encontró el registro 'contador1'.");
                        break;
                    }

                    // Confirmar la transacción
                    connection.commit();
                    res.close(); // Cerrar el ResultSet después de cada iteración

                } catch (SQLException e) {
                    System.out.println("Error en la transacción, se hará rollback.");
                    connection.rollback();
                    e.printStackTrace();
                }
            }

            System.out.println("Valor final: " + cuenta);

            // Cerrar las sentencias
            consulta.close();
            actualización.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // main
} // class ContadorSqlTransaccional

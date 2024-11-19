import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContadorEnBDUpdatable {

    public static void main(String[] args) {
        final String claveContador = "contador1";
        final String sqlConsulta = "SELECT nombre, cuenta FROM contadores WHERE nombre = ?";

        try {
            // Conectar a la base de datos H2
            Connection connection = DriverManager.getConnection(
                    "jdbc:h2:/home/alumno/bbdd/h2/contadores");

            // Preparar la consulta con ResultSet actualizable
            PreparedStatement consulta = connection.prepareStatement(
                    sqlConsulta,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            consulta.setString(1, claveContador);
            int cuenta = 0;

            for (int i = 0; i < 1000; i++) {
                // Ejecutar la consulta
                if (consulta.execute()) {
                    ResultSet res = consulta.getResultSet();
                    if (res.next()) {
                        cuenta = res.getInt("cuenta") + 1;
                        res.updateInt("cuenta", cuenta);
                        res.updateRow(); // Actualizar la fila en la base de datos
                    } else {
                        System.out.println("Error: No se encontró el registro con nombre '" + claveContador + "'.");
                        break;
                    }
                } else {
                    System.out.println("Error al ejecutar la consulta.");
                    break;
                }
            }

            System.out.println("Valor final: " + cuenta);

            // Cerrar recursos
            consulta.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // main

} // class

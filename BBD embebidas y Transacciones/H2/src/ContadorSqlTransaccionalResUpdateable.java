import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContadorSqlTransaccionalResUpdateable {

    public static void main(String[] args) {
        String sqlConsulta = "SELECT nombre, cuenta FROM contadores WHERE nombre='contador1' FOR UPDATE";

        try (Connection connection = DriverManager.getConnection("jdbc:h2:/home/alumno/bbdd/h2/contadores")) {

            // Desactivar auto-commit al inicio para manejar manualmente las transacciones
            connection.setAutoCommit(false);

            // Preparar la consulta actualizable
            PreparedStatement consulta = connection.prepareStatement(sqlConsulta,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int cuenta = 0;

            for (int i = 0; i < 1000; i++) {
                try {
                    ResultSet res = consulta.executeQuery();
                    if (res.next()) {
                        cuenta = res.getInt("cuenta") + 1;
                        res.updateInt("cuenta", cuenta);  // Actualiza el valor de la cuenta
                        res.updateRow();  // Aplica el cambio en la base de datos
                    } else {
                        System.out.println("Registro 'contador1' no encontrado.");
                        break;
                    }

                    // Confirmar transacción
                    connection.commit();
                    res.close(); // Cerrar el ResultSet al final de cada iteración

                } catch (SQLException e) {
                    System.out.println("Error en la transacción, realizando rollback.");
                    connection.rollback(); // Revertir transacción en caso de error
                    e.printStackTrace();
                }
            }

            System.out.println("Valor final: " + cuenta);

            // Cerrar recursos
            consulta.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // main
} // class ContadorSqlTransaccionalResUpdateable

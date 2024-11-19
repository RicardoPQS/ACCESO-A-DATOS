package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Crear {
	public static void main(String[] args) throws SQLException {
		// 
		Connection connection = DriverManager.getConnection("jdbc:h2:/home/alumno/bbdd/h2/contadores");
		Statement statement = connection.createStatement();
		try {
			statement.execute("CREATE TABLE IF NOT EXISTS contadores(nombre varchar(10) primary key, cuenta int)");
			
			String insertSQL = "insert into contadores(nombre,cuenta) values (?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
			
			insertStatement.setString(1, "contador1");
			insertStatement.setInt(2, 0);
			int rowInserted = insertStatement.executeUpdate();
			System.out.println("Filas: " +rowInserted);
			
			insertStatement.close();
			
	
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			statement.close();
			connection.close();
		}
	}
}

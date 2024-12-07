package apartado1.Model;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JOptionPane;

public class ModeloAlumnosJDBC implements IModeloAlumnos {

	private static String cadenaConexion = "jdbc:mysql://localhost:3306/adat1";
	private static String user = "adat1";
	private static String pass = "rpqs";

	public ModeloAlumnosJDBC() {

	}

	@Override
	
	public List<String> getAll() {
		List<String> contenido = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
			PreparedStatement consulta = con.prepareStatement("select * from alumnos;");
			ResultSet datos = consulta.executeQuery();
			
			while (datos.next()) {
	            String dni = datos.getString("dni");
	            String nombre = datos.getString("nombre");
	            String apellidos = datos.getString("apellidos");
	            String codigoPostal = datos.getString("codigo_postal");
	            
	            Alumno al = new Alumno(dni,nombre,apellidos,codigoPostal);
	            
	            contenido.add(al.toString());
	        }
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return contenido;

	}

	@Override

	public Alumno getAlumnoPorDNI(String DNI) {
		Alumno al = null;
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);) {
			PreparedStatement consulta = con.prepareStatement("select * from alumnos where dni = '" + DNI + "';");
			ResultSet datos = consulta.executeQuery();
			
			if (datos.next()) {
	            String dni = datos.getString("dni");
	            String nombre = datos.getString("nombre");
	            String apellidos = datos.getString("apellidos");
	            String codigoPostal = datos.getString("codigo_postal");

	            al = new Alumno(dni, nombre, apellidos, codigoPostal);
	        }
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return al;

	}

	@Override

	public boolean modificarAlumno(Alumno alumno) {

		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass)) {
	        // Preparar la consulta para actualizar el alumno con el DNI especificado
	        PreparedStatement consulta = con.prepareStatement(
	            "UPDATE alumnos SET nombre = ?, apellidos = ?, codigo_postal = ? WHERE dni = ?");

	        // Establecer los valores de los parámetros en la consulta
	        consulta.setString(1, alumno.getNombre());
	        consulta.setString(2, alumno.getApellidos());
	        consulta.setString(3, alumno.getCP());
	        consulta.setString(4, alumno.getDNI());

	        // Ejecutar la actualización y comprobar el resultado
	        int resultado = consulta.executeUpdate();

	        // Si el resultado es mayor que 0, la actualización fue exitosa
	        if (resultado > 0) {
	            System.out.println("Alumno actualizado correctamente.");
	            return true;
	        } else {
	            System.out.println("No se encontró ningún alumno con el DNI especificado.");
	            return false;
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al actualizar el alumno: " + e.getMessage());
	        return false;
	    }

	}

	@Override

	public boolean eliminarAlumno(String DNI) {
	    try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass)) {
	        // Preparar la consulta de eliminación usando el DNI
	        PreparedStatement consulta = con.prepareStatement("DELETE FROM alumnos WHERE dni = ?");

	        // Establecer el valor del DNI en el parámetro de la consulta
	        consulta.setString(1, DNI);

	        // Ejecutar la eliminación y comprobar el resultado
	        int resultado = consulta.executeUpdate();

	        // Si el resultado es mayor que 0, la eliminación fue exitosa
	        if (resultado > 0) {
	            JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente", "Eliminación",
	                    JOptionPane.INFORMATION_MESSAGE);
	            return true;
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró ningún alumno con el DNI especificado", 
	                    "Eliminación", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al eliminar el alumno: " + e.getMessage(), "Error", 
	                JOptionPane.ERROR_MESSAGE);
	        System.err.println("Error al eliminar el alumno: " + e.getMessage());
	        return false;
	    }
	}

	@Override

	public boolean crear(Alumno alumno) {

		try (Connection con = DriverManager.getConnection(this.cadenaConexion, this.user, this.pass)) {

			// Preparar la consulta para insertar un nuevo alumno, omitiendo el campo 'id'

			PreparedStatement st = con.prepareStatement(
					"INSERT INTO alumnos (dni, nombre, apellidos, codigo_postal) VALUES (?, ?, ?, ?)");

			st.setString(1, alumno.getDNI());
			st.setString(2, alumno.getNombre());
			st.setString(3, alumno.getApellidos());
			st.setString(4, alumno.getCP());

			// Ejecutar la consulta
			int resultado = st.executeUpdate();

			// Verificar si la inserción fue exitosa
			if (resultado > 0) {
				JOptionPane.showMessageDialog(null, "Alumno insertado correctamente", "Inserción",
						JOptionPane.INFORMATION_MESSAGE);
				return true; // Inserción exitosa
			} else {
				return false; // No se insertó ningún registro
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en inserción", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			return false; // Error durante la inserción
		}

	}

}
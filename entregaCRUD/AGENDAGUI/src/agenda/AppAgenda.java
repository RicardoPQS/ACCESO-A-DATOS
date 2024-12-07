package agenda;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AppAgenda {
	public static String cadenaConexion = "jdbc:mysql://localhost:3306/agenda";
	public static String user = "agenda";
	public static String pass = "hola";
	public static String ficheroAgendaCSV = "agenda.csv";
	public static String ficheroAgendaSERIAL = "agenda.ser";
	protected File fichero;
	protected Agenda agenda;
	protected VistaAgenda vista;

	public AppAgenda() {
		agenda = new Agenda();
	}
	/*
	 * public AppAgenda(String fileName) { fichero = new File(fileName); try {
	 * agenda = ES_Agenda.leeAgendaDeCsv(fichero); } catch (IOException e) { agenda
	 * = new Agenda(); } } // constructor
	 */

	public void editarContacto(String nombre, String telefono) {
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass)) {
			PreparedStatement consulta = con.prepareStatement("UPDATE contactos SET telefono = ? WHERE nombre = ?");

			consulta.setString(1, telefono);
			consulta.setString(2, nombre);

			int resultado = consulta.executeUpdate();

			if (resultado > 0) {
				System.out.println("Contacto actualizado correctamente.");
			} 
			
		} catch (SQLException e) {
			System.err.println("Error al actualizar el contacto: " + e.getMessage());
		}
		agenda.getContacto(nombre).setTeléfono(telefono);
	} // editaContacto

	public Contacto borrarContacto(String nombre) {
		try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass)) {
			PreparedStatement consulta = con.prepareStatement("DELETE FROM contactos WHERE nombre = ?");
			consulta.setString(1, nombre);

			int resultado = consulta.executeUpdate();

			if (resultado > 0) {
				JOptionPane.showMessageDialog(null, "Contacto eliminado correctamente", "Eliminación",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No se encontró ningún contacto con el nombre especificado",
						"Eliminación", JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el contacto: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Error al eliminar el contacto: " + e.getMessage());
		}
		return agenda.borrarContacto(nombre);
	} // borrarContacto

	
	public Contacto añadirContacto(String nombre, String telefono) {
		try (Connection con = DriverManager.getConnection(this.cadenaConexion, this.user, this.pass)) {

			PreparedStatement st = con.prepareStatement("INSERT INTO contactos (nombre, telefono) VALUES (?, ?)");

			st.setString(1, nombre);
			st.setString(2, telefono);

			// Ejecutar la consulta
			int resultado = st.executeUpdate();

			// Verificar si la inserción fue exitosa
			if (resultado > 0) {
				JOptionPane.showMessageDialog(null, "Contacto insertado correctamente", "Inserción",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en inserción", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		return agenda.addContacto(new Contacto(nombre, telefono));
	} // añadirContacto

	public void rellenaVista() {
		vista.actualizaListado(agenda.getTodos());
	} // rellenaVista

	public boolean guardarFicheroCSV() {
		try {
			ES_Agenda.escribeAgendaEnCsv(new File(ficheroAgendaCSV), agenda);
			return true;
		} catch (IOException e) {
			return false;
		}
	} // guardaFicheroCSV

	public void recargaFicheroCSV() {
		try {
			agenda = ES_Agenda.leeAgendaDeCsv(new File(ficheroAgendaCSV));
		} catch (IOException e) {
			agenda = new Agenda();
		}
		vista.actualizaListado(agenda.getTodos());
	} // recargaFicheroCSV

	public void recargaFicheroSerial() {
		try {
			agenda = ES_Agenda.leeAgendaDeSerial(new File(ficheroAgendaSERIAL));
		} catch (Exception e) {
			agenda = new Agenda();
		}
		vista.actualizaListado(agenda.getTodos());
	} // recargaFicheroSerial

	public boolean guardarFicheroSerial() {
		try {
			ES_Agenda.escribeAgendaEnSerial(new File(ficheroAgendaSERIAL), agenda);
			return true;
		} catch (IOException e) {
			return false;
		}
	} // guardaFicheroSerial

	public List<Contacto> cargarContactosDesdeBD() {
        List<Contacto> listaContactos = new ArrayList<>();
        String consultaSQL = "SELECT * FROM contactos"; // Ajusta el nombre de tu tabla si es necesario

        try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);
             PreparedStatement consulta = con.prepareStatement(consultaSQL);
             ResultSet datos = consulta.executeQuery()) {

            while (datos.next()) {
                String nombre = datos.getString("nombre"); // Asegúrate de que coincida con el nombre de tu columna
                String telefono = datos.getString("telefono"); // Ajusta a tu columna para el teléfono
                listaContactos.add(new Contacto(nombre, telefono));
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar contactos: " + e.getMessage());
        }
        return listaContactos;
    }
	
	
	public Contacto buscarContactoPorNombre(String nombreBuscado) {
        Contacto contactoEncontrado = null;
        String consultaSQL = "SELECT * FROM contactos WHERE nombre = ?"; // Ajusta el nombre de tu tabla y columna

        try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass);
             PreparedStatement consulta = con.prepareStatement(consultaSQL)) {
            
            consulta.setString(1, nombreBuscado); // Establece el nombre en el parámetro
            ResultSet datos = consulta.executeQuery();

            if (datos.next()) {
                String nombre = datos.getString("nombre"); // Asegúrate de que coincida con el nombre de tu columna
                String telefono = datos.getString("telefono"); // Ajusta a tu columna para el teléfono
                contactoEncontrado = new Contacto(nombre, telefono);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar contacto: " + e.getMessage());
        }

        return contactoEncontrado;
    }
	
	
	@Override
	public String toString() {
		return agenda.toCSV();
	} // toString

	public static void main(String[] args) {
		AppAgenda app = new AppAgenda();
		app.vista = new VistaAgenda(app);
		app.rellenaVista();
	} // main

} // class

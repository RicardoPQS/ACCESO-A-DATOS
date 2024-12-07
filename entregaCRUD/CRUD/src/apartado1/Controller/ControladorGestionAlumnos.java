package apartado1.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import apartado1.Model.IModeloAlumnos;
import apartado1.UI.VentanaAlumnos;
import apartado1.Model.Alumno;



public class ControladorGestionAlumnos implements ActionListener, ListSelectionListener {

	private IModeloAlumnos model;
	private VentanaAlumnos view;

	public ControladorGestionAlumnos(IModeloAlumnos model, VentanaAlumnos view) {
		this.model = model;
        this.view = view;
        anadirListeners(this);
        
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.pack();
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
	}

	private void anadirListeners(ControladorGestionAlumnos controladorGestionAlumnos) {
		view.btnCargarTodos.addActionListener(controladorGestionAlumnos);
        view.btnCrear.addActionListener(controladorGestionAlumnos);
        view.btnModificar.addActionListener(controladorGestionAlumnos);
        view.btnEliminar.addActionListener(controladorGestionAlumnos);  
        
        view.jListaAlumnos.addListSelectionListener(controladorGestionAlumnos);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent event) {
	   	 String actionCommand = event.getActionCommand();
	
	     System.out.println("estoy en actionPerformed con la opcion "+actionCommand);
	
	     switch (actionCommand) {
	     case "Cargar Todos": {
	    	    // Obtener la lista de alumnos desde el modelo
	    	    List<String> listaAlumnos = model.getAll();

	    	    // Verificar si se obtuvieron resultados
	    	    if (listaAlumnos != null && !listaAlumnos.isEmpty()) {
	    	        DefaultListModel<String> modelo = new DefaultListModel<>();

	    	        // Añadir cada alumno a la lista del modelo para mostrar
	    	        for (String alumnoInfo : listaAlumnos) {
	    	            modelo.addElement(alumnoInfo);
	    	        }

	    	        // Asignar el modelo actualizado a jListaAlumnos en la vista
	    	        view.jListaAlumnos.setModel(modelo);
	    	    } else {
	    	        JOptionPane.showMessageDialog(view, "No se encontraron alumnos.", "Cargar Todos", JOptionPane.INFORMATION_MESSAGE);
	    	    }
	    	    break;
	    	}
	     	case "Crear Nuevo":{
	     		// Recoger los datos desde los campos de texto
	            String dni = view.textFieldDNI.getText();
	            String nombre = view.textFieldNombre.getText();
	            String apellidos = view.textFieldApellidos.getText();
	            String cp = view.textFieldCP.getText();

	            // Validar que no haya campos vacíos
	            if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || cp.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
	            } else {
	                // Crear un nuevo objeto Alumno
	                Alumno nuevoAlumno = new Alumno(dni, nombre, apellidos, cp);

	                // Intentar crear el alumno en la base de datos
	                boolean exito = model.crear(nuevoAlumno);
	                
	                if (exito) {
	                	JOptionPane.showMessageDialog(null, "Alumno insetado", "Insercion", JOptionPane.INFORMATION_MESSAGE);
	                    limpiarCampos(); // Limpiar los campos después de la creación
	                } else {
	                	JOptionPane.showMessageDialog(null, "Alumno no valido", "Insercion", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	            break;
	     	}
	     	case "Modificar": {
	            String dni = view.textFieldDNI.getText();
	            String nombre = view.textFieldNombre.getText();
	            String apellidos = view.textFieldApellidos.getText();
	            String cp = view.textFieldCP.getText();

	            if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || cp.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de modificar.", "Error", JOptionPane.ERROR_MESSAGE);
	                break;
	            }

	            Alumno alumnoModificado = new Alumno(dni, nombre, apellidos, cp);
	            boolean exitoModificacion = model.modificarAlumno(alumnoModificado);

	            if (exitoModificacion) {
	                JOptionPane.showMessageDialog(null, "Alumno modificado correctamente", "Modificación", JOptionPane.INFORMATION_MESSAGE);
	                limpiarCampos();
	                actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Cargar Todos"));
	            } else {
	                JOptionPane.showMessageDialog(null, "No se pudo modificar el alumno", "Modificación", JOptionPane.ERROR_MESSAGE);
	            }
	            break;
	        }
	     	case "Eliminar": {
	            String dni = view.textFieldDNI.getText();

	            // Verificar si el DNI está vacío
	            if (dni.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Por favor, ingrese el DNI del alumno a eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
	                break;
	            }

	            // Confirmación antes de eliminar
	            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este alumno?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
	            if (confirmacion != JOptionPane.YES_OPTION) {
	                break;
	            }

	            // Intentar eliminar el alumno
	            boolean exitoEliminacion = model.eliminarAlumno(dni);

	            if (exitoEliminacion) {
	                JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente", "Eliminación", JOptionPane.INFORMATION_MESSAGE);
	                limpiarCampos();
	                actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Cargar Todos"));
	            } else {
	                JOptionPane.showMessageDialog(null, "No se pudo eliminar el alumno. Verifique el DNI.", "Eliminación", JOptionPane.ERROR_MESSAGE);
	            }
	            break;
	        }
	     		
	     		
	     }
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO implementar el pinchar de una lista
	    System.out.println("estoy en valueChanged");
		if (!e.getValueIsAdjusting()) {//This line prevents double events

     		//TODO
			// view.jListaAlumnos

	    }

		
	
	}

	private void limpiarCampos() {
		view.textFieldDNI.setText("");
        view.textFieldNombre.setText("");
        view.textFieldApellidos.setText("");
        view.textFieldCP.setText("");
		
	}
	
	private void cargarAlumno(Alumno alumno) {
        if (alumno == null) {
        	limpiarCampos();
        }

        view.textFieldDNI.setText(alumno.getDNI());
        view.textFieldNombre.setText(alumno.getNombre());
        view.textFieldApellidos.setText(alumno.getApellidos());
        view.textFieldCP.setText(alumno.getCP());
    }

}

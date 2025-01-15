package apartado1;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Continente {
	@Id
	int id;
	String nombre;
	
	public Continente() {
		
	}
	
	public Continente(String nombre) {
		this.nombre = nombre;
	}
	
	public Continente(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int nia) {
		this.id = nia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}

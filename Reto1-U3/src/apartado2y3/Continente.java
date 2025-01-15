package apartado2y3;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "continents")
public class Continente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "continent_id", nullable = false)
	int id;
	@Column(name = "name", nullable = false)
	String nombre;
	@OneToMany(mappedBy = "continente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Region> regiones;

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

	public List<Region> getRegiones() {
		return regiones;
	}

	public void setRegiones(List<Region> regiones) {
		this.regiones = regiones;
	}

}

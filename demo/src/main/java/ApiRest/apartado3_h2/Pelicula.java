package ApiRest.apartado3_h2;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "peliculas")
public class Pelicula {
	@Id
    private Long id;
    private String nombre;
    private String director;
    private String clasificacion;

    public Pelicula() {}

    public Pelicula(Long id, String nombre, String director, String clasificacion) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.clasificacion = clasificacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(id, pelicula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


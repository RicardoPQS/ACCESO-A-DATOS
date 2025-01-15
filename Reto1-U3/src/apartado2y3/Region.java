package apartado2y3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Region {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;

    // Relación ManyToOne: Muchas regiones pueden pertenecer a un continente
    @ManyToOne
    @JoinColumn(name = "continent_id") // Especifica la columna que se usará para la clave foránea
    private Continente continente;

    // Constructor vacío necesario para JPA
    public Region() {
    }

    // Constructor con parámetros
    public Region(String nombre, Continente continente) {
        this.nombre = nombre;
        this.continente = continente;
    }

    // Getter y Setter para 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para 'nombre'
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para 'continente'
    public Continente getContinente() {
        return continente;
    }

    public void setContinente(Continente continente) {
        this.continente = continente;
    }
}

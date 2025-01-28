package database;
// Generated 26 ene 2025, 12:58:29 by Hibernate Tools 6.5.1.Final

/**
 * Regions generated by hbm2java
 */
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "regions") 
public class Regions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Integer regionId;

    @ManyToOne
    @JoinColumn(name = "continent_id", nullable = false)
    private Continents continents;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    public Regions() {
    }

    public Regions(Continents continents, String name) {
        this.continents = continents;
        this.name = name;
    }

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Continents getContinents() {
		return this.continents;
	}

	public void setContinents(Continents continents) {
		this.continents = continents;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

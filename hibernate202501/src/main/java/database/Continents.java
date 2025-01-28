package database;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "continents") 
public class Continents implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "continent_id")
    private Integer continentId;

    @Column(name = "name", nullable = false, length = 100) 
    private String name;

    @OneToMany(mappedBy = "continents", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Regions> regionses = new HashSet<>(0); 

    
    public Continents() {
    }

    public Continents(String name) {
        this.name = name;
    }

    public Continents(String name, Set<Regions> regionses) {
        this.name = name;
        this.regionses = regionses;
    }

    public Integer getContinentId() {
        return this.continentId;
    }

    public void setContinentId(Integer continentId) {
        this.continentId = continentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Regions> getRegionses() {
        return this.regionses;
    }

    public void setRegionses(Set<Regions> regionses) {
        this.regionses = regionses;
    }
}

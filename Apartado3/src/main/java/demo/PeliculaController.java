package demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
	private PeliculaRespository repository;
	
	public PeliculaController(PeliculaRespository repository) {
		this.repository = repository;
	}
	
	
	@GetMapping
	List<Pelicula> getPeliculas(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	Pelicula getPelicula(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
	}
	
	@PostMapping
	Pelicula crearPelicula(@RequestBody Pelicula nueva) {
		return repository.save(nueva);
	}
	
	@PutMapping("/{id}")
	Pelicula modificarPelicula(@RequestBody Pelicula nueva, @PathVariable Long id) {
		return repository.findById(id).map(pelicula -> {
			pelicula.setNombre(nueva.getNombre());
			pelicula.setDirector(nueva.getDirector());
			pelicula.setClasificacion(nueva.getClasificacion());
			return repository.save(pelicula);
		}).orElseGet(() ->{
			return repository.save(nueva);
		});
	}
	
	@PatchMapping("/{id}")
	Pelicula modificarParcial(@RequestBody Pelicula nueva, @PathVariable Long id) {
		return repository.findById(id).map(pelicula -> {
			if(nueva.getNombre() != null) {
				pelicula.setNombre(nueva.getNombre());
			}
			if(nueva.getDirector() != null) {
				pelicula.setDirector(nueva.getDirector());
			}
			if(nueva.getClasificacion() != null) {
				pelicula.setClasificacion(nueva.getClasificacion());
			}
			return repository.save(pelicula);
		}).orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
	}
	
	@DeleteMapping("/{id}")
	void borrarPelicula(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}

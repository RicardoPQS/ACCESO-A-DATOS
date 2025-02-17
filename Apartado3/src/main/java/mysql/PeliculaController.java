package mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private PeliculaRepository repository;

	public PeliculaController(PeliculaRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	List<Pelicula> getPeliculas() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	Pelicula getPelicula(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese id"));
	}

	
	@PostMapping
	public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula nueva) {
	    if (repository.existsById(nueva.getId())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(null); 
	    }
	    return ResponseEntity.ok(repository.save(nueva)); 
	}
	/*
	@PostMapping
	ResponseEntity<String> crearPelicula(@RequestBody Pelicula nueva) {
		if(repository.existsById(nueva.getId())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Id ya existe");
		}
		
		return ResponseEntity.ok("Pelicula Creada Correctamente");
	}*/

	@DeleteMapping("/{id}")
	ResponseEntity<String> eliminarPelicula(@PathVariable Long id) {
		Optional<Pelicula> pelicula = repository.findById(id);
		if (pelicula.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok("Peli borrada con exito");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula no encontrada");
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<String> modificarPeli(@PathVariable Long id, @RequestBody Pelicula modificado) {
		Optional<Pelicula> peli = repository.findById(id);
		if (peli.isPresent()) {
			Pelicula nueva = peli.get();
			nueva.setNombre(modificado.getNombre());
			nueva.setClasificacion(modificado.getClasificacion());
			nueva.setDirector(modificado.getDirector());
			repository.save(nueva);
			return ResponseEntity.ok("Pelicula modificada correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la peli");
		}
	}

	@PatchMapping("/{id}")
	ResponseEntity<String> modificarParcial(@PathVariable Long id, @RequestBody Pelicula modificada) {
		Optional<Pelicula> aux = repository.findById(id);
		if (aux.isPresent()) {
			Pelicula peli = aux.get();
			if (peli.getNombre() != null) {
				peli.setNombre(modificada.getNombre());
			}
			if (peli.getDirector() != null) {
				peli.setDirector(modificada.getDirector());
			}
			if (peli.getClasificacion() != null) {
				peli.setClasificacion(modificada.getClasificacion());
			}
			repository.save(peli);
			return ResponseEntity.ok("Modificada correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la peli");
		}
	}

}
package ApiRest.apartado3_h2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    private final PeliculaService service;

    public PeliculaController(PeliculaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pelicula>> obtenerPeliculas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(@PathVariable Long id) {
        Optional<Pelicula> pelicula = service.obtenerPorId(id);
        return pelicula.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> agregarPelicula(@RequestBody Pelicula pelicula) {
        if (service.agregarPelicula(pelicula)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Película agregada");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("La película ya existe");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPelicula(@PathVariable Long id) {
        if (service.eliminarPelicula(id)) {
            return ResponseEntity.ok("Película eliminada");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Película no encontrada");
    }
}


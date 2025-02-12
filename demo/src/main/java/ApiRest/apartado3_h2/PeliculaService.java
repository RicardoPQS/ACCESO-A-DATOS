package ApiRest.apartado3_h2;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {
    private final PeliculaRepository repository;

    public PeliculaService(PeliculaRepository repository) {
        this.repository = repository;
    }

    public List<Pelicula> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<Pelicula> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public boolean agregarPelicula(Pelicula pelicula) {
        if (repository.existsById(pelicula.getId())) {
            return false; // Ya existe
        }
        repository.save(pelicula);
        return true;
    }

    public boolean eliminarPelicula(Long id) {
        if (!repository.existsById(id)) {
            return false; // No existe para eliminar
        }
        repository.deleteById(id);
        return true;
    }
}

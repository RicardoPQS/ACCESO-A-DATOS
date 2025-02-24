package aparatado1_1_SinRest;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

  public Cliente findByNombre(String nombre);
  public List<Cliente> findByApellido(String apellido);

}

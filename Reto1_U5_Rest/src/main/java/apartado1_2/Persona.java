package apartado1_2;

import org.springframework.data.annotation.Id;

public class Persona {

  @Id private String id;

  private String nombre;
  private String apellido;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
}


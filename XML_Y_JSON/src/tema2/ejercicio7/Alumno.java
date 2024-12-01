/**
 *Alumno.java
 *@author Laura Lozano
 *@version 1.0
 */
package tema2.ejercicio7;

/**
 *  @descrition Clase que describe un Alumno para los ejercicios
 *	@author Laura
 *  @date 26/3/2015
 *  @version 1.0
 *  @license GPLv3
 */
public class Alumno {

	String nombre;
	String apellidos;
	int anoNacimiento;
	Direccion direccion;

	/**
	 * Constructor con todos los par�metros
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param anoNacimiento
	 * @param calle
	 * @param numero
	 * 
	 * 
	 */
	
	public Alumno() {

	}
	
	public Alumno(String nombre, String apellidos, int anoNacimiento,
			String calle, int numero) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = new Direccion(calle, numero);
		this.anoNacimiento = anoNacimiento;

	}

	/**
	 * Constructor copia
	 * 
	 * @param alumno
	 */
	public Alumno(Alumno alumno) {
		this.nombre = alumno.getNombre();
		this.apellidos = alumno.getApellidos();
		this.anoNacimiento = alumno.getAnoNacimiento();
		this.direccion = new Direccion(alumno.direccion);

	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	/**
	 *
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 *
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 *
	 * @return
	 */
	public int getAnoNacimiento() {
		return anoNacimiento;
	}

	/**
	 *
	 * @param anoNacimiento
	 */
	public void setAnoNacimiento(int anoNacimiento) {
		this.anoNacimiento = anoNacimiento;
	}

	/**
	 *
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 *
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 *
	 * @return
	 */
	public String toString() {
		return "El alumno se llama:" + getNombre() + " " + getApellidos()
				+ "\tNaci� en el a�o:" + getAnoNacimiento()
				+ "\tVive en la calle:" + getDireccion().toString();

	}

	/**
	 * M�todo que determina cuando un alumno es igual a otro
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Alumno))
			return false;
		Alumno c = (Alumno) obj;
		if (nombre.equals(c.getNombre()) && apellidos.equals(c.getApellidos())
				&& anoNacimiento == c.getAnoNacimiento())
			return true;
		else
			return false;
	}

	/**
	 * Siempre que sobreescribamos el m�todo equals, tambi�n tenemos que
	 * sobreescribir tambi�n el m�todo hashCode. En el API de java para Object
	 * del m�todo hashCode se especifica lo siguiente:
	 * 
	 * Cuando este m�todo es invocado sobre el mismo objeto una o m�s veces
	 * durante una ejecuci�n en una aplicaci�n, el hashCode debe de ser
	 * consistente devolviendo siempre el mismo valor, siempre que no se
	 * modifique el objeto. Este valor no tiene que ser consistente entre
	 * ejecuciones distintas de la aplicaci�n. Si dos objetos son iguales segun
	 * el m�todo equals, entonces el hashCode de los dos objetos tiene que ser
	 * el mismo. Si dos objetos no son iguales, el hashCode no tiene que ser
	 * necesariamente distinto, pero es recomendable que lo sea.
	 */
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
		hash = 71 * hash
				+ (this.apellidos != null ? this.apellidos.hashCode() : 0);
		hash = 71 * hash + (int) this.anoNacimiento;

		return hash;
	}

	
}

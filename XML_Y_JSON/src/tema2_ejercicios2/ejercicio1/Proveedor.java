package tema2_ejercicios2.ejercicio1;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @descrition
 * @author Laura
 * @date 27/4/2015
 * @version 1.0
 * @license GPLv3
 */

@XStreamAlias("proveedor")
public class Proveedor {

	@XStreamAsAttribute
	private int identificador;
	@XStreamAsAttribute
	private String nombre;
	@XStreamAsAttribute
	private String calle;
	@XStreamAsAttribute
	private String ciudad;
	@XStreamAsAttribute
	private String pais;
	@XStreamAsAttribute
	private int cp;
	@XStreamAsAttribute
	private boolean esNacional;

	@XStreamAsAttribute
	private String cif; // nuevo atributo

	@XStreamAsAttribute
	private String empresa; // nuevo atributo
	private List<Cafe> cafes;

	public Proveedor() {
		super();
		cafes = new ArrayList<Cafe>();
	}

	public void addCafe(Cafe cafe) {
		cafes.add(cafe);
	}

	public boolean isEsNacional() {
		return esNacional;
	}

	public void setEsNacional(boolean esNacional) {
		this.esNacional = esNacional;
	}

	public List<Cafe> getCafes() {
		return cafes;
	}

	public void setCafes(List<Cafe> cafes) {
		this.cafes = cafes;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Proveedor [identificador=" + identificador + ", nombre=" + nombre + ", calle=" + calle + ", ciudad="
				+ ciudad + ", pais=" + pais + ", cp=" + cp + ", esNacional=" + esNacional + ", cafes="
				+ cafes.toString() + "]";
	}

}

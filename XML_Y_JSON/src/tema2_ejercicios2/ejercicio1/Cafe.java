package tema2_ejercicios2.ejercicio1;

/**
 * @descrition Clase para representar objetos Cafe del modelo
 * @author Laura
 * @date 27/4/2015
 * @version 1.0
 * @license GPLv3
 */

public class Cafe {
	private String nombre;
	private int proveedorId;
	private float precio;
	private int ventas;
	private int total;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(int proveedorId) {
		this.proveedorId = proveedorId;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	 @Override
	    public String toString() {
	        return "Cafe{" + "nombre=" + nombre + "precio=" + precio+ "ventas=" + ventas + "total=" + total +'}';
	    }

}

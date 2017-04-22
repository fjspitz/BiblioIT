/* Subcategoria.java
 * Creado el 21 abr. 2017
 */

package ar.com.fjs.biblioit.model;

import java.io.Serializable;

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 21 abr. 2017
 */
public class Subcategoria implements Serializable {
	private static final long serialVersionUID = -732371885336117218L;
	private long ID;
	private String nombre;
	private Categoria categoria;
	
	public Subcategoria() {
		categoria = new Categoria();
	}
	
	/**
	 * @param iD
	 * @param nombre
	 * @param categoria
	 */
	public Subcategoria(long iD, String nombre, Categoria categoria) {
		super();
		ID = iD;
		this.nombre = nombre;
		this.categoria = categoria;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return String.valueOf(this.ID);
	}
	
}

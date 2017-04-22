/* Categoria.java
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
public class Categoria implements Serializable {
	private static final long serialVersionUID = -5609109087507236272L;
	private long ID;
	private String nombre;
	
	public Categoria() {}
	
	/**
	 * @param iD
	 * @param nombre
	 */
	public Categoria(long iD, String nombre) {
		super();
		ID = iD;
		this.nombre = nombre;
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
}

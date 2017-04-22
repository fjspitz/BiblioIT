/* Editorial.java
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
public class Editorial implements Serializable {
	private static final long serialVersionUID = 8029627964449723686L;
	private long ID;
	private String nombre;
	private String abreviatura;
	
	public Editorial() {}
	
	/**
	 * @param iD
	 * @param nombre
	 * @param abreviatura
	 */
	public Editorial(long iD, String nombre, String abreviatura) {
		super();
		ID = iD;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
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

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
}

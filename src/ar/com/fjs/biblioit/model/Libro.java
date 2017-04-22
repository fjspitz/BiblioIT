/* Libro.java
 * Creado el 20 abr. 2017
 */

package ar.com.fjs.biblioit.model;

import java.io.Serializable;

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 20 abr. 2017
 */
public class Libro implements Serializable {
	private static final long serialVersionUID = 6189292793968788061L;
	private long ID;
	private String nombre;
	private String ISBN;
	private int paginas;
	private short anio;
	private int calificacion;
	private Editorial editorial;
	private Subcategoria subcategoria;

	public Libro() {
		editorial = new Editorial();
		subcategoria = new Subcategoria();
	}
	
	/**
	 * @param iD
	 * @param nombre
	 * @param iSBN
	 * @param paginas
	 * @param anio
	 * @param editorial
	 * @param subcategoria
	 */
	public Libro(long iD, String nombre, String iSBN, int paginas, short anio, 
			int calificacion, Editorial editorial, Subcategoria subcategoria) {
		super();
		ID = iD;
		this.nombre = nombre;
		ISBN = iSBN;
		this.paginas = paginas;
		this.anio = anio;
		this.calificacion = calificacion;
		this.editorial = editorial;
		this.subcategoria = subcategoria;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
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

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public short getAnio() {
		return anio;
	}

	public void setAnio(short anio) {
		this.anio = anio;
	}

	@Override
	public String toString() {
		return String.valueOf(this.ID);
	}
}

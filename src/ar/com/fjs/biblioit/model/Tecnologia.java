/* Tecnologia.java
 * Creado el 21 abr. 2017
 */

package ar.com.fjs.biblioit.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 21 abr. 2017
 */
public class Tecnologia implements Serializable {
	private static final long serialVersionUID = 9114655959181894365L;
	private long ID;
	private String nombre;
	private Date fechaAparicion;
	private String ultimaVersion;
	private Date fechaUltimaVersion;
	private Propietario propietario;
	
	public Tecnologia() {}

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

	public Date getFechaAparicion() {
		return fechaAparicion;
	}

	public void setFechaAparicion(Date fechaAparicion) {
		this.fechaAparicion = fechaAparicion;
	}

	public String getUltimaVersion() {
		return ultimaVersion;
	}

	public void setUltimaVersion(String ultimaVersion) {
		this.ultimaVersion = ultimaVersion;
	}

	public Date getFechaUltimaVersion() {
		return fechaUltimaVersion;
	}

	public void setFechaUltimaVersion(Date fechaUltimaVersion) {
		this.fechaUltimaVersion = fechaUltimaVersion;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
}

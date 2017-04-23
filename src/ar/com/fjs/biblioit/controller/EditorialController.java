/* EditorialController.java
 * Creado el 22 abr. 2017
 */

package ar.com.fjs.biblioit.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ar.com.fjs.biblioit.database.DatabaseManagment;
import ar.com.fjs.biblioit.model.Editorial;

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 22 abr. 2017
 */

@Named("editorial")
@ViewScoped
public class EditorialController implements Serializable {
	private static final long serialVersionUID = 7076206703803939771L;
	@Inject private DatabaseManagment db;
	
	private List<Editorial> listado;
	
	private long id;
	private Editorial editorial;
	private int cantidad;
	
	public EditorialController() {}
	
	@PostConstruct
	public void initialize() {
		editorial = new Editorial();
		listado = db.getListadoEditoriales();
		cantidad = listado.size();
	}
	
	public String cargar() throws SQLException {
		editorial = db.cargarUnEditorial(id);
		return null;
	}
	
	public String guardar() {
		db.guardarEditorial(editorial);
		return "listaEditoriales";
	}

	public List<Editorial> getListado() {
		return listado;
	}

	public void setListado(List<Editorial> listado) {
		this.listado = listado;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}

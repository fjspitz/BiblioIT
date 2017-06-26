/* LibroController.java
 * Creado el 26 jun. 2017
 */

package ar.com.fjs.biblioit.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ar.com.fjs.biblioit.database.DatabaseManagment;
import ar.com.fjs.biblioit.model.Editorial;
import ar.com.fjs.biblioit.model.Libro;
import ar.com.fjs.biblioit.model.Subcategoria;

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 26 jun. 2017
 */

@Named("libro")
@RequestScoped
public class LibroController implements Serializable {
	private static final long serialVersionUID = -5326272170624741154L;
	@Inject private DatabaseManagment db;
	private Libro libro;
	private long id;
	private List<Subcategoria> listadoSubcategorias;
	private List<Editorial> listadoEditoriales;
	
	public LibroController() {}
	
	@PostConstruct
	public void initialize() throws SQLException {
		libro = new Libro();
		id = 0;
		listadoSubcategorias = db.getListadoSubcategorias();
		listadoEditoriales = db.getListadoEditoriales();
	}
	
	public String cargar() throws SQLException {
		libro = db.cargarUnLibro(id);
		return null;
	}
	
	public String guardar() {
		db.guardarLibro(libro);
		return "listaLibros";
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Subcategoria> getListadoSubcategorias() {
		return listadoSubcategorias;
	}

	public void setListadoSubcategorias(List<Subcategoria> listadoSubcategorias) {
		this.listadoSubcategorias = listadoSubcategorias;
	}

	public List<Editorial> getListadoEditoriales() {
		return listadoEditoriales;
	}

	public void setListadoEditoriales(List<Editorial> listadoEditoriales) {
		this.listadoEditoriales = listadoEditoriales;
	}
}

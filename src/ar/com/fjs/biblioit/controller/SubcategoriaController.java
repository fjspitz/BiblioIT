/* CategoriaController.java
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
import ar.com.fjs.biblioit.model.Categoria;
import ar.com.fjs.biblioit.model.Subcategoria;

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 22 abr. 2017
 */

@Named("subcategoria")
@ViewScoped
public class SubcategoriaController implements Serializable {
	private static final long serialVersionUID = 3090233358178133604L;
	@Inject private DatabaseManagment db;
	
	private List<Subcategoria> listado;
	private List<Categoria> listadoCategorias;
	private long id;
	private int cantidad;
	private Subcategoria subcategoria;
	
	public SubcategoriaController() {}
	
	@PostConstruct
	public void initalize() {
		subcategoria = new Subcategoria();
		listado = db.getListadoSubcategorias();
		listadoCategorias = db.getListadoCategorias();
		cantidad = listado.size();
	}
	
	public String cargar() throws SQLException {
		subcategoria = db.cargarUnaSubcategoria(id);
		return null;
	}
	
	public String guardar() {
		db.guardarSubcategoria(subcategoria);
		return "listaSubcategorias";
	}

	public List<Subcategoria> getListado() {
		return listado;
	}

	public void setListado(List<Subcategoria> listado) {
		this.listado = listado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Categoria> getListadoCategorias() {
		return listadoCategorias;
	}

	public void setListadoCategorias(List<Categoria> listadoCategorias) {
		this.listadoCategorias = listadoCategorias;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}

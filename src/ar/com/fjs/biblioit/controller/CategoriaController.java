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

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 22 abr. 2017
 */

@Named("categoria")
@ViewScoped
public class CategoriaController implements Serializable {
	private static final long serialVersionUID = 3090233358178133604L;
	@Inject private DatabaseManagment db;
	
	private List<Categoria> listado;
	private long id;
	private int cantidad;
	private Categoria categoria;
	
	public CategoriaController() {}
	
	@PostConstruct
	public void initalize() {
		categoria = new Categoria();
		listado = db.getListadoCategorias();
		cantidad = listado.size();
	}
	
	public String cargar() throws SQLException {
		categoria = db.cargarUnaCategoria(id);
		return null;
	}
	
	public String guardar() {
		db.guardarCategoria(categoria);
		return "listaCategorias";
	}

	public List<Categoria> getListado() {
		return listado;
	}

	public void setListado(List<Categoria> listado) {
		this.listado = listado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

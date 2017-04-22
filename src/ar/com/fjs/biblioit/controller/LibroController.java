/* LibroController.java
 * Creado el 20 abr. 2017
 */

package ar.com.fjs.biblioit.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
 * @version 1.0, 20 abr. 2017
 */

@Named("libro")
@ViewScoped
public class LibroController implements Serializable {
	private static final long serialVersionUID = 7800352691960185618L;
	@Inject private DatabaseManagment db;
	
	private List<Libro> listado;
	private List<Subcategoria> listadoSubcategorias;
	private List<Editorial> listadoEditoriales;
	
	private String filtroNombre;
	private Subcategoria filtroSubcategoria;
	private int cantidad;
	
	private long id;
	private String nombre;
	private String ISBN;
	private int paginas;
	private short anio;
	private int calificacion;
	private Subcategoria subcategoria;
	private Editorial editorial;
	
	public LibroController() {
		filtroNombre = "";
		filtroSubcategoria = new Subcategoria();
		listadoSubcategorias = new ArrayList<Subcategoria>();
		listadoEditoriales = new ArrayList<Editorial>();
		subcategoria = new Subcategoria();
		editorial = new Editorial();
	}
	
	@PostConstruct
	public void initialize() {
		try {
			listado = db.cargarListado("", null);
			cantidad = listado.size();
			listadoSubcategorias = db.getListadoSubcategorias();
			listadoEditoriales = db.getListadoEditoriales();
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Se ha producido un error con la base de datos", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgs", msg);
			e.printStackTrace();
		}
	}
	
	public String filtrarListado() {
		try {
			listado = db.cargarListado(filtroNombre, filtroSubcategoria);
			cantidad = listado.size();
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Se ha producido un error con la base de datos", e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msgs", msg);
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Subcategoria> getListadoSubcategorias() {
		return listadoSubcategorias;
	}

	public void setListadoSubcategorias(List<Subcategoria> listadoSubcategorias) {
		this.listadoSubcategorias = listadoSubcategorias;
	}

	public Subcategoria getFiltroSubcategoria() {
		return filtroSubcategoria;
	}

	public void setFiltroSubcategoria(Subcategoria filtroSubcategoria) {
		this.filtroSubcategoria = filtroSubcategoria;
	}

	public List<Libro> getListado() {
		return listado;
	}

	public void setListado(List<Libro> listado) {
		this.listado = listado;
	}
	
	public String getFiltroNombre() {
		return filtroNombre;
	}

	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public List<Editorial> getListadoEditoriales() {
		return listadoEditoriales;
	}

	public void setListadoEditoriales(List<Editorial> listadoEditoriales) {
		this.listadoEditoriales = listadoEditoriales;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

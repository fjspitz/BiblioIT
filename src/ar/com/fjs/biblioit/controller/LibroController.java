/* LibroController.java
 * Creado el 20 abr. 2017
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
import ar.com.fjs.biblioit.model.Libro;

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 20 abr. 2017
 */

@Named("libro")
@RequestScoped
public class LibroController implements Serializable {
	private static final long serialVersionUID = 7800352691960185618L;
	
	private List<Libro> listado;
	@Inject private DatabaseManagment db;
	
	@PostConstruct
	public void initialize() {
		try {
			listado = db.cargarListado();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Libro> getListado() {
		return listado;
	}

	public void setListado(List<Libro> listado) {
		this.listado = listado;
	}	
}

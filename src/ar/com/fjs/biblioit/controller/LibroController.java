/* LibroController.java
 * Creado el 20 abr. 2017
 */

package ar.com.fjs.biblioit.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;

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
	@Resource(name = "MySQL Database")
	private DataSource datasource;
	
	
	@PostConstruct
	public void initialize() {
		try {
			listado = cargarListado();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<Libro> cargarListado() throws SQLException {
		List<Libro> lista = new ArrayList<Libro>();
		Connection conn =  datasource.getConnection();
		
		Statement stmt = conn.createStatement();
		
		String strSelect = "select * from libro";
        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
        System.out.println();

        ResultSet rset = stmt.executeQuery(strSelect);
        
        int rowCount = 0;
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
        	Libro l = new Libro();
        	
        	l.setID(rset.getLong("id"));
        	l.setNombre(rset.getString("nombre"));
        	l.setISBN(rset.getString("isbn"));
        	lista.add(l);
        	
        	//long title = rset.getLong("id");
        	//String price = rset.getString("nombre");
           ++rowCount;
        }
        System.out.println("Total number of records = " + rowCount);
		
		return lista;
		
	}

	public List<Libro> getListado() {
		return listado;
	}

	public void setListado(List<Libro> listado) {
		this.listado = listado;
	}
	
	
}

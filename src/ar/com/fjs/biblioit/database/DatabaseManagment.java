/* DatabaseManagment.java
 * Creado el 21 abr. 2017
 */

package ar.com.fjs.biblioit.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

import ar.com.fjs.biblioit.model.Libro;

/**
 * Add one sentence class summary here.
 * Add class description here.
 *
 * @author Fernando J. Spitz
 * @version 1.0, 21 abr. 2017
 */

@Named("db")
@ApplicationScoped
public class DatabaseManagment implements Serializable {
	private static final long serialVersionUID = 7273284532771000386L;
	
	@Resource(name="MySQL Database") 
	private DataSource datasource;
	
	public DatabaseManagment() {}
	
	public List<Libro> cargarListado() throws SQLException {
		List<Libro> lista = new ArrayList<Libro>();
		Connection conn =  datasource.getConnection();
		
		Statement stmt = conn.createStatement();
		
		String strSelect = "select * from libro";

        ResultSet rset = stmt.executeQuery(strSelect);
        
        while(rset.next()) {
        	Libro l = new Libro();
        	
        	l.setID(rset.getLong("id"));
        	l.setNombre(rset.getString("nombre"));
        	l.setISBN(rset.getString("isbn"));
        	lista.add(l);
        	
        	//long title = rset.getLong("id");
        	//String price = rset.getString("nombre");
        }
		return lista;
	}
}

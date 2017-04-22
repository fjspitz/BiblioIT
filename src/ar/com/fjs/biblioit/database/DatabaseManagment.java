/* DatabaseManagment.java
 * Creado el 21 abr. 2017
 */

package ar.com.fjs.biblioit.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

import ar.com.fjs.biblioit.model.Categoria;
import ar.com.fjs.biblioit.model.Editorial;
import ar.com.fjs.biblioit.model.Libro;
import ar.com.fjs.biblioit.model.Subcategoria;

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
	private static Connection conn;
	
	@Resource(name="MySQL Database") 
	private DataSource datasource;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	private List<Subcategoria> listadoSubcategorias;
	private List<Editorial> listadoEditoriales;
	private List<Libro> listadoLibros;
	
	public DatabaseManagment() {}
	
	@PostConstruct
	public void initialize() throws SQLException {
		conn = datasource.getConnection();
		listadoSubcategorias = cargarListadoSubcategorias();
		listadoEditoriales = cargarListadoEditoriales();
	}
	
	public List<Libro> cargarListado(String nombreLibro, Subcategoria filtroSubcategoria) throws SQLException {
		List<Libro> lista = new ArrayList<Libro>();
		StringBuilder strSQL = new StringBuilder();
		boolean whereClause = false;

		strSQL.append("select " + 
						"l.id, l.nombre, l.isbn, l.subcategoria, l.editorial, l.paginas, l.calificacion, l.anio, " +
						"s.nombre as nombreSubcategoria, s.categoria as idCategoria, e.nombre as nombreEditorial, e.abreviatura as abreviaturaEditorial, c.nombre as nombreCategoria " + 
						"from libro l inner join " + 
						"subcategoria s on (l.subcategoria = s.id) inner join " + 
						"editorial e on (l.editorial = e.id) inner join " + 
						"categoria c on (s.categoria = c.id)");
		stmt = conn.createStatement();
		
		if (! (nombreLibro == null || nombreLibro.trim().equalsIgnoreCase(""))) {
			if (! whereClause) {
				strSQL.append(" where ");
			}
			strSQL.append("l.nombre LIKE '" + nombreLibro.trim() + "%" + "\'");
		}
		if (! (filtroSubcategoria == null || filtroSubcategoria.getID() == 0)) {
			if (! whereClause) {
				strSQL.append(" where ");
			} else {
				strSQL.append(" and ");
			}
			strSQL.append("l.subcategoria = " + filtroSubcategoria.getID());
		}

		System.out.println("SQL: " + strSQL.toString());
        ResultSet rs = stmt.executeQuery(strSQL.toString());
        
        while(rs.next()) {
        	Editorial editorial = new Editorial(rs.getLong("editorial"), rs.getString("nombreEditorial"), rs.getString("abreviaturaEditorial"));
        	Categoria categoria = new Categoria(rs.getLong("idCategoria"), rs.getString("nombreCategoria"));
        	Subcategoria subcategoria = new Subcategoria(rs.getLong("subcategoria"), rs.getString("nombreSubcategoria"), categoria);
        	Libro libro = new Libro(rs.getLong("id"), rs.getString("nombre"), rs.getString("isbn"), rs.getInt("paginas"), rs.getShort("anio"), rs.getInt("calificacion"), editorial, subcategoria);
        	lista.add(libro);
        }
		return lista;
	}
	
	private List<Subcategoria> cargarListadoSubcategorias() throws SQLException {
		List<Subcategoria> lista = new ArrayList<Subcategoria>();
		
		String strSQL = "select s.id, s.nombre from subcategoria s order by s.nombre";
		
		stmt = conn.createStatement();
		
		System.out.println("SQL: " + strSQL);
        ResultSet rs = stmt.executeQuery(strSQL);
		
        while(rs.next()) {
        	Subcategoria subcategoria = new Subcategoria(rs.getLong("id"), rs.getString("nombre"), null);
        	lista.add(subcategoria);
        }
		return lista;
	}
	
	private List<Editorial> cargarListadoEditoriales() throws SQLException {
		List<Editorial> lista = new ArrayList<Editorial>();
		
		String strSQL = "select e.id, e.nombre from editorial e";
		
		stmt = conn.createStatement();
		
		System.out.println("SQL: " + strSQL);
        ResultSet rs = stmt.executeQuery(strSQL);
		
        while(rs.next()) {
        	Editorial editorial = new Editorial(rs.getLong("id"), rs.getString("nombre"), null);
        	lista.add(editorial);
        }
		return lista;
	}
	
	public void guardarLibro(Libro libro) {
		String strSQL;
		boolean isUpdate = true;
		
		if (libro.getID() == 0) {
			isUpdate = false;
			strSQL = "insert into libro (nombre, isbn, subcategoria, editorial, paginas, calificacion, anio) values (?,?,?,?,?,?,?)";
		} else {
			strSQL = "update libro set nombre = ?, isbn = ?, subcategoria = ?, editorial = ?, paginas = ?, calificacion = ?, anio = ? where id = ?";
		}
		
		try {
			pstmt = conn.prepareStatement(strSQL);
			
			pstmt.setString(1, libro.getNombre());
			pstmt.setString(2, libro.getISBN());
			pstmt.setLong(3, libro.getSubcategoria().getID());
			pstmt.setLong(4, libro.getEditorial().getID());
			pstmt.setInt(5, libro.getPaginas());
			pstmt.setInt(6, libro.getCalificacion());
			pstmt.setInt(7, libro.getAnio());
			if (isUpdate) {
				pstmt.setLong(8, libro.getID());
			}
			
			System.out.println("SQL: " + strSQL);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Libro cargarUnLibro(long ID) throws SQLException {
		String strSQL = "select " + 
				"l.id, l.nombre, l.isbn, l.subcategoria, l.editorial, l.paginas, l.calificacion, l.anio, " +
				"s.nombre as nombreSubcategoria, s.categoria as idCategoria, e.nombre as nombreEditorial, e.abreviatura as abreviaturaEditorial, c.nombre as nombreCategoria " + 
				"from libro l inner join " + 
				"subcategoria s on (l.subcategoria = s.id) inner join " + 
				"editorial e on (l.editorial = e.id) inner join " + 
				"categoria c on (s.categoria = c.id) " + 
				"where l.id = " + ID;
		stmt = conn.createStatement();
		
		System.out.println("SQL: " + strSQL);
        ResultSet rs = stmt.executeQuery(strSQL);
		
        while(rs.next()) {
        	Editorial editorial = new Editorial(rs.getLong("editorial"), rs.getString("nombreEditorial"), rs.getString("abreviaturaEditorial"));
        	Categoria categoria = new Categoria(rs.getLong("idCategoria"), rs.getString("nombreCategoria"));
        	Subcategoria subcategoria = new Subcategoria(rs.getLong("subcategoria"), rs.getString("nombreSubcategoria"), categoria);
        	Libro libro = new Libro(rs.getLong("id"), rs.getString("nombre"), rs.getString("isbn"), rs.getInt("paginas"), rs.getShort("anio"), rs.getInt("calificacion"), editorial, subcategoria);
        	return libro;
        }
		return null;
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

	public List<Libro> getListadoLibros() {
		return listadoLibros;
	}

	public void setListadoLibros(List<Libro> listadoLibros) {
		this.listadoLibros = listadoLibros;
	}
}

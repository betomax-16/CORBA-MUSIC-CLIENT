package SqlMusica;

import SqlMusica.ConexionMySQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Cancion {

	private String id;
	private String titulo;
	private String duracion;
	
	public Cancion(String titulo, String duracion) {
		this.titulo = titulo;
		this.duracion = duracion;
	}
	
	public Cancion(String id, String titulo, String duracion) {
		this.id = id;
		this.titulo = titulo;
		this.duracion = duracion;
	}
	
	public static List<Cancion> getAll() throws SQLException {
		ConexionMySQL conn = new ConexionMySQL();
		Connection con = conn.conectarMySQL();
		Statement estado = con.createStatement();
		ResultSet resultado = estado.executeQuery("select * from canciones");
		List<Cancion> canciones = new ArrayList<Cancion>();
		while (resultado.next()) {
			canciones.add(new Cancion(resultado.getString("id"), resultado.getString("titulo"), resultado.getString("duracion")));
		}
		return canciones;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	@Override
	public String toString() {
		return this.titulo;
	}
}

/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ubicacionDTO;

public class jdbcUbicacionDAO implements ubicacionDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;

	public jdbcUbicacionDAO() {
		this.connect = Conexion.getInstance().conectar();
	}

	public void cerrarBD() throws SQLException {
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}

	@Override
	public void agregarUbicacion(ubicacionDTO u) throws SQLException {
		this.ps = this.connect
				.prepareStatement("insert into ubicaciones(nombre,descripcion,edificio,planta) values (?,?,?,?)");
		this.ps.setString(1, u.getNombre());
		this.ps.setString(2, u.getDescripcion());
		this.ps.setString(3, u.getEdificio());
		this.ps.setString(4, u.getPlanta());
		this.ps.executeUpdate();
	}

	@Override
	public boolean modificarUbicacion(int id, String valor, String campo) throws SQLException {
		boolean resultado = false;
		switch (campo) {
		case "Nombre":
			this.ps = this.connect.prepareStatement("update ubicaciones set nombre = ? where id = ?");
			break;
		case "Descripcion":
			this.ps = this.connect.prepareStatement("update ubicaciones set descripcion = ? where id = ?");
			break;
		case "Edificio":
			this.ps = this.connect.prepareStatement("update ubicaciones set edificio = ? where id = ?");
			break;
		case "Planta":
			this.ps = this.connect.prepareStatement("update ubicaciones set planta = ? where id = ?");
			break;
		}
		this.ps.setString(1, valor);
		this.ps.setInt(2, id);
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public boolean eliminarUbicacion(ubicacionDTO u) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from ubicaciones where id = ?");
		this.ps.setInt(1, u.getId());
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public ArrayList<ubicacionDTO> leerUbicaciones() throws SQLException {
		ArrayList<ubicacionDTO> aux = new ArrayList<>();
		this.ps = this.connect.prepareStatement("select * from ubicaciones");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			aux.add(new ubicacionDTO(this.rs.getInt("id"), this.rs.getString("nombre"),
					this.rs.getString("descripcion"), this.rs.getString("edificio"), this.rs.getString("planta")));
		}
		return aux;
	}

	public ArrayList<ubicacionDTO> leerNombresUbicaciones() throws SQLException {
		ArrayList<ubicacionDTO> aux = new ArrayList<>();
		this.ps = this.connect.prepareStatement("select nombre from ubicaciones");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			aux.add(new ubicacionDTO(this.rs.getString("nombre")));
		}
		return aux;
	}

	@Override
	public int obtnerIdUbicacion(String nombre) throws SQLException {
		this.ps = this.connect.prepareStatement("select id from ubicaciones where nombre = ?");
		this.ps.setString(1, nombre);
		this.rs = this.ps.executeQuery();
		this.rs.next();
		return this.rs.getInt("id");
	}

}

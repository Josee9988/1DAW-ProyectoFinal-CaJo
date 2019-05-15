/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
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
				.prepareStatement("INSERT INTO ubicaciones(nombre,descripcion,edificio,planta) values (?,?,?,?)");
		this.ps.setString(1, u.getNombre());
		this.ps.setString(2, u.getDescripcion());
		this.ps.setString(3, u.getEdificio());
		this.ps.setString(4, u.getPlanta());
		this.ps.executeUpdate();
	}

	@Override
	public boolean modificarUbicacion(ubicacionDTO ubicacion) throws SQLException {
		boolean resultado = false;
		int n = 0;
		this.ps = this.connect.prepareStatement(
				"UPDATE ubicaciones SET nombre = ?, descripcion = ?, edificio = ?, planta = ?  WHERE id = ?");
		this.ps.setString(1, ubicacion.getNombre());
		this.ps.setString(2, ubicacion.getDescripcion());
		this.ps.setString(3, ubicacion.getEdificio());
		this.ps.setString(4, ubicacion.getPlanta());
		this.ps.setInt(5, ubicacion.getId());
		n = this.ps.executeUpdate();

		if (n == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public boolean eliminarUbicacion(int id) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("DELETE FROM ubicaciones where id = ?");
		this.ps.setInt(1, id);
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
	public int obtenerIdUbicacion(String nombre) throws SQLException {
		this.ps = this.connect.prepareStatement("select id from ubicaciones where nombre = ?");
		this.ps.setString(1, nombre);
		this.rs = this.ps.executeQuery();
		this.rs.next();
		return this.rs.getInt("id");
	}

}

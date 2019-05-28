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

	/**
	 * Método que llama a la clase "Conexion" y obtiene la instancia del objeto de
	 * tipo "Connection". A éste método de la clase
	 * "Conexion.getInstance().conectar()" le igualaremos nuestra variable "connect"
	 * de tipo Connection
	 */
	public jdbcUbicacionDAO() {
		this.connect = Conexion.getInstance().conectar();
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
		this.ps.close();
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
		this.ps.close();
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
		this.ps.close();
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
		this.ps.close();
		this.rs.close();
		return aux;
	}

	@Override
	public ArrayList<String> leerNombresUbicaciones() throws SQLException {
		ArrayList<String> aux = new ArrayList<>();
		this.ps = this.connect.prepareStatement("select nombre from ubicaciones");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			aux.add(this.rs.getString("nombre"));
		}
		this.ps.close();
		this.rs.close();
		return aux;
	}

	@Override
	public int obtenerIdUbicacion(String nombre) throws SQLException {
		int id = 0;
		this.ps = this.connect.prepareStatement("select id from ubicaciones where nombre = ?");
		this.ps.setString(1, nombre);
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			id = this.rs.getInt("id");
		}
		this.ps.close();
		this.rs.close();
		return id;
	}

	@Override
	public String devolverNombre(int id) throws SQLException {
		String nombre = "";
		this.ps = this.connect.prepareStatement("SELECT nombre FROM ubicaciones WHERE id = ?");
		this.ps.setInt(1, id);
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			nombre = this.rs.getString("nombre");
		}
		this.ps.close();
		this.rs.close();
		return nombre;
	}

	public ArrayList<ubicacionDTO> filtrar(String texto) throws SQLException {
		ArrayList<ubicacionDTO> aux = new ArrayList<>();
		this.ps = this.connect.prepareStatement("SELECT * FROM ubicaciones WHERE nombre LIKE ? OR descripcion LIKE ?");
		this.ps.setString(1, "%" + texto + "%");
		this.ps.setString(2, "%" + texto + "%");

		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			aux.add(new ubicacionDTO(this.rs.getInt("id"), this.rs.getString("nombre"),
					this.rs.getString("descripcion"), this.rs.getString("edificio"), this.rs.getString("planta")));
		}
		this.ps.close();
		this.rs.close();
		return aux;
	}

}

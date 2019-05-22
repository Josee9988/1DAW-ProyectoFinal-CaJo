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

import dto.incidenciaDTO;
import dto.usuarioDTO;

public class jdbcIncidenciasDAO implements incidenciasDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Método que llama a la clase "Conexion" y obtiene la instancia del objeto de
	 * tipo "Connection". A éste método de la clase
	 * "Conexion.getInstance().conectar()" le igualaremos nuestra variable "connect"
	 * de tipo Connection
	 */
	public jdbcIncidenciasDAO() {
		this.connect = Conexion.getInstance().conectar();
	}

	/**
	 * cerrarBD cierra la base de datos
	 *
	 * @throws SQLException si hay una excepción SQL
	 */
	public void cerrarBD() throws SQLException {
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}

	@Override
	public boolean eliminarIncidencia(int id) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("DELETE FROM incidencias WHERE id_incidencia = ?");
		this.ps.setInt(1, id);
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public ArrayList<incidenciaDTO> leerIncidencias(usuarioDTO u) throws SQLException {
		ArrayList<incidenciaDTO> incidencias = new ArrayList<>();
		if (u.getRol() == 1 || u.getRol() == 2) {
			this.ps = this.connect.prepareStatement("SELECT * FROM incidencias WHERE usuario = ?");
			this.ps.setString(1, u.getUser());
		} else {
			this.ps = this.connect.prepareStatement("SELECT * FROM incidencias");
		}
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			incidencias.add(new incidenciaDTO(this.rs.getInt("id_incidencia"), this.rs.getString("usuario"),
					this.rs.getString("descripcion"), this.rs.getString("elemento"), this.rs.getInt("ubicacion"),
					this.rs.getDate("fecha"), this.rs.getString("urgencia"), this.rs.getString("categoria"),
					this.rs.getString("materiales")));
		}
		return incidencias;
	}

	@Override
	public boolean modificarIncidencia(incidenciaDTO incidencia) throws SQLException {
		boolean resultado;
		int n = 0;
		this.ps = this.connect.prepareStatement(
				"UPDATE incidencias SET usuario = ?, descripcion = ?, elemento = ?, ubicacion = ?, fecha = ?, urgencia = ?, categoria = ?, materiales = ? WHERE id_incidencia = ?");

		this.ps.setString(1, incidencia.getUsuario());
		this.ps.setString(2, incidencia.getDescripcion());
		this.ps.setString(3, incidencia.getElemento());
		this.ps.setInt(4, incidencia.getUbicacionI());
		this.ps.setDate(5, incidencia.getFecha());
		this.ps.setString(6, incidencia.getUrgencia());
		this.ps.setString(7, incidencia.getCategoria());
		this.ps.setString(8, incidencia.getMateriales());
		this.ps.setInt(9, incidencia.getId());

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
	public void crearIncidencia(incidenciaDTO i) throws SQLException {
		this.ps = this.connect.prepareStatement(
				"INSERT INTO incidencias(usuario,descripcion,elemento,ubicacion,fecha,urgencia,categoria,materiales) VALUES(?,?,?,?,?,?,?,?)");
		this.ps.setString(1, i.getUsuario());
		this.ps.setString(2, i.getDescripcion());
		this.ps.setString(3, i.getElemento());
		this.ps.setInt(4, i.getUbicacionI());
		this.ps.setDate(5, i.getFecha());
		this.ps.setString(6, i.getUrgencia());
		this.ps.setString(7, i.getCategoria());
		this.ps.setString(8, i.getMateriales());
		this.ps.executeUpdate();
	}

	@Override
	public ArrayList<Integer> leerIncidencias() throws SQLException {
		ArrayList<Integer> incidencias = new ArrayList<>();

		this.ps = this.connect.prepareStatement("SELECT id_incidencia FROM incidencias");

		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {

			incidencias.add((this.rs.getInt("id_incidencia")));
		}
		return incidencias;
	}

	@Override
	public String obtenerNombreIncidencia(int id) throws SQLException {
		String nombre = "";
		this.ps = this.connect.prepareStatement("SELECT descripcion FROM incidencias WHERE id_incidencia = ? LIMIT 1");
		this.ps.setInt(1, id);

		this.rs = this.ps.executeQuery();
		if (this.rs.next()) {
			nombre = this.rs.getString("descripcion");
		}
		this.ps.close();
		this.rs.close();
		return nombre;
	}

	@Override
	public int obtenerId(String descripcion) throws SQLException {
		int idToReturn = 0;
		this.ps = this.connect.prepareStatement("SELECT id_incidencia FROM incidencias WHERE descripcion = ? LIMIT 1");
		this.ps.setString(1, descripcion);

		this.rs = this.ps.executeQuery();
		if (this.rs.next()) {
			idToReturn = this.rs.getInt("id_incidencia");
		}
		this.ps.close();
		this.rs.close();
		return idToReturn;
	}

	@Override
	public ArrayList<String> leerDescripcionesIncidencias() throws SQLException {
		ArrayList<String> arrayToReturn = new ArrayList<>();

		this.ps = this.connect.prepareStatement("SELECT descripcion FROM incidencias");

		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {

			arrayToReturn.add((this.rs.getString("descripcion")));
		}
		return arrayToReturn;
	}

}

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

import dto.mensajesDTO;

public class jdbcMensajesDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;

	public jdbcMensajesDAO() {
		this.connect = Conexion.getInstance().conectar();
	}

	public void cerrarBD() throws SQLException {
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}

	public ArrayList<mensajesDTO> leerMensajes() throws SQLException {
		ArrayList<mensajesDTO> aux = new ArrayList<>();
		this.ps = this.connect.prepareStatement("SELECT * FROM mensajes");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			aux.add(new mensajesDTO(this.rs.getInt("id"), this.rs.getString("asunto"), this.rs.getString("cuerpo"),
					this.rs.getInt("incidencia"), this.rs.getDate("fecha"), this.rs.getInt("id_emisor"),
					this.rs.getInt("id_receptor")));
		}
		return aux;
	}

	public void crearMensaje(mensajesDTO m) throws SQLException {
		this.ps = this.connect.prepareStatement(
				"insert into mensajes(asunto,cuerpo,fecha,id_emisor,id_receptor,incidencia) values(?,?,?,?,?,?)");
		this.ps.setString(1, m.getAsunto());
		this.ps.setString(2, m.getCuerpo());
		this.ps.setDate(3, m.getFecha());
		this.ps.setInt(4, m.getEmisor());
		this.ps.setInt(5, m.getReceptor());
		this.ps.setInt(6, m.getIncidencia());
		this.ps.executeUpdate();
		this.ps.close();
	}

	public void modificarMensaje(mensajesDTO m) throws SQLException {
		this.ps = this.connect.prepareStatement(
				"UPDATE mensajes SET asunto = ?, cuerpo = ?, fecha = ?, id_emisor = ?, id_receptor = ?, incidencia = ? WHERE id = ?");
		this.ps.setString(1, m.getAsunto());
		this.ps.setString(2, m.getCuerpo());
		this.ps.setDate(3, m.getFecha());
		this.ps.setInt(4, m.getEmisor());
		this.ps.setInt(5, m.getReceptor());
		this.ps.setInt(6, m.getIncidencia());
		this.ps.setInt(7, m.getId());
		this.ps.executeUpdate();
		this.ps.close();

		this.ps.close();
	}

	public boolean eliminarMensajes(int id) throws SQLException {
		boolean resultado;
		int n = 0;
		this.ps = this.connect.prepareStatement("DELETE FROM mensajes WHERE id = ?");
		this.ps.setInt(1, id);
		n = this.ps.executeUpdate();
		if (n == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		this.ps.close();
		return resultado;
	}

}

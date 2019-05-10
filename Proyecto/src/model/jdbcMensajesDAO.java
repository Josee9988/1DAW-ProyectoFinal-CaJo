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
		this.ps = this.connect.prepareStatement("select * from mensajes");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			aux.add(new mensajesDTO(this.rs.getInt("id"), this.rs.getString("asunto"), this.rs.getString("cuerpo"),
					this.rs.getDate("fecha")));
		}
		return aux;
	}

	public void crearMensaje(mensajesDTO m) throws SQLException {
		this.ps = this.connect.prepareStatement("insert into mensajes(asunto,cuerpo,fecha) values(?,?,?)");
		this.ps.setString(1, m.getAsunto());
		this.ps.setString(2, m.getCuerpo());
		this.ps.setDate(3, m.getFecha());
		this.ps.executeUpdate();
	}

}

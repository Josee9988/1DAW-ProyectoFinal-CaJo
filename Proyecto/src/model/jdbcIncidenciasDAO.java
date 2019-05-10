package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.incidenciaDTO;

public class jdbcIncidenciasDAO implements incidenciasDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;

	public jdbcIncidenciasDAO() {
		this.connect = Conexion.getInstance().conectar();
	}

	public void cerrarBD() throws SQLException {
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}

	public boolean eliminarIncidencia(incidenciaDTO i) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from incidencias where id_incidencia = ?");
		this.ps.setInt(1, i.getId());
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	public ArrayList<incidenciaDTO> leerIncidencias(String nombreCompleto, int rol) throws SQLException {
		ArrayList<incidenciaDTO> incidencias = new ArrayList<>();
		if(rol == 1 || rol == 2) {
			this.ps = this.connect.prepareStatement("select * from incidencias");
		}else {
			this.ps = this.connect.prepareStatement("select * from incidencias where usuario = ?");	
			this.ps.setString(1,nombreCompleto);
		}
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			incidencias.add(new incidenciaDTO(this.rs.getInt("id_incidencia"), this.rs.getString("usuario"),
					this.rs.getString("descripcion"), this.rs.getString("elemento"), this.rs.getString("ubicacion"),
					this.rs.getDate("fecha"), this.rs.getString("urgencia"), this.rs.getString("categoria"),
					this.rs.getString("materiales")));
		}
		return incidencias;
	}

	public boolean modificarIncidencia(incidenciaDTO i) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("update incidencias set urgencia = ? where id_incidencia = ?");
		this.ps.setString(1, i.getUrgencia());
		this.ps.setInt(2, i.getId());
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	public void crearIncidencia(incidenciaDTO i) throws SQLException {
		this.ps = this.connect.prepareStatement(
				"insert into incidencias(usuario,descripcion,elemento,ubicacion,fecha,urgencia,categoria,materiales) values(?,?,?,?,?,?,?,?)");
		this.ps.setString(1, i.getUsuario());
		this.ps.setString(2, i.getDescripcion());
		this.ps.setString(3, i.getElemento());
		this.ps.setString(4, i.getUbicacion());
		this.ps.setDate(5, i.getFecha());
		this.ps.setString(6, i.getUrgencia());
		this.ps.setString(7, i.getCategoria());
		this.ps.setString(8, i.getMateriales());
		this.ps.executeUpdate();
	}

}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.proveedorDTO;

public class jdbcProveedoresDAO implements proveedoresDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;

	public jdbcProveedoresDAO() {
		this.connect = Conexion.getInstance().conectar();
	}

	public void cerrarBD() throws SQLException {
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}
	
	public boolean eliminarProveedor(proveedorDTO p) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from proveedores where id = ?");
		this.ps.setInt(1, p.getId());
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	public ArrayList<proveedorDTO> leerProveedores() throws SQLException {
		ArrayList<proveedorDTO> proveedores = new ArrayList<>();
		this.ps = this.connect.prepareStatement("select * from proveedores");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			proveedores.add(new proveedorDTO(this.rs.getInt("id"), this.rs.getString("nombre"),
					this.rs.getString("contacto"), this.rs.getString("direccion"), this.rs.getInt("valoracion")));
		}
		return proveedores;
	}

	public void agregarProveedor(proveedorDTO p) throws SQLException {
		this.ps = this.connect
				.prepareStatement("insert into proveedores(nombre,contacto,direccion,valoracion) values (?,?,?,?)");
		this.ps.setString(1, p.getNombre());
		this.ps.setString(2, p.getContacto());
		this.ps.setString(3, p.getDireccion());
		this.ps.setInt(4, p.getValoracion());
		this.ps.executeUpdate();
	}

	public boolean modificarProveedor(int id, String valor, String campo) throws SQLException {
		boolean resultado = false;
		switch (campo) {
		case "Nombre":
			this.ps = this.connect.prepareStatement("update proveedores set nombre = ? where id = ?");
			break;
		case "Contacto":
			this.ps = this.connect.prepareStatement("update proveedores set contacto = ? where id = ?");
			break;
		case "Direccion":
			this.ps = this.connect.prepareStatement("update proveedores set direccion = ? where id = ?");
			break;
		case "Valoracion":
			this.ps = this.connect.prepareStatement("update proveedores set valoracion = ? where id = ?");
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

}

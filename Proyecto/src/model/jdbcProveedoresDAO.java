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

import dto.proveedorDTO;

public class jdbcProveedoresDAO implements proveedoresDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Método que llama a la clase "Conexion" y obtiene la instancia del objeto de
	 * tipo "Connection". A éste método de la clase
	 * "Conexion.getInstance().conectar()" le igualaremos nuestra variable "connect"
	 * de tipo Connection
	 */
	public jdbcProveedoresDAO() {
		this.connect = Conexion.getInstance().conectar();
	}

	/**
	 * cierra las conexiones con la base de datos
	 * 
	 * @throws SQLException si ha habido una excepción de tipo SQL
	 */
	public void cerrarBD() throws SQLException {
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}

	@Override
	public boolean eliminarProveedor(int id) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from proveedores where id = ?");
		this.ps.setInt(1, id);
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	@Override
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

	@Override
	public void agregarProveedor(proveedorDTO p) throws SQLException {
		this.ps = this.connect
				.prepareStatement("insert into proveedores(nombre,contacto,direccion,valoracion) values (?,?,?,?)");
		this.ps.setString(1, p.getNombre());
		this.ps.setString(2, p.getContacto());
		this.ps.setString(3, p.getDireccion());
		this.ps.setInt(4, p.getValoracion());
		this.ps.executeUpdate();
	}

	@Override
	public boolean modificarProveedor(proveedorDTO proveedor) throws SQLException {
		boolean resultado = false;
		this.ps = this.connect.prepareStatement(
				"update proveedores set nombre = ?, contacto = ?, direccion = ?, valoracion = ?  where id = ?");
		this.ps.setString(1, proveedor.getNombre());
		this.ps.setString(2, proveedor.getContacto());
		this.ps.setString(3, proveedor.getDireccion());
		this.ps.setInt(4, proveedor.getValoracion());
		this.ps.setInt(5, proveedor.getId());
		this.ps.executeUpdate();
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}

		this.ps.close();
		return resultado;
	}

}

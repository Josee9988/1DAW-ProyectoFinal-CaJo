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

import dto.usuarioDTO;

public class jdbcUsuarioDAO implements usuarioDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;

	public jdbcUsuarioDAO() {
		this.connect = Conexion.getInstance().conectar();
	}

	public void cerrarBD() throws SQLException {
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}

	@Override
	public int comprobarExistencia(usuarioDTO user) throws SQLException {
		int rol = 0;
		this.ps = this.connect.prepareStatement("select * from usuarios where user = ? and password = ?");
		this.ps.setString(1, user.getUser());
		this.ps.setString(2, user.getPassword());
		this.rs = this.ps.executeQuery();
		if (this.rs.next()) {
			rol = this.rs.getInt("rol");
		}
		return rol;
	}

	@Override
	public void crearUsuario(usuarioDTO user) throws SQLException {
		this.ps = this.connect.prepareStatement(
				"insert into usuarios(user,password,rol,nombre,apellidos,telefono,direccion) values (?,?,?,?,?,?,?)");
		this.ps.setString(1, user.getUser());
		this.ps.setString(2, user.getPassword());
		this.ps.setInt(3, user.getRol());
		this.ps.setString(4, user.getNombre());
		this.ps.setString(5, user.getApellidos());
		this.ps.setString(6, user.getTelefono());
		this.ps.setString(7, user.getDireccion());
		this.ps.executeUpdate();
	}

	@Override
	public boolean modificarUsuario(usuarioDTO user) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("update usuarios set rol = ? where user = ?");
		this.ps.setInt(1, user.getRol());
		this.ps.setString(2, user.getUser());
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public String devolverNombre(usuarioDTO user) throws SQLException {
		String nombre = "";
		String apellidos = "";
		this.ps = this.connect.prepareStatement("select * from usuarios where user = ? and password = ?");
		this.ps.setString(1, user.getUser());
		this.ps.setString(2, user.getPassword());
		this.rs = this.ps.executeQuery();
		if (this.rs.next()) {
			nombre = this.rs.getString("nombre");
			apellidos = this.rs.getString("apellidos");
		}
		return nombre.concat(" ").concat(apellidos);
	}

	public ArrayList<usuarioDTO> leerUsuarios() throws SQLException {
		ArrayList<usuarioDTO> usuarios = new ArrayList<>();
		this.ps = this.connect.prepareStatement("select * from usuarios");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			usuarios.add(new usuarioDTO(this.rs.getInt("id"),this.rs.getString("user"),this.rs.getString("password"),this.rs.getInt("rol"),this.rs.getString("nombre"),this.rs.getString("apellidos"),this.rs.getString("direccion"),this.rs.getString("telefono")));
		}
		return usuarios;
	}

}

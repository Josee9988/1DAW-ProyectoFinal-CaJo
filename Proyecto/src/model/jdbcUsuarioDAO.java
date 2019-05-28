/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import controllerUtilidades.crypto_controller;
import dto.usuarioDTO;

public class jdbcUsuarioDAO implements usuarioDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;
	private crypto_controller crypto_controller;

	/**
	 * Método que llama a la clase "Conexion" y obtiene la instancia del objeto de
	 * tipo "Connection". A éste método de la clase
	 * "Conexion.getInstance().conectar()" le igualaremos nuestra variable "connect"
	 * de tipo Connection
	 */
	public jdbcUsuarioDAO() {
		this.connect = Conexion.getInstance().conectar();
		this.crypto_controller = new crypto_controller();
	}


	@Override
	public int comprobarExistencia(usuarioDTO user) throws SQLException {
		int rol = 0;
		this.ps = this.connect.prepareStatement("SELECT * FROM usuarios WHERE user = ? AND password = ?");
		this.ps.setString(1, user.getUser());
		this.ps.setString(2, user.getPassword());
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			rol = this.rs.getInt("rol");
		}
		this.ps.close();
		this.rs.close();
		return rol;
	}

	@Override
	public void crearUsuario(usuarioDTO user) throws SQLException, InvalidKeyException, NoSuchAlgorithmException,
	NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		this.ps = this.connect.prepareStatement(
				"insert into usuarios(user,password,rol,nombre,apellidos,telefono,direccion) values (?,?,?,?,?,?,?)");
		this.ps.setString(1, user.getUser());
		this.ps.setString(2, this.crypto_controller.encrypt(user.getPassword()));
		this.ps.setInt(3, user.getRol());
		this.ps.setString(4, user.getNombre());
		this.ps.setString(5, user.getApellidos());
		this.ps.setString(6, user.getTelefono());
		this.ps.setString(7, user.getDireccion());
		this.ps.executeUpdate();
		this.ps.close();
	}

	@Override
	public void modificarUsuario(usuarioDTO user) throws SQLException, InvalidKeyException, NoSuchAlgorithmException,
	NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// boolean resultado;
		this.ps = this.connect.prepareStatement(
				"UPDATE usuarios SET user = ?, password = ?, rol = ?, nombre = ?, apellidos = ?, telefono = ?, direccion = ? WHERE id = ?");

		this.ps.setString(1, user.getUser());
		this.ps.setString(2, this.crypto_controller.encrypt(user.getPassword()));
		this.ps.setInt(3, user.getRol());
		this.ps.setString(4, user.getNombre());
		this.ps.setString(5, user.getApellidos());
		this.ps.setString(6, user.getTelefono());
		this.ps.setString(7, user.getDireccion());
		this.ps.setInt(8, user.getId());
		this.ps.executeUpdate();
		this.ps.close();
	}

	@Override
	public String devolverNombre(usuarioDTO user) throws SQLException {
		String nombre = "";
		String apellidos = "";
		this.ps = this.connect.prepareStatement("SELECT * FROM usuarios WHERE user = ? AND password = ?");
		this.ps.setString(1, user.getUser());
		this.ps.setString(2, user.getPassword());
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			nombre = this.rs.getString("nombre");
			apellidos = this.rs.getString("apellidos");
		}
		this.ps.close();
		this.rs.close();
		return nombre.concat(" ").concat(apellidos);
	}

	@Override
	public ArrayList<usuarioDTO> leerUsuarios() throws SQLException, InvalidKeyException, IllegalBlockSizeException,
	BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		ArrayList<usuarioDTO> usuarios = new ArrayList<>();
		this.ps = this.connect.prepareStatement("select * from usuarios");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			usuarios.add(new usuarioDTO(this.rs.getInt("id"), this.rs.getString("user"),
					this.crypto_controller.decrypt(this.rs.getString("password")), this.rs.getInt("rol"),
					this.rs.getString("nombre"), this.rs.getString("apellidos"), this.rs.getString("telefono"),
					this.rs.getString("direccion")));
		}
		this.ps.close();
		this.rs.close();
		return usuarios;
	}

	@Override
	public void eliminarUsuario(int id) throws SQLException {
		this.ps = this.connect.prepareStatement("DELETE FROM usuarios WHERE id = ?");
		this.ps.setInt(1, id);
		this.ps.executeUpdate();
		this.ps.close();
	}

	@Override
	public int devolverId(String nombre, String apellidos) throws SQLException {
		int id = 0;
		this.ps = this.connect
				.prepareStatement("SELECT id FROM usuarios WHERE nombre LIKE ? AND apellidos LIKE ? LIMIT 1");
		this.ps.setString(1, nombre + "%");
		this.ps.setString(2, apellidos + "%");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			id = this.rs.getInt("id");

		}
		this.ps.close();
		this.rs.close();
		return id;
	}

	@Override
	public int devolverId(String user) throws SQLException {
		int id = 0;
		this.ps = this.connect.prepareStatement("SELECT id FROM usuarios WHERE user = ?");
		this.ps.setString(1, user);
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
		String apellidos = "";
		this.ps = this.connect.prepareStatement("SELECT nombre, apellidos FROM usuarios WHERE id = ?");
		this.ps.setInt(1, id);

		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			nombre = this.rs.getString("nombre");
			apellidos = this.rs.getString("apellidos");
		}
		this.ps.close();
		this.rs.close();
		return nombre.concat(" ").concat(apellidos);

	}

	@Override
	public ArrayList<String> leerDestinatarios() throws SQLException {
		ArrayList<String> incidencias = new ArrayList<>();

		this.ps = this.connect.prepareStatement("select user from usuarios");

		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {

			incidencias.add((this.rs.getString("user")));
		}
		this.ps.close();
		this.rs.close();
		return incidencias;
	}

	@Override
	public String leerUsuario(String nombre, String apellidos) throws SQLException {
		String usuario = "";
		this.ps = this.connect.prepareStatement("SELECT user FROM usuarios WHERE nombre LIKE ? AND apellidos LIKE ?");
		this.ps.setString(1, nombre + "%");
		this.ps.setString(2, apellidos + "%");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			usuario = this.rs.getString("user");
		}
		this.ps.close();
		this.rs.close();
		return usuario;
	}

	public boolean userEncontrado(String user) throws SQLException {
		boolean encontrado = false;
		this.ps = this.connect.prepareStatement("SELECT user FROM usuarios WHERE user = ?");
		this.ps.setString(1, user);

		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			encontrado = true;
		}
		this.ps.close();
		this.rs.close();
		return encontrado;

	}

}

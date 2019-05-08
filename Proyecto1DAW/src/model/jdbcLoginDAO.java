package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.incidenciaDTO;
import dto.mensajesDTO;
import dto.proveedorDTO;
import dto.ubicacionDTO;
import dto.usuarioDTO;

public class jdbcLoginDAO implements loginDAO {

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;

	public jdbcLoginDAO() {
		this.connect = this.conectar();
	}

	public void cerrarBD() throws SQLException {
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}

	@Override
	public int comprobarUsuario(usuarioDTO user) throws SQLException {
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

	public boolean eliminarUsuario(usuarioDTO user) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from usuarios where user = ?");
		this.ps.setString(1, user.getUser());
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
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

	public ArrayList<incidenciaDTO> leerIncidencias() throws SQLException {
		ArrayList<incidenciaDTO> incidencias = new ArrayList<>();
		this.ps = this.connect.prepareStatement("select * from incidencias");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			incidencias.add(new incidenciaDTO(this.rs.getInt("id_incidencia"), this.rs.getString("usuario"),
					this.rs.getString("descripcion"), this.rs.getString("elemento"), this.rs.getString("ubicacion"),
					this.rs.getDate("fecha"), this.rs.getString("urgencia"), this.rs.getString("categoria"),
					this.rs.getString("materiales")));
		}
		return incidencias;
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

	public void agregarUbicacion(ubicacionDTO u) throws SQLException {
		this.ps = this.connect
				.prepareStatement("insert into ubicaciones(nombre,descripcion,edificio,planta) values (?,?,?,?)");
		this.ps.setString(1, u.getNombre());
		this.ps.setString(2, u.getDescripcion());
		this.ps.setString(3, u.getEdificio());
		this.ps.setString(4, u.getPlanta());
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

	public boolean modificarUbicacion(int id, String valor, String campo) throws SQLException {
		boolean resultado = false;
		switch (campo) {
		case "Nombre":
			this.ps = this.connect.prepareStatement("update ubicaciones set nombre = ? where id = ?");
			break;
		case "Descripcion":
			this.ps = this.connect.prepareStatement("update ubicaciones set descripcion = ? where id = ?");
			break;
		case "Edificio":
			this.ps = this.connect.prepareStatement("update ubicaciones set edificio = ? where id = ?");
			break;
		case "Planta":
			this.ps = this.connect.prepareStatement("update ubicaciones set planta = ? where id = ?");
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

	public ArrayList<ubicacionDTO> leerNombresUbicaciones() throws SQLException {
		ArrayList<ubicacionDTO> aux = new ArrayList<>();
		this.ps = this.connect.prepareStatement("select nombre from ubicaciones");
		this.rs = this.ps.executeQuery();
		while (this.rs.next()) {
			aux.add(new ubicacionDTO(this.rs.getString("nombre")));
		}
		return aux;
	}

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

	public int obtnerIdUbicacion(String nombre) throws SQLException {
		this.ps = this.connect.prepareStatement("select id from ubicaciones where nombre = ?");
		this.ps.setString(1, nombre);
		this.rs = this.ps.executeQuery();
		this.rs.next();
		return this.rs.getInt("id");
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

	public boolean eliminarUbicacion(ubicacionDTO u) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from ubicaciones where id = ?");
		this.ps.setInt(1, u.getId());
		if (this.ps.executeUpdate() == 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	public void crearMensaje(mensajesDTO m) throws SQLException {
		this.ps = this.connect.prepareStatement("insert into mensajes(asunto,cuerpo,fecha) values(?,?,?)");
		this.ps.setString(1, m.getAsunto());
		this.ps.setString(2, m.getCuerpo());
		this.ps.setDate(3, m.getFecha());
		this.ps.executeUpdate();
	}

	public Connection conectar() {

		Connection Conexion = null; // hacemos la reserva de memoria del objeto Connection
		try {
			// establecemos la conexión con DriverManager.getConnection
			Conexion = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/proyectodaw?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "1234");
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

		return Conexion; // devuelve la conexión realizada
	}

}

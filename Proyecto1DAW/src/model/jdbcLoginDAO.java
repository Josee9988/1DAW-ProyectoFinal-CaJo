package model;

import java.sql.*;
import java.util.ArrayList;

import dto.incidenciaDTO;
import dto.mensajesDTO;
import dto.proveedorDTO;
import dto.ubicacionDTO;
import dto.usuarioDTO;

public class jdbcLoginDAO implements loginDAO{

	private Connection connect;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public jdbcLoginDAO() {
		this.connect = conectar();
	}
	
	public void cerrarBD() throws SQLException{
		this.ps.close();
		this.rs.close();
		this.connect.close();
	}
	
	@Override
	public int comprobarUsuario(usuarioDTO user) throws SQLException {
		int rol = 0;
		this.ps = this.connect.prepareStatement("select * from usuarios where user = ? and password = ?");
		this.ps.setString(1,user.getUser());
		this.ps.setString(2,user.getPassword());
		this.rs = this.ps.executeQuery();
		if(this.rs.next()) {
			rol = rs.getInt("rol"); 
		}
		return rol;
	}
	
	public void crearUsuario(usuarioDTO user) throws SQLException {
		this.ps = this.connect.prepareStatement("insert into usuarios(user,password,rol,nombre,apellidos,telefono,direccion) values (?,?,?,?,?,?,?)");
		this.ps.setString(1,user.getUser());
		this.ps.setString(2,user.getPassword());
		this.ps.setInt(3,user.getRol());
		this.ps.setString(4,user.getNombre());
		this.ps.setString(5,user.getApellidos());
		this.ps.setString(6,user.getTelefono());
		this.ps.setString(7,user.getDireccion());
		this.ps.executeUpdate();
	}
	
	public boolean modificarUsuario(usuarioDTO user) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("update usuarios set rol = ? where user = ?");
		this.ps.setInt(1,user.getRol());
		this.ps.setString(2,user.getUser());
		if(this.ps.executeUpdate()==1) {
			resultado = true;
		}else {
			resultado = false;
		}
	  return resultado;
	}
	
	public boolean eliminarUsuario(usuarioDTO user) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from usuarios where user = ?");
		this.ps.setString(1,user.getUser());
		if(this.ps.executeUpdate()==1) {
			resultado = true;
		}else {
			resultado = false;
		}
	  return resultado;
	}
	
	public boolean eliminarProveedor(proveedorDTO p) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from proveedores where id = ?");
		this.ps.setInt(1,p.getId());
		if(this.ps.executeUpdate()==1) {
			resultado = true;
		}else {
			resultado = false;
		}
	  return resultado;
	}
	
	public boolean eliminarIncidencia(incidenciaDTO i) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from incidencias where id_incidencia = ?");
		this.ps.setInt(1,i.getId());
		if(this.ps.executeUpdate()==1) {
			resultado = true;
		}else {
			resultado = false;
		}
	  return resultado;
	}
	
	public String devolverNombre(usuarioDTO user) throws SQLException {
		String nombre = "";
		String apellidos = "";
		this.ps = this.connect.prepareStatement("select * from usuarios where user = ? and password = ?");
		this.ps.setString(1,user.getUser());
		this.ps.setString(2,user.getPassword());
		this.rs = this.ps.executeQuery();
		if(this.rs.next()) {
			nombre = rs.getString("nombre");
			apellidos = rs.getString("apellidos");
		}
		return nombre.concat(" ").concat(apellidos);
	}
	
	public ArrayList<incidenciaDTO> leerIncidencias() throws SQLException{
		ArrayList<incidenciaDTO> incidencias = new ArrayList<incidenciaDTO>();
		this.ps = this.connect.prepareStatement("select * from incidencias");
		this.rs = this.ps.executeQuery();
		while(rs.next()) {
			incidencias.add(new incidenciaDTO(rs.getInt("id_incidencia"),rs.getString("usuario"),rs.getString("descripcion"),rs.getString("elemento"),rs.getString("ubicacion"),rs.getDate("fecha"),rs.getString("urgencia")
					,rs.getString("categoria"),rs.getString("materiales")));
		}
		return incidencias;
	}
	
	public ArrayList<proveedorDTO> leerProveedores() throws SQLException{
		ArrayList<proveedorDTO> proveedores = new ArrayList<proveedorDTO>();
		this.ps = this.connect.prepareStatement("select * from proveedores");
		this.rs = this.ps.executeQuery();
		while(rs.next()) {
			proveedores.add(new proveedorDTO(rs.getInt("id"),rs.getString("nombre"),rs.getString("contacto"),rs.getString("direccion"),rs.getInt("valoracion")));
		}
		return proveedores;
	}
	
	public void agregarProveedor(proveedorDTO p) throws SQLException {
		this.ps = this.connect.prepareStatement("insert into proveedores(nombre,contacto,direccion,valoracion) values (?,?,?,?)");
		this.ps.setString(1, p.getNombre());
		this.ps.setString(2, p.getContacto());
		this.ps.setString(3, p.getDireccion());
		this.ps.setInt(4, p.getValoracion());
		this.ps.executeUpdate();
	}
	
	public void agregarUbicacion(ubicacionDTO u) throws SQLException {
		this.ps = this.connect.prepareStatement("insert into ubicaciones(nombre,descripcion,edificio,planta) values (?,?,?,?)");
		this.ps.setString(1,u.getNombre());
		this.ps.setString(2,u.getDescripcion());
		this.ps.setString(3,u.getEdificio());
		this.ps.setString(4,u.getPlanta());
		this.ps.executeUpdate();
	}
	
	public boolean modificarProveedor(int id, String valor, String campo) throws SQLException {
		boolean resultado = false;
		switch (campo) {
		case "Nombre":this.ps = this.connect.prepareStatement("update proveedores set nombre = ? where id = ?");
			break;
		case "Contacto":this.ps = this.connect.prepareStatement("update proveedores set contacto = ? where id = ?");
			break;
		case "Direccion":this.ps = this.connect.prepareStatement("update proveedores set direccion = ? where id = ?");
			break;
		case "Valoracion":this.ps = this.connect.prepareStatement("update proveedores set valoracion = ? where id = ?");
			break;
		}
		this.ps.setString(1, valor);
		this.ps.setInt(2, id);
		if(this.ps.executeUpdate()==1) {
			resultado = true;
		}else {
			resultado = false;
		}
		return resultado;
	}
	
	public boolean modificarUbicacion(int id, String valor, String campo) throws SQLException {
		boolean resultado = false;
		switch (campo) {
		case "Nombre":this.ps = this.connect.prepareStatement("update ubicaciones set nombre = ? where id = ?");
			break;
		case "Descripcion":this.ps = this.connect.prepareStatement("update ubicaciones set descripcion = ? where id = ?");
			break;
		case "Edificio":this.ps = this.connect.prepareStatement("update ubicaciones set edificio = ? where id = ?");
			break;
		case "Planta":this.ps = this.connect.prepareStatement("update ubicaciones set planta = ? where id = ?");
			break;
		}
		this.ps.setString(1, valor);
		this.ps.setInt(2, id);
		if(this.ps.executeUpdate()==1) {
			resultado = true;
		}else {
			resultado = false;
		}
		return resultado;
	}
	
	public boolean modificarIncidencia(incidenciaDTO i) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("update incidencias set urgencia = ? where id_incidencia = ?");
		this.ps.setString(1,i.getUrgencia());
		this.ps.setInt(2,i.getId());
		if(this.ps.executeUpdate()==1) {
			resultado = true;
		}else {
			resultado = false;
		}
	  return resultado;
	}
	
	public void crearIncidencia(incidenciaDTO i) throws SQLException {
		this.ps = this.connect.prepareStatement("insert into incidencias(usuario,descripcion,elemento,ubicacion,fecha,urgencia,categoria,materiales) values(?,?,?,?,?,?,?,?)");
		this.ps.setString(1,i.getUsuario());
		this.ps.setString(2,i.getDescripcion());
		this.ps.setString(3,i.getElemento());
		this.ps.setString(4,i.getUbicacion());
		this.ps.setDate(5,i.getFecha());
		this.ps.setString(6,i.getUrgencia());
		this.ps.setString(7,i.getCategoria());
		this.ps.setString(8,i.getMateriales());
		this.ps.executeUpdate();
	}
	
	public ArrayList<ubicacionDTO> leerNombresUbicaciones() throws SQLException {
		ArrayList<ubicacionDTO> aux = new ArrayList<ubicacionDTO>();
		this.ps = this.connect.prepareStatement("select nombre from ubicaciones");
		this.rs = this.ps.executeQuery();
		while(rs.next()) {
			aux.add(new ubicacionDTO(rs.getString("nombre")));
		}
		return aux;		
	}
	
	public ArrayList<ubicacionDTO> leerUbicaciones() throws SQLException {
		ArrayList<ubicacionDTO> aux = new ArrayList<ubicacionDTO>();
		this.ps = this.connect.prepareStatement("select * from ubicaciones");
		this.rs = this.ps.executeQuery();
		while(rs.next()) {
			aux.add(new ubicacionDTO(rs.getInt("id"),rs.getString("nombre"),rs.getString("descripcion"),rs.getString("edificio"),rs.getString("planta")));
		}
		return aux;		
	}
	
	public int obtnerIdUbicacion(String nombre) throws SQLException {
		this.ps = this.connect.prepareStatement("select id from ubicaciones where nombre = ?");
		this.ps.setString(1,nombre);
		this.rs = this.ps.executeQuery();
		this.rs.next();
		return this.rs.getInt("id");
	}
	
	public ArrayList<mensajesDTO> leerMensajes() throws SQLException {
		ArrayList<mensajesDTO> aux = new ArrayList<mensajesDTO>();
		this.ps = this.connect.prepareStatement("select * from mensajes");
		this.rs = this.ps.executeQuery();
		while(rs.next()) {
			aux.add(new mensajesDTO(rs.getInt("id"),rs.getString("asunto"),rs.getString("cuerpo"),rs.getDate("fecha")));
		}
		return aux;	
	}
	
	public boolean eliminarUbicacion(ubicacionDTO u) throws SQLException {
		boolean resultado;
		this.ps = this.connect.prepareStatement("delete from ubicaciones where id = ?");
		this.ps.setInt(1,u.getId());
		if(this.ps.executeUpdate()==1) {
			resultado = true;
		}else {
			resultado = false;
		}
	  return resultado;
	}
	
	public void crearMensaje(mensajesDTO m) throws SQLException {
		this.ps = this.connect.prepareStatement("insert into mensajes(asunto,cuerpo,fecha) values(?,?,?)");
		this.ps.setString(1,m.getAsunto());
		this.ps.setString(2,m.getCuerpo());
		this.ps.setDate(3,m.getFecha());
		this.ps.executeUpdate();
	}
	
	public Connection conectar(){	
		Connection conexion=null;	
		try{		
			conexion= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/_?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC","","");
		}catch(java.sql.SQLException sql){
			System.out.println("Error" + sql);		
		}
      return conexion;
	}
	
}

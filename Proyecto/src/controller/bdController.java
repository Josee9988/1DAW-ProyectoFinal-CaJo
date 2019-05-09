//package controller;
//
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import dto.incidenciaDTO;
//import dto.mensajesDTO;
//import dto.proveedorDTO;
//import dto.ubicacionDTO;
//import dto.usuarioDTO;
//import model.jdbcUsuarioDAO;
//
//public class bdController {
//
//	private static bdController instance;
//	private jdbcUsuarioDAO bd;
//	private ArrayList<incidenciaDTO> incidencias;
//
//	private bdController() {
//		this.incidencias = new ArrayList<incidenciaDTO>();
//		this.bd = new jdbcUsuarioDAO();
//	}
//
//	public static bdController getInstance() {
//		if (instance == null) {
//			instance = new bdController();
//		}
//		return instance;
//	}
//	
//	public void crearUsuario(String user,String password, int rol, String nombre, String apellidos, String telefono, String direccion) throws SQLException {
//		this.bd.crearUsuario(new usuarioDTO(user,password,rol,nombre,apellidos,telefono,direccion));
//	}
//	
//	public void agregarUbicacion(String nombre, String descripcion, String edificio, String planta) throws SQLException {
//		this.bd.agregarUbicacion(new ubicacionDTO(nombre,descripcion,edificio,planta));
//	}
//	
//	public boolean eliminarUsuario(String userName) throws SQLException {
//		return this.bd.eliminarUsuario(new usuarioDTO(new usuarioDTO(userName)));
//	}
//	
//	public boolean modificarUsuario(String userName, int rol) throws SQLException {
//		return this.bd.modificarUsuario(new usuarioDTO(new usuarioDTO(userName,rol)));
//	}
//
//	public int ComprobarExistencia(String usuario, String password) throws SQLException {
//		return this.bd.comprobarUsuario(new usuarioDTO(usuario, password));
//	}
//
//	public String NombreApellidos(String usuario, String password) throws SQLException {
//		return this.bd.devolverNombre(new usuarioDTO(usuario, password));
//	}
//
//	public void nuevaIncidencia(String usuario, String descripcion, String elemento, String ubicacion, Date fecha,
//			String urgencia, String categoria, String materiales) throws SQLException {
//		this.bd.crearIncidencia(
//				new incidenciaDTO(usuario, descripcion, elemento, Integer.toString(this.bd.obtnerIdUbicacion(ubicacion)), fecha, urgencia, categoria, materiales));
//	}
//
//	public ArrayList<incidenciaDTO> leerIncidencias(String nombreCompleto, int rol) throws SQLException {
//		this.incidencias = new ArrayList<incidenciaDTO>();
//		if(rol == 1 || rol == 2) {
//			for (incidenciaDTO x : this.bd.leerIncidencias()) {
//				if (x.getUsuario().equals(nombreCompleto)) {
//					this.incidencias.add(x);
//				}
//			}
//		}else{
//			for (incidenciaDTO x : this.bd.leerIncidencias()) {
//				this.incidencias.add(x);
//			}
//		}
//		return this.incidencias;
//	}
//	
//	public ArrayList<proveedorDTO> leerProveedores() throws SQLException {
//		return this.bd.leerProveedores();
//	}
//	
//	public ArrayList<ubicacionDTO> leerUbicaciones() throws SQLException {
//		return this.bd.leerUbicaciones();
//	}
//	
//	public ArrayList<mensajesDTO> leerMensajes() throws SQLException {
//		return this.bd.leerMensajes();
//	}
//	
//	public void crearMensaje(String asunto, String cuerpo, Date fecha) throws SQLException {
//		this.bd.crearMensaje(new mensajesDTO(asunto,cuerpo,fecha));
//	}
//	
//	public ArrayList<String> leerNombresUbicaciones() throws SQLException {
//		ArrayList<String> nombres = new ArrayList<String>();
//		for (ubicacionDTO x : this.bd.leerNombresUbicaciones()) {
//			nombres.add(x.getNombre());
//		}
//		return nombres;
//	}
//	
//	public void agregarProveedor(String nombre, String contacto, String direccion, int valoracion) throws SQLException {
//		this.bd.agregarProveedor(new proveedorDTO(nombre,contacto,direccion,valoracion));
//	}
//	
//	public boolean modificarProveedor(int id, String valor, String campo) throws SQLException {
//		return this.bd.modificarProveedor(id, valor, campo);	
//	}
//	
//	public boolean modificarUbicacion(int id, String valor, String campo) throws SQLException {
//		return this.bd.modificarUbicacion(id, valor, campo);	
//	}
//	
//	public boolean eliminarProveedor(int id) throws SQLException {
//		return this.bd.eliminarProveedor(new proveedorDTO(id));
//	}
//	
//	public boolean eliminarUbicacion(int id) throws SQLException {
//		return this.bd.eliminarUbicacion(new ubicacionDTO(id));
//	}
//	
//	public boolean eliminarIncidencia(int id) throws SQLException {
//		return this.bd.eliminarIncidencia(new incidenciaDTO(id));
//	}
//	
//	public boolean modificarIncidencia(int id, String urgencia) throws SQLException {
//		return this.bd.modificarIncidencia(new incidenciaDTO(id,urgencia));
//	}	
//
//}

package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.incidenciaDTO;
import dto.usuarioDTO;

public interface usuarioDAO {
	public int comprobarUsuario(usuarioDTO user) throws SQLException;
	public void crearUsuario(usuarioDTO user) throws SQLException;
	public boolean modificarUsuario(usuarioDTO user) throws SQLException;
	public String devolverNombre(usuarioDTO user) throws SQLException;
	public ArrayList<incidenciaDTO> leerIncidencias() throws SQLException;
}

package model;

import java.sql.SQLException;
import dto.usuarioDTO;

public interface usuarioDAO {
	public int comprobarExistencia(usuarioDTO user) throws SQLException;
	public void crearUsuario(usuarioDTO user) throws SQLException;
	public boolean modificarUsuario(usuarioDTO user) throws SQLException;
	public String devolverNombre(usuarioDTO user) throws SQLException;
}

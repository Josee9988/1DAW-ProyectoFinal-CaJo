package model;

import java.sql.SQLException;
import dto.usuarioDTO;

public interface loginDAO {
	public int comprobarUsuario(usuarioDTO user) throws SQLException;
}

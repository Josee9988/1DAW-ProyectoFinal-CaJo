/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.SQLException;

import dto.usuarioDTO;

public interface usuarioDAO {
	int comprobarExistencia(usuarioDTO user) throws SQLException;
	void crearUsuario(usuarioDTO user) throws SQLException;
	boolean modificarUsuario(usuarioDTO user) throws SQLException;
	String devolverNombre(usuarioDTO user) throws SQLException;
}

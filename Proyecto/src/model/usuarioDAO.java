/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.incidenciaDTO;
import dto.usuarioDTO;

public interface usuarioDAO {
	int comprobarUsuario(usuarioDTO user) throws SQLException;

	void crearUsuario(usuarioDTO user) throws SQLException;

	boolean modificarUsuario(usuarioDTO user) throws SQLException;

	String devolverNombre(usuarioDTO user) throws SQLException;

	ArrayList<incidenciaDTO> leerIncidencias() throws SQLException;
}

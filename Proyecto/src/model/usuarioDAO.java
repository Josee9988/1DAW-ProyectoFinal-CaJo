/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dto.usuarioDTO;

public interface usuarioDAO {
	int comprobarExistencia(usuarioDTO user) throws SQLException;

	void crearUsuario(usuarioDTO user) throws SQLException;

	void modificarUsuario(usuarioDTO user) throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

	void eliminarUsuario(int id) throws SQLException;

	String devolverNombre(usuarioDTO user) throws SQLException;
}

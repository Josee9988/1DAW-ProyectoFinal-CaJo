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
	/**
	 * comprobarExistencia comprueba un usuario y devuelve el rol
	 * 
	 * @param user el usuario a comprobar
	 * @return rol devuelve el rol (int)
	 * @throws SQLException si ha habido una excepción SQL
	 */
	int comprobarExistencia(usuarioDTO user) throws SQLException;

	/**
	 * crearUsuario crea el usuario en la base de datos
	 * 
	 * @param user objeto usuarioDTO
	 * @throws SQLException              si ha habido una excepción SQL
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 */
	void crearUsuario(usuarioDTO user) throws SQLException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * modificarUsuario modifica el usuario
	 * 
	 * @param user recibe el objeto usuarioDTO
	 * @throws SQLException              si ha habido una excepción SQL
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 */
	void modificarUsuario(usuarioDTO user) throws SQLException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException;

	/**
	 * eliminarUsuario elimina un usuario a través de una ID
	 * 
	 * @param id recibe la id a eliminar
	 * @throws SQLException si ha habido una excepción SQL
	 */
	void eliminarUsuario(int id) throws SQLException;

	/**
	 * devolverNombre devuelve el nombre de un usuario
	 * 
	 * @param user recibe el objeto usuarioDTO
	 * @return devuelve una string con el nombre
	 * @throws SQLException si ha habido una excepción SQL
	 */
	String devolverNombre(usuarioDTO user) throws SQLException;
}

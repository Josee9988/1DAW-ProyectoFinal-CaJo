/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

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
	 * @throws SQLException              si ha habido una excepción SQL
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
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
	 * devolverNombre devuelve el nombre de un usuario
	 *
	 * @param user recibe el objeto usuarioDTO
	 * @return devuelve una string con el nombre
	 * @throws SQLException si ha habido una excepción SQL
	 */
	String devolverNombre(usuarioDTO user) throws SQLException;

	/**
	 * leerUsuarios lee todos los usuarios de la base de datos y los almacena en un
	 * ArrayList de tipo usuarioDTO
	 *
	 * @return devuelve un ArrayList de tipo usuarioDTO con todos los usuarios
	 *         encontrados
	 * @throws SQLException              si ha habido una excepción SQL
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 */
	ArrayList<usuarioDTO> leerUsuarios() throws SQLException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException;

	/**
	 * eliminarUsuario elimina un usuario a través de una ID
	 *
	 * @param id recibe la id a eliminar
	 * @throws SQLException si ha habido una excepción SQL
	 */
	void eliminarUsuario(int id) throws SQLException;

	/**
	 * devolverId devuelve una id a partir de un nombre y un apellido
	 *
	 * @param nombre    String nombre recibido
	 * @param apellidos String apellidos recibidos
	 * @return devuelve la id encontrada
	 * @throws SQLException si ha habido una excepción SQL
	 */
	int devolverId(String nombre, String apellidos) throws SQLException;

	/**
	 * devolverId devuelve una id a partir de un nick de usuario
	 *
	 * @param user String del nick del usuario en concreto
	 * @return devuelve un entero con la id encontrada
	 * @throws SQLException si ha habido una excepción SQL
	 */
	int devolverId(String user) throws SQLException;

	/**
	 * devolverNombre devuelve un nombre a partir de una id
	 *
	 * @param id entero id recibida para encontrar un nombre
	 * @return devuelve un String con el nombre encontrado
	 * @throws SQLException si ha habido una excepción SQL
	 */
	String devolverNombre(int id) throws SQLException;

	/**
	 * leerDestinatarios lee todos los nicks de usuarios encontrados en la base de
	 * datos
	 *
	 * @return devuelve un ArrayList de tipo String con los nicks de usuarios
	 *         encontrados
	 * @throws SQLException si ha habido una excepción SQL
	 */
	ArrayList<String> leerDestinatarios() throws SQLException;

	/**
	 * leerUsuario lee un nick de usuario a partir de un Nombre y un Apellido
	 *
	 * @param nombre    String nombre recibido
	 * @param apellidos String apellidos recibidos
	 * @return devuelve un string con el nick de usuario encontrado
	 * @throws SQLException si ha habido una excepción SQL
	 */
	String leerUsuario(String nombre, String apellidos) throws SQLException;

	/**
	 * filtrar es llamado por la clase consultar correspondiente y busca los
	 * elementos con un like y devolverá un arraylist con todos los objetos
	 * encontrados * @param texto texto a buscar en diferentes campos
	 * 
	 * @return devuelve un arrayList con todos los objetos encontrados
	 * @throws SQLException              si ha habido una excepción SQL
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 */
	ArrayList<usuarioDTO> filtrar(String texto) throws SQLException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException;

	/**
	 * userEncontrado busca en la base de datos un usuario en concreto recibido por
	 * parámetro
	 * 
	 * @param user el usuario a buscar
	 * @return devuelve true si lo ha encontrado o false si no existe
	 * @throws SQLException si ha habido una excepción SQL
	 */
	boolean userEncontrado(String user) throws SQLException;

	/**
	 * obtenerUser busca en la base de datos un usuario en concreto recibiendo por
	 * parámetro el nombre completo
	 * 
	 * @param nombre completo a buscar
	 * @return devuelve el username
	 * @throws SQLException si ha habido una excepción SQL
	 */
	String obtenerUser(String nombrecompleto) throws SQLException;

	/**
	 * obtenerRol busca en la base de datos el rol de usuario en concreto, recibiendo por
	 * parámetro el nombre completo
	 * 
	 * @param nombre completo a buscar
	 * @return devuelve el rol
	 * @throws SQLException si ha habido una excepción SQL
	 */
	int obtenerRol(String nombreCompleto) throws SQLException;
}

/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.mensajesDTO;

public interface mensajesDAO {

	/**
	 * leerMensajes se lee los mensajes y se devuelven los registros en forma de
	 * arraylist
	 *
	 * @param user es el nick de usuario. como id entero
	 * @param rol  es el rol que recibe, si es jefe o profesor solo mostrará los
	 *             suyos.
	 * @return ArrayList de mensajesDTO que ha encontrado.
	 * @throws SQLException si ha habido una excepción SQL
	 */
	ArrayList<mensajesDTO> leerMensajes(int user, int rol) throws SQLException;

	/**
	 * crearMensaje crea el mensaje en la base de dato
	 *
	 * @param m objeto mensajesDTO
	 * @throws SQLException si hay una excepción SQL
	 */
	void crearMensaje(mensajesDTO m) throws SQLException;

	/**
	 * modificarMensaje modifica un mensaje, agrega todos los datos nuevos
	 * recibiendo un objeto mensajes
	 *
	 * @param m recibe el objeto mensajes y modifica todos los valores a la id que
	 *          referencia
	 * @throws SQLException si hay una excepción SQL
	 */
	void modificarMensaje(mensajesDTO m) throws SQLException;

	/**
	 * eliminarMensajes elimina un mensaje de la base de datos a partir de una id
	 *
	 * @param id entero id a eliminar
	 * @return devuelve un booleano true si es correcto o false si ha habido un
	 *         problema
	 * @throws SQLException si hay una excepción SQL
	 */
	boolean eliminarMensajes(int id) throws SQLException;

}

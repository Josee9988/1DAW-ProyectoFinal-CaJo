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
	 * leerMensajes se lee los mensajes y se devuelven los registros en forma de arraylist
	 * @return ArrayList de mensajesDTO que ha encontrado.
	 * @throws SQLException si ha habido una excepción SQL
	 */
	ArrayList<mensajesDTO> leerMensajes() throws SQLException;

	/**
	 * crearMensaje crea el mensaje en la base de dato
	 * @param m objeto mensajesDTO
	 * @throws SQLException si hay una excepción SQL
	 */
	void crearMensaje(mensajesDTO m) throws SQLException;

}

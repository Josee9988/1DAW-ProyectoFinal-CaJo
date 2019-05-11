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

	ArrayList<mensajesDTO> leerMensajes() throws SQLException;

	void crearMensaje(mensajesDTO m) throws SQLException;

}

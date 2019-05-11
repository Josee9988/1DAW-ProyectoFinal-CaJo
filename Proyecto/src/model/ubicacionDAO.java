/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.ubicacionDTO;

public interface ubicacionDAO {
	void agregarUbicacion(ubicacionDTO u) throws SQLException;

	boolean modificarUbicacion(int id, String valor, String campo) throws SQLException;

	boolean eliminarUbicacion(ubicacionDTO u) throws SQLException;

	ArrayList<ubicacionDTO> leerUbicaciones() throws SQLException;

	int obtnerIdUbicacion(String nombre) throws SQLException;
}

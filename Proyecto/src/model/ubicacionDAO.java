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
	/**
	 * agregarUbicacion agrega una ubicación en la base de datos
	 * 
	 * @param u objeto ubicacionDTO
	 * @throws SQLException si ha habido una excepción SQL
	 */
	void agregarUbicacion(ubicacionDTO u) throws SQLException;

	/**
	 * leerUbicaciones lee las ubicaciones y las devuelve todas en un arraylist
	 * 
	 * @return devuelve todas las ubicaciones en un arraylist de ubicacionDTO
	 * @throws SQLException si ha habido una excepción SQL
	 */
	ArrayList<ubicacionDTO> leerUbicaciones() throws SQLException;

	/**
	 * obtnerIdUbicacion obtiene el id de la ubicación
	 * 
	 * @param nombre nombre de la ubicación a buscar
	 * @return devuelve un entero con la id
	 * @throws SQLException si ha habido una excepción SQL
	 */
	int obtenerIdUbicacion(String nombre) throws SQLException;

	/**
	 * eliminarUbicacion elimina una ubicación de la base de datos
	 * 
	 * @param id recibe la id para eliminarla
	 * @return devuelve un booleano de si todo ha ido bien o no
	 * @throws SQLException si ha habido una excepción SQL
	 */
	boolean eliminarUbicacion(int id) throws SQLException;

	/**
	 * modificarUbicacion modifica una ubicación a partir de un objeto ubicacionDTO
	 * 
	 * @param ubicacion recibe el objeto ubicacionDTO
	 * @return devuelve un booleano de si todo ha ido bien o no
	 * @throws SQLException si ha habido una excepción SQL
	 */
	boolean modificarUbicacion(ubicacionDTO ubicacion) throws SQLException;
}

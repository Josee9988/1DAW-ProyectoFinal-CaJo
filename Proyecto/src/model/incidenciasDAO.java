/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.incidenciaDTO;
import dto.usuarioDTO;

public interface incidenciasDAO {
	/**
	 * leerIncidencias lee las incidencias de la BD y las devuelve
	 * 
	 * @param u objeto usuarioDTO
	 * @return devuelve un ArrayList las incidencias encontradas
	 * @throws SQLException si hay una excepción SQL
	 */
	ArrayList<incidenciaDTO> leerIncidencias(usuarioDTO u) throws SQLException;

	/**
	 * modificarIncidencia modifica una indicencia
	 * 
	 * @param i objeto incidenciaDTO a modificar
	 * @return devuelve un booleano si se ha modificado o no
	 * @throws SQLException si hay una excepción SQL
	 */
	boolean modificarIncidencia(incidenciaDTO i) throws SQLException;

	/**
	 * crearIncidencia crea la incidencia en una base de datos
	 * 
	 * @param i objeto de incidenciaDTO
	 * @throws SQLException excepción SQL
	 */
	void crearIncidencia(incidenciaDTO i) throws SQLException;

	/**
	 * eliminarIncidencia elimina una incidencia en la base de datos recibiendo una
	 * ID
	 * 
	 * @param i es la id que recibe a eliminar
	 * @return
	 * @throws SQLException
	 */
	boolean eliminarIncidencia(int i) throws SQLException;
}

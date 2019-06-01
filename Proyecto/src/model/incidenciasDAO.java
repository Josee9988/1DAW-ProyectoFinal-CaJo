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
	 * @return true si ha ido todo bien o false si no se ha eliminado
	 * @throws SQLException excepción SQL
	 */
	boolean eliminarIncidencia(int i) throws SQLException;

	/**
	 * leerIncidencias guarda todas las Ids de las incidencias en un ArrayList de
	 * tipo Integer
	 *
	 * @return ArrayList<Integer> con todas las incidencias
	 * @throws SQLException excepción SQL
	 */
	ArrayList<Integer> leerIncidencias() throws SQLException;

	/**
	 * obtenerNombreIncidencia recibe un id y devuelve el nombre encontrado a partir
	 * de esa ID
	 *
	 * @param id entero id recibido, es la id a buscar en la base de datos
	 * @return String del nombre encontrado
	 * @throws SQLException excepción SQL
	 */
	String obtenerNombreIncidencia(int id) throws SQLException;

	/**
	 * obtenerId REcibe una descripción y devuelve el id encontrado a partir de esa
	 * descripción
	 *
	 * @param descripcion String con la descripción a buscar en la base de datos
	 * @return devuelve un entero con la id encontrada
	 * @throws SQLException excepción SQL
	 */
	int obtenerId(String descripcion) throws SQLException;

	/**
	 * leerDescripcionesIncidenciasEspecificas lee las descripciones y las guarda en
	 * un ArrayList
	 *
	 * @return devuelve un ArrayList de tipo string con las descripciones de las
	 *         incidencias
	 * @throws SQLException excepción SQL
	 */
	ArrayList<String> leerDescripcionesIncidenciasEspecificas(usuarioDTO u) throws SQLException;

	/**
	 * filtrar es llamado por la clase consultar correspondiente y busca los
	 * elementos con un like y devolverá un arraylist con todos los objetos
	 * encontrados
	 *
	 * @param u     usuarioDTO en el cual obtendremos la id y el rol
	 * @param texto texto a buscar en diferentes campos
	 *
	 * @return devuelve un arrayList con todos los objetos encontrados
	 * @throws SQLException si ha habido una excepción de tipo SQL
	 */
	ArrayList<incidenciaDTO> filtrar(usuarioDTO u, String texto) throws SQLException;

}

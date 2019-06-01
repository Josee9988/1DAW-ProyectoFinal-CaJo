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
	 * @throws SQLException si ha habido una excepción SQL
	 */
	void modificarUbicacion(ubicacionDTO ubicacion) throws SQLException;

	/**
	 * leerNombresUbicaciones lee todos los nombres de las ubicaciones y las va
	 * almacenando en un ArrayList de tipo String
	 *
	 * @return devuelve el ArrayList de tipo String relleno con todos los nombres
	 * @throws SQLException si ha habido una excepción SQL
	 */
	ArrayList<String> leerNombresUbicaciones() throws SQLException;

	/**
	 * devolverNombre devuelve un String con un nombre a partir de una id recibida.
	 *
	 * @param id entero id recibida que buscará en la base de datos dicha id para
	 *           obtener el nombre
	 * @return devuelve un String con el nombre
	 * @throws SQLException si ha habido una excepción SQL
	 */
	String devolverNombre(int id) throws SQLException;

	/**
	 * filtrar es llamado por la clase consultar correspondiente y busca los
	 * elementos con un like y devolverá un arraylist con todos los objetos
	 * encontrados
	 *
	 * @param texto texto a buscar en diferentes campos
	 * @return devuelve un arrayList con todos los objetos encontrados
	 * @throws SQLException si ha habido una excepción de tipo SQL
	 */
	ArrayList<ubicacionDTO> filtrar(String texto) throws SQLException;
}

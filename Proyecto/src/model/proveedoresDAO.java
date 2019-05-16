/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.proveedorDTO;

public interface proveedoresDAO {

	/**
	 * leerProveedores lee los proveedores y devuelve un arraylist con todos los de
	 * la base de datos
	 * 
	 * @return ArrayList<proveedorDTO> con todos los registros de la base de datos
	 * @throws SQLException si ha habido una excepción SQL
	 */
	ArrayList<proveedorDTO> leerProveedores() throws SQLException;

	/**
	 * agregarProveedor agrega un proveedor a la base de datos
	 * 
	 * @param p objeto proveedorDTO a agregar
	 * @throws SQLException si ha habido una excepción SQL
	 */
	void agregarProveedor(proveedorDTO p) throws SQLException;

	/**
	 * eliminarProveedor elimina un proveedor de la base de datos
	 * 
	 * @param id id a elimianr
	 * @return devuelve un booleano si ha ido todo correcto o no
	 * @throws SQLException si ha habido una excepción SQL
	 */
	boolean eliminarProveedor(int id) throws SQLException;

	/**
	 * modificarProveedor modifica un proveedor
	 * 
	 * @param proveedor objeto con los nuevos datos de proveedor
	 * @return devuelve un booleano si ha ido todo correcto o no
	 * @throws SQLException si ha habido una excepción SQL
	 */
	boolean modificarProveedor(proveedorDTO proveedor) throws SQLException;

}

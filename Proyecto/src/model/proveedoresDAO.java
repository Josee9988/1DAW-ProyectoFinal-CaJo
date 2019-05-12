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

	ArrayList<proveedorDTO> leerProveedores() throws SQLException;

	void agregarProveedor(proveedorDTO p) throws SQLException;

	boolean modificarProveedor(int id, String valor, String campo) throws SQLException;

	boolean eliminarProveedor(int id) throws SQLException;

}

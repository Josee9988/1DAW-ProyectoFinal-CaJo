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

	boolean eliminarProveedor(proveedorDTO p) throws SQLException;
	ArrayList<proveedorDTO> leerProveedores() throws SQLException;
	void agregarProveedor(proveedorDTO p) throws SQLException;
	boolean modificarProveedor(int id, String valor, String campo) throws SQLException;

}

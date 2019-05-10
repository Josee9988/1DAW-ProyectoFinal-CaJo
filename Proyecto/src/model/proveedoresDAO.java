package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.proveedorDTO;

public interface proveedoresDAO {
	
	public boolean eliminarProveedor(proveedorDTO p) throws SQLException;
	public ArrayList<proveedorDTO> leerProveedores() throws SQLException;
	public void agregarProveedor(proveedorDTO p) throws SQLException;
	public boolean modificarProveedor(int id, String valor, String campo) throws SQLException;

}

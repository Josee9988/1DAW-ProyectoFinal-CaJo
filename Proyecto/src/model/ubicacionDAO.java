package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.ubicacionDTO;

public interface ubicacionDAO {
	public void agregarUbicacion(ubicacionDTO u) throws SQLException;
	public boolean modificarUbicacion(int id, String valor, String campo) throws SQLException;
	public boolean eliminarUbicacion(ubicacionDTO u) throws SQLException;
	public ArrayList<ubicacionDTO> leerUbicaciones() throws SQLException;
	public int obtnerIdUbicacion(String nombre) throws SQLException;
}

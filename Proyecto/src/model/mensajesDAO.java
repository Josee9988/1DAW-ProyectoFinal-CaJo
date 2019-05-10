package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.mensajesDTO;

public interface mensajesDAO {
	
	public ArrayList<mensajesDTO> leerMensajes() throws SQLException;
	public void crearMensaje(mensajesDTO m) throws SQLException;

}

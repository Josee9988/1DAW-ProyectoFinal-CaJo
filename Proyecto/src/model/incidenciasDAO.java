package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.incidenciaDTO;

public interface incidenciasDAO {	
	public boolean eliminarIncidencia(incidenciaDTO i) throws SQLException;
	public ArrayList<incidenciaDTO> leerIncidencias() throws SQLException;
	public boolean modificarIncidencia(incidenciaDTO i) throws SQLException;
	public void crearIncidencia(incidenciaDTO i) throws SQLException;
}

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
	ArrayList<incidenciaDTO> leerIncidencias(usuarioDTO u) throws SQLException;

	boolean modificarIncidencia(incidenciaDTO i) throws SQLException;

	void crearIncidencia(incidenciaDTO i) throws SQLException;

	boolean eliminarIncidencia(int i) throws SQLException;
}

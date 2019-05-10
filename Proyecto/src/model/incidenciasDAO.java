/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.incidenciaDTO;

public interface incidenciasDAO {
	boolean eliminarIncidencia(incidenciaDTO i) throws SQLException;

	ArrayList<incidenciaDTO> leerIncidencias() throws SQLException;

	boolean modificarIncidencia(incidenciaDTO i) throws SQLException;

	void crearIncidencia(incidenciaDTO i) throws SQLException;
}

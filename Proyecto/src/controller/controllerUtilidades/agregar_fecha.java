/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 24, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerUtilidades;

import java.sql.SQLException;
import java.util.Date;

import controller.controllerIncidencias.consultar_incidencias;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class agregar_fecha {
	@FXML
	private DatePicker fecha;
	@FXML
	private Button aplicarFecha;

	private Stage stage;
	private consultar_incidencias consultar_incidencias;

	/**
	 * agregar_fecha constructor default
	 */
	public agregar_fecha() {
		this.consultar_incidencias = new consultar_incidencias();
	}

	@FXML
	/**
	 * aplicarFecha cuando se le da al botón coge el valor, si es que hay un valor y
	 * se envía a consultar_incidencias, para que se modifique la fecha
	 *
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void aplicarFecha() throws SQLException {
		if (this.fecha.getValue() != null) {
			Date date = java.sql.Date.valueOf(this.fecha.getValue());
			this.stage = (Stage) this.aplicarFecha.getScene().getWindow(); // seleccionamos la escena actual
			this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
			this.consultar_incidencias.agregarFechaView(date);
		}
	}

}

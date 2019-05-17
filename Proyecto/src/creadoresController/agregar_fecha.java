package creadoresController;

import java.sql.SQLException;
import java.util.Date;

import controller.consultar_incidencias;
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

	public agregar_fecha(){
		this.consultar_incidencias = new consultar_incidencias();
		this.stage = null;
	}

	public void inicializar() {

	}


	@FXML
	public void aplicarFecha() throws SQLException {
		if (this.fecha.getValue() != null) {
			Date date = java.sql.Date.valueOf(this.fecha.getValue());
			this.stage = (Stage) this.aplicarFecha.getScene().getWindow(); // seleccionamos la escena actual
			this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
			this.consultar_incidencias.agregarFechaView(date);
		}
	}

}

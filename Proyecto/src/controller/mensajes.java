/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class mensajes {
	
	private java.util.Date date_util;
	private java.sql.Date date_sql;
	
	@FXML
	private TextField asunto;
	@FXML
	private TextField cuerpo;
	@FXML
	private TextField fecha;
	@FXML
	private TextField resultado;

	public void inicializar() {	
		this.resultado.setEditable(false);
		this.date_util = new Date();
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date_util));
		this.date_sql = new java.sql.Date(this.date_util.getTime());
	}
	
	@FXML
	public void crearMensaje() throws SQLException {
		if(!this.asunto.getText().isEmpty() && !this.cuerpo.getText().isEmpty()) {
			bdController.getInstance().crearMensaje(this.asunto.getText(), this.cuerpo.getText(), this.date_sql);
			this.resultado.setText("Ok!");
			this.asunto.clear();
			this.cuerpo.clear();
		}else {
			resultadoIncorrecto();
		}
	}
	
	public void resultadoIncorrecto() {
		this.resultado.setText("Campo(s) no validos");
	}
}

package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class administrar_incidencias {

	private Date date;
	private ObservableList<String> urgencia;

	@FXML
	private TextField fecha;
	@FXML
	private TextField nombreCompletoEncabezado;
	@FXML
	private TextField userIDM;
	@FXML
	private TextField userIDE;
	@FXML
	private TextField resultado;
	@FXML
	private ComboBox<String> camposUrgencia;

	public administrar_incidencias() {
		this.urgencia = FXCollections.observableArrayList("Alta", "Media", "Baja", "Indiferente");
	}

	public void inicializar(String nombreCompleto) {
		this.date = new Date();
		this.nombreCompletoEncabezado.setText(nombreCompleto);
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.camposUrgencia.setItems(urgencia);
		this.camposUrgencia.getSelectionModel().selectFirst();
		this.nombreCompletoEncabezado.setEditable(false);
		this.fecha.setEditable(false);
	}

	@FXML
	public void modificarIncidencia() throws NumberFormatException, SQLException {
		this.resultado.clear();
		if (!this.userIDM.getText().isEmpty() && isNumeric(this.userIDM.getText())) {
			if (bdController.getInstance().modificarIncidencia(Integer.parseInt(this.userIDM.getText()),
					this.camposUrgencia.getItems().get(this.camposUrgencia.getSelectionModel().getSelectedIndex()))) {
				this.resultado.setText("Ok!");
				this.userIDM.clear();
			} else {
				this.resultado.setText("No encontrado");
			}
		} else {
			resultadoIncorrecto();
		}
	}

	@FXML
	public void eliminarIncidencia() throws NumberFormatException, SQLException {
		this.resultado.clear();
		if (!this.userIDE.getText().isEmpty() && isNumeric(this.userIDE.getText())) {
			if (bdController.getInstance().eliminarIncidencia(Integer.parseInt(this.userIDE.getText()))) {
				this.resultado.setText("Ok!");
				this.userIDE.clear();
			} else {
				this.resultado.setText("No encontrado");
			}
		} else {
			resultadoIncorrecto();
		}
	}
	
	public void resultadoIncorrecto() {
		this.resultado.setText("Campo(s) no validos");
	}

	public static boolean isNumeric(String cadena) {

		boolean resultado;

		try {
			Integer.parseInt(cadena);
			resultado = true;
		} catch (NumberFormatException excepcion) {
			resultado = false;
		}

		return resultado;
	}
}

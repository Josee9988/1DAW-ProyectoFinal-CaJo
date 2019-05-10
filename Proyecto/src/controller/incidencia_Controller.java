/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class incidencia_Controller {

	private java.util.Date date_util;
	private java.sql.Date date_sql;
	private bdController bd;
	private ObservableList<String> urgencia;
	private ObservableList<String> categorias;
	private ObservableList<String> nombresUbicaciones;

	@FXML
	private TextField descripcion;
	@FXML
	private TextField username;
	@FXML
	private TextField elemento;
	@FXML
	private TextField elementos_afectados;
	@FXML
	private TextField resultado;
	@FXML
	private TextField fecha;
	@FXML
	private ComboBox<String> ubicacion;
	@FXML
	private ComboBox<String> nivelUrgencia;
	@FXML
	private ComboBox<String> categoria;

	public incidencia_Controller() throws SQLException {
		this.urgencia = FXCollections.observableArrayList("Alta", "Media", "Baja", "Indiferente");
		this.categorias = FXCollections.observableArrayList("Hardware", "Software", "Otra");
		this.nombresUbicaciones = FXCollections
				.observableArrayList(bdController.getInstance().leerNombresUbicaciones());
	}

	public void inicializar(String nombreCompleto, int rol) {
		this.nivelUrgencia.setItems(this.urgencia);
		this.nivelUrgencia.getSelectionModel().selectFirst();
		this.categoria.setItems(this.categorias);
		this.categoria.getSelectionModel().selectFirst();
		this.ubicacion.setItems(this.nombresUbicaciones);
		this.ubicacion.getSelectionModel().selectFirst();
		this.resultado.setEditable(false);
		this.date_util = new Date();
		this.username.setText(nombreCompleto);
		if (rol == 1 | rol == 2) {
			this.username.setEditable(false);
		} else {
			this.username.setEditable(true);
		}
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date_util));
		this.date_sql = new java.sql.Date(this.date_util.getTime());
	}

	@FXML
	public void crearIncidencia() throws SQLException {
		this.bd = bdController.getInstance();
		if (!username.getText().isEmpty() && !descripcion.getText().isEmpty() && !elemento.getText().isEmpty()) {
					this.bd.nuevaIncidencia(username.getText(), descripcion.getText(), elemento.getText(),
						this.ubicacion.getItems().get(this.ubicacion.getSelectionModel().getSelectedIndex()), this.date_sql,
						this.nivelUrgencia.getItems().get(this.nivelUrgencia.getSelectionModel().getSelectedIndex()),
						this.categoria.getItems().get(this.categoria.getSelectionModel().getSelectedIndex()),
						this.elementos_afectados.getText());
				this.resultado.setText("Ok!");
		} else {
			resultadoIncorrecto();
		}
	}

	public void resultadoIncorrecto() {
		this.resultado.setText("Campo(s) no validos");
	}

}

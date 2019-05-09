package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class administrar_ubicaciones {
	
	private java.util.Date date_util;
	private ObservableList<String> campos;
	
	@FXML
	private TextField nombreCompletoEncabezado;
	@FXML
	private TextField fecha;
	@FXML
	private TextField idM;
	@FXML
	private TextField idE;
	@FXML
	private TextField nombre;
	@FXML
	private TextField descripcion;
	@FXML
	private TextField edificio;
	@FXML
	private TextField planta;
	@FXML
	private TextField nuevoValor;
	@FXML
	private TextField resultado;
	@FXML
	private ComboBox<String> campoModificar;
	
	public administrar_ubicaciones() {
		this.date_util = new Date();
		this.campos = FXCollections.observableArrayList("Nombre", "Descripcion", "Edificio", "Planta");
	}
	
	public void inicializar(String nombreCompleto) {
		this.nombreCompletoEncabezado.setText(nombreCompleto);
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date_util));
		this.nombreCompletoEncabezado.setEditable(false);
		this.fecha.setEditable(false);
		this.campoModificar.setItems(this.campos);
		this.campoModificar.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void modificarUbicacion() throws NumberFormatException, SQLException {
		if(!this.idM.getText().isEmpty() && !this.nuevoValor.getText().isEmpty() && isNumeric(this.idM.getText())) {
			if(bdController.getInstance().modificarUbicacion(Integer.parseInt(this.idM.getText()),this.nuevoValor.getText(),
					this.campoModificar.getItems().get(this.campoModificar.getSelectionModel().getSelectedIndex()))) {
				this.resultado.setText("Ok!");
				this.idM.clear();
				this.nuevoValor.clear();
			}else {
				this.resultado.setText("No encontrado");
			}
		}else {
			resultadoIncorrecto();
		}
	}
	
	@FXML
	public void eliminarUbicacion() throws NumberFormatException, SQLException {
		if(!this.idE.getText().isEmpty() && isNumeric(this.idE.getText())) {
			if(bdController.getInstance().eliminarUbicacion(Integer.parseInt(this.idE.getText()))) {
				this.resultado.setText("Ok!");
				this.idE.clear();
			}else {
				this.resultado.setText("No encontrado");
			}
		}else {
			resultadoIncorrecto();
		}
	}
	
	@FXML
	public void agregarUbicacion() throws SQLException {
		if(!this.nombre.getText().isEmpty() && !this.descripcion.getText().isEmpty() && !this.edificio.getText().isEmpty() && !this.planta.getText().isEmpty()) {
			bdController.getInstance().agregarUbicacion(this.nombre.getText(), this.descripcion.getText(), this.edificio.getText(), this.planta.getText());
			this.resultado.setText("Ok!");
			this.nombre.clear();
			this.descripcion.clear();
			this.edificio.clear();
			this.planta.clear();
		}else {
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

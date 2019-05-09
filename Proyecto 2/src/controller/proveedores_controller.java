package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class proveedores_controller {

	private java.util.Date date_util;
	private ObservableList<String> campos;
	private ObservableList<Integer> valoraciones;

	@FXML
	private TextField nombreCompletoEncabezado;
	@FXML
	private TextField fecha;
	@FXML
	private TextField idM;
	@FXML
	private TextField idE;
	@FXML
	private TextField nombreProveedorA;
	@FXML
	private TextField contacto;
	@FXML
	private TextField direccion;
	@FXML
	private TextField nuevoValor;
	@FXML
	private TextField resultado;
	@FXML
	private ComboBox<String> campoModificar;
	@FXML
	private ComboBox<Integer> valoracion;

	public proveedores_controller() {
		this.campos = FXCollections.observableArrayList("Nombre", "Contacto", "Direccion", "Valoracion");
		this.valoraciones = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		this.date_util = new Date();
	}

	public void inicializar(String nombreCompleto) {
		this.nombreCompletoEncabezado.setText(nombreCompleto);
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date_util));
		this.campoModificar.setItems(this.campos);
		this.valoracion.setItems(this.valoraciones);
		this.campoModificar.getSelectionModel().selectFirst();
		this.valoracion.getSelectionModel().selectFirst();
		this.nombreCompletoEncabezado.setEditable(false);
		this.fecha.setEditable(false);
	}

	@FXML
	public void modificarProveedor() throws SQLException {
		if (!this.idM.getText().isEmpty() && !this.nuevoValor.getText().isEmpty() && isNumeric(this.idM.getText())) {
			if (bdController.getInstance().modificarProveedor(Integer.parseInt(this.idM.getText()),
					this.nuevoValor.getText(),
					this.campoModificar.getItems().get(this.campoModificar.getSelectionModel().getSelectedIndex()))) {
				this.resultado.setText("Ok!");
				this.idM.clear();
				this.nuevoValor.clear();
			} else {
				this.resultado.setText("No encontrado");
			}
		} else {
			resultadoIncorrecto();
		}
	}

	@FXML
	public void eliminarProveedor() throws SQLException {
		if (!this.idE.getText().isEmpty() && isNumeric(this.idE.getText())) {
			if (bdController.getInstance().eliminarProveedor(Integer.parseInt(this.idE.getText()))) {
				this.resultado.setText("Ok!");
				this.idE.clear();
			} else {
				this.resultado.setText("No encontrado");
			}
		} else {
			resultadoIncorrecto();
		}
	}

	@FXML
	public void crearProveedor() throws SQLException {
		if (!this.nombreProveedorA.getText().isEmpty() && !this.contacto.getText().isEmpty()
				&& !this.direccion.getText().isEmpty()) {
			bdController.getInstance().agregarProveedor(this.nombreProveedorA.getText(), this.contacto.getText(),
					this.direccion.getText(),
					this.valoracion.getItems().get(this.valoracion.getSelectionModel().getSelectedIndex()));
			this.resultado.setText("Ok!");
			this.nombreProveedorA.clear();
			this.contacto.clear();
			this.direccion.clear();
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

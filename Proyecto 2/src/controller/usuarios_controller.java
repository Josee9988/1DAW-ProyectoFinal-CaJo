package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class usuarios_controller {
	
	private ObservableList<String> permisos;
	private java.util.Date date_util;
	
	@FXML
	private TextField nombreCompletoEncabezado;
	@FXML
	private TextField fecha;
	@FXML
	private TextField userIDM;
	@FXML
	private TextField userIDE;
	@FXML
	private TextField usernameC;
	@FXML
	private TextField password;
	@FXML
	private TextField telefono;
	@FXML
	private TextField direccion;
	@FXML
	private TextField apellidosUsuario;
	@FXML
	private TextField nombreUsuario;
	@FXML
	private TextField resultado;
	@FXML
	private ComboBox<String> permisosM;
	@FXML
	private ComboBox<String> permisosCrear;
	
	public usuarios_controller() {
		this.permisos = FXCollections.observableArrayList("Profesor","Jefe de departamento","Mantenimiento","Administrador");
		this.date_util = new Date();
	}
	
	public void inicializar(String nombreCompleto) {
		this.nombreCompletoEncabezado.setText(nombreCompleto);
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date_util));
		this.permisosM.setItems(this.permisos);
		this.permisosCrear.setItems(this.permisos);
		this.permisosM.getSelectionModel().selectFirst();
		this.permisosCrear.getSelectionModel().selectFirst();
		this.nombreCompletoEncabezado.setEditable(false);
		this.fecha.setEditable(false);
	}
	
	@FXML
	public void modificarUsuario() throws SQLException {
		this.resultado.clear();
		boolean resultadoBoolean = false; 
		if(!this.userIDM.getText().isEmpty()) {	
			switch (this.permisosM.getItems().get(this.permisosM.getSelectionModel().getSelectedIndex())) {
			case "Profesor":resultadoBoolean = bdController.getInstance().modificarUsuario(this.userIDM.getText(),1);
				break;
			case "Jefe de departamento":resultadoBoolean = bdController.getInstance().modificarUsuario(this.userIDM.getText(),2);	
				break;
			case "Mantenimiento":resultadoBoolean = bdController.getInstance().modificarUsuario(this.userIDM.getText(),3);	
				break;
			case "Administrador":resultadoBoolean = bdController.getInstance().modificarUsuario(this.userIDM.getText(),4);	
				break;
			}
			if (resultadoBoolean) {
				this.resultado.setText("Ok!");
				this.userIDM.clear();
			}else {
				this.resultado.setText("No encontrado");
			}
		}else {
			resultadoIncorrecto();
		}
	}
	
	@FXML
	public void eliminarUsuario() throws SQLException {
		this.resultado.clear();
		if(!this.userIDE.getText().isEmpty()) {	
			if (bdController.getInstance().eliminarUsuario(this.userIDE.getText())) {
				this.resultado.setText("Ok!");
				this.userIDE.clear();
			}else {
				this.resultado.setText("No encontrado");
			}
		}else {
			resultadoIncorrecto();
		}
	}
	
	@FXML
	public void crearUsuario() throws SQLException {
		this.resultado.clear();
		if(!this.usernameC.getText().isEmpty() && !this.password.getText().isEmpty() && !this.telefono.getText().isEmpty() && !this.direccion.getText().isEmpty()
				&& !this.nombreUsuario.getText().isEmpty() && !this.apellidosUsuario.getText().isEmpty()) {	
			switch (this.permisosCrear.getItems().get(this.permisosCrear.getSelectionModel().getSelectedIndex())) {
			case "Profesor":bdController.getInstance().crearUsuario(this.usernameC.getText(), this.password.getText(), 1, this.nombreUsuario.getText(), this.apellidosUsuario.getText(), this.telefono.getText(), this.direccion.getText());
				break;
			case "Jefe de departamento":bdController.getInstance().crearUsuario(this.usernameC.getText(), this.password.getText(), 2, this.nombreUsuario.getText(), this.apellidosUsuario.getText(), this.telefono.getText(), this.direccion.getText());
				break;
			case "Mantenimiento":bdController.getInstance().crearUsuario(this.usernameC.getText(), this.password.getText(), 3, this.nombreUsuario.getText(), this.apellidosUsuario.getText(), this.telefono.getText(), this.direccion.getText());
				break;
			case "Administrador":bdController.getInstance().crearUsuario(this.usernameC.getText(), this.password.getText(), 4, this.nombreUsuario.getText(), this.apellidosUsuario.getText(), this.telefono.getText(), this.direccion.getText());
				break;
			}
			this.resultado.setText("Ok!");
			this.usernameC.clear();
			this.password.clear();
			this.telefono.clear();
			this.direccion.clear();
			this.nombreUsuario.clear();
			this.apellidosUsuario.clear();
		}else {
			resultadoIncorrecto();
		}
	}
	
	public void resultadoIncorrecto() {
		this.resultado.setText("Campo(s) vacio");
	}
	
}

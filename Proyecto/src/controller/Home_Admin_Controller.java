package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Home_Admin_Controller {

	private Date date;
	private int rol_number;
	private Stage usuarios;
	private Stage incidencias;
	private Stage proveedores;
	private Stage ubicaciones;
	private Stage mensajes;
	private Stage acercaDe;
	private FXMLLoader fxmlLoaderAdministrarUsuarios;
	private FXMLLoader fxmlLoaderAdministrarIncidencias;
	private FXMLLoader fxmlLoaderAdministrarProveedores;
	private FXMLLoader fxmlLoaderAdministrarUbicaciones;
	private FXMLLoader fxmlLoaderAdministrarMensajes;
	private FXMLLoader fxmlLoaderAcercaDe;
	private Parent root1;
	private Parent root2;
	private Parent root3;
	private Parent root4;
	private Parent root5;
	private Parent root6;
	private consultar_usuarios controllerUsuarios;
	private consultar_incidencias controllerIncidencias;
	private consultar_proveedores controllerProveedores;
	private consultar_ubicaciones controllerUbicaciones;
	private consultar_mensajes controllerMensajes;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;
	private Scene scene5;
	private Scene scene6;
	@FXML
	private TextField nombre;
	@FXML
	private TextField rol_name;
	@FXML
	private TextField fecha;

	public Home_Admin_Controller() throws IOException {

		this.usuarios = new Stage();
		this.incidencias = new Stage();
		this.proveedores = new Stage();
		this.ubicaciones = new Stage();
		this.mensajes = new Stage();
		this.acercaDe = new Stage();

		this.fxmlLoaderAdministrarUsuarios = new FXMLLoader(getClass().getResource("/view/consultarUsuarios.fxml"));
		this.fxmlLoaderAdministrarIncidencias = new FXMLLoader(
				getClass().getResource("/view/consultarIncidencias.fxml"));
		this.fxmlLoaderAdministrarProveedores = new FXMLLoader(
				getClass().getResource("/view/consultarProveedores.fxml"));
		this.fxmlLoaderAdministrarUbicaciones = new FXMLLoader(
				getClass().getResource("/view/consultarUbicaciones.fxml"));
		this.fxmlLoaderAdministrarMensajes = new FXMLLoader(getClass().getResource("/view/consultarMensajes.fxml"));
		this.fxmlLoaderAcercaDe = new FXMLLoader(getClass().getResource("/view/acerca_de.fxml"));

		this.root1 = (Parent) this.fxmlLoaderAdministrarUsuarios.load();
		this.root2 = (Parent) this.fxmlLoaderAdministrarIncidencias.load();
		this.root3 = (Parent) this.fxmlLoaderAdministrarProveedores.load();
		this.root4 = (Parent) this.fxmlLoaderAdministrarUbicaciones.load();
		this.root5 = (Parent) this.fxmlLoaderAdministrarMensajes.load();
		this.root6 = (Parent) this.fxmlLoaderAcercaDe.load();

		this.controllerUsuarios = this.fxmlLoaderAdministrarUsuarios.<consultar_usuarios>getController();
		this.controllerIncidencias = this.fxmlLoaderAdministrarIncidencias.<consultar_incidencias>getController();
		this.controllerProveedores = this.fxmlLoaderAdministrarProveedores.<consultar_proveedores>getController();
		this.controllerUbicaciones = this.fxmlLoaderAdministrarUbicaciones.<consultar_ubicaciones>getController();
		this.controllerMensajes = this.fxmlLoaderAdministrarMensajes.<consultar_mensajes>getController();

		this.scene1 = new Scene(this.root1);
		this.scene2 = new Scene(this.root2);
		this.scene3 = new Scene(this.root3);
		this.scene4 = new Scene(this.root4);
		this.scene5 = new Scene(this.root5);
		this.scene6 = new Scene(this.root6);

	}

	public void recibirParametros(String nombreCompleto, int rol) {
		this.date = new Date();
		this.nombre.setEditable(false);
		this.rol_name.setEditable(false);
		this.fecha.setEditable(false);
		this.nombre.setText(nombreCompleto);
		this.rol_name.setText("Administrador");
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.rol_number = rol;
	}

	@FXML
	public void administrarUsuarios() throws SQLException {
		this.controllerUsuarios.inicializar(this.nombre.getText());
		this.usuarios.setScene(this.scene1);
		this.usuarios.show();
	}

	@FXML
	public void administrarProveedores() throws SQLException {
		this.controllerProveedores.inicializar(this.nombre.getText());
		this.proveedores.setScene(this.scene2);
		this.proveedores.show();
	}

	@FXML
	public void administrarIncidencias() throws SQLException {
		this.controllerIncidencias.inicializar(this.nombre.getText(), this.rol_number);
		this.incidencias.setScene(this.scene3);
		this.incidencias.show();
	}

	@FXML
	public void administrarMensajes() throws SQLException {
		this.controllerMensajes.inicializar(this.nombre.getText());
		this.mensajes.setScene(this.scene4);
		this.mensajes.show();
	}

	@FXML
	public void administrarUbicaciones() throws SQLException {
		this.controllerUbicaciones.inicializar(this.nombre.getText());
		this.ubicaciones.setScene(this.scene5);
		this.ubicaciones.show();
	}

	@FXML
	public void acercaDe() throws SQLException {
		this.acercaDe.setScene(this.scene6);
		this.acercaDe.show();
	}

}
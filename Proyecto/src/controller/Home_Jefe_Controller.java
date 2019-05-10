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

public class Home_Jefe_Controller {

	private Date date;
	private int rol_number;

	private Stage incidencias;
	private Stage proveedores;
	private Stage mensajes;
	private Stage acercaDe;
	private FXMLLoader fxmlLoaderAdministrarIncidencias;
	private FXMLLoader fxmlLoaderAdministrarProveedores;
	private FXMLLoader fxmlLoaderConsultarMensajes;
	private FXMLLoader fxmlLoaderAcerca;
	private Parent root1;
	private Parent root2;
	private Parent root3;
	private Parent root4;
	private consultar_incidencias controllerIncidencias;
	private consultar_proveedores controllerProveedores;
	private consultar_mensajes controllerMensajes;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;

	@FXML
	private TextField nombre;
	@FXML
	private TextField rol_name;
	@FXML
	private TextField fecha;

	public Home_Jefe_Controller() throws IOException {

		this.incidencias = new Stage();
		this.proveedores = new Stage();
		this.mensajes = new Stage();
		this.acercaDe = new Stage();

		this.fxmlLoaderAdministrarIncidencias = new FXMLLoader(
				getClass().getResource("/view/consultarIncidencias.fxml"));
		this.fxmlLoaderAdministrarProveedores = new FXMLLoader(
				getClass().getResource("/view/consultarProveedores.fxml"));
		this.fxmlLoaderConsultarMensajes = new FXMLLoader(getClass().getResource("/view/consultarMensajes.fxml"));
		this.fxmlLoaderAcerca = new FXMLLoader(getClass().getResource("/view/acerca_de.fxml"));

		this.root1 = (Parent) this.fxmlLoaderAdministrarIncidencias.load();
		this.root2 = (Parent) this.fxmlLoaderAdministrarProveedores.load();
		this.root3 = (Parent) this.fxmlLoaderConsultarMensajes.load();
		this.root4 = (Parent) this.fxmlLoaderAcerca.load();

		this.controllerIncidencias = this.fxmlLoaderAdministrarIncidencias.<consultar_incidencias>getController();
		this.controllerProveedores = this.fxmlLoaderAdministrarProveedores.<consultar_proveedores>getController();
		this.controllerMensajes = this.fxmlLoaderConsultarMensajes.<consultar_mensajes>getController();

		this.scene1 = new Scene(this.root1);
		this.scene2 = new Scene(this.root2);
		this.scene3 = new Scene(this.root3);
		this.scene4 = new Scene(this.root4);

	}

	public void recibirParametros(String nombreCompleto, int rol) {
		this.date = new Date();
		this.nombre.setEditable(false);
		this.rol_name.setEditable(false);
		this.fecha.setEditable(false);
		this.nombre.setText(nombreCompleto);
		this.rol_name.setText("Jefe de Departamento");
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.rol_number = rol;
	}

	@FXML
	public void administrarProveedores() throws SQLException {
		this.controllerProveedores.inicializar(this.nombre.getText());
		this.proveedores.setScene(this.scene1);
		this.proveedores.show();
	}

	@FXML
	public void administrarIncidencias() throws SQLException {
		this.controllerIncidencias.inicializar(this.nombre.getText(), this.rol_number);
		this.incidencias.setScene(this.scene2);
		this.incidencias.show();
	}

	@FXML
	public void administrarMensajes() throws SQLException {
		this.controllerMensajes.inicializar(this.nombre.getText());
		this.mensajes.setScene(this.scene3);
		this.mensajes.show();
	}

	@FXML
	public void acercaDe() {
		this.acercaDe.setScene(this.scene4);
		this.acercaDe.show();
	}

}

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
	
	private Stage incidenciaCrear;
	private Stage incidenciaConsultar;
	private Stage proveedorAdmin;
	private Stage proveedorConsultar;
	private Stage acercaDe;
	private Stage mensajesConsultar;
	private Stage mensajesCrear;
	private FXMLLoader fxmlLoaderCrearIncidencia;
	private FXMLLoader fxmlLoaderConsultarIncidencias;
	private FXMLLoader fxmlLoaderAdministrarProveedores;
	private FXMLLoader fxmlLoaderConsultarProveedores;
	private FXMLLoader fxmlLoaderAcerca;
	private FXMLLoader fxmlLoaderConsultarMensajes;
	private FXMLLoader fxmlLoaderCrearMensajes;
	private Parent root2;
	private Parent root3;
	private Parent root4;
	private Parent root5;
	private Parent root6;
	private Parent root8;
	private Parent root9;
	private incidencia_Controller controllerIncidencias;
	private consultar_incidencias controllerConsularIncidencias;
	private proveedores_controller controllerProveedores;
	private consultar_proveedores controllerConsultarProveedores;
	private acercaDe_controller controllerInformacion;
	private mensajes controllerCrearMensajes;
	private consultar_mensajes controllerConsultarMensajes;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;
	private Scene scene5;
	private Scene scene6;
	private Scene scene8;
	private Scene scene9;

	@FXML
	private TextField nombre;
	@FXML
	private TextField rol_name;
	@FXML
	private TextField fecha;

	public Home_Jefe_Controller() throws IOException {
		
		this.incidenciaCrear = new Stage();
		this.proveedorAdmin = new Stage();
		this.acercaDe = new Stage();
		this.mensajesCrear = new Stage();

		this.fxmlLoaderCrearIncidencia = new FXMLLoader(getClass().getResource("/view/incidencia.fxml"));
		this.fxmlLoaderAdministrarProveedores = new FXMLLoader(getClass().getResource("/view/proveedores_admin.fxml"));
		this.fxmlLoaderAcerca = new FXMLLoader(getClass().getResource("/view/acerca_de.fxml"));
		this.fxmlLoaderCrearMensajes = new FXMLLoader(getClass().getResource("/view/mensajes.fxml"));

		this.root2 = (Parent) this.fxmlLoaderCrearIncidencia.load();
		this.root4 = (Parent) this.fxmlLoaderAdministrarProveedores.load();
		this.root6 = (Parent) this.fxmlLoaderAcerca.load();
		this.root9 = (Parent) this.fxmlLoaderCrearMensajes.load();

		this.controllerIncidencias = this.fxmlLoaderCrearIncidencia.<incidencia_Controller>getController();
		this.controllerProveedores = this.fxmlLoaderAdministrarProveedores.<proveedores_controller>getController();
		this.controllerCrearMensajes = this.fxmlLoaderCrearMensajes.<mensajes>getController();
		this.controllerInformacion = this.fxmlLoaderAcerca.<acercaDe_controller>getController();
			
		this.scene2 = new Scene(this.root2);
		this.scene4 = new Scene(this.root4);
		this.scene6 = new Scene(this.root6);
		this.scene9 = new Scene(this.root9);

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
	public void crearIncidencia() throws IOException, SQLException {
		this.controllerIncidencias.inicializar(this.nombre.getText(), this.rol_number);
		this.incidenciaCrear.setScene(this.scene2);
		this.incidenciaCrear.show();
	}

	@FXML
	public void consultarIncidencias() throws IOException, SQLException {
		this.incidenciaConsultar = new Stage();
		this.fxmlLoaderConsultarIncidencias = new FXMLLoader(getClass().getResource("/view/consultarIncidencias.fxml"));
		this.root3 = (Parent) this.fxmlLoaderConsultarIncidencias.load();
		this.scene3 = new Scene(this.root3);
		this.controllerConsularIncidencias = this.fxmlLoaderConsultarIncidencias.<consultar_incidencias>getController();
		this.controllerConsularIncidencias.inicializar(this.nombre.getText(), this.rol_number);
		this.incidenciaConsultar.setScene(this.scene3);
		this.incidenciaConsultar.show();
	}

	@FXML
	public void administrarProveedores() throws IOException, SQLException {
		this.controllerProveedores.inicializar(this.nombre.getText());
		this.proveedorAdmin.setScene(this.scene4);
		this.proveedorAdmin.show();
	}

	@FXML
	public void consultarProveedores() throws IOException, SQLException {
		this.proveedorConsultar = new Stage();
		this.fxmlLoaderConsultarProveedores = new FXMLLoader(getClass().getResource("/view/consultarProveedores.fxml"));
		this.root5 = (Parent) this.fxmlLoaderConsultarProveedores.load();
		this.controllerConsultarProveedores = this.fxmlLoaderConsultarProveedores.<consultar_proveedores>getController();
		this.scene5 = new Scene(this.root5);
		this.controllerConsultarProveedores.inicializar(this.nombre.getText());
		this.proveedorConsultar.setScene(this.scene5);
		this.proveedorConsultar.show();
	}

	@FXML
	public void acercaDe() throws IOException, SQLException {
		this.controllerInformacion.inicializar();
		this.acercaDe.setScene(this.scene6);
		this.acercaDe.show();
	}
	
	@FXML
	public void consultarMensajes() throws IOException, SQLException {
		this.mensajesConsultar = new Stage();
		this.fxmlLoaderConsultarMensajes = new FXMLLoader(getClass().getResource("/view/consultarMensajes.fxml"));
		this.root8 = (Parent) this.fxmlLoaderConsultarMensajes.load();
		this.controllerConsultarMensajes = this.fxmlLoaderConsultarMensajes.<consultar_mensajes>getController();
		this.scene8 = new Scene(this.root8);
		this.controllerConsultarMensajes.inicializar(this.nombre.getText());
		this.mensajesConsultar.setScene(this.scene8);
		this.mensajesConsultar.show();
	}
	
	@FXML
	public void crearMensajes() throws IOException, SQLException {
		this.controllerCrearMensajes.inicializar();
		this.mensajesCrear.setScene(this.scene9);
		this.mensajesCrear.show();
	}

}

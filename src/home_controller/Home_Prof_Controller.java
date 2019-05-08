package home_controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import acerca_controller.acercaDe_controller;
import consultar_controller.consultar_incidencias;
import consultar_controller.consultar_mensajes;
import consultar_controller.consultar_proveedores;
import incidencia_controller.incidencia_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mensajes_controller.mensajes;

public class Home_Prof_Controller {

	private Date date;
	private int rol_number;
	private Stage incidencia;
	private Stage incidenciaProfe;
	private Stage proveedor;
	private Stage mensajeCrear;
	private Stage mensajeConsultar;
	private Stage acercaDe;
	private FXMLLoader fxmlLoadercrearIncidencia;
	private FXMLLoader fxmlLoaderconsultarIncidencia;
	private FXMLLoader fxmlLoaderconsultarProveedor;
	private FXMLLoader fxmlLoaderCrearMensaje;
	private FXMLLoader fxmlLoaderConsultarMensajes;
	private FXMLLoader fxmlLoaderAcerca;
	private Parent rootCrearIncidencia;
	private Parent rootConsultarIncidencia;
	private Parent rootConsultarProveedor;
	private Parent rootCrearMensaje;
	private Parent rootConsultarMensajes;
	private Parent acerca;
	private incidencia_Controller controllerIncidencia;
	private consultar_incidencias controllerConsulta;
	private consultar_proveedores controllerProveedor;
	private mensajes controllerCrearMensaje;
	private consultar_mensajes controllerConsultarMensajes;
	private acercaDe_controller controllerInformacion;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;
	private Scene scene5;
	private Scene scene6;

	@FXML
	private TextField username;
	@FXML
	private TextField rol_name;
	@FXML
	private TextField fecha;

	public Home_Prof_Controller() throws IOException {
		this.incidencia = new Stage();
		this.mensajeCrear = new Stage();
		this.acercaDe = new Stage();
		
		this.fxmlLoadercrearIncidencia = new FXMLLoader(getClass().getResource("/view/incidencia.fxml"));
		this.fxmlLoaderCrearMensaje = new FXMLLoader(getClass().getResource("/view/mensajes.fxml"));
		this.fxmlLoaderAcerca = new FXMLLoader(getClass().getResource("/view/acerca_de.fxml"));
		
		this.rootCrearIncidencia = (Parent) this.fxmlLoadercrearIncidencia.load();
		this.rootCrearMensaje = (Parent) this.fxmlLoaderCrearMensaje.load();
		this.acerca = (Parent) this.fxmlLoaderAcerca.load();
		
		this.controllerIncidencia = this.fxmlLoadercrearIncidencia.<incidencia_Controller>getController();
		this.controllerCrearMensaje = this.fxmlLoaderCrearMensaje.<mensajes>getController();
		this.controllerInformacion = this.fxmlLoaderAcerca.<acercaDe_controller>getController();
		
		this.scene1 = new Scene(this.rootCrearIncidencia);
		this.scene5 = new Scene(this.rootCrearMensaje);
		this.scene4 = new Scene(this.acerca);
	}

	public void recibirParametros(String nombreCompleto, int rol) {
		this.date = new Date();
		this.username.setEditable(false);
		this.rol_name.setEditable(false);
		this.fecha.setEditable(false);
		this.username.setText(nombreCompleto);
		this.rol_name.setText("Profesor");
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.rol_number = rol;
	}

	@FXML
	private void crearIncidencia() throws IOException, SQLException {
		this.controllerIncidencia.inicializar(this.username.getText(), this.rol_number);
		this.incidencia.setScene(this.scene1);
		this.incidencia.show();
	}

	@FXML
	private void consultarIncidencia() throws IOException, SQLException {
		this.incidenciaProfe = new Stage();
		this.fxmlLoaderconsultarIncidencia = new FXMLLoader(getClass().getResource("/view/consultarIncidencias.fxml"));
		this.rootConsultarIncidencia = (Parent) this.fxmlLoaderconsultarIncidencia.load();
		this.controllerConsulta = this.fxmlLoaderconsultarIncidencia.<consultar_incidencias>getController();
		this.scene2 = new Scene(this.rootConsultarIncidencia);
		this.controllerConsulta.inicializar(this.username.getText(), this.rol_number);
		this.incidenciaProfe.setScene(this.scene2);
		this.incidenciaProfe.show();
	}

	@FXML
	private void consultarProveedor() throws IOException, SQLException {
		this.proveedor = new Stage();
		this.fxmlLoaderconsultarProveedor = new FXMLLoader(getClass().getResource("/view/consultarProveedores.fxml"));
		this.rootConsultarProveedor = (Parent) this.fxmlLoaderconsultarProveedor.load();
		this.controllerProveedor = this.fxmlLoaderconsultarProveedor.<consultar_proveedores>getController();
		this.scene3 = new Scene(this.rootConsultarProveedor);
		this.controllerProveedor.inicializar(this.username.getText());
		this.proveedor.setScene(this.scene3);
		this.proveedor.show();
	}
	
	@FXML
	private void crearMensaje() throws IOException, SQLException {
		this.controllerCrearMensaje.inicializar();
		this.mensajeCrear.setScene(this.scene5);
		this.mensajeCrear.show();
	}
	
	@FXML
	private void consultarMensajes() throws IOException, SQLException {
		this.mensajeConsultar = new Stage();
		this.fxmlLoaderConsultarMensajes = new FXMLLoader(getClass().getResource("/view/consultarMensajes.fxml"));
		this.rootConsultarMensajes = (Parent) this.fxmlLoaderConsultarMensajes.load();
		this.controllerConsultarMensajes = this.fxmlLoaderConsultarMensajes.<consultar_mensajes>getController();
		this.scene6 = new Scene(this.rootConsultarMensajes);
		this.controllerConsultarMensajes.inicializar(this.username.getText());
		this.mensajeConsultar.setScene(this.scene6);
		this.mensajeConsultar.show();
	}

	@FXML
	private void acercaDe() {
		this.controllerInformacion.inicializar();
		this.acercaDe.setScene(this.scene4);
		this.acercaDe.show();
	}
}

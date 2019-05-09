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
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Home_Mantenimiento_Controller {

	private Date date;
	private int rol_number;
	private Stage incidenciaCrear;
	private Stage incidenciaConsultar;
	private Stage incidenciasAdmin;
	private Stage mensajesConsultar;
	private Stage mensajesCrear;
	private Stage acercaDe;
	private FXMLLoader fxmlLoaderCrearIncidencia;
	private FXMLLoader fxmlLoaderConsultarIncidencias;
	private FXMLLoader fxmlLoaderAdministrarIncidencias;
	private FXMLLoader fxmlLoaderConsultarMensajes;
	private FXMLLoader fxmlLoaderCrearMensajes;
	private FXMLLoader fxmlLoaderAcerca;
	private Parent root2;
	private Parent root3;
	private Parent root4;
	private Parent root5;
	private Parent root6;
	private Parent root7;
	private incidencia_Controller controllerIncidencias;
	private consultar_incidencias controllerConsularIncidencias;
	private acercaDe_controller controllerInformacion;
	private administrar_incidencias controllerAdministrarIncidencias;
	private mensajes controllerCrearMensajes;
	private consultar_mensajes controllerConsultarMensajes;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;
	private Scene scene5;
	private Scene scene6;
	private Scene scene7;

	@FXML
	private TextField nombre;
	@FXML
	private TextField rol_name;
	@FXML
	private TextField fecha;
	private Image icon;

	public Home_Mantenimiento_Controller() throws IOException {
		
		this.incidenciaCrear = new Stage();
		this.incidenciasAdmin = new Stage();
		this.mensajesCrear = new Stage();
		this.acercaDe = new Stage();

		this.fxmlLoaderCrearIncidencia = new FXMLLoader(getClass().getResource("/view/incidencia.fxml"));
		this.fxmlLoaderAdministrarIncidencias = new FXMLLoader(getClass().getResource("/view/incidencias_admin.fxml"));
		this.fxmlLoaderCrearMensajes = new FXMLLoader(getClass().getResource("/view/mensajes.fxml"));
		this.fxmlLoaderAcerca = new FXMLLoader(getClass().getResource("/view/acerca_de.fxml"));

		this.root2 = (Parent) this.fxmlLoaderCrearIncidencia.load();
		this.root4 = (Parent) this.fxmlLoaderCrearMensajes.load();
		this.root6 = (Parent) this.fxmlLoaderAcerca.load();
		this.root7 = (Parent) this.fxmlLoaderAdministrarIncidencias.load();

		this.controllerIncidencias = this.fxmlLoaderCrearIncidencia.<incidencia_Controller>getController();
		this.controllerInformacion = this.fxmlLoaderAcerca.<acercaDe_controller>getController();
		this.controllerCrearMensajes = this.fxmlLoaderCrearMensajes.<mensajes>getController();
		this.controllerAdministrarIncidencias = this.fxmlLoaderAdministrarIncidencias.<administrar_incidencias>getController();
			
		this.scene2 = new Scene(this.root2);
		this.scene4 = new Scene(this.root4);
		this.scene6 = new Scene(this.root6);
		this.scene7 = new Scene(this.root7);
		this.icon = null;

	}

	public void recibirParametros(String nombreCompleto, int rol) {
		this.date = new Date();
		this.nombre.setEditable(false);
		this.rol_name.setEditable(false);
		this.fecha.setEditable(false);
		this.nombre.setText(nombreCompleto);
		this.rol_name.setText("Mantenimiento");
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
	public void acercaDe() throws IOException, SQLException {
		this.controllerInformacion.inicializar();
		this.acercaDe.setScene(this.scene6);
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png")); //decimos dónde está el icono
		acercaDe.getIcons().add(icon); //agregamos el icono
		acercaDe.setTitle("Proyecto Jose Carlos"); //ponemos el título de la ventana
		this.acercaDe.show();
	}
	
	@FXML
	public void crearMensaje() throws IOException, SQLException {
		this.controllerCrearMensajes.inicializar();
		this.mensajesCrear.setScene(this.scene4);
		this.mensajesCrear.show();
	}
	
	@FXML
	public void consultarMensaje() throws IOException, SQLException {
		this.mensajesConsultar = new Stage();
		this.fxmlLoaderConsultarMensajes = new FXMLLoader(getClass().getResource("/view/consultarMensajes.fxml"));
		this.root5 = (Parent) this.fxmlLoaderConsultarMensajes.load();
		this.scene5 = new Scene(this.root5);
		this.controllerConsultarMensajes = this.fxmlLoaderConsultarMensajes.<consultar_mensajes>getController();
		this.controllerConsultarMensajes.inicializar(this.nombre.getText());
		this.mensajesConsultar.setScene(this.scene5);
		this.mensajesConsultar.show();
	}
	
	@FXML
	public void administrarIncidencias() throws IOException, SQLException {
		this.controllerAdministrarIncidencias.inicializar(this.nombre.getText());
		this.incidenciasAdmin.setScene(this.scene7);
		this.incidenciasAdmin.show();
	}
	
}
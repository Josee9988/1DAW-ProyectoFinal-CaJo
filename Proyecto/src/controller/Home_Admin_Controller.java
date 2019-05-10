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
	private FXMLLoader fxmlLoaderAdministrarUsuarios;
	private FXMLLoader fxmlLoaderAdministrarIncidencias;
	private FXMLLoader fxmlLoaderAdministrarProveedores;
	private FXMLLoader fxmlLoaderAdministrarUbicaciones;
	private FXMLLoader fxmlLoaderAdministrarMensajes;
	private Parent root1;
	private Parent root2;
	private Parent root3;
	private Parent root4;
	private Parent root5;
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
	@FXML
	private TextField nombre;
	@FXML
	private TextField rol_name;
	@FXML
	private TextField fecha;

	public Home_Admin_Controller() throws IOException {
		
		this.usuarios = new Stage();
		this.incidenciaCrear = new Stage();
		this.proveedorAdmin = new Stage();
		this.incidenciasAdmin = new Stage();
		this.acercaDe = new Stage();
		this.mensajesCrear = new Stage();
		this.ubicacionesAdmin = new Stage();

		this.fxmlLoaderAdministrarUsuarios = new FXMLLoader(getClass().getResource("/view/users_admin.fxml"));
		this.fxmlLoaderCrearIncidencia = new FXMLLoader(getClass().getResource("/view/incidencia.fxml"));
		this.fxmlLoaderAdministrarProveedores = new FXMLLoader(getClass().getResource("/view/proveedores_admin.fxml"));
		this.fxmlLoaderAdministrarIncidencias = new FXMLLoader(getClass().getResource("/view/incidencias_admin.fxml"));
		this.fxmlLoaderAcerca = new FXMLLoader(getClass().getResource("/view/acerca_de.fxml"));
		this.fxmlLoaderCrearMensajes = new FXMLLoader(getClass().getResource("/view/mensajes.fxml"));
		this.fxmlLoaderAdministrarUbicaciones = new FXMLLoader(getClass().getResource("/view/ubicaciones_admin.fxml"));

		this.root1 = (Parent) this.fxmlLoaderAdministrarUsuarios.load();
		this.root2 = (Parent) this.fxmlLoaderCrearIncidencia.load();
		this.root4 = (Parent) this.fxmlLoaderAdministrarProveedores.load();
		this.root6 = (Parent) this.fxmlLoaderAcerca.load();
		this.root7 = (Parent) this.fxmlLoaderAdministrarIncidencias.load();
		this.root9 = (Parent) this.fxmlLoaderCrearMensajes.load();
		this.root11 = (Parent) this.fxmlLoaderAdministrarUbicaciones.load();

//		this.controllerUsuarios = this.fxmlLoaderAdministrarUsuarios.<usuarios_controller>getController();
//		this.controllerIncidencias = this.fxmlLoaderCrearIncidencia.<incidencia_Controller>getController();
//		this.controllerProveedores = this.fxmlLoaderAdministrarProveedores.<proveedores_controller>getController();
//		this.controllerInformacion = this.fxmlLoaderAcerca.<acercaDe_controller>getController();
//		this.controllerAdministrarIncidencias = this.fxmlLoaderAdministrarIncidencias.<administrar_incidencias>getController();
//		this.controllerCrearMensajes = this.fxmlLoaderCrearMensajes.<mensajes>getController();
//		this.controllerAdministrarUbicaciones = this.fxmlLoaderAdministrarUbicaciones.<administrar_ubicaciones>getController();
			
		this.scene1 = new Scene(this.root1);
		this.scene2 = new Scene(this.root2);
		this.scene4 = new Scene(this.root4);
		this.scene6 = new Scene(this.root6);
		this.scene7 = new Scene(this.root7);
		this.scene9 = new Scene(this.root9);
		this.scene11 = new Scene(this.root11);

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
	public void administrarUsuarios() {
		
	}
	
	@FXML
	public void administrarProveedores() {
		
	}
	
	@FXML
	public void administrarIncidencias() {
		
	}
	
	@FXML
	public void administrarMensajes() {
		
	}
	
	@FXML
	public void administrarUbicaciones() {
		
	}
	
	@FXML
	public void acercaDe() {
		
	}

}
/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
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

public class Home_Admin_Controller {

	private Date date;
	private int rol_number;
	private Stage usuarios;
	private Stage incidenciaCrear;
	private Stage incidenciaConsultar;
	private Stage proveedorAdmin;
	private Stage proveedorConsultar;
	private Stage incidenciasAdmin;
	private Stage acercaDe;
	private Stage mensajesConsultar;
	private Stage mensajesCrear;
	private Stage ubicacionesConsultar;
	private Stage ubicacionesAdmin;
	private FXMLLoader fxmlLoaderAdministrarUsuarios;
	private FXMLLoader fxmlLoaderCrearIncidencia;
	private FXMLLoader fxmlLoaderConsultarIncidencias;
	private FXMLLoader fxmlLoaderAdministrarProveedores;
	private FXMLLoader fxmlLoaderConsultarProveedores;
	private FXMLLoader fxmlLoaderAdministrarIncidencias;
	private FXMLLoader fxmlLoaderAcerca;
	private FXMLLoader fxmlLoaderConsultarMensajes;
	private FXMLLoader fxmlLoaderCrearMensajes;
	private FXMLLoader fxmlLoaderConsultarUbicaciones;
	private FXMLLoader fxmlLoaderAdministrarUbicaciones;
	private Parent root1;
	private Parent root2;
	private Parent root3;
	private Parent root4;
	private Parent root5;
	private Parent root6;
	private Parent root7;
	private Parent root8;
	private Parent root9;
	private Parent root10;
	private Parent root11;
	private usuarios_controller controllerUsuarios;
	private incidencia_Controller controllerIncidencias;
	private consultar_incidencias controllerConsularIncidencias;
	private proveedores_controller controllerProveedores;
	private consultar_proveedores controllerConsultarProveedores;
	private acercaDe_controller controllerInformacion;
	private administrar_incidencias controllerAdministrarIncidencias;
	private consultar_mensajes controllerConsultarMensajes;
	private mensajes controllerCrearMensajes;
	private consultar_ubicaciones controllerConsultarUbicaciones;
	private administrar_ubicaciones controllerAdministrarUbicaciones;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;
	private Scene scene5;
	private Scene scene6;
	private Scene scene7;
	private Scene scene8;
	private Scene scene9;
	private Scene scene10;
	private Scene scene11;
	private Image icon;

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

		this.controllerUsuarios = this.fxmlLoaderAdministrarUsuarios.<usuarios_controller>getController();
		this.controllerIncidencias = this.fxmlLoaderCrearIncidencia.<incidencia_Controller>getController();
		this.controllerProveedores = this.fxmlLoaderAdministrarProveedores.<proveedores_controller>getController();
		this.controllerInformacion = this.fxmlLoaderAcerca.<acercaDe_controller>getController();
		this.controllerAdministrarIncidencias = this.fxmlLoaderAdministrarIncidencias.<administrar_incidencias>getController();
		this.controllerCrearMensajes = this.fxmlLoaderCrearMensajes.<mensajes>getController();
		this.controllerAdministrarUbicaciones = this.fxmlLoaderAdministrarUbicaciones.<administrar_ubicaciones>getController();
			
		this.scene1 = new Scene(this.root1);
		this.scene2 = new Scene(this.root2);
		this.scene4 = new Scene(this.root4);
		this.scene6 = new Scene(this.root6);
		this.scene7 = new Scene(this.root7);
		this.scene9 = new Scene(this.root9);
		this.scene11 = new Scene(this.root11);
		this.icon = null;

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
	public void administrarUsuarios() throws IOException {
		this.controllerUsuarios.inicializar(this.nombre.getText());
		this.usuarios.setScene(this.scene1);
		this.usuarios.show();
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
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png")); //decimos dónde está el icono
		acercaDe.getIcons().add(icon); //agregamos el icono
		acercaDe.setTitle("Proyecto Jose Carlos"); //ponemos el título de la ventana
		this.acercaDe.show();
	}
	
	@FXML
	public void administrarIncidencias() throws IOException, SQLException {
		this.controllerAdministrarIncidencias.inicializar(this.nombre.getText());
		this.incidenciasAdmin.setScene(this.scene7);
		this.incidenciasAdmin.show();
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
	
	@FXML
	public void consultarUbicaciones() throws IOException, SQLException {
		this.ubicacionesConsultar = new Stage();
		this.fxmlLoaderConsultarUbicaciones = new FXMLLoader(getClass().getResource("/view/consultarUbicaciones.fxml"));
		this.root10 = (Parent) this.fxmlLoaderConsultarUbicaciones.load();
		this.controllerConsultarUbicaciones = this.fxmlLoaderConsultarUbicaciones.<consultar_ubicaciones>getController();
		this.scene10 = new Scene(this.root10);
		this.controllerConsultarUbicaciones.inicializar(this.nombre.getText());
		this.ubicacionesConsultar.setScene(this.scene10);
		this.ubicacionesConsultar.show();
	}
	
	@FXML
	public void administrarUbicaciones() throws IOException, SQLException {
		this.controllerAdministrarUbicaciones.inicializar(this.nombre.getText());
		this.ubicacionesAdmin.setScene(this.scene11);
		this.ubicacionesAdmin.show();
	}
	
}

/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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

	private Image icon;
	private Stage stage;

	public Home_Admin_Controller() throws IOException {
		this.acercaDe = new Stage();
		this.fxmlLoaderAcercaDe = new FXMLLoader(this.getClass().getResource("/view/acerca_de.fxml"));
		this.root6 = (Parent) this.fxmlLoaderAcercaDe.load();
		this.scene6 = new Scene(this.root6);

		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.stage = null;
	}

	public void recibirParametros(String nombreCompleto, int rol) {
		this.date = new Date();
		this.nombre.setEditable(false);
		this.rol_name.setEditable(false);
		this.fecha.setEditable(false);
		this.nombre.setText(nombreCompleto);
		this.rol_name.setText("Administrador");
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.rol_number = rol;
	}

	@FXML
	public void administrarUsuarios() throws SQLException, IOException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		this.usuarios = new Stage();
		this.fxmlLoaderAdministrarUsuarios = new FXMLLoader(
				this.getClass().getResource("/view/consultarUsuarios.fxml"));
		this.root1 = (Parent) this.fxmlLoaderAdministrarUsuarios.load();
		this.scene1 = new Scene(this.root1);
		this.controllerUsuarios = this.fxmlLoaderAdministrarUsuarios.<consultar_usuarios>getController();
		this.controllerUsuarios.inicializar(this.nombre.getText());
		this.usuarios.setScene(this.scene1);
		this.usuarios.getIcons().add(this.icon); // agregamos el icono
		this.usuarios.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.usuarios.show();
	}

	@FXML
	public void administrarProveedores() throws SQLException, IOException {
		this.proveedores = new Stage();
		this.fxmlLoaderAdministrarProveedores = new FXMLLoader(
				this.getClass().getResource("/view/consultarProveedores.fxml"));
		this.root2 = (Parent) this.fxmlLoaderAdministrarProveedores.load();
		this.scene2 = new Scene(this.root2);
		this.controllerProveedores = this.fxmlLoaderAdministrarProveedores.<consultar_proveedores>getController();
		this.controllerProveedores.inicializar(this.nombre.getText());
		this.proveedores.setScene(this.scene2);
		this.proveedores.getIcons().add(this.icon); // agregamos el icono
		this.proveedores.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.proveedores.show();
	}

	@FXML
	public void administrarIncidencias() throws SQLException, IOException {
		this.incidencias = new Stage();
		this.fxmlLoaderAdministrarIncidencias = new FXMLLoader(
				this.getClass().getResource("/view/consultarIncidencias.fxml"));
		this.root3 = (Parent) this.fxmlLoaderAdministrarIncidencias.load();
		this.scene3 = new Scene(this.root3);
		this.controllerIncidencias = this.fxmlLoaderAdministrarIncidencias.<consultar_incidencias>getController();
		this.controllerIncidencias.inicializar(this.nombre.getText(), this.rol_number);
		this.incidencias.setScene(this.scene3);
		this.incidencias.getIcons().add(this.icon); // agregamos el icono
		this.incidencias.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.incidencias.show();
	}

	@FXML
	public void administrarMensajes() throws SQLException, IOException {
		this.mensajes = new Stage();
		this.fxmlLoaderAdministrarMensajes = new FXMLLoader(
				this.getClass().getResource("/view/consultarMensajes.fxml"));
		this.root4 = (Parent) this.fxmlLoaderAdministrarMensajes.load();
		this.scene4 = new Scene(this.root4);
		this.controllerMensajes = this.fxmlLoaderAdministrarMensajes.<consultar_mensajes>getController();
		this.controllerMensajes.inicializar(this.nombre.getText());
		this.mensajes.setScene(this.scene4);
		this.mensajes.getIcons().add(this.icon); // agregamos el icono
		this.mensajes.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.mensajes.show();
	}

	@FXML
	public void administrarUbicaciones() throws SQLException, IOException {
		this.ubicaciones = new Stage();
		this.fxmlLoaderAdministrarUbicaciones = new FXMLLoader(
				this.getClass().getResource("/view/consultarUbicaciones.fxml"));
		this.root5 = (Parent) this.fxmlLoaderAdministrarUbicaciones.load();
		this.scene5 = new Scene(this.root5);
		this.controllerUbicaciones = this.fxmlLoaderAdministrarUbicaciones.<consultar_ubicaciones>getController();
		this.controllerUbicaciones.inicializar(this.nombre.getText());
		this.ubicaciones.setScene(this.scene5);
		this.ubicaciones.getIcons().add(this.icon); // agregamos el icono
		this.ubicaciones.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.ubicaciones.show();
	}

	@FXML
	public void acercaDe() throws SQLException {
		this.acercaDe.setScene(this.scene6);
		this.acercaDe.getIcons().add(this.icon); // agregamos el icono
		this.acercaDe.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.acercaDe.show();
	}

}
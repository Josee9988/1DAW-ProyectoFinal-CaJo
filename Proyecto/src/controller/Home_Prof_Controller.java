/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
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

public class Home_Prof_Controller {

	private Date date;
	private int rol_number;
	private Stage incidencias;
	private Stage proveedores;
	private Stage mensajes;
	private Stage acercaDe;
	private FXMLLoader fxmlLoaderAdministrarIncidencia;
	private FXMLLoader fxmlLoaderAdministrarProveedores;
	private FXMLLoader fxmlLoaderAdministrarMensajes;
	private FXMLLoader fxmlLoaderAcerca;
	private Parent rootAdministrarIncidencia;
	private Parent rootAdministrarProveedores;
	private Parent rootAdministrarMensajes;
	private Parent rootAcerca;
	private consultar_incidencias controllerIncidencia;
	private consultar_proveedores controllerProveedores;
	private consultar_mensajes controllerMensajes;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;

	@FXML
	private TextField username;
	@FXML
	private TextField rol_name;
	@FXML
	private TextField fecha;

	private Image icon;

	public Home_Prof_Controller() throws IOException {
		this.incidencias = new Stage();
		this.proveedores = new Stage();
		this.mensajes = new Stage();
		this.acercaDe = new Stage();

		this.fxmlLoaderAdministrarIncidencia = new FXMLLoader(
				this.getClass().getResource("/view/consultarIncidencias.fxml"));
		this.fxmlLoaderAdministrarProveedores = new FXMLLoader(
				this.getClass().getResource("/view/consultarProveedores.fxml"));
		this.fxmlLoaderAdministrarMensajes = new FXMLLoader(
				this.getClass().getResource("/view/consultarMensajes.fxml"));
		this.fxmlLoaderAcerca = new FXMLLoader(this.getClass().getResource("/view/acerca_de.fxml"));

		this.rootAdministrarIncidencia = (Parent) this.fxmlLoaderAdministrarIncidencia.load();
		this.rootAdministrarProveedores = (Parent) this.fxmlLoaderAdministrarProveedores.load();
		this.rootAdministrarMensajes = (Parent) this.fxmlLoaderAdministrarMensajes.load();
		this.rootAcerca = (Parent) this.fxmlLoaderAcerca.load();

		this.controllerIncidencia = this.fxmlLoaderAdministrarIncidencia.<consultar_incidencias>getController();
		this.controllerProveedores = this.fxmlLoaderAdministrarProveedores.<consultar_proveedores>getController();
		this.controllerMensajes = this.fxmlLoaderAdministrarMensajes.<consultar_mensajes>getController();

		this.scene1 = new Scene(this.rootAdministrarIncidencia);
		this.scene2 = new Scene(this.rootAdministrarProveedores);
		this.scene3 = new Scene(this.rootAdministrarMensajes);
		this.scene4 = new Scene(this.rootAcerca);

		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
	}

	/**
	 * recibirParametros recibe los parametros necesarios para inicializar varias textfields
	 * @param nombreCompleto nombre + apellidos en un string
	 * @param rol rol que toma el usuario
	 */
	public void recibirParametros(String nombreCompleto, int rol) {
		this.date = new Date();
		this.username.setEditable(false);
		this.rol_name.setEditable(false);
		this.fecha.setEditable(false);
		this.username.setText(nombreCompleto);
		this.rol_name.setText("Profesor");
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.rol_number = rol;
	}

	@FXML
	public void administrarIncidencias() throws SQLException {
		this.controllerIncidencia.inicializar(this.username.getText(), this.rol_number);
		this.incidencias.setScene(this.scene1);
		this.incidencias.getIcons().add(this.icon); // agregamos el icono
		this.incidencias.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.incidencias.show();
	}

	@FXML
	public void consultarProveedores() throws SQLException {
		this.controllerProveedores.inicializar(this.username.getText());
		this.proveedores.setScene(this.scene2);
		this.proveedores.getIcons().add(this.icon); // agregamos el icono
		this.proveedores.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.proveedores.show();
	}

	@FXML
	public void administrarMensajes() throws SQLException {
		this.controllerMensajes.inicializar(this.username.getText());
		this.mensajes.setScene(this.scene3);
		this.mensajes.getIcons().add(this.icon); // agregamos el icono
		this.mensajes.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.mensajes.show();
	}

	@FXML
	public void acercaDe() throws SQLException {
		this.acercaDe.setScene(this.scene4);
		this.acercaDe.getIcons().add(this.icon); // agregamos el icono
		this.acercaDe.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.acercaDe.setResizable(false);
		this.acercaDe.show();
	}
}

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

import controllerIncidencias.consultar_incidencias;
import controllerMensajes.consultar_mensajes;
import controllerProveedores.consultar_proveedores;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

	private Image icon;

	/**
	 * Home_Jefe_Controller constructor default que inicializa variables
	 *
	 * @throws IOException si ha habido una excepción SQL
	 */
	public Home_Jefe_Controller() throws IOException {

		this.incidencias = new Stage();
		this.proveedores = new Stage();
		this.mensajes = new Stage();
		this.acercaDe = new Stage();

		this.fxmlLoaderAdministrarIncidencias = new FXMLLoader(
				this.getClass().getResource("/view/consultarIncidencias.fxml"));
		this.fxmlLoaderAdministrarProveedores = new FXMLLoader(
				this.getClass().getResource("/view/consultarProveedores.fxml"));
		this.fxmlLoaderConsultarMensajes = new FXMLLoader(this.getClass().getResource("/view/consultarMensajes.fxml"));
		this.fxmlLoaderAcerca = new FXMLLoader(this.getClass().getResource("/view/acerca_de.fxml"));

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
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
	}

	/**
	 * recibirParametros recibe los parametros necesarios para inicializar varias
	 * textfields
	 *
	 * @param nombreCompleto nombre + apellidos en un string
	 * @param rol            rol que toma el usuario
	 */
	public void recibirParametros(String nombreCompleto, int rol) {
		this.date = new Date();
		this.nombre.setEditable(false);
		this.rol_name.setEditable(false);
		this.fecha.setEditable(false);
		this.nombre.setText(nombreCompleto);
		this.rol_name.setText("Jefe de Departamento");
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.rol_number = rol;
	}

	@FXML
	/**
	 * administrarProveedores abre la view de administrar proveedores la cual
	 * muestra todos los proveedores en una tableview
	 *
	 * @throws SQLException por si hay una excepción de tipo SQL
	 */
	public void administrarProveedores() throws SQLException {
		this.controllerProveedores.inicializar(this.nombre.getText(), this.rol_number);
		this.proveedores.setScene(this.scene2);
		this.proveedores.getIcons().add(this.icon); // agregamos el icono
		this.proveedores.setTitle("Administrar Proveedores"); // ponemos el título de la ventana
		this.proveedores.show();
	}

	@FXML
	/**
	 * administrarIncidencias abre la view de administrar incidencias la cual
	 * muestra todas las incidencias en una tableview
	 *
	 * @throws SQLException por si hay una excepción de tipo SQL
	 */
	public void administrarIncidencias() throws SQLException {
		this.controllerIncidencias.inicializar(this.nombre.getText(), this.rol_number);
		this.incidencias.setScene(this.scene1);
		this.incidencias.getIcons().add(this.icon); // agregamos el icono
		this.incidencias.setTitle("Administrar Incidencias"); // ponemos el título de la ventana
		this.incidencias.show();
	}

	@FXML
	/**
	 * administrarMensajes abre la view de administrar mensajes la cual muestra
	 * todos los mensajes en una tableview
	 *
	 * @throws SQLException por si hay una excepción de tipo SQL
	 */
	public void administrarMensajes() throws SQLException {
		this.controllerMensajes.inicializar(this.nombre.getText(), this.rol_number);
		this.mensajes.setScene(this.scene3);
		this.mensajes.getIcons().add(this.icon); // agregamos el icono
		this.mensajes.setTitle("Administrar Mensajes"); // ponemos el título de la ventana
		this.mensajes.show();
	}

	@FXML
	/**
	 * acercaDe abre la view de acercaDe la cual contiene información sobre el
	 * programa
	 */
	public void acercaDe() {
		this.acercaDe.setScene(this.scene4);
		this.acercaDe.getIcons().add(this.icon); // agregamos el icono
		this.acercaDe.setTitle("Acerca de"); // ponemos el título de la ventana
		this.acercaDe.setResizable(false);
		this.acercaDe.show();
	}

}

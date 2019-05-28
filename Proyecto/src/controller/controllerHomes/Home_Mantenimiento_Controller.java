/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerHomes;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.controllerIncidencias.consultar_incidencias;
import controller.controllerMensajes.consultar_mensajes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Conexion;

public class Home_Mantenimiento_Controller {

	private Date date;
	private int rol_number;

	private Stage incidencias;
	private Stage mensajes;
	private Stage acercaDe;
	private FXMLLoader fxmlLoaderAdministrarIncidencia;
	private FXMLLoader fxmlLoaderAdministrarMensajes;
	private FXMLLoader fxmlLoaderAcerca;
	private Parent root1;
	private Parent root2;
	private Parent root3;
	private consultar_incidencias controllerIncidencias;
	private consultar_mensajes controllerMensajes;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;

	@FXML
	private TextField nombre;
	@FXML
	private TextField rol_name;
	@FXML
	private TextField fecha;

	private Image icon;

	/**
	 * Home_Mantenimiento_Controller constructor default que inicializa variables
	 *
	 * @throws IOException si ha habido una excepción de tipo SQl
	 */
	public Home_Mantenimiento_Controller() throws IOException {

		this.incidencias = new Stage();
		this.mensajes = new Stage();
		this.acercaDe = new Stage();

		this.fxmlLoaderAdministrarIncidencia = new FXMLLoader(
				this.getClass().getResource("/view/consultarIncidencias.fxml"));
		this.fxmlLoaderAdministrarMensajes = new FXMLLoader(
				this.getClass().getResource("/view/consultarMensajes.fxml"));
		this.fxmlLoaderAcerca = new FXMLLoader(this.getClass().getResource("/view/acerca_de.fxml"));

		this.root1 = (Parent) this.fxmlLoaderAdministrarIncidencia.load();
		this.root2 = (Parent) this.fxmlLoaderAdministrarMensajes.load();
		this.root3 = (Parent) this.fxmlLoaderAcerca.load();

		this.controllerIncidencias = this.fxmlLoaderAdministrarIncidencia.<consultar_incidencias>getController();
		this.controllerMensajes = this.fxmlLoaderAdministrarMensajes.<consultar_mensajes>getController();

		this.scene1 = new Scene(this.root1);
		this.scene2 = new Scene(this.root2);
		this.scene3 = new Scene(this.root3);

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
		this.rol_name.setText("Mantenimiento");
		this.fecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.rol_number = rol;
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
		this.mensajes.setScene(this.scene2);
		this.mensajes.getIcons().add(this.icon); // agregamos el icono
		this.mensajes.setTitle("Administrar Mensajes"); // ponemos el título de la ventana
		this.mensajes.show();
	}

	@FXML
	/**
	 * cerrarBD cierra la conexión con la base de datos de forma segura y cierra el
	 * programa
	 *
	 * @param SQLException si ha habido una excepción SQL
	 */
	public void cerrarBD() throws SQLException {
		Conexion.getInstance().cerrarConexion();
		System.exit(0);
	}

	@FXML
	/**
	 * acercaDe abre la view de acercaDe la cual contiene información sobre el
	 * programa
	 */
	public void acercaDe() {
		this.acercaDe.setScene(this.scene3);
		this.acercaDe.getIcons().add(this.icon); // agregamos el icono
		this.acercaDe.setTitle("Acerca de"); // ponemos el título de la ventana
		this.acercaDe.setResizable(false);
		this.acercaDe.show();
	}

}
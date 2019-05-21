/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package controllerMensajes;

import java.sql.SQLException;
import java.util.Calendar;

import dto.mensajesDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.jdbcIncidenciasDAO;
import model.jdbcUsuarioDAO;

public class agregar_mensajes {
	@FXML
	private TextField asunto;
	@FXML
	private TextArea cuerpo;
	@FXML
	private ComboBox incidencia;
	@FXML
	private ComboBox destinatario;
	@FXML
	private Button agregarmensaje;

	private ObservableList<String> incidenciaBox;
	private ObservableList<String> destinatarioBox;

	private consultar_mensajes consultar_mensajes;
	private Stage stage;
	private String nombrecompleto;
	private jdbcUsuarioDAO bdusuario;
	private jdbcIncidenciasDAO incidencias;
	private mensajesDTO mensajesDTO;

	/**
	 * agregar_mensajes constructor default que inicializa variables
	 */
	public agregar_mensajes() {
		this.consultar_mensajes = new consultar_mensajes();
		this.stage = null;
		this.bdusuario = new jdbcUsuarioDAO();
		this.nombrecompleto = "";
		this.incidencias = new jdbcIncidenciasDAO();
		this.mensajesDTO = new mensajesDTO();
	}

	/**
	 * inicializarMensajes inicializamos los mensajes. los ComboBoxes
	 *
	 * @param nombrecompleto nombre + apellidos de el usuario logeado
	 * @throws SQLException excepción SQL
	 */
	public void inicializarMensajes(String nombrecompleto) throws SQLException {
		this.nombrecompleto = nombrecompleto;
		// TODO: que salgan los que tocan y que luego con un switch se pase a numeros de
		// id de incidencia y de usuario

		this.incidenciaBox = FXCollections.observableArrayList(this.incidencias.leerDescripcionesIncidencias());
		this.destinatarioBox = FXCollections.observableArrayList(this.bdusuario.leerDestinatarios());

		this.incidencia.setItems(this.incidenciaBox);
		this.incidencia.setEditable(false);
		this.incidencia.getSelectionModel().select(0);
		this.incidencia.getStyleClass().add("center-aligned");// clase del css para centrar combobox

		this.destinatario.setItems(this.destinatarioBox);
		this.destinatario.setEditable(false);
		this.destinatario.getSelectionModel().select(0);
		this.destinatario.getStyleClass().add("center-aligned");// clase del css para centrar combobox
	}

	@FXML
	/**
	 * agregarmensaje método que tras recibir el texto de los TextFields rellenados
	 * por el usuario rellena un objeto mensajesDTO y lo pasa a la base de datos
	 *
	 * @throws SQLException excepción SQL
	 */
	public void agregarmensaje() throws SQLException {
		this.mensajesDTO.setAsunto(this.asunto.getText());
		this.mensajesDTO.setCuerpo(this.cuerpo.getText());
		// Ponemos la fecha actual
		java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		this.mensajesDTO.setFecha(sqlDate);

		this.mensajesDTO.setIncidencia(this.incidencias.obtenerIdDesdeDescripcion((String) this.incidencia.getValue())); // añadimos
																															// incidencia
		this.mensajesDTO.setReceptor(this.bdusuario.devolverId((String) this.destinatario.getValue())); // añadimos
																										// receptor
		String[] nombreYapellidos = this.nombrecompleto.split(" ");
		int idEmisor = this.bdusuario.devolverId(nombreYapellidos[0], nombreYapellidos[1]); // añadimos emisor
		this.mensajesDTO.setEmisor(idEmisor);
		this.stage = (Stage) this.agregarmensaje.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.consultar_mensajes.agregarEnBaseDatos(this.mensajesDTO);
	}

}

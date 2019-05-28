/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerMensajes;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.jdbcIncidenciasDAO;
import model.jdbcMensajesDAO;
import model.jdbcUsuarioDAO;

public class agregar_mensajes {
	@FXML
	private TextField asunto;
	@FXML
	private TextArea cuerpo;
	@FXML
	private ComboBox<String> incidencia;
	@FXML
	private ComboBox<String> destinatario;
	@FXML
	private Button agregarmensaje;
	@FXML
	private Text textoError;

	private ObservableList<String> incidenciaBox;
	private ObservableList<String> destinatarioBox;

	private Stage stage;
	private String nombrecompleto;
	private jdbcUsuarioDAO bdusuario;
	private jdbcIncidenciasDAO incidencias;
	private jdbcMensajesDAO jdbcMensajesDAO;
	private mensajesDTO mensajesDTO;

	/**
	 * agregar_mensajes constructor default que inicializa variables
	 */
	public agregar_mensajes() {
		this.bdusuario = new jdbcUsuarioDAO();
		this.nombrecompleto = "";
		this.incidencias = new jdbcIncidenciasDAO();
		this.mensajesDTO = new mensajesDTO();
		this.jdbcMensajesDAO = new jdbcMensajesDAO();
	}

	/**
	 * inicializarMensajes inicializamos los mensajes. los ComboBoxes
	 *
	 * @param nombrecompleto nombre + apellidos de el usuario logeado
	 * @throws SQLException excepción SQL
	 */
	public void inicializarMensajes(String nombrecompleto) throws SQLException {
		this.nombrecompleto = nombrecompleto;

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

		this.mensajesDTO.setIncidencia(this.incidencias.obtenerId(this.incidencia.getValue())); // añadimos
		// incidencia
		this.mensajesDTO.setReceptor(this.bdusuario.devolverId(this.destinatario.getValue())); // añadimos
		// receptor
		String[] nombreYapellidos = this.nombrecompleto.split(" ");
		int idEmisor = this.bdusuario.devolverId(nombreYapellidos[0], nombreYapellidos[1]); // añadimos emisor
		this.mensajesDTO.setEmisor(idEmisor);
		this.stage = (Stage) this.agregarmensaje.getScene().getWindow(); // seleccionamos la escena actual

		if (this.asunto.getText().isEmpty() || this.cuerpo.getText().isEmpty() || this.incidencia.getValue().equals("")
				|| this.destinatario.getValue().equals("")) {
			this.textoError.setText("Rellena todos los campos");

		} else {
			this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
			this.jdbcMensajesDAO.crearMensaje(this.mensajesDTO);
		}

	}

}

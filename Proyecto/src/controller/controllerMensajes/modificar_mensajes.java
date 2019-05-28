package controller.controllerMensajes;

import java.sql.SQLException;
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

public class modificar_mensajes {
	@FXML
	private TextField asunto;
	@FXML
	private TextArea cuerpo;
	@FXML
	private ComboBox<String> incidencia;
	@FXML
	private Button modificarMensaje;
	@FXML
	private Text textoError;
	private int id;

	private ObservableList<String> incidenciaBox;

	private Stage stage;
	private jdbcIncidenciasDAO incidencias;
	private jdbcMensajesDAO jdbcMensajesDAO;
	private mensajesDTO mensajesDTO;

	/**
	 * agregar_mensajes constructor default que inicializa variables
	 */
	public modificar_mensajes() {
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
	public void inicializarMensajes(mensajesDTO m) throws SQLException {
		this.id = m.getId();
		this.incidenciaBox = FXCollections.observableArrayList(this.incidencias.leerDescripcionesIncidencias());

		this.incidencia.setItems(this.incidenciaBox);
		this.incidencia.setEditable(false);
		this.incidencia.getSelectionModel().select(0);
		this.incidencia.getStyleClass().add("center-aligned");// clase del css para centrar combobox
		
		this.asunto.setText(m.getAsunto());
		this.cuerpo.setText(m.getCuerpo());
	}

	@FXML
	/**
	 * agregarmensaje método que tras recibir el texto de los TextFields rellenados
	 * por el usuario rellena un objeto mensajesDTO y lo pasa a la base de datos
	 *
	 * @throws SQLException excepción SQL
	 */
	public void modificarMensaje() throws SQLException {
		this.mensajesDTO.setAsunto(this.asunto.getText());
		this.mensajesDTO.setCuerpo(this.cuerpo.getText());
		this.mensajesDTO.setId(this.id);
		this.mensajesDTO.setIncidencia(this.incidencias.obtenerId(this.incidencia.getValue())); // añadimos incidencia

		this.stage = (Stage) this.modificarMensaje.getScene().getWindow(); // seleccionamos la escena actual
		if (this.asunto.getText().isEmpty() || this.cuerpo.getText().isEmpty() || this.incidencia.getValue().equals("")) {
			this.textoError.setText("Rellena todos los campos");

		} else {
			this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
			this.jdbcMensajesDAO.modificarMensaje(this.mensajesDTO);
		}

	}

}

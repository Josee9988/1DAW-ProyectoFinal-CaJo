/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package creadoresController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import controller.consultar_mensajes;
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

	private consultar_mensajes consultar_mensajes;
	private Stage stage;
	private String nombrecompleto;
	private jdbcUsuarioDAO bdusuario;

	public agregar_mensajes() {
		this.consultar_mensajes = new consultar_mensajes();
		this.stage = null;
		this.bdusuario = new jdbcUsuarioDAO();
		this.nombrecompleto = "";
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
		jdbcIncidenciasDAO incidendias = new jdbcIncidenciasDAO();
		ArrayList<Integer> incidenciasArray = incidendias.leerIncidencias();
		ArrayList<Integer> destinatariosArray = this.bdusuario.leerDestinatarios();
		ObservableList<Integer> incidenciaBox = FXCollections.observableArrayList(incidenciasArray);
		ObservableList<Integer> destinatarioBox = FXCollections.observableArrayList(destinatariosArray);

		this.incidencia.setItems(incidenciaBox);
		this.incidencia.setEditable(false);
		this.incidencia.getSelectionModel().select(0);
		this.incidencia.getStyleClass().add("center-aligned");// clase del css para centrar combobox

		this.destinatario.setItems(destinatarioBox);
		this.destinatario.setEditable(false);
		this.destinatario.getSelectionModel().select(0);
		this.destinatario.getStyleClass().add("center-aligned");// clase del css para centrar combobox
	}

	@FXML
	public void agregarmensaje() throws SQLException {
		mensajesDTO mensajesDTO = new mensajesDTO();
		mensajesDTO.setAsunto(this.asunto.getText());
		mensajesDTO.setCuerpo(this.cuerpo.getText());
		// POnemos la fecha actual
		java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		mensajesDTO.setFecha(sqlDate);
		mensajesDTO.setIncidencia((int) this.incidencia.getValue());
		mensajesDTO.setReceptor((int) this.destinatario.getValue());
		String[] nombreYapellidos = this.nombrecompleto.split(" ");
		int idEmisor = this.bdusuario.devolverId(nombreYapellidos[0], nombreYapellidos[1]);
		mensajesDTO.setEmisor(idEmisor);
		this.stage = (Stage) this.agregarmensaje.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.consultar_mensajes.agregarEnBaseDatos(mensajesDTO);
	}

}

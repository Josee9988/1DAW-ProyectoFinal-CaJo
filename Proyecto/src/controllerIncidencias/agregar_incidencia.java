/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package controllerIncidencias;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dto.incidenciaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.jdbcUbicacionDAO;
import model.jdbcUsuarioDAO;

public class agregar_incidencia {
	@FXML
	private TextField descripcion;
	@FXML
	private TextField elemento;
	@FXML
	private DatePicker date;
	@FXML
	private TextField urgencia;
	@FXML
	private TextField categoria;
	@FXML
	private TextField materiales;
	@FXML
	private ComboBox<String> ubicacion;
	@FXML
	private Button agregarincidencia;

	private Stage stage;

	private consultar_incidencias consultar;

	private String nombreCompleto;

	/**
	 * agregar_incidencia constructor default que inicializa variables
	 */
	public agregar_incidencia() {
		this.consultar = new consultar_incidencias();
		this.nombreCompleto = "";
		this.stage = null;
	}

	/**
	 * inicializar inicializa los ComboBox llamando a las baes de datos de
	 * ubicaciones para poner seleccionarlas en el ComboBox
	 *
	 * @param nombreCompleto nombre y apellidos del usuario logeado
	 * @throws SQLException por si ha habido una excepción SQL
	 */
	public void inicializar(String nombreCompleto) throws SQLException {
		this.nombreCompleto = nombreCompleto;
		jdbcUbicacionDAO jdbcUbicacionDAO = new jdbcUbicacionDAO();
		ArrayList<String> ubicacionesArray = jdbcUbicacionDAO.leerNombresUbicaciones();

		ObservableList<String> ubicacionBox = FXCollections.observableArrayList(ubicacionesArray);
		this.ubicacion.setItems(ubicacionBox);
		this.ubicacion.setEditable(false);
		this.ubicacion.getSelectionModel().select(0);
		this.ubicacion.getStyleClass().add("center-aligned");// clase del css para centrar combobox

	}

	@FXML
	/**
	 * agregarincidencia tras recibir los datos introducidos por el usuario desde
	 * TextFields y ComboBox, rellenamos un objeto incideciaDTO y se lo pasamos a la
	 * base de datos
	 *
	 * @throws SQLException por si ha habido una excepción SQL
	 */
	public void agregarincidencia() throws SQLException {
		incidenciaDTO incidenciaDTO = new incidenciaDTO();
		jdbcUsuarioDAO jdbcUsuarioDAO = new jdbcUsuarioDAO();
		String[] nombreYapellidos = this.nombreCompleto.split(" ");

		incidenciaDTO.setUsuario(jdbcUsuarioDAO.leerUsuario(nombreYapellidos[0], nombreYapellidos[1]));
		incidenciaDTO.setDescripcion(this.descripcion.getText());
		incidenciaDTO.setElemento(this.elemento.getText());

		// Si la fecha está vacia cogerá la actual
		if (this.date.getValue() == null) {
			java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			incidenciaDTO.setFecha(sqlDate);

		} else {
			Date date = java.sql.Date.valueOf(this.date.getValue());
			incidenciaDTO.setFecha((java.sql.Date) date);
		}
		incidenciaDTO.setUrgencia(this.urgencia.getText());
		incidenciaDTO.setCategoria(this.categoria.getText());
		incidenciaDTO.setUbicacion(this.ubicacion.getValue());
		this.stage = (Stage) this.agregarincidencia.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.consultar.agregarIncidenciaEnBD(incidenciaDTO);
	}

}

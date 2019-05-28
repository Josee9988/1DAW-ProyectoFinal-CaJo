/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerIncidencias;

import java.sql.SQLException;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.jdbcIncidenciasDAO;
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
	private ComboBox<String> urgencia;
	@FXML
	private ComboBox<String> categoria;
	@FXML
	private TextField materiales;
	@FXML
	private ComboBox<String> ubicacion;
	@FXML
	private Button agregarincidencia;
	@FXML
	private Text textoError;

	private Stage stage;

	private String nombreCompleto;
	private jdbcUbicacionDAO jdbcUbicacionDAO;
	private incidenciaDTO incidenciaDTO;
	private jdbcUsuarioDAO jdbcUsuarioDAO;
	private jdbcIncidenciasDAO jdbcIncidenciasDAO;

	/**
	 * agregar_incidencia constructor default que inicializa variables
	 */
	public agregar_incidencia() {
		this.nombreCompleto = "";
		this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
		this.incidenciaDTO = new incidenciaDTO();
		this.jdbcUsuarioDAO = new jdbcUsuarioDAO();
		this.jdbcIncidenciasDAO = new jdbcIncidenciasDAO();
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
		ObservableList<String> ubicacionBox = FXCollections
				.observableArrayList(this.jdbcUbicacionDAO.leerNombresUbicaciones());
		ObservableList<String> categoriaArray = FXCollections.observableArrayList("Hardware", "Software", "Otros");
		ObservableList<String> urgenciaArray = FXCollections.observableArrayList("Alta", "Media", "Baja",
				"Indiferente");

		// urgencia
		this.urgencia.setItems(urgenciaArray);
		this.urgencia.setEditable(false);
		this.urgencia.getSelectionModel().select(3);

		// categoría
		this.categoria.setItems(categoriaArray);
		this.categoria.setEditable(false);
		this.categoria.getSelectionModel().select(2);

		// ubicación
		this.ubicacion.setItems(ubicacionBox);
		this.ubicacion.setEditable(false);
		this.ubicacion.getSelectionModel().select(0);

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
		String[] nombreYapellidos = this.nombreCompleto.split(" ");

		this.incidenciaDTO.setUsuario(this.jdbcUsuarioDAO.leerUsuario(nombreYapellidos[0], nombreYapellidos[1]));
		this.incidenciaDTO.setDescripcion(this.descripcion.getText());
		this.incidenciaDTO.setElemento(this.elemento.getText());

		// Si la fecha está vacia cogerá la actual
		if (this.date.getValue() == null) {
			java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			this.incidenciaDTO.setFecha(sqlDate);

		} else {
			Date date = java.sql.Date.valueOf(this.date.getValue());
			this.incidenciaDTO.setFecha((java.sql.Date) date);
		}
		this.incidenciaDTO.setUrgencia(this.urgencia.getValue());
		this.incidenciaDTO.setCategoria(this.categoria.getValue());
		this.incidenciaDTO.setUbicacion(this.ubicacion.getValue());
		this.incidenciaDTO.setMateriales(this.materiales.getText());
		this.stage = (Stage) this.agregarincidencia.getScene().getWindow(); // seleccionamos la escena actual

		if (this.descripcion.getText().isEmpty() || this.elemento.getText().isEmpty()) {
			this.textoError.setText("Rellena todos los campos");
		} else {
			this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
			this.incidenciaDTO
			.setUbicacionI(this.jdbcUbicacionDAO.obtenerIdUbicacion(this.incidenciaDTO.getUbicacion()));
			this.jdbcIncidenciasDAO.crearIncidencia(this.incidenciaDTO);
		}
	}

}
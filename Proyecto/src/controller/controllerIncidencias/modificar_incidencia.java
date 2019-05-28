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

public class modificar_incidencia {
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
	private Button modificarIncidencia;
	@FXML
	private Text textoError;

	private Stage stage;
	private int id;

	private jdbcUbicacionDAO jdbcUbicacionDAO;
	private incidenciaDTO incidenciaDTO;
	private jdbcIncidenciasDAO jdbcIncidenciasDAO;

	/**
	 * agregar_incidencia constructor default que inicializa variables
	 */
	public modificar_incidencia() {
		this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
		this.incidenciaDTO = new incidenciaDTO();
		this.jdbcIncidenciasDAO = new jdbcIncidenciasDAO();
	}

	/**
	 * inicializar inicializa los ComboBox llamando a las baes de datos de
	 * ubicaciones para poner seleccionarlas en el ComboBox
	 *
	 * @param nombreCompleto nombre y apellidos del usuario logeado
	 * @throws SQLException por si ha habido una excepción SQL
	 */
	public void inicializar(incidenciaDTO i) throws SQLException {
		ObservableList<String> ubicacionBox = FXCollections
				.observableArrayList(this.jdbcUbicacionDAO.leerNombresUbicaciones());
		ObservableList<String> categoriaArray = FXCollections.observableArrayList("Hardware", "Software", "Otros");
		ObservableList<String> urgenciaArray = FXCollections.observableArrayList("Alta", "Media", "Baja",
				"Indiferente");
		
		this.descripcion.setText(i.getDescripcion());
		this.materiales.setText(i.getMateriales());
		this.elemento.setText(i.getElemento());
		this.id = i.getId();
		
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
	public void modificarIncidencia() throws SQLException {

		// Si la fecha está vacia cogerá la actual
		if (this.date.getValue() == null) {
			java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			this.incidenciaDTO.setFecha(sqlDate);

		} else {
			Date date = java.sql.Date.valueOf(this.date.getValue());
			this.incidenciaDTO.setFecha((java.sql.Date) date);
		}

		this.incidenciaDTO.setDescripcion(this.descripcion.getText());
		this.incidenciaDTO.setElemento(this.elemento.getText());
		this.incidenciaDTO.setUrgencia(this.urgencia.getValue());
		this.incidenciaDTO.setCategoria(this.categoria.getValue());
		this.incidenciaDTO.setUbicacion(this.ubicacion.getValue());
		this.incidenciaDTO.setMateriales(this.materiales.getText());
		this.incidenciaDTO.setId(this.id);
		this.incidenciaDTO.setUbicacionI(this.jdbcUbicacionDAO.obtenerIdUbicacion(this.incidenciaDTO.getUbicacion()));
		
		this.stage = (Stage) this.modificarIncidencia.getScene().getWindow(); // seleccionamos la escena actual
		if (this.descripcion.getText().isEmpty() || this.elemento.getText().isEmpty()) {
			this.textoError.setText("Rellena todos los campos");
		} else {
			this.jdbcIncidenciasDAO.modificarIncidencia(this.incidenciaDTO);
			this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		}
	}

}

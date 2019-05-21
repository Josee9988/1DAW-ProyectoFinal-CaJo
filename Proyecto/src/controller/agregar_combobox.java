package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import controllerIncidencias.consultar_incidencias;
import controllerMensajes.consultar_mensajes;
import controllerUsuarios.consultar_usuarios;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.jdbcIncidenciasDAO;
import model.jdbcUbicacionDAO;

public class agregar_combobox {
	@FXML
	private Label texto;
	@FXML
	private ComboBox comboBox;
	@FXML
	private Button aplicarBoton;
	private Stage stage;
	private int tipo;

	//al no saber cuál vamos a utilizar, pero sabemos que los utilizamos almenos en dos ocasiones los ponemos
	private jdbcIncidenciasDAO jdbcIncidenciasDAO;
	private jdbcUbicacionDAO jdbcUbicacionDAO;
	private consultar_incidencias consultar_incidencias;
	private consultar_mensajes consultar_mensajes;
	private consultar_usuarios consultar_usuarios;


	/**
	 * agregar_combobox constructor default que inicializa los valores necesarios
	 */
	public agregar_combobox() {
		this.tipo = 0;
	}


	/**
	 * inicializar método que se llama al principio que inicializa los comboBox llamando a la base de datos para que los rellene
	 * @param tipo recibe un entero, si es un 0 será porque rellenaremos el combobox con incidencias, si es 1 es porque es de ubicaciones, y 2 si es porque lo llaman los usuarios y el comboBox se rellena con los roles
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void inicializar(int tipo) throws SQLException {//0 == incidencias; 1 == ubicaciones; 2 == rolesUsuarios
		this.tipo = tipo;
		ObservableList<String> comboBox = null;
		ArrayList<String> ArrayToCombo = null;
		if (tipo == 0) {// si es una incidencia...
			this.texto.setText("Agregue incidencia");
			this.aplicarBoton.setText("Aplicar incidencia");
			this.jdbcIncidenciasDAO = new jdbcIncidenciasDAO();
			//agregamos descripciones al combobox
			ArrayToCombo = this.jdbcIncidenciasDAO.leerDescripcionesIncidencias();
		}else if(tipo == 1){// si es una ubicación
			this.texto.setText("Agregue ubicación");
			this.aplicarBoton.setText("Aplicar ubicación");
			this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
			//agregamos nombres al combobox
			ArrayToCombo = this.jdbcUbicacionDAO.leerNombresUbicacionesString();
		}else { //roles usuarios
			this.texto.setText("Agregue un rol");
			this.aplicarBoton.setText("Aplicar rol");
			ArrayToCombo = new ArrayList<>(Arrays.asList("Profesor", "Jefe Dpto", "Mantenimiento", "Admin"));
		}
		// fin if

		comboBox = FXCollections.observableArrayList(ArrayToCombo);
		this.comboBox.setItems(comboBox);
		this.comboBox.setEditable(false);
		this.comboBox.getSelectionModel().select(0);
		this.comboBox.getStyleClass().add("center-aligned");// clase del css para centrar combobox
	}// fin inicializar


	@FXML
	/**
	 * aplicarBoton método que se ejecuta en el momento en el que le damos al botón de aplicar cambios
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void aplicarBoton() throws SQLException {
		int idToReturn = 0;
		if (this.tipo == 0) { // si es una incidencia...
			this.consultar_mensajes = new consultar_mensajes();
			this.jdbcIncidenciasDAO = new jdbcIncidenciasDAO();
			idToReturn = this.jdbcIncidenciasDAO.obtenerIdDesdeDescripcion((String) this.comboBox.getValue());
			this.consultar_mensajes.agregarIncidenciaDeComboBox(idToReturn);

		}else if(this.tipo == 1) { // si es una ubicación
			this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
			this.consultar_incidencias = new consultar_incidencias();
			idToReturn = this.jdbcUbicacionDAO.obtenerIdUbicacion((String) this.comboBox.getValue());
			this.consultar_incidencias.agregarIncidenciaDeComboBox(idToReturn);
		}else {
			int resultado = 0;
			switch ((String) this.comboBox.getValue()) {
			case "Profesor":
				resultado = 1;
				break;
			case "Jefe Dpto.":
				resultado = 2;
				break;
			case "Mantenimiento":
				resultado = 3;
				break;
			case "Admin":
				resultado = 4;
				break;
			default:
				resultado=1;
				break;
			}//fin switch
			this.consultar_usuarios = new consultar_usuarios();
			this.consultar_usuarios.agregarRolDeComboBox(resultado);

		}
		//cerrar ventana actual
		this.stage = (Stage) this.aplicarBoton.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
	}


}

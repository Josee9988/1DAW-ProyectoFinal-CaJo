/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 24, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerUtilidades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import controller.controllerIncidencias.consultar_incidencias;
import controller.controllerMensajes.consultar_mensajes;
import controller.controllerUsuarios.consultar_usuarios;
import dto.usuarioDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.jdbcIncidenciasDAO;
import model.jdbcUbicacionDAO;
import model.jdbcUsuarioDAO;

public class agregar_combobox {
	@FXML
	private Label texto;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private Button aplicarBoton;
	private Stage stage;
	private int tipo;

	// al no saber cuál vamos a utilizar, pero sabemos que los utilizamos almenos en
	// dos ocasiones los ponemos
	private jdbcIncidenciasDAO jdbcIncidenciasDAO;
	private jdbcUbicacionDAO jdbcUbicacionDAO;
	private jdbcUsuarioDAO jdbcUsuariosDAO;
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
	 * inicializar método que se llama al principio que inicializa los comboBox
	 * llamando a la base de datos para que los rellene 0 == incidencias; 1 ==
	 * ubicaciones; 2 == rolesUsuarios, 3 = urgencia, 4 = categoría
	 *
	 * @param tipo recibe un entero, si es un 0 será porque rellenaremos el combobox
	 *             con incidencias, si es 1 es porque es de ubicaciones, y 2 si es
	 *             porque lo llaman los usuarios y el comboBox se rellena con los
	 *             roles. 3 si lo llama incidencia y se tiene que rellenar con una
	 *             urgencia, 4 si es una categoría, llamada desde incidencia también
	 *             y se rellena con categorías
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void inicializar(int tipo, String nombreCompleto) throws SQLException {
		this.tipo = tipo;
		ObservableList<String> comboBox = null;
		ArrayList<String> ArrayToCombo = null;
		if (tipo == 0) {// si es una incidencia...
			this.texto.setText("Agregue incidencia");
			this.aplicarBoton.setText("Aplicar incidencia");
			this.jdbcUsuariosDAO = new jdbcUsuarioDAO();
			this.jdbcIncidenciasDAO = new jdbcIncidenciasDAO();
			// agregamos descripciones al combobox
			ArrayToCombo = this.jdbcIncidenciasDAO.leerDescripcionesIncidenciasEspecificas(new usuarioDTO(
					this.jdbcUsuariosDAO.obtenerUser(nombreCompleto),
					this.jdbcUsuariosDAO.obtenerRol(nombreCompleto)));
		} else if (tipo == 1) {// si es una ubicación
			this.texto.setText("Agregue ubicación");
			this.aplicarBoton.setText("Aplicar ubicación");
			this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
			// agregamos nombres al combobox
			ArrayToCombo = this.jdbcUbicacionDAO.leerNombresUbicaciones();
		} else if (tipo == 2) { // roles usuarios
			this.texto.setText("Agregue un rol");
			this.aplicarBoton.setText("Aplicar rol");
			ArrayToCombo = new ArrayList<>(Arrays.asList("Profesor", "Jefe Dpto", "Mantenimiento", "Admin"));
		} else if (tipo == 3) { // urgencias incidencias
			this.texto.setText("Agregue una urgencia");
			this.aplicarBoton.setText("Aplicar urgencia");
			ArrayToCombo = new ArrayList<>(Arrays.asList("Alta", "Media", "Baja", "Indiferente"));
		} else { // categoría incidencias
			this.texto.setText("Agregue una categoría");
			this.aplicarBoton.setText("Aplicar categoría");
			ArrayToCombo = new ArrayList<>(Arrays.asList("Hardware", "Software", "Otros"));

		}
		// fin if

		comboBox = FXCollections.observableArrayList(ArrayToCombo);
		this.comboBox.setItems(comboBox);
		this.comboBox.setEditable(false);
		this.comboBox.getSelectionModel().select(0);
	}// fin inicializar

	@FXML
	/**
	 * aplicarBoton método que se ejecuta en el momento en el que le damos al botón
	 * de aplicar cambios
	 *
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void aplicarBoton() throws SQLException {
		int idToReturn = 0;
		if (this.tipo == 0) { // si es una incidencia...(clase mensaje)
			this.consultar_mensajes = new consultar_mensajes();
			this.jdbcIncidenciasDAO = new jdbcIncidenciasDAO();
			idToReturn = this.jdbcIncidenciasDAO.obtenerId(this.comboBox.getValue());
			this.consultar_mensajes.agregarIncidenciaDeComboBox(idToReturn);

		} else if (this.tipo == 1) { // si es una ubicación (clase incidencia)
			this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
			this.consultar_incidencias = new consultar_incidencias();
			idToReturn = this.jdbcUbicacionDAO.obtenerIdUbicacion(this.comboBox.getValue());
			this.consultar_incidencias.agregarIncidenciaDeComboBox(idToReturn);

		} else if (this.tipo == 2) { // usuarios clase usuario
			int resultado = 0;
			switch (this.comboBox.getValue()) {
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
				resultado = 1;
				break;
			}// fin switch
			this.consultar_usuarios = new consultar_usuarios();
			this.consultar_usuarios.agregarRolDeComboBox(resultado);

		} else if (this.tipo == 3) {// urgencias incidencias
			this.consultar_incidencias = new consultar_incidencias();
			this.consultar_incidencias.agregarUrgenciaDeComboBox(this.comboBox.getValue());

		} else { // categoría incidencias
			this.consultar_incidencias = new consultar_incidencias();
			this.consultar_incidencias.agregarCategoriaDeComboBox(this.comboBox.getValue());
		}
		// cerrar ventana actual
		this.stage = (Stage) this.aplicarBoton.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
	}

}

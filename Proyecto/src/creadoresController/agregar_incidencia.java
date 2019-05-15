/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package creadoresController;

import java.sql.SQLException;
import java.util.Date;

import controller.consultar_incidencias;
import dto.incidenciaDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregar_incidencia {
	@FXML
	private TextField usuario;
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
	private TextField ubicacion;
	@FXML
	private Button agregarincidencia;

	private Stage stage;

	private consultar_incidencias consultar;

	public agregar_incidencia() {
		this.consultar = new consultar_incidencias();

	}

	public void inicializar() {

	}

	@FXML
	public void agregarincidencia() throws SQLException {
		incidenciaDTO incidenciaDTO = new incidenciaDTO();
		incidenciaDTO.setUsuario(this.usuario.getText());
		incidenciaDTO.setDescripcion(this.descripcion.getText());
		incidenciaDTO.setElemento(this.elemento.getText());

		Date date = java.sql.Date.valueOf(this.date.getValue());
		incidenciaDTO.setFecha((java.sql.Date) date);
		incidenciaDTO.setUrgencia(this.urgencia.getText());
		incidenciaDTO.setCategoria(this.categoria.getText());
		incidenciaDTO.setUbicacion(this.ubicacion.getText());
		this.stage = (Stage) this.agregarincidencia.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.consultar.agregarIncidenciaEnBD(incidenciaDTO);
	}

}

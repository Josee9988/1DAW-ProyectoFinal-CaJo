/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dto.mensajesDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.jdbcMensajesDAO;

public class consultar_mensajes {

	private Date date;
	private jdbcMensajesDAO bdmensajes;

	@FXML
	private TableView<mensajesDTO> tabla;
	@FXML
	private TableColumn<mensajesDTO, Integer> id;
	@FXML
	private TableColumn<mensajesDTO, String> asunto;
	@FXML
	private TableColumn<mensajesDTO, String> cuerpo;
	@FXML
	private TableColumn<mensajesDTO, Integer> incidencia;
	@FXML
	private TableColumn<mensajesDTO, String> fecha;
	@FXML
	private TextField usuario_encabezado;
	@FXML
	private TextField fecha_encabezado;

	public consultar_mensajes() {
		this.tabla = new TableView<>();
		this.bdmensajes = new jdbcMensajesDAO();
	}

	public void inicializar(String nombreCompleto) throws SQLException {
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.asunto.setCellValueFactory(new PropertyValueFactory<>("Asunto"));
		this.cuerpo.setCellValueFactory(new PropertyValueFactory<>("Cuerpo"));
		this.incidencia.setCellValueFactory(new PropertyValueFactory<>("Incidencia"));
		this.fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
		this.tabla.getItems().addAll(this.bdmensajes.leerMensajes());
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);
	}

	@FXML
	public void agregarMensaje() {

	}

	@FXML
	public void modificarMensaje() {

	}

	@FXML
	public void eliminarMensaje() {

	}

}

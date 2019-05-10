/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
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

public class consultar_mensajes {
	
private Date date;
	
	@FXML
	private TableView<mensajesDTO> tabla;
	@FXML
	private TableColumn<mensajesDTO, Integer> id;
	@FXML
	private TableColumn<mensajesDTO, String> asunto;
	@FXML
	private TableColumn<mensajesDTO, String> cuerpo;
	@FXML
	private TableColumn<mensajesDTO, String> fecha;
	@FXML
	private TextField usuario_encabezado;
	@FXML
	private TextField fecha_encabezado;
	
	public consultar_mensajes() {
		this.tabla = new TableView<mensajesDTO>();
	}
	
	public void inicializar(String nombreCompleto) throws SQLException {
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.asunto.setCellValueFactory(new PropertyValueFactory<>("Asunto"));
		this.cuerpo.setCellValueFactory(new PropertyValueFactory<>("Cuerpo"));
		this.fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
		this.tabla.getItems().addAll(bdController.getInstance().leerMensajes());
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);
	}
	
}

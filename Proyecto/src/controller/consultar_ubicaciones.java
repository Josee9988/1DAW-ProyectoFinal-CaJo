/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dto.ubicacionDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class consultar_ubicaciones {
	
	private Date date;
	
	@FXML
	private TableView<ubicacionDTO> tabla;
	@FXML
	private TableColumn<ubicacionDTO, Integer> id;
	@FXML
	private TableColumn<ubicacionDTO, String> nombre;
	@FXML
	private TableColumn<ubicacionDTO, String> descripcion;
	@FXML
	private TableColumn<ubicacionDTO, String> edificio;
	@FXML
	private TableColumn<ubicacionDTO, String> planta;
	@FXML
	private TextField usuario_encabezado;
	@FXML
	private TextField fecha_encabezado;
	
	public consultar_ubicaciones() {
		this.tabla = new TableView<ubicacionDTO>();
	}
	
	public void inicializar(String nombreCompleto) throws SQLException {
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
		this.edificio.setCellValueFactory(new PropertyValueFactory<>("Edificio"));
		this.planta.setCellValueFactory(new PropertyValueFactory<>("Planta"));
		this.tabla.getItems().addAll(bdController.getInstance().leerUbicaciones());
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);
	}
	
}

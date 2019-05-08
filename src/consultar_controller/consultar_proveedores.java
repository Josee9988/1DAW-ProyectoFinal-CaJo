package consultar_controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Controller.bdController;
import dto.incidenciaDTO;
import dto.proveedorDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class consultar_proveedores {
	
	private Date date;
	
	@FXML
	private TableView<proveedorDTO> tabla;
	@FXML
	private TableColumn<incidenciaDTO, Integer> id;
	@FXML
	private TableColumn<incidenciaDTO, String> nombre;
	@FXML
	private TableColumn<incidenciaDTO, String> contacto;
	@FXML
	private TableColumn<incidenciaDTO, String> direccion;
	@FXML
	private TableColumn<incidenciaDTO, Integer> valoracion;
	@FXML
	private TextField usuario_encabezado;
	@FXML
	private TextField fecha_encabezado;
	
	public consultar_proveedores() {
		this.tabla = new TableView<proveedorDTO>();
	}
	
	public void inicializar(String nombreCompleto) throws SQLException {
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.contacto.setCellValueFactory(new PropertyValueFactory<>("Contacto"));
		this.direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
		this.valoracion.setCellValueFactory(new PropertyValueFactory<>("Valoracion"));	
		this.tabla.getItems().addAll(bdController.getInstance().leerProveedores());
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);
	}

}

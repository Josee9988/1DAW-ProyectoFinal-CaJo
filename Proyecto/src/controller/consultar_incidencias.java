package controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.jdbcIncidenciasDAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dto.incidenciaDTO;
import dto.usuarioDTO;

public class consultar_incidencias {

	private Date date;
	private int rol_number;
	private jdbcIncidenciasDAO bdincidencias;
	
	@FXML
	private TableView<incidenciaDTO> tabla;
	@FXML
	private TableColumn<incidenciaDTO, Integer> id;
	@FXML
	private TableColumn<incidenciaDTO, String> usuario;
	@FXML
	private TableColumn<incidenciaDTO, String> descripcion;
	@FXML
	private TableColumn<incidenciaDTO, String> elemento;
	@FXML
	private TableColumn<incidenciaDTO, Date> fecha;
	@FXML
	private TableColumn<incidenciaDTO, String> urgencia;
	@FXML
	private TableColumn<incidenciaDTO, String> categoria;
	@FXML
	private TableColumn<incidenciaDTO, String> materiales;
	@FXML
	private TableColumn<incidenciaDTO, String> ubicacion;
	@FXML
	private TextField usuario_encabezado;
	@FXML
	private TextField fecha_encabezado;

	public consultar_incidencias() {
		this.tabla = new TableView<incidenciaDTO>();
		this.bdincidencias = new jdbcIncidenciasDAO();
	}
	
	public void inicializar(String nombreCompleto,int rol) throws SQLException {
		this.rol_number = rol;
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.usuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
		this.descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
		this.elemento.setCellValueFactory(new PropertyValueFactory<>("Elemento"));
		this.fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
		this.urgencia.setCellValueFactory(new PropertyValueFactory<>("Urgencia"));	
		this.categoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
		this.materiales.setCellValueFactory(new PropertyValueFactory<>("Materiales"));
		this.ubicacion.setCellValueFactory(new PropertyValueFactory<>("Ubicacion"));
		this.tabla.getItems().addAll(this.bdincidencias.leerIncidencias(new usuarioDTO(this.usuario_encabezado.getText(),this.rol_number)));
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);
	}
	
	@FXML
	public void agregarIncidencia() {
		
	}
	
	@FXML
	public void modificarIncidencia() {
		
	}
	
	@FXML
	public void eliminarIncidencia() {
		
	}
	
}

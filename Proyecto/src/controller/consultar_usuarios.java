package controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dto.usuarioDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.jdbcUsuarioDAO;

public class consultar_usuarios {
	
	private Date date;
	private jdbcUsuarioDAO bdusuarios;
	
	@FXML
	private TableView<usuarioDTO> tabla;
	@FXML
	private TableColumn<usuarioDTO, Integer> id;
	@FXML
	private TableColumn<usuarioDTO, String> usuario;
	@FXML
	private TableColumn<usuarioDTO, String> password;
	@FXML
	private TableColumn<usuarioDTO, Integer> rol;
	@FXML
	private TableColumn<usuarioDTO, String> nombre;
	@FXML
	private TableColumn<usuarioDTO, String> apellidos;
	@FXML
	private TableColumn<usuarioDTO, String> telefono;
	@FXML
	private TableColumn<usuarioDTO, String> direccion;
	@FXML
	private TextField usuario_encabezado;
	@FXML
	private TextField fecha_encabezado;
	
	public consultar_usuarios() {
		this.tabla = new TableView<usuarioDTO>();
		this.bdusuarios = new jdbcUsuarioDAO();
	}
	
	public void inicializar(String nombreCompleto) throws SQLException {
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.usuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
		this.password.setCellValueFactory(new PropertyValueFactory<>("Password"));
		this.rol.setCellValueFactory(new PropertyValueFactory<>("Rol"));
		this.nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.apellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
		this.telefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
		this.direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
		this.tabla.getItems().addAll(this.bdusuarios.leerUsuarios());
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);
	}
	
	@FXML
	public void agregarUsuario() {
		
	}
	
	@FXML
	public void modificarUsuario() {
		
	}
	
	@FXML
	public void eliminarUsuario() {
		
	}

}

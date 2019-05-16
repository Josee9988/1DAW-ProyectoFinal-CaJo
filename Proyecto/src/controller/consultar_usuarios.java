/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import creadoresController.agregar_usuarios;
import dto.usuarioDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
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
	private TableColumn<usuarioDTO, String> rol;
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

	private Stage agregar_usuarios;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_usuarios;
	private agregar_usuarios controller_agregar_usuarios;
	private Image icon;

	private jdbcUsuarioDAO dbusuario;
	private String nombreCompleto;

	usuarioDTO usuarioSelected;
	int idselected;

	/**
	 * consultar_usuarios constructor parametrizado que inicializa variables.
	 */
	public consultar_usuarios() {
		this.tabla = new TableView<>();
		this.bdusuarios = new jdbcUsuarioDAO();
		this.usuarioSelected = new usuarioDTO();
		this.idselected = -1;
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.dbusuario = new jdbcUsuarioDAO();
		this.nombreCompleto = "";
	}

	/**
	 * inicializar inicializa el tableview, cogiendo los datos de la base de datos y
	 * asignándoselos
	 * 
	 * @param nombreCompleto recibe el nombre y apellidos del usuario logeado
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void inicializar(String nombreCompleto) throws SQLException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		this.nombreCompleto = nombreCompleto;
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.usuario.setCellValueFactory(new PropertyValueFactory<>("user"));
		this.password.setCellValueFactory(new PropertyValueFactory<>("Password"));
		this.rol.setCellValueFactory(new PropertyValueFactory<>("RolS"));
		this.nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.apellidos.setCellValueFactory(new PropertyValueFactory<>("Apellidos"));
		this.telefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
		this.direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));

		// Cambiamos el valor numérico de rol a un texto entendible.(solo para
		// mostrárlos)
		ArrayList<usuarioDTO> arrayToAdd = this.bdusuarios.leerUsuarios();
		for (usuarioDTO i : arrayToAdd) {
			String resultado = "";
			switch (i.getRol()) {
			case 1:
				resultado = "Profesor";
				break;
			case 2:
				resultado = "Jefe Dpto.";
				break;
			case 3:
				resultado = "Mantenimiento";
				break;
			case 4:
				resultado = "Admin";
				break;
			default:
				break;
			}
			i.setRolS(resultado);
		}

		this.tabla.getItems().addAll(arrayToAdd);
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);

		this.tabla.setEditable(true);// hacemos la tabla entera editable

		// Hacemos todos los campos editables menos "id". Porque es un autoincrement y
		// nunca va a ser relevante a la hora de modificar un usuario
		this.usuario.setCellFactory(TextFieldTableCell.forTableColumn());
		this.password.setCellFactory(TextFieldTableCell.forTableColumn());
		// this.rol.setCellFactory(TextFieldTableCell.forTableColumn());
		this.nombre.setCellFactory(TextFieldTableCell.forTableColumn());
		this.apellidos.setCellFactory(TextFieldTableCell.forTableColumn());
		this.telefono.setCellFactory(TextFieldTableCell.forTableColumn());
		this.direccion.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	/**
	 * agregarEnBaseDatos agrega el objeto usuarioDTO en la base de datos
	 *
	 * @param user objeto usuarioDTO creado por el usuario
	 * @throws SQLException              si hay una excepción de SQL
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 */
	public void agregarEnBaseDatos(usuarioDTO user) throws SQLException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		this.dbusuario.crearUsuario(user);// lo agrega en la base de datos
	}

	@FXML
	/**
	 * agregarUsuario agrega un usuario a través de una view
	 * 
	 * @throws IOException si hay una excepción de tipo SQl
	 */
	public void agregarUsuario() throws IOException { // boton agregar
		// creamos la escena
		this.agregar_usuarios = new Stage();
		this.fxmlLoaderagregar_usuarios = new FXMLLoader(this.getClass().getResource("/view/agregarUser.fxml"));
		this.root1 = (Parent) this.fxmlLoaderagregar_usuarios.load();
		this.controller_agregar_usuarios = this.fxmlLoaderagregar_usuarios.<agregar_usuarios>getController();
		this.scene1 = new Scene(this.root1);
		this.controller_agregar_usuarios.inicializar(); // llamamos al método inicializar
		this.agregar_usuarios.setScene(this.scene1);
		this.agregar_usuarios.getIcons().add(this.icon); // agregamos el icono
		this.agregar_usuarios.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.agregar_usuarios.show();
	}

	@FXML
	/**
	 * modificarUsuario modifica un usuario en el tableview y base de datos a través
	 * de darle doble click o abriendo un tableview si no se ha modificado nada con
	 * doble click.
	 * 
	 * @throws SQLException              si hay una excepción de SQL
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 */
	public void modificarUsuario() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, SQLException { // boton modificar
		// Si un valor no se ha modificado cogerá el que estaba en la fila.
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			this.usuarioSelected.setId(this.idselected); // id no cambiará
			if (this.usuarioSelected.getUser().equals("")) {
				this.usuarioSelected.setUser(this.tabla.getSelectionModel().getSelectedItem().getUser());
			}
			if (this.usuarioSelected.getPassword().equals("")) {
				this.usuarioSelected.setPassword(this.tabla.getSelectionModel().getSelectedItem().getPassword());
			}
			if (this.usuarioSelected.getRol() == 0) {
				this.usuarioSelected.setRol(this.tabla.getSelectionModel().getSelectedItem().getRol());
			}
			if (this.usuarioSelected.getNombre().equals("")) {
				this.usuarioSelected.setNombre(this.tabla.getSelectionModel().getSelectedItem().getNombre());
			}
			if (this.usuarioSelected.getApellidos().equals("")) {
				this.usuarioSelected.setApellidos(this.tabla.getSelectionModel().getSelectedItem().getApellidos());
			}
			if (this.usuarioSelected.getTelefono().equals("")) {
				this.usuarioSelected.setTelefono(this.tabla.getSelectionModel().getSelectedItem().getTelefono());
			}
			if (this.usuarioSelected.getDireccion().equals("")) {
				this.usuarioSelected.setDireccion(this.tabla.getSelectionModel().getSelectedItem().getDireccion());
			}

			this.idselected = -1;
			this.bdusuarios.modificarUsuario(this.usuarioSelected);
			this.usuarioSelected = new usuarioDTO();
		}
	}

	@FXML
	/**
	 * restart borra todos los elementos del tableview y vuelve a rellenarla con los
	 * datos de la base de datos
	 * 
	 * @throws SQLException              si hay una excepción de SQL
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 */
	public void restart() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, SQLException {
		this.idselected = -1;
		this.usuarioSelected = new usuarioDTO();
		this.tabla.getItems().clear(); // borramos todos los datos
		this.inicializar(this.nombreCompleto);
		// this.tabla.getItems().addAll(this.bdusuarios.leerUsuarios());

	}

	@FXML
	/**
	 * eliminarUsuario elimina un usuario en la base de datos y en el tableview
	 * 
	 * @throws SQLException si hay una excepción de SQL
	 */
	public void eliminarUsuario() throws SQLException { // boton eliminar
		this.bdusuarios.eliminarUsuario(this.tabla.getSelectionModel().getSelectedItem().getId()); // lo eliminamos en
		// la bd
		this.tabla.getItems().remove(this.tabla.getSelectionModel().getSelectedItem()); // lo eliminamos en la tabla

	}

	// ######################### MODIFICACIONES #########################
	@FXML
	/**
	 * editModificarUsuario si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarUsuario(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.usuarioSelected.setUser(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.usuarioSelected.setUser(edditedCell.getNewValue().toString());
			}
		}

	}

	@FXML
	/**
	 * editModificarPass si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarPass(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.usuarioSelected.setPassword(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.usuarioSelected.setPassword(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	/**
	 * editModificarRol si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarRol(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.usuarioSelected.setRol((int) edditedCell.getNewValue());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.usuarioSelected.setRol((int) edditedCell.getNewValue());
			}
		}
	}

	@FXML
	/**
	 * editModificarNombre si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarNombre(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.usuarioSelected.setNombre(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.usuarioSelected.setNombre(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	/**
	 * editModificarApellidos si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarApellidos(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.usuarioSelected.setApellidos(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.usuarioSelected.setApellidos(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	/**
	 * editModificarTelefono si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarTelefono(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.usuarioSelected.setTelefono(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.usuarioSelected.setTelefono(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	/**
	 * editModificarDireccion si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarDireccion(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.usuarioSelected.setDireccion(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.usuarioSelected.setDireccion(edditedCell.getNewValue().toString());
			}
		}
	}

}

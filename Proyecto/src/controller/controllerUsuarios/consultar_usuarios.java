/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerUsuarios;

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

import controller.controllerUtilidades.agregar_combobox;
import controller.controllerUtilidades.confirmar_controller;
import dto.usuarioDTO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
	@FXML
	private TextField filtro;
	@FXML
	private Text textoError;

	private Stage agregar_usuarios;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_usuarios;
	private agregar_usuarios controller_agregar_usuarios;
	private Image icon;

	private String nombreCompleto;

	usuarioDTO usuarioSelected;
	int idselected;

	// pop up de comboB
	private Stage agregar_combobox;
	private Parent root2;
	private Scene scene2;
	private FXMLLoader fxmlLoaderagregar_combo;
	private agregar_combobox controller_agregar_combo;
	private static int idRolCombo;

	// pop up de confirmación de eliminación
	private Stage confirmacion_eliminacion;
	private Parent rootEliminacion;
	private Scene sceneEliminacion;
	private FXMLLoader fxmlLoaderagregar_eliminacion;
	private confirmar_controller controller_confirmar_controller;

	/**
	 * consultar_usuarios constructor parametrizado que inicializa variables.
	 */
	public consultar_usuarios() {
		this.tabla = new TableView<>();
		this.bdusuarios = new jdbcUsuarioDAO();
		this.usuarioSelected = new usuarioDTO();
		this.idselected = -1;
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.nombreCompleto = "";
		consultar_usuarios.idRolCombo = -1;
	}

	/**
	 * inicializar inicializa el tableview, cogiendo los datos de la base de datos y
	 * asignándoselos
	 *
	 * @param nombreCompleto recibe el nombre y apellidos del usuario logeado
	 * @throws SQLException              si ha habido alguna excepción de tipo SQL
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 */
	public void inicializar(String nombreCompleto) throws SQLException, InvalidKeyException, IllegalBlockSizeException,
	BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		this.nombreCompleto = nombreCompleto;
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
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
		this.nombre.setCellFactory(TextFieldTableCell.forTableColumn());
		this.apellidos.setCellFactory(TextFieldTableCell.forTableColumn());
		this.telefono.setCellFactory(TextFieldTableCell.forTableColumn());
		this.direccion.setCellFactory(TextFieldTableCell.forTableColumn());

		// Evento que cuando dé doble click en las columna(s) que queremos abrá views.
		this.tabla.setOnMouseClicked(new EventHandler<MouseEvent>() {
			// para evento fecha
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) { // para que haya dos clicks
					TablePosition<?, ?> pos = consultar_usuarios.this.tabla.getSelectionModel().getSelectedCells()
							.get(0);
					if (pos.getColumn() == 3) { // si es la columna(s) que queremos...
						consultar_usuarios.this.idselected = consultar_usuarios.this.tabla.getSelectionModel()
								.getSelectedItem().getId();

						consultar_usuarios.this.usuarioSelected.setId(consultar_usuarios.this.idselected); // id

						// creamos la view
						consultar_usuarios.this.agregar_combobox = new Stage();
						consultar_usuarios.this.fxmlLoaderagregar_combo = new FXMLLoader(
								this.getClass().getResource("/view/agregarComboBox.fxml"));
						try {
							consultar_usuarios.this.root2 = (Parent) consultar_usuarios.this.fxmlLoaderagregar_combo
									.load();
						} catch (IOException e) {
							System.out.println(e.toString());
						}

						consultar_usuarios.this.controller_agregar_combo = consultar_usuarios.this.fxmlLoaderagregar_combo
								.<agregar_combobox>getController();
								consultar_usuarios.this.scene2 = new Scene(consultar_usuarios.this.root2);
								try {
									consultar_usuarios.this.controller_agregar_combo.inicializar(2);
								} catch (SQLException e) {
									System.out.println(e.toString());
								} // llamamos al método inicializar

								consultar_usuarios.this.agregar_combobox.setScene(consultar_usuarios.this.scene2);
								consultar_usuarios.this.agregar_combobox.getIcons().add(consultar_usuarios.this.icon); // agregamos
								// el icono
								consultar_usuarios.this.agregar_combobox.setTitle("Agregar en ComboBox"); // ponemos el título
								// de la ventana
								consultar_usuarios.this.agregar_combobox.show();
					}
				}
			}
		}); // fin doble click en las columnas que queremos para abrir views

	}

	/**
	 * agregarRolDeComboBox se llama cuando se envía el pop up de combobox.
	 *
	 * @param resultado es el entero con el rol que ha seleccionado
	 */
	public void agregarRolDeComboBox(int resultado) {
		consultar_usuarios.idRolCombo = resultado;

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
		this.agregar_usuarios.setTitle("Agregar Usuario"); // ponemos el título de la ventana
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

			if (consultar_usuarios.idRolCombo != -1) {
				this.usuarioSelected.setRol(consultar_usuarios.idRolCombo);
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
		this.tabla.getItems().clear(); // borramos todos los datos
		this.inicializar(this.nombreCompleto);
	}

	@FXML
	/**
	 * abre una pestaña de confirmación con un botón eliminar el cuál llamará a un
	 * método para eliminar el usuario o por lo contrario cancelar símplemente
	 * cerrará la ventana actual.
	 *
	 * @param IOException si ha habido una excepción de tipo IO
	 */
	public void eliminarUsuario() throws IOException { // boton eliminar
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			if (!this.tabla.getSelectionModel().getSelectedItem().getUser().equals("root")) {
				// creamos la escena
				this.confirmacion_eliminacion = new Stage();
				this.fxmlLoaderagregar_eliminacion = new FXMLLoader(
						this.getClass().getResource("/view/confirmacionEliminacion.fxml"));
				this.rootEliminacion = (Parent) this.fxmlLoaderagregar_eliminacion.load();
				this.controller_confirmar_controller = this.fxmlLoaderagregar_eliminacion
						.<confirmar_controller>getController();
						this.sceneEliminacion = new Scene(this.rootEliminacion);
						this.controller_confirmar_controller.inicializar(0,
								this.tabla.getSelectionModel().getSelectedItem().getId()); // llamamos
						// al
						// método
						// inicializar
						this.confirmacion_eliminacion.setScene(this.sceneEliminacion);
						this.confirmacion_eliminacion.getIcons().add(this.icon); // agregamos el icono
						this.confirmacion_eliminacion.setTitle("Eliminar usuario"); // ponemos el título de la ventana
						this.confirmacion_eliminacion.show();
			} else {
				this.textoError.setText("No se puede eliminar al usuario 'root'");
			}
		}
	}

	/**
	 * eliminarUsuario elimina un usuario en la base de datos y en el tableview
	 *
	 * @param id, recibe la id a eliminar en la base de datos
	 * @throws SQLException              si hay una excepción de SQL
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 */
	public void eliminarUsuarioBD(int id) throws SQLException, InvalidKeyException, IllegalBlockSizeException,
	BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		this.bdusuarios.eliminarUsuario(id); // lo eliminamos en la bd
		// this.tabla.getItems().remove(this.usuarioSelected);
	}

	// ######################### MODIFICACIONES #########################
	@FXML
	/**
	 * commitFIltro cuando se ha pulsado intro en nuestro TextField procederá a
	 * borrar todos los datos de la base de datos y reemplazarlos por los elementos
	 * encontrados en la base de datos. Es un filtro que se aplica a campos
	 * específicos
	 *
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 * @throws SQLException              si ha habido una excepción SQL
	 */
	public void commitFIltro() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
	NoSuchAlgorithmException, NoSuchPaddingException, SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		ArrayList<usuarioDTO> arrayToAdd = this.bdusuarios.filtrar(this.filtro.getText());
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
	}

	@FXML
	/**
	 * editModificarUsuario si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarUsuario(CellEditEvent<?, ?> edditedCell) {
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
	public void editModificarPass(CellEditEvent<?, ?> edditedCell) {
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
	 * editModificarNombre si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editModificarNombre(CellEditEvent<?, ?> edditedCell) {
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
	public void editModificarApellidos(CellEditEvent<?, ?> edditedCell) {
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
	public void editModificarTelefono(CellEditEvent<?, ?> edditedCell) {
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
	public void editModificarDireccion(CellEditEvent<?, ?> edditedCell) {
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

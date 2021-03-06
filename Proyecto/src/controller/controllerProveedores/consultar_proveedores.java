/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerProveedores;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import controller.controllerUtilidades.confirmar_controller;
import dto.incidenciaDTO;
import dto.proveedorDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.jdbcProveedoresDAO;

public class consultar_proveedores {

	private Date date;
	private jdbcProveedoresDAO bdproveedores;

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
	@FXML
	private Button agregarP;
	@FXML
	private Button modificarP;
	@FXML
	private Button eliminarP;
	@FXML
	private TextField filtro;
	@FXML
	private Text textoError;
	@FXML
	private Button modificarProveedorManual;

	private String nombreCompleto;

	private Stage agregar_proveedor;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_proveedor;
	private agregar_proveedor controller_agregar_proveedor;
	private Image icon;
	private int idselected;
	private proveedorDTO proveedorDTO;
	private int rol;

	// pop up de confirmación de eliminación
	private Stage confirmacion_eliminacion;
	private Parent rootEliminacion;
	private Scene sceneEliminacion;
	private FXMLLoader fxmlLoaderagregar_eliminacion;
	private confirmar_controller controller_confirmar_controller;

	/**
	 * consultar_proveedores constructor default inicializa valores necesarios
	 */
	public consultar_proveedores() {
		this.tabla = new TableView<>();
		this.bdproveedores = new jdbcProveedoresDAO();
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.idselected = -1;
		this.proveedorDTO = new proveedorDTO();
		this.rol = 0;
		this.nombreCompleto = "";
	}

	/**
	 * inicializar inicializa el tableview, cogiendo los datos de la base de datos y
	 * asignándoselos
	 *
	 * @param nombreCompleto recibe el nombre y apellidos del usuario logeado
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void inicializar(String nombreCompleto, int rol) throws SQLException {
		this.nombreCompleto = nombreCompleto;
		this.rol = rol;
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.contacto.setCellValueFactory(new PropertyValueFactory<>("Contacto"));
		this.direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
		this.valoracion.setCellValueFactory(new PropertyValueFactory<>("Valoracion"));
		this.tabla.getItems().clear();
		this.tabla.getItems().addAll(this.bdproveedores.leerProveedores());
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);

		if (this.rol == 1) {
			this.agregarP.setVisible(false);
			this.modificarP.setVisible(false);
			this.eliminarP.setVisible(false);
			this.modificarProveedorManual.setVisible(false);
			this.id.setEditable(false);
			this.nombre.setEditable(false);
			this.contacto.setEditable(false);
			this.direccion.setEditable(false);
			this.valoracion.setEditable(false);
		}

		this.tabla.setEditable(true);// hacemos la tabla entera editable

		// Hacemos todos los campos editables menos "id". Porque es un autoincrement y
		// nunca va a ser relevante a la hora de modificar un usuario
		this.nombre.setCellFactory(TextFieldTableCell.forTableColumn());
		this.contacto.setCellFactory(TextFieldTableCell.forTableColumn());
		this.valoracion.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		this.direccion.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	@FXML
	/**
	 * agregarProveedor agrega un proveedor a través de una view
	 *
	 * @throws IOException si ha habido una excepción de tipo IO
	 */
	public void agregarProveedor() throws IOException {
		// creamos la escena
		this.agregar_proveedor = new Stage();
		this.fxmlLoaderagregar_proveedor = new FXMLLoader(this.getClass().getResource("/view/agregarProveedor.fxml"));
		this.root1 = (Parent) this.fxmlLoaderagregar_proveedor.load();
		this.controller_agregar_proveedor = this.fxmlLoaderagregar_proveedor.<agregar_proveedor>getController();
		this.scene1 = new Scene(this.root1);
		this.controller_agregar_proveedor.inicializar(); // llamamos al método inicializar
		this.agregar_proveedor.setScene(this.scene1);
		this.agregar_proveedor.getIcons().add(this.icon); // agregamos el icono
		this.agregar_proveedor.setTitle("Agregar Proveedor"); // ponemos el título de la ventana
		this.agregar_proveedor.show();
	}

	@FXML
	/**
	 * modificarProveedor modifica un proveedor tras ser modificado con doble click
	 * o a través de una view
	 *
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void modificarProveedor() throws SQLException {
		// Si un valor no se ha modificado cogerá el que estaba en la fila.
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			this.proveedorDTO.setId(this.idselected); // id no cambiará
			if (this.proveedorDTO.getNombre().isEmpty()) {
				this.proveedorDTO.setNombre(this.tabla.getSelectionModel().getSelectedItem().getNombre());
			}
			if (this.proveedorDTO.getContacto().isEmpty()) {
				this.proveedorDTO.setContacto(this.tabla.getSelectionModel().getSelectedItem().getContacto());
			}
			if (this.proveedorDTO.getDireccion().isEmpty()) {
				this.proveedorDTO.setDireccion(this.tabla.getSelectionModel().getSelectedItem().getDireccion());
			}
			this.idselected = -1;
			this.bdproveedores.modificarProveedor(this.proveedorDTO);
			this.proveedorDTO = new proveedorDTO();
		}

	}

	@FXML
	/**
	 * eliminarProveedor abre una pestaña de confirmación con un botón eliminar el
	 * cuál llamará a un método para eliminar el proveedor o por lo contrario
	 * cancelar símplemente cerrará la ventana actual.
	 *
	 * @param IOExcepcion si ha habido una excepción IO
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void eliminarProveedor() throws SQLException, IOException {
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			// creamos la escena
			this.confirmacion_eliminacion = new Stage();
			this.fxmlLoaderagregar_eliminacion = new FXMLLoader(
					this.getClass().getResource("/view/confirmacionEliminacion.fxml"));
			this.rootEliminacion = (Parent) this.fxmlLoaderagregar_eliminacion.load();
			this.controller_confirmar_controller = this.fxmlLoaderagregar_eliminacion
					.<confirmar_controller>getController();
			this.sceneEliminacion = new Scene(this.rootEliminacion);
			this.controller_confirmar_controller.inicializar(2,
					this.tabla.getSelectionModel().getSelectedItem().getId()); // llamamos al método inicializar
			this.confirmacion_eliminacion.setScene(this.sceneEliminacion);
			this.confirmacion_eliminacion.getIcons().add(this.icon); // agregamos el icono
			this.confirmacion_eliminacion.setTitle("Eliminar proveedor"); // ponemos el título de la ventana
			this.confirmacion_eliminacion.show();
		}
	}

	/**
	 * elimina un proveedor en la base de datos
	 *
	 * @param id, recibe la id a eliminar en la base de datos
	 * @throws SQLException si hay una excepción de SQL
	 */
	public void eliminarProveedorBD(int id) throws SQLException {
		this.bdproveedores.eliminarProveedor(id);
	}

	@FXML
	/**
	 * restart borra todos los elementos del tableview y vuelve a rellenarla con los
	 * datos de la base de datos
	 *
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void restart() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		this.inicializar(this.nombreCompleto, this.rol);
		// this.tabla.getItems().addAll(this.bdproveedores.leerProveedores());

	}

	// ##### MODIFICACIONES
	@FXML
	/**
	 * commitFIltro cuando se ha pulsado intro en nuestro TextField procederá a
	 * borrar todos los datos de la base de datos y reemplazarlos por los elementos
	 * encontrados en la base de datos. Es un filtro que se aplica a campos
	 * específicos
	 *
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void modificarProveedorManual() throws SQLException, IOException {
		proveedorDTO proveedor = this.tabla.getSelectionModel().getSelectedItem();
		if (proveedor != null) {
			// creamos la escena
			this.agregar_proveedor = new Stage();
			this.fxmlLoaderagregar_proveedor = new FXMLLoader(
					this.getClass().getResource("/view/agregarProveedor.fxml"));
			this.root1 = (Parent) this.fxmlLoaderagregar_proveedor.load();
			this.controller_agregar_proveedor = this.fxmlLoaderagregar_proveedor.<agregar_proveedor>getController();
			this.scene1 = new Scene(this.root1);
			this.controller_agregar_proveedor.inicializar(proveedor); // llamamos al método inicializar
			this.agregar_proveedor.setScene(this.scene1);
			this.agregar_proveedor.getIcons().add(this.icon); // agregamos el icono
			this.agregar_proveedor.setTitle("Modificar Proveedor"); // ponemos el título de la ventana
			this.agregar_proveedor.show();
		} else {
			this.textoError.setText("Nada seleccionado");
		}
	}

	@FXML
	/**
	 * commitFIltro cuando se ha pulsado intro en nuestro TextField procederá a
	 * borrar todos los datos de la base de datos y reemplazarlos por los elementos
	 * encontrados en la base de datos. Es un filtro que se aplica a campos
	 * específicos
	 *
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void commitFIltro() throws SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		this.tabla.getItems().addAll(this.bdproveedores.filtrar(this.filtro.getText()));
	}

	@FXML
	/**
	 * editNombre si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editNombre(CellEditEvent<?, ?> edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.proveedorDTO.setNombre(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.proveedorDTO.setNombre(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	/**
	 * editContacto si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editContacto(CellEditEvent<?, ?> edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.proveedorDTO.setContacto(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.proveedorDTO.setContacto(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	/**
	 * editDireccion si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editDireccion(CellEditEvent<?, ?> edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.proveedorDTO.setDireccion(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.proveedorDTO.setDireccion(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	/**
	 * editValoracion si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editValoracion(CellEditEvent<?, ?> edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.proveedorDTO.setValoracion((int) edditedCell.getNewValue());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.proveedorDTO.setValoracion((int) edditedCell.getNewValue());
			}
		}
	}

}

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
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dto.incidenciaDTO;
import dto.proveedorDTO;
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

	private Stage agregar_proveedor;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_proveedor;
	private controller.agregar_proveedor controller_agregar_proveedor;
	private Image icon;
	private int idselected;
	private proveedorDTO proveedorDTO;

	public consultar_proveedores() {
		this.tabla = new TableView<>();
		this.bdproveedores = new jdbcProveedoresDAO();
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.idselected = -1;
		this.proveedorDTO = new proveedorDTO();
	}

	public void inicializar(String nombreCompleto) throws SQLException {
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.contacto.setCellValueFactory(new PropertyValueFactory<>("Contacto"));
		this.direccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
		this.valoracion.setCellValueFactory(new PropertyValueFactory<>("Valoracion"));
		this.tabla.getItems().addAll(this.bdproveedores.leerProveedores());
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);

		this.tabla.setEditable(true);// hacemos la tabla entera editable

		// Hacemos todos los campos editables menos "id". Porque es un autoincrement y
		// nunca va a ser relevante a la hora de modificar un usuario
		this.nombre.setCellFactory(TextFieldTableCell.forTableColumn());
		this.contacto.setCellFactory(TextFieldTableCell.forTableColumn());
		this.valoracion.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		this.direccion.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	@FXML
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
		this.agregar_proveedor.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.agregar_proveedor.show();
	}

	@FXML
	public void modificarProveedor() throws SQLException {
		// Si un valor no se ha modificado cogerá el que estaba en la fila.
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			this.proveedorDTO.setId(this.idselected); // id no cambiará
			if (this.proveedorDTO.getNombre().equals("")) {
				this.proveedorDTO.setNombre(this.tabla.getSelectionModel().getSelectedItem().getNombre());
			}
			if (this.proveedorDTO.getContacto().equals("")) {
				this.proveedorDTO.setContacto(this.tabla.getSelectionModel().getSelectedItem().getContacto());
			}
			if (this.proveedorDTO.getValoracion() == 0) {
				this.proveedorDTO.setValoracion(this.tabla.getSelectionModel().getSelectedItem().getValoracion());
			}
			if (this.proveedorDTO.getDireccion().equals("")) {
				this.proveedorDTO.setDireccion(this.tabla.getSelectionModel().getSelectedItem().getDireccion());
			}
			this.idselected = -1;
			this.bdproveedores.modificarProveedor(this.proveedorDTO);
			this.proveedorDTO = new proveedorDTO();
		}

	}

	@FXML
	public void eliminarProveedor() throws SQLException {
		this.bdproveedores.eliminarProveedor(this.tabla.getSelectionModel().getSelectedItem().getId()); // lo eliminamos
		// en la bd
		this.tabla.getItems().remove(this.tabla.getSelectionModel().getSelectedItem()); // lo eliminamos en la tabla
	}

	@FXML
	public void restart() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		this.tabla.getItems().addAll(this.bdproveedores.leerProveedores());

	}

	public void agregarProveedorEnBD(proveedorDTO proveedor) throws SQLException {
		this.bdproveedores.agregarProveedor(proveedor);

	}

	// ##### MODIFICACIONES
	@FXML
	public void editNombre(CellEditEvent edditedCell) {
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
	public void editContacto(CellEditEvent edditedCell) {
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
	public void editDireccion(CellEditEvent edditedCell) {
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
	public void editValoracion(CellEditEvent edditedCell) {
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

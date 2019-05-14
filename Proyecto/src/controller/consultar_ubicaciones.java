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

import creadoresController.agregar_ubicacion;
import dto.ubicacionDTO;
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
import model.jdbcUbicacionDAO;

public class consultar_ubicaciones {

	private Date date;
	private jdbcUbicacionDAO bdubicaciones;

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

	private Stage agregar_ubicacion;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_ubicacion;
	private creadoresController.agregar_ubicacion controller_agregar_ubicacion;
	private Image icon;
	private int idselected;
	private ubicacionDTO ubicacionDTO;

	public consultar_ubicaciones() {
		this.tabla = new TableView<>();
		this.bdubicaciones = new jdbcUbicacionDAO();
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.idselected = -1;
		this.ubicacionDTO = new ubicacionDTO();
	}

	public void inicializar(String nombreCompleto) throws SQLException {
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		this.descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
		this.edificio.setCellValueFactory(new PropertyValueFactory<>("Edificio"));
		this.planta.setCellValueFactory(new PropertyValueFactory<>("Planta"));
		this.tabla.getItems().addAll(this.bdubicaciones.leerUbicaciones());
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);

		this.tabla.setEditable(true);// hacemos la tabla entera editable

		// Hacemos todos los campos editables menos "id". Porque es un autoincrement y
		// nunca va a ser relevante a la hora de modificar un usuario
		this.nombre.setCellFactory(TextFieldTableCell.forTableColumn());
		this.descripcion.setCellFactory(TextFieldTableCell.forTableColumn());
		this.edificio.setCellFactory(TextFieldTableCell.forTableColumn());
		this.planta.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	@FXML
	public void agregarUbicacion() throws IOException {
		// creamos la escena
		this.agregar_ubicacion = new Stage();
		this.fxmlLoaderagregar_ubicacion = new FXMLLoader(this.getClass().getResource("/view/agregarUbicacion.fxml"));
		this.root1 = (Parent) this.fxmlLoaderagregar_ubicacion.load();
		this.controller_agregar_ubicacion = this.fxmlLoaderagregar_ubicacion.<agregar_ubicacion>getController();
		this.scene1 = new Scene(this.root1);
		this.controller_agregar_ubicacion.inicializar(); // llamamos al método inicializar
		this.agregar_ubicacion.setScene(this.scene1);
		this.agregar_ubicacion.getIcons().add(this.icon); // agregamos el icono
		this.agregar_ubicacion.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.agregar_ubicacion.show();
	}

	@FXML
	public void modificarUbicacion() throws SQLException {
		// Si un valor no se ha modificado cogerá el que estaba en la fila.
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			this.ubicacionDTO.setId(this.idselected); // id no cambiará
			if (this.ubicacionDTO.getNombre().equals("")) {
				this.ubicacionDTO.setNombre(this.tabla.getSelectionModel().getSelectedItem().getNombre());
			}
			if (this.ubicacionDTO.getDescripcion().equals("")) {
				this.ubicacionDTO.setDescripcion(this.tabla.getSelectionModel().getSelectedItem().getDescripcion());
			}
			if (this.ubicacionDTO.getEdificio().equals("")) {
				this.ubicacionDTO.setEdificio(this.tabla.getSelectionModel().getSelectedItem().getEdificio());
			}
			if (this.ubicacionDTO.getPlanta().equals("")) {
				this.ubicacionDTO.setPlanta(this.tabla.getSelectionModel().getSelectedItem().getPlanta());
			}
			this.idselected = -1;
			this.bdubicaciones.modificarUbicacion(this.ubicacionDTO);
			this.ubicacionDTO = new ubicacionDTO();
		}
	}

	@FXML
	public void eliminarUbicacion() throws SQLException {
		this.bdubicaciones.eliminarUbicacion(this.tabla.getSelectionModel().getSelectedItem().getId()); // lo eliminamos
		// en la bd
		this.tabla.getItems().remove(this.tabla.getSelectionModel().getSelectedItem()); // lo eliminamos en la tabla
	}

	@FXML
	public void restart() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException, SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		this.tabla.getItems().addAll(this.bdubicaciones.leerUbicaciones());

	}

	public void agregarUbicacionEnBD(ubicacionDTO ubicacion) throws SQLException {
		this.bdubicaciones.agregarUbicacion(ubicacion);
	}

	// #####MODIFICACIONESs
	@FXML
	public void editNombre(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.ubicacionDTO.setNombre(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.ubicacionDTO.setNombre(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editDescripcion(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.ubicacionDTO.setDescripcion(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.ubicacionDTO.setDescripcion(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editEdificio(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.ubicacionDTO.setEdificio(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.ubicacionDTO.setEdificio(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editPlanta(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.ubicacionDTO.setPlanta(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.ubicacionDTO.setPlanta(edditedCell.getNewValue().toString());
			}
		}
	}

}

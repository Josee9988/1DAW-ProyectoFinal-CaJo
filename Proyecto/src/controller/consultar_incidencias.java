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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import creadoresController.agregar_incidencia;
import dto.incidenciaDTO;
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
import model.jdbcIncidenciasDAO;

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

	private int idselected = -1;
	private incidenciaDTO incidenciaSelected;
	private Stage agregar_incidencia;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_incidencia;
	private agregar_incidencia controller_agregar_incidencia;
	private Image icon;
	private String nombreCompleto;
	private int rol;

	public consultar_incidencias() {
		this.tabla = new TableView<>();
		this.bdincidencias = new jdbcIncidenciasDAO();
		this.incidenciaSelected = new incidenciaDTO();
		this.idselected = -1;
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.nombreCompleto = "";
		this.rol = 0;
	}

	public void inicializar(String nombreCompleto, int rol) throws SQLException {
		this.rol_number = rol;
		this.nombreCompleto = nombreCompleto;
		this.rol = rol;
		this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.usuario.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
		this.descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
		this.elemento.setCellValueFactory(new PropertyValueFactory<>("Elemento"));
		this.fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
		this.urgencia.setCellValueFactory(new PropertyValueFactory<>("Urgencia"));
		this.categoria.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
		this.materiales.setCellValueFactory(new PropertyValueFactory<>("Materiales"));
		this.ubicacion.setCellValueFactory(new PropertyValueFactory<>("Ubicacion"));
		this.tabla.getItems().addAll(
				this.bdincidencias.leerIncidencias(new usuarioDTO(this.usuario_encabezado.getText(), this.rol_number)));
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);

		this.tabla.setEditable(true);// hacemos la tabla entera editable

		// Hacemos todos los campos editables menos "id". Porque es un autoincrement y
		// nunca va a ser relevante a la hora de modificar un usuario
		this.usuario.setCellFactory(TextFieldTableCell.forTableColumn());
		this.descripcion.setCellFactory(TextFieldTableCell.forTableColumn());
		this.elemento.setCellFactory(TextFieldTableCell.forTableColumn());
		//TODO: make list
		//fecha.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
		this.urgencia.setCellFactory(TextFieldTableCell.forTableColumn());
		this.categoria.setCellFactory(TextFieldTableCell.forTableColumn());
		this.materiales.setCellFactory(TextFieldTableCell.forTableColumn());
		this.ubicacion.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	@FXML
	public void agregarIncidencia() throws IOException {
		this.agregar_incidencia= new Stage();
		this.fxmlLoaderagregar_incidencia = new FXMLLoader(this.getClass().getResource("/view/agregarIncidencia.fxml"));
		this.root1 = (Parent) this.fxmlLoaderagregar_incidencia.load();
		this.controller_agregar_incidencia = this.fxmlLoaderagregar_incidencia.<agregar_incidencia>getController();
		this.scene1 = new Scene(this.root1);
		this.controller_agregar_incidencia.inicializar(); // llamamos al método inicializar
		this.agregar_incidencia.setScene(this.scene1);
		this.agregar_incidencia.getIcons().add(this.icon); // agregamos el icono
		this.agregar_incidencia.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.agregar_incidencia.show();
	}


	public void agregarIncidenciaEnBD(incidenciaDTO incidenciaDTO) throws SQLException {
		this.bdincidencias.crearIncidencia(incidenciaDTO);
	}

	@FXML
	public void modificarIncidencia() throws SQLException {
		// Si un valor no se ha modificado cogerá el que estaba en la fila.
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			this.incidenciaSelected.setId(this.idselected); // id no cambiará
			if (this.incidenciaSelected.getUsuario().equals("")) {
				this.incidenciaSelected.setUsuario(this.tabla.getSelectionModel().getSelectedItem().getUsuario());
			}
			if (this.incidenciaSelected.getDescripcion().equals("")) {
				this.incidenciaSelected.setDescripcion(this.tabla.getSelectionModel().getSelectedItem().getDescripcion());
			}
			if (this.incidenciaSelected.getElemento().equals("")) {
				this.incidenciaSelected.setElemento(this.tabla.getSelectionModel().getSelectedItem().getElemento());
			}
			//TODO: hacerlo como un desplegable de fechas
			//if (this.incidenciaSelected.getfecha().equals("")) {
			//	this.incidenciaSelected.setNombre(this.tabla.getSelectionModel().getSelectedItem().getNombre());
			//}
			if (this.incidenciaSelected.getUrgencia().equals("")) {
				this.incidenciaSelected.setUrgencia(this.tabla.getSelectionModel().getSelectedItem().getUrgencia());
			}
			if (this.incidenciaSelected.getCategoria().equals("")) {
				this.incidenciaSelected.setCategoria(this.tabla.getSelectionModel().getSelectedItem().getCategoria());
			}
			if (this.incidenciaSelected.getMateriales().equals("")) {
				this.incidenciaSelected.setMateriales(this.tabla.getSelectionModel().getSelectedItem().getMateriales());
			}
			if (this.incidenciaSelected.getUbicacion().equals("")) {
				this.incidenciaSelected.setUbicacion(this.tabla.getSelectionModel().getSelectedItem().getUbicacion());
			}


			this.bdincidencias.modificarIncidencia(this.incidenciaSelected);
			this.idselected = -1;
			this.incidenciaSelected = new incidenciaDTO();
		}
	}

	@FXML
	public void eliminarIncidencia() throws SQLException {
		this.bdincidencias.eliminarIncidencia(this.tabla.getSelectionModel().getSelectedItem().getId()); // lo eliminamos
		// en la bd
		this.tabla.getItems().remove(this.tabla.getSelectionModel().getSelectedItem()); // lo eliminamos en la tabla
	}

	@FXML
	public void restart() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
	NoSuchAlgorithmException, NoSuchPaddingException, SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		this.tabla.getItems().addAll(this.bdincidencias.leerIncidencias(new usuarioDTO(this.usuario_encabezado.getText(), this.rol_number)));

	}


	//MODIFICACIONES
	@FXML
	public void editUsuario(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setUsuario(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setUsuario(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editDescripcion(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setDescripcion(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setDescripcion(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editElemento(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setElemento(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setElemento(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editFecha(CellEditEvent edditedCell) throws ParseException {
		Date date = new Date();
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setFecha((java.sql.Date) new SimpleDateFormat("dd-MM-yyyy").parse(edditedCell.getNewValue().toString()));
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setElemento(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editUrgencia(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setUrgencia(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setUrgencia(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editCategoria(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setCategoria(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setCategoria(edditedCell.getNewValue().toString());
			}
		}
	}


	@FXML
	public void editMateriales(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setMateriales(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setMateriales(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editUbicacion(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setUbicacion(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setUbicacion(edditedCell.getNewValue().toString());
			}
		}
	}






}

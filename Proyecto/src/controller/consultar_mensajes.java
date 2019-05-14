/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import creadoresController.agregar_mensajes;
import dto.mensajesDTO;
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
import model.jdbcMensajesDAO;

public class consultar_mensajes {

	private Date date;
	private jdbcMensajesDAO bdmensajes;

	@FXML
	private TableView<mensajesDTO> tabla;
	@FXML
	private TableColumn<mensajesDTO, Integer> id;
	@FXML
	private TableColumn<mensajesDTO, String> asunto;
	@FXML
	private TableColumn<mensajesDTO, String> cuerpo;
	@FXML
	private TableColumn<mensajesDTO, Integer> incidencia;
	@FXML
	private TableColumn<mensajesDTO, Date> fecha;
	@FXML
	private TableColumn<mensajesDTO, Integer> emisor;
	@FXML
	private TableColumn<mensajesDTO, Integer> receptor;
	@FXML
	private TextField usuario_encabezado;
	@FXML
	private TextField fecha_encabezado;

	private int idselected = -1;
	private mensajesDTO mensajesSelected;
	private Stage agregar_mensaje;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_mensaje;
	private agregar_mensajes controller_agregar_mensajes;
	private Image icon;
	private String nombreCompleto;

	public consultar_mensajes() {
		this.tabla = new TableView<>();
		this.bdmensajes = new jdbcMensajesDAO();
		this.mensajesSelected = new mensajesDTO();
		this.idselected = -1;
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.nombreCompleto = "";
	}

	public void inicializar(String nombreCompleto) throws SQLException {
		this.nombreCompleto = nombreCompleto;
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.asunto.setCellValueFactory(new PropertyValueFactory<>("Asunto"));
		this.cuerpo.setCellValueFactory(new PropertyValueFactory<>("Cuerpo"));
		this.incidencia.setCellValueFactory(new PropertyValueFactory<>("Incidencia"));
		this.fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
		this.emisor.setCellValueFactory(new PropertyValueFactory<>("Emisor"));
		this.receptor.setCellValueFactory(new PropertyValueFactory<>("Receptor"));
		this.tabla.getItems().addAll(this.bdmensajes.leerMensajes());

		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);

		this.tabla.setEditable(true);// hacemos la tabla entera editable

		// Hacemos todos los campos editables menos "id". Porque es un autoincrement y
		// nunca va a ser relevante a la hora de modificar un usuario
		this.asunto.setCellFactory(TextFieldTableCell.forTableColumn());
		this.cuerpo.setCellFactory(TextFieldTableCell.forTableColumn());
		this.incidencia.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		// TODO: make it happen
		// this.fecha.setCellFactory(TextFieldTableCell.forTableColumn());
		this.emisor.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		this.receptor.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	}

	@FXML
	public void agregarMensaje() throws IOException {
		this.agregar_mensaje = new Stage();
		this.fxmlLoaderagregar_mensaje = new FXMLLoader(this.getClass().getResource("/view/agregarMensaje.fxml"));
		this.root1 = (Parent) this.fxmlLoaderagregar_mensaje.load();
		this.controller_agregar_mensajes = this.fxmlLoaderagregar_mensaje.<agregar_mensajes>getController();
		this.scene1 = new Scene(this.root1);
		this.controller_agregar_mensajes.inicializarMensajes(this.nombreCompleto); // llamamos al método inicializar
		this.agregar_mensaje.setScene(this.scene1);
		this.agregar_mensaje.getIcons().add(this.icon); // agregamos el icono
		this.agregar_mensaje.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.agregar_mensaje.show();
	}

	@FXML
	public void modificarMensaje() throws SQLException {
		// Si un valor no se ha modificado cogerá el que estaba en la fila.
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			this.mensajesSelected.setId(this.idselected); // id no cambiará
			if (this.mensajesSelected.getAsunto().equals("")) {
				this.mensajesSelected.setAsunto(this.tabla.getSelectionModel().getSelectedItem().getAsunto());
			}
			if (this.mensajesSelected.getCuerpo().equals("")) {
				this.mensajesSelected.setCuerpo(this.tabla.getSelectionModel().getSelectedItem().getCuerpo());
			}
			if (this.mensajesSelected.getIncidencia() == 0) {
				this.mensajesSelected.setIncidencia(this.tabla.getSelectionModel().getSelectedItem().getIncidencia());
			}
			// TODO: hacerlo como un desplegable de fechas
			// if (this.mensajesSelected.getFecha().equals("")) {
			// this.mensajesSelected.setFecha(this.tabla.getSelectionModel().getSelectedItem().getFecha());
			// }
			if (this.mensajesSelected.getEmisor() == 0) {
				this.mensajesSelected.setEmisor(this.tabla.getSelectionModel().getSelectedItem().getEmisor());
			}
			if (this.mensajesSelected.getReceptor() == 0) {
				this.mensajesSelected.setReceptor(this.tabla.getSelectionModel().getSelectedItem().getReceptor());
			}

			this.bdmensajes.modificarMensaje(this.mensajesSelected);
			this.idselected = -1;
			this.mensajesSelected = new mensajesDTO();
		}
	}

	@FXML
	public void eliminarMensaje() throws SQLException {
		this.bdmensajes.eliminarMensajes(this.tabla.getSelectionModel().getSelectedItem().getId()); // lo eliminamos
		// en la bd
		this.tabla.getItems().remove(this.tabla.getSelectionModel().getSelectedItem()); // lo eliminamos en la tabla
	}

	@FXML
	public void restart() throws SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		this.tabla.getItems().addAll(this.bdmensajes.leerMensajes());
	}

	// MODIFICACIONES
	@FXML
	public void editAsunto(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.mensajesSelected.setAsunto(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.mensajesSelected.setAsunto(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editCuerpo(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.mensajesSelected.setCuerpo(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.mensajesSelected.setCuerpo(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editIncidencia(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.mensajesSelected.setIncidencia((int) (edditedCell.getNewValue()));
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.mensajesSelected.setIncidencia((int) (edditedCell.getNewValue()));
			}
		}
	}

	@FXML
	public void editFecha(CellEditEvent edditedCell) {
		/*
		 * if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
		 * this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
		 * this.mensajesSelected.setFecha(edditedCell.getNewValue().toString()); } else
		 * { if (this.tabla.getSelectionModel().getSelectedItem().getId() ==
		 * this.idselected) {// si correcto
		 * this.mensajesSelected.setFecha(edditedCell.getNewValue().toString()); } }
		 */
	}

	@FXML
	public void editEmisor(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.mensajesSelected.setAsunto(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.mensajesSelected.setAsunto(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	public void editReceptor(CellEditEvent edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.mensajesSelected.setAsunto(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.mensajesSelected.setAsunto(edditedCell.getNewValue().toString());
			}
		}
	}

	public void agregarEnBaseDatos(mensajesDTO mensajesDTO) throws SQLException {
		this.bdmensajes.crearMensaje(mensajesDTO);
	}

}

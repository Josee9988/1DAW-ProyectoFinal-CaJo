/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controllerMensajes;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controllerUtilidades.agregar_combobox;
import controllerUtilidades.confirmar_controller;
import dto.mensajesDTO;
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
import javafx.stage.Stage;
import model.jdbcIncidenciasDAO;
import model.jdbcMensajesDAO;
import model.jdbcUsuarioDAO;

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
	private TableColumn<mensajesDTO, String> incidencia;
	@FXML
	private TableColumn<mensajesDTO, Date> fecha;
	@FXML
	private TableColumn<mensajesDTO, String> emisor;
	@FXML
	private TableColumn<mensajesDTO, String> receptor;
	@FXML
	private TextField usuario_encabezado;
	@FXML
	private TextField fecha_encabezado;

	private jdbcIncidenciasDAO jdbcIncidenciasDAO;
	private jdbcUsuarioDAO jdbcUsuarioDAO;

	private int idselected;
	private mensajesDTO mensajesSelected;
	private Stage agregar_mensaje;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_mensaje;
	private agregar_mensajes controller_agregar_mensajes;
	private Image icon;
	private String nombreCompleto;
	private int rol;

	// pop up de comboB
	private Stage agregar_combobox;
	private Parent root2;
	private Scene scene2;
	private FXMLLoader fxmlLoaderagregar_combo;
	private agregar_combobox controller_agregar_combo;
	private static int incidenciaCombo;

	// pop up de confirmación de eliminación
	private Stage confirmacion_eliminacion;
	private Parent rootEliminacion;
	private Scene sceneEliminacion;
	private FXMLLoader fxmlLoaderagregar_eliminacion;
	private confirmar_controller controller_confirmar_controller;

	/**
	 * consultar_mensajes constructor default que inicializa valores
	 */
	public consultar_mensajes() {
		this.tabla = new TableView<>();
		this.mensajesSelected = new mensajesDTO();
		this.idselected = -1;
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.nombreCompleto = "";
		consultar_mensajes.incidenciaCombo = -1;
		this.bdmensajes = new jdbcMensajesDAO();
		this.jdbcIncidenciasDAO = new jdbcIncidenciasDAO();
		this.jdbcUsuarioDAO = new jdbcUsuarioDAO();
		this.rol = 0;
	}

	/**
	 * inicializar inicializa el tableview, cogiendo los datos de la base de datos y
	 * asignándoselos
	 *
	 * @param rol            entero del tipo de usuario que se ha logeado
	 * @param nombreCompleto recibe el nombre y apellidos del usuario logeado
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void inicializar(String nombreCompleto, int rol) throws SQLException {
		this.rol = rol;
		this.nombreCompleto = nombreCompleto;
		this.id.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.asunto.setCellValueFactory(new PropertyValueFactory<>("Asunto"));
		this.cuerpo.setCellValueFactory(new PropertyValueFactory<>("Cuerpo"));
		this.incidencia.setCellValueFactory(new PropertyValueFactory<>("IncidenciaS"));
		this.fecha.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
		this.emisor.setCellValueFactory(new PropertyValueFactory<>("EmisorS"));
		this.receptor.setCellValueFactory(new PropertyValueFactory<>("ReceptorS"));

		// cambiamos valores numéricos de incidencias, emisor y receptor para que salgan
		// como el nombre al que corresponden esas ids
		String nombreCompletoArray[] = nombreCompleto.split(" ");
		ArrayList<mensajesDTO> mensajesToAdd = new ArrayList<>();
		// this.jdbcUsuarioDAO.devolverId(nombreCompletoArray[0],
		// nombreCompletoArray[1]); // conseguimos id

		mensajesToAdd.addAll(this.bdmensajes
				.leerMensajes(this.jdbcUsuarioDAO.devolverId(nombreCompletoArray[0], nombreCompletoArray[1]), rol));
		for (mensajesDTO i : mensajesToAdd) {
			if (this.jdbcIncidenciasDAO.obtenerNombreIncidencia(i.getIncidencia()).length() > 64) {
				i.setIncidenciaS(this.jdbcIncidenciasDAO.obtenerNombreIncidencia(i.getIncidencia()).substring(0, 64));

			} else {
				i.setIncidenciaS(this.jdbcIncidenciasDAO.obtenerNombreIncidencia(i.getIncidencia()));

			}
			i.setEmisorS(this.jdbcUsuarioDAO.devolverNombre(i.getEmisor()));
			i.setReceptorS(this.jdbcUsuarioDAO.devolverNombre(i.getReceptor()));
		}

		this.tabla.getItems().addAll(mensajesToAdd);

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

		// Evento que cuando dé doble click en las columna(s) que queremos abrá views.
		this.tabla.setOnMouseClicked(new EventHandler<MouseEvent>() {
			// para evento fecha
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) { // para que haya dos clicks
					TablePosition<?, ?> pos = consultar_mensajes.this.tabla.getSelectionModel().getSelectedCells()
							.get(0);
					if (pos.getColumn() == 3) { // si es la columna(s) que queremos...
						consultar_mensajes.this.idselected = consultar_mensajes.this.tabla.getSelectionModel()
								.getSelectedItem().getId();

						consultar_mensajes.this.mensajesSelected.setId(consultar_mensajes.this.idselected); // id

						// creamos la view
						consultar_mensajes.this.agregar_combobox = new Stage();
						consultar_mensajes.this.fxmlLoaderagregar_combo = new FXMLLoader(
								this.getClass().getResource("/view/agregarComboBox.fxml"));
						try {
							consultar_mensajes.this.root2 = (Parent) consultar_mensajes.this.fxmlLoaderagregar_combo
									.load();
						} catch (IOException e) {
							System.out.println(e.toString());
						}

						consultar_mensajes.this.controller_agregar_combo = consultar_mensajes.this.fxmlLoaderagregar_combo
								.<agregar_combobox>getController();
						consultar_mensajes.this.scene2 = new Scene(consultar_mensajes.this.root2);
						try {
							consultar_mensajes.this.controller_agregar_combo.inicializar(0);
						} catch (SQLException e) {
							System.out.println(e.toString());
						} // llamamos al método
							// inicializar
						consultar_mensajes.this.agregar_combobox.setScene(consultar_mensajes.this.scene2);
						consultar_mensajes.this.agregar_combobox.getIcons().add(consultar_mensajes.this.icon); // agregamos
						// el
						// icono
						consultar_mensajes.this.agregar_combobox.setTitle("Proyecto Jose Carlos"); // ponemos el título
						// de la ventana
						consultar_mensajes.this.agregar_combobox.show();
					}
				}
			}
		}); // fin doble click en las columnas que queremos para abrir views

	}

	@FXML
	/**
	 * agregarMensaje agrega un mensaje, desde una view
	 *
	 * @throws IOException  si ha habido una excepción IO
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void agregarMensaje() throws IOException, SQLException {
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
	/**
	 * modificarMensaje modifica un mensaje; desde el texto modificado o desde una
	 * view
	 *
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
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
			if (this.mensajesSelected.getEmisor() == 0) {
				this.mensajesSelected.setEmisor(this.tabla.getSelectionModel().getSelectedItem().getEmisor());
			}
			if (this.mensajesSelected.getReceptor() == 0) {
				this.mensajesSelected.setReceptor(this.tabla.getSelectionModel().getSelectedItem().getReceptor());
			}

			if (consultar_mensajes.incidenciaCombo != -1) {
				this.mensajesSelected.setIncidencia(consultar_mensajes.incidenciaCombo);
			}

			this.bdmensajes.modificarMensaje(this.mensajesSelected);
			this.idselected = -1;
			consultar_mensajes.incidenciaCombo = -1;
			this.mensajesSelected = new mensajesDTO();
		}
	}

	@FXML
	/**
	 * eliminarMensaje abre una pestaña de confirmación con un botón eliminar el
	 * cuál llamará a un método para eliminar el mensajes o por lo contrario
	 * cancelar símplemente cerrará la ventana actual.
	 *
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 * @param IOExcepcion si ha habido una excepción IO
	 *
	 */
	public void eliminarMensaje() throws SQLException, IOException {
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			// creamos la escena
			this.confirmacion_eliminacion = new Stage();
			this.fxmlLoaderagregar_eliminacion = new FXMLLoader(
					this.getClass().getResource("/view/confirmacionEliminacion.fxml"));
			this.rootEliminacion = (Parent) this.fxmlLoaderagregar_eliminacion.load();
			this.controller_confirmar_controller = this.fxmlLoaderagregar_eliminacion
					.<confirmar_controller>getController();
			this.sceneEliminacion = new Scene(this.rootEliminacion);
			this.controller_confirmar_controller.inicializar(3,
					this.tabla.getSelectionModel().getSelectedItem().getId()); // llamamos
			// al
			// método
			// inicializar
			this.confirmacion_eliminacion.setScene(this.sceneEliminacion);
			this.confirmacion_eliminacion.getIcons().add(this.icon); // agregamos el icono
			this.confirmacion_eliminacion.setTitle("Eliminar mensaje"); // ponemos el título de la ventana
			this.confirmacion_eliminacion.show();
		}
		/*
		 * this.bdmensajes.eliminarMensajes(this.tabla.getSelectionModel().
		 * getSelectedItem().getId()); // lo eliminamos // en la bd
		 * this.tabla.getItems().remove(this.tabla.getSelectionModel().getSelectedItem()
		 * ); // lo eliminamos en la tabla
		 */
	}

	/**
	 * elimina el mensaje en la base de datos
	 *
	 * @param id, recibe la id a eliminar en la base de datos
	 * @throws SQLException si hay una excepción de SQL
	 */
	public void eliminarMensajeBD(int id) throws SQLException {
		this.bdmensajes.eliminarMensajes(id);
	}

	/**
	 *
	 * @param idToReturn
	 */
	public void agregarIncidenciaDeComboBox(int idToReturn) {
		consultar_mensajes.incidenciaCombo = idToReturn;
	}

	@FXML
	/**
	 * restart borra todos los elementos del tableview y vuelve a rellenarla con los
	 * datos de la base de datos
	 *
	 * @throws SQLException
	 */
	public void restart() throws SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		this.inicializar(this.nombreCompleto, this.rol);
		// this.tabla.getItems().addAll(this.bdmensajes.leerMensajes());
	}

	// MODIFICACIONES
	@FXML
	/**
	 * editAsunto si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editAsunto(CellEditEvent<?, ?> edditedCell) {
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
	/**
	 * editCuerpo si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editCuerpo(CellEditEvent<?, ?> edditedCell) {
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
	/**
	 * editIncidencia si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editIncidencia(CellEditEvent<?, ?> edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.mensajesSelected.setIncidencia((int) (edditedCell.getNewValue()));
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.mensajesSelected.setIncidencia((int) (edditedCell.getNewValue()));
			}
		}
	}

}

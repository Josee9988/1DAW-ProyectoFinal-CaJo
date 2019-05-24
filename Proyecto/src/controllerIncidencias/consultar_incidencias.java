/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controllerIncidencias;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controllerUtilidades.agregar_combobox;
import controllerUtilidades.agregar_fecha;
import dto.incidenciaDTO;
import dto.usuarioDTO;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import model.jdbcUbicacionDAO;

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
	@FXML
	private Button resolverI;

	private jdbcUbicacionDAO jdbcUbicacionDAO;

	private int idselected;
	private incidenciaDTO incidenciaSelected;
	private Stage agregar_incidencia;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_incidencia;
	private agregar_incidencia controller_agregar_incidencia;
	private Image icon;
	private String nombreCompleto;
	private int rol;

	// pop up de fecha
	private Stage agregar_fecha;
	private Parent root2;
	private Scene scene2;
	private FXMLLoader fxmlLoaderagregar_fecha;
	@SuppressWarnings("unused")
	private agregar_fecha controller_agregar_fecha;
	private static Date fechaSelected;

	// pop up de ubicación
	private Stage agregar_ubicacion;
	private Parent root3;
	private Scene scene3;
	private FXMLLoader fxmlLoaderagregar_ubicacion;
	private agregar_combobox controller_agregar_ubicacion;
	private static int idUbicacion;

	/**
	 * consultar_incidencias constructor default el cual inicializa valores
	 */
	public consultar_incidencias() {
		this.tabla = new TableView<>();
		this.bdincidencias = new jdbcIncidenciasDAO();
		this.incidenciaSelected = new incidenciaDTO();
		this.idselected = -1;
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.nombreCompleto = "";
		this.rol = 0;
		consultar_incidencias.idUbicacion = -1;
		this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
	}

	/**
	 * inicializar inicializa todos los elementos necesarios de la tabla cogiendo
	 * los datos de la base de datos y aplicándoselos
	 *
	 * @param nombreCompleto el nombre y el apellido de la persona logeada
	 * @param rol            el rol que el usuario tiene
	 * @throws SQLException si ha habido un fallo de sql...
	 */
	public void inicializar(String nombreCompleto, int rol) throws SQLException {
		if (rol == 1 || rol == 2) {// si es un profesor o un jefe no podrá resolver incidencias, no mostramos el
			// botón
			this.resolverI.setVisible(false);
		}
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

		ArrayList<incidenciaDTO> arrayIncidenciaToAdd = new ArrayList<>();
		arrayIncidenciaToAdd.addAll(
				this.bdincidencias.leerIncidencias(new usuarioDTO(this.usuario_encabezado.getText(), this.rol_number)));
		for (incidenciaDTO i : arrayIncidenciaToAdd) {
			i.setUbicacion(this.jdbcUbicacionDAO.devolverNombre(i.getUbicacionI()));
		}
		this.tabla.getItems().addAll(arrayIncidenciaToAdd);
		this.date = new Date();
		this.usuario_encabezado.setText(nombreCompleto);
		this.fecha_encabezado.setText(new SimpleDateFormat("dd-MM-yyyy").format(this.date));
		this.usuario_encabezado.setEditable(false);
		this.fecha_encabezado.setEditable(false);

		this.tabla.setEditable(true);// hacemos la tabla entera editable

		// Hacemos todos los campos editables menos "id". Porque es un autoincrement y
		// nunca va a ser relevante a la hora de modificar un usuario
		// this.usuario.setCellFactory(TextFieldTableCell.forTableColumn());
		this.descripcion.setCellFactory(TextFieldTableCell.forTableColumn());
		this.elemento.setCellFactory(TextFieldTableCell.forTableColumn());
		this.urgencia.setCellFactory(TextFieldTableCell.forTableColumn());
		this.categoria.setCellFactory(TextFieldTableCell.forTableColumn());
		this.materiales.setCellFactory(TextFieldTableCell.forTableColumn());
		// this.ubicacion.setCellFactory(TextFieldTableCell.forTableColumn());
		consultar_incidencias.fechaSelected = null;

		// Evento que cuando dé doble click en las columna(s) que queremos abrá views.
		this.tabla.setOnMouseClicked(new EventHandler<MouseEvent>() {
			// para evento fecha
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) { // para que haya dos clicks
					TablePosition<?, ?> pos = consultar_incidencias.this.tabla.getSelectionModel().getSelectedCells()
							.get(0);
					// UBICACIÓN:
					if (pos.getColumn() == 8) { // si es la columna(s) que queremos...
						// int row = pos.getRow();
						consultar_incidencias.this.idselected = consultar_incidencias.this.tabla.getSelectionModel()
								.getSelectedItem().getId();

						consultar_incidencias.this.incidenciaSelected.setId(consultar_incidencias.this.idselected); // id
						// no
						// cambiará

						// creamos la view
						consultar_incidencias.this.agregar_ubicacion = new Stage();
						consultar_incidencias.this.fxmlLoaderagregar_ubicacion = new FXMLLoader(
								this.getClass().getResource("/view/agregarComboBox.fxml"));
						try {
							consultar_incidencias.this.root3 = (Parent) consultar_incidencias.this.fxmlLoaderagregar_ubicacion
									.load();
						} catch (IOException e) {
							System.out.println(e.toString());
						}

						consultar_incidencias.this.controller_agregar_ubicacion = consultar_incidencias.this.fxmlLoaderagregar_ubicacion
								.<agregar_combobox>getController();
								consultar_incidencias.this.scene3 = new Scene(consultar_incidencias.this.root3);
								try {
									consultar_incidencias.this.controller_agregar_ubicacion.inicializar(1);
								} catch (SQLException e) {
									System.out.println(e.toString());
								} // llamamos al método
								// inicializar
								consultar_incidencias.this.agregar_ubicacion.setScene(consultar_incidencias.this.scene3);
								consultar_incidencias.this.agregar_ubicacion.getIcons().add(consultar_incidencias.this.icon); // agregamos
								// el
								// icono
								consultar_incidencias.this.agregar_ubicacion.setTitle("Proyecto Jose Carlos"); // ponemos el
								// título
								// de la ventana
								consultar_incidencias.this.agregar_ubicacion.show();
					} else if (pos.getColumn() == 4) { // si es la columna(s) que queremos...//FECHA:
						// int row = pos.getRow();
						consultar_incidencias.this.idselected = consultar_incidencias.this.tabla.getSelectionModel()
								.getSelectedItem().getId();

						consultar_incidencias.this.incidenciaSelected.setId(consultar_incidencias.this.idselected);
						// id no cambiará

						// creamos la view
						consultar_incidencias.this.agregar_fecha = new Stage();
						consultar_incidencias.this.fxmlLoaderagregar_fecha = new FXMLLoader(
								this.getClass().getResource("/view/agregarFecha.fxml"));
						try {
							consultar_incidencias.this.root2 = (Parent) consultar_incidencias.this.fxmlLoaderagregar_fecha
									.load();
						} catch (IOException e) {
							System.out.println(e.toString());
						}

						consultar_incidencias.this.controller_agregar_fecha = consultar_incidencias.this.fxmlLoaderagregar_fecha
								.<agregar_fecha>getController();
								consultar_incidencias.this.scene2 = new Scene(consultar_incidencias.this.root2);
								// consultar_incidencias.this.controller_agregar_fecha.inicializar(); //
								// llamamos al método
								// inicializar
								consultar_incidencias.this.agregar_fecha.setScene(consultar_incidencias.this.scene2);
								consultar_incidencias.this.agregar_fecha.getIcons().add(consultar_incidencias.this.icon); // agregamos
								// el
								// icono
								consultar_incidencias.this.agregar_fecha.setTitle("Proyecto Jose Carlos"); // ponemos el título
								// de la ventana
								consultar_incidencias.this.agregar_fecha.show();
					}
				}
			}
		}); // fin doble click en las columnas que queremos para abrir views
	}

	@FXML
	/**
	 * agregarIncidencia abre la view para agregar una incidencia
	 *
	 * @throws IOException  si ha habido una excepción IO
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void agregarIncidencia() throws IOException, SQLException {
		this.agregar_incidencia = new Stage();
		this.fxmlLoaderagregar_incidencia = new FXMLLoader(this.getClass().getResource("/view/agregarIncidencia.fxml"));
		this.root1 = (Parent) this.fxmlLoaderagregar_incidencia.load();
		this.controller_agregar_incidencia = this.fxmlLoaderagregar_incidencia.<agregar_incidencia>getController();
		this.scene1 = new Scene(this.root1);
		this.controller_agregar_incidencia.inicializar(this.nombreCompleto); // llamamos al método inicializar
		this.agregar_incidencia.setScene(this.scene1);
		this.agregar_incidencia.getIcons().add(this.icon); // agregamos el icono
		this.agregar_incidencia.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
		this.agregar_incidencia.show();
	}


	@FXML
	/**
	 * modificarIncidencia modifica una incidencia al ser modificada o abre una
	 * nueva view si no se ha modificado ningún valor
	 *
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void modificarIncidencia() throws SQLException {
		// Si un valor no se ha modificado cogerá el que estaba en la fila.
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			this.incidenciaSelected.setId(this.idselected); // id no cambiará
			if (this.incidenciaSelected.getUsuario().equals("")) {
				this.incidenciaSelected.setUsuario(this.tabla.getSelectionModel().getSelectedItem().getUsuario());
			}
			if (this.incidenciaSelected.getDescripcion().equals("")) {
				this.incidenciaSelected
				.setDescripcion(this.tabla.getSelectionModel().getSelectedItem().getDescripcion());
			}
			if (this.incidenciaSelected.getElemento().equals("")) {
				this.incidenciaSelected.setElemento(this.tabla.getSelectionModel().getSelectedItem().getElemento());
			}
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
			if (consultar_incidencias.fechaSelected == null) {
				this.incidenciaSelected.setFecha(this.tabla.getSelectionModel().getSelectedItem().getFecha());
			} else {
				this.incidenciaSelected.setFecha((java.sql.Date) consultar_incidencias.fechaSelected);

			}
			if (consultar_incidencias.idUbicacion != -1) {
				this.incidenciaSelected.setUbicacionI(consultar_incidencias.idUbicacion);

			}
			this.bdincidencias.modificarIncidencia(this.incidenciaSelected);
			this.idselected = -1;
			consultar_incidencias.idUbicacion = -1;
			this.incidenciaSelected = new incidenciaDTO();
		}
	}

	/**
	 * agregarFechaView cuando se llama desde el método agregar fecha, (el pop up)
	 *
	 * @param date fecha recibida del pop up de agregarfecha
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void agregarFechaView(Date date) throws SQLException {
		consultar_incidencias.fechaSelected = date;
		this.incidenciaSelected.setId(this.idselected); // id no cambiará
		this.incidenciaSelected.setFecha((java.sql.Date) date);

	}

	/**
	 * agregarIncidenciaDeComboBox cuando se llama desde el método agregar
	 * incidencia desde el combobox (el pop up)
	 *
	 * @param idToReturn id recibida por el pop up del combobox (id seleccionada)
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void agregarIncidenciaDeComboBox(int idToReturn) throws SQLException {
		consultar_incidencias.idUbicacion = idToReturn;
	}

	@FXML
	/**
	 * eliminarIncidencia elimina la incidencia seleccionada del tableview y la base
	 * de datos
	 *
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void eliminarIncidencia() throws SQLException {
		this.bdincidencias.eliminarIncidencia(this.tabla.getSelectionModel().getSelectedItem().getId()); // lo
		// eliminamos
		// en la bd
		this.tabla.getItems().remove(this.tabla.getSelectionModel().getSelectedItem()); // lo eliminamos en la tabla
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
	}

	// MODIFICACIONES
	@FXML
	/**
	 * editUsuario si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editUsuario(CellEditEvent<?, ?> edditedCell) {
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
	/**
	 * editDescripcion si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editDescripcion(CellEditEvent<?, ?> edditedCell) {
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
	/**
	 * editElemento si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editElemento(CellEditEvent<?, ?> edditedCell) {
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
	/**
	 * editUrgencia si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editUrgencia(CellEditEvent<?, ?> edditedCell) {
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
	/**
	 * editCategoria si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editCategoria(CellEditEvent<?, ?> edditedCell) {
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
	/**
	 * editMateriales si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editMateriales(CellEditEvent<?, ?> edditedCell) {
		if (this.idselected == -1) {// si es la primera vez que cambiamos un valor...
			this.idselected = this.tabla.getSelectionModel().getSelectedItem().getId();
			this.incidenciaSelected.setMateriales(edditedCell.getNewValue().toString());
		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.incidenciaSelected.setMateriales(edditedCell.getNewValue().toString());
			}
		}
	}

}
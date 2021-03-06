/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerUbicaciones;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.controllerUtilidades.confirmar_controller;
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
import javafx.scene.text.Text;
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
	@FXML
	private TextField filtro;
	@FXML
	private Text textoError;

	private Stage agregar_ubicacion;
	private Parent root1;
	private Scene scene1;
	private FXMLLoader fxmlLoaderagregar_ubicacion;
	private Image icon;
	private int idselected;
	private ubicacionDTO ubicacionDTO;

	// pop up de confirmación de eliminación
	private Stage confirmacion_eliminacion;
	private Parent rootEliminacion;
	private Scene sceneEliminacion;
	private FXMLLoader fxmlLoaderagregar_eliminacion;
	private confirmar_controller controller_confirmar_controller;
	private agregar_ubicacion modificarManulal;

	/**
	 * consultar_ubicaciones constructor default que inicializa variables.
	 */
	public consultar_ubicaciones() {
		this.tabla = new TableView<>();
		this.bdubicaciones = new jdbcUbicacionDAO();
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
		this.idselected = -1;
		this.ubicacionDTO = new ubicacionDTO();
	}

	/**
	 * inicializar inicializa el tableview, cogiendo los datos de la base de datos y
	 * asignándoselos
	 *
	 * @param nombreCompleto recibe el nombre y apellidos del usuario logeado
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
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
	/**
	 * agregarUbicacion agrega una ubicación a través de una view
	 *
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void agregarUbicacion() throws IOException {
		// creamos la escena
		this.agregar_ubicacion = new Stage();
		this.fxmlLoaderagregar_ubicacion = new FXMLLoader(this.getClass().getResource("/view/agregarUbicacion.fxml"));
		this.root1 = (Parent) this.fxmlLoaderagregar_ubicacion.load();
		this.scene1 = new Scene(this.root1);
		// inicializar
		this.agregar_ubicacion.setScene(this.scene1);
		this.agregar_ubicacion.getIcons().add(this.icon); // agregamos el icono
		this.agregar_ubicacion.setTitle("Agregar Ubicación"); // ponemos el título de la ventana
		this.agregar_ubicacion.show();
	}

	@FXML
	/**
	 * modificarUbicacion modifica una ubicación tras ser modificado con doble click
	 * o a través de una view
	 *
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void modificarUbicacion() throws SQLException {
		// Si un valor no se ha modificado cogerá el que estaba en la fila.
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			this.ubicacionDTO.setId(this.idselected); // id no cambiará
			if (this.ubicacionDTO.getNombre().isEmpty()) {
				this.ubicacionDTO.setNombre(this.tabla.getSelectionModel().getSelectedItem().getNombre());
			}
			if (this.ubicacionDTO.getDescripcion().isEmpty()) {
				this.ubicacionDTO.setDescripcion(this.tabla.getSelectionModel().getSelectedItem().getDescripcion());
			}
			if (this.ubicacionDTO.getEdificio().isEmpty()) {
				this.ubicacionDTO.setEdificio(this.tabla.getSelectionModel().getSelectedItem().getEdificio());
			}
			if (this.ubicacionDTO.getPlanta().isEmpty()) {
				this.ubicacionDTO.setPlanta(this.tabla.getSelectionModel().getSelectedItem().getPlanta());
			}
			this.idselected = -1;
			this.bdubicaciones.modificarUbicacion(this.ubicacionDTO);
			this.ubicacionDTO = new ubicacionDTO();
		}
	}

	@FXML
	/**
	 * eliminarUbicacion elimina una ubicación seleccionada
	 *
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 * @param IOExcepcion si ha habido una excepción IO
	 */
	public void eliminarUbicacion() throws SQLException, IOException {
		if (this.tabla.getSelectionModel().getSelectedItem() != null) {
			// creamos la escena
			this.confirmacion_eliminacion = new Stage();
			this.fxmlLoaderagregar_eliminacion = new FXMLLoader(
					this.getClass().getResource("/view/confirmacionEliminacion.fxml"));
			this.rootEliminacion = (Parent) this.fxmlLoaderagregar_eliminacion.load();
			this.controller_confirmar_controller = this.fxmlLoaderagregar_eliminacion
					.<confirmar_controller>getController();
			this.sceneEliminacion = new Scene(this.rootEliminacion);
			this.controller_confirmar_controller.inicializar(1,
					this.tabla.getSelectionModel().getSelectedItem().getId()); // llamamos
			// al
			// método
			// inicializar
			this.confirmacion_eliminacion.setScene(this.sceneEliminacion);
			this.confirmacion_eliminacion.getIcons().add(this.icon); // agregamos el icono
			this.confirmacion_eliminacion.setTitle("Eliminar ubicación"); // ponemos el título de la ventana
			this.confirmacion_eliminacion.show();
		}
	}

	/**
	 * elimina una ubicacion en la base de datos
	 *
	 * @param id, recibe la id a eliminar en la base de datos
	 * @throws SQLException si hay una excepción de SQL
	 */
	public void eliminarUbicacionBD(int id) throws SQLException {
		this.bdubicaciones.eliminarUbicacion(id);
	}

	@FXML
	/**
	 * restart borra todos los elementos del tableview y vuelve a rellenarla con los
	 * datos de la base de datos
	 *
	 * @throws SQLException si ha habido alguna excepción de tipo SQL
	 */
	public void restart() throws SQLException {
		this.tabla.getItems().clear(); // borramos todos los datos
		this.tabla.getItems().addAll(this.bdubicaciones.leerUbicaciones());
	}

	// #####MODIFICACIONES
	@FXML
	/**
	 * commitFIltro cuando se ha pulsado intro en nuestro TextField procederá a
	 * borrar todos los datos de la base de datos y reemplazarlos por los elementos
	 * encontrados en la base de datos. Es un filtro que se aplica a campos
	 * específicos
	 *
	 * @throws SQLException si ha habido una excepción SQL
	 */
	public void modificarUbicacionManual() throws SQLException, IOException {
		ubicacionDTO ubicacion = this.tabla.getSelectionModel().getSelectedItem();
		if (ubicacion != null) {
			// creamos la escena
			this.agregar_ubicacion = new Stage();
			this.fxmlLoaderagregar_ubicacion = new FXMLLoader(
					this.getClass().getResource("/view/agregarUbicacion.fxml"));
			this.root1 = (Parent) this.fxmlLoaderagregar_ubicacion.load();
			this.modificarManulal = this.fxmlLoaderagregar_ubicacion.<agregar_ubicacion>getController();
			this.modificarManulal.inicializar(ubicacion);
			this.scene1 = new Scene(this.root1);
			// inicializar
			this.agregar_ubicacion.setScene(this.scene1);
			this.agregar_ubicacion.getIcons().add(this.icon); // agregamos el icono
			this.agregar_ubicacion.setTitle("Modificar Ubicación"); // ponemos el título de la ventana
			this.agregar_ubicacion.show();
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
		this.tabla.getItems().addAll(this.bdubicaciones.filtrar(this.filtro.getText()));
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
			this.ubicacionDTO.setNombre(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.ubicacionDTO.setNombre(edditedCell.getNewValue().toString());
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
			this.ubicacionDTO.setDescripcion(edditedCell.getNewValue().toString());

		} else {
			if (this.tabla.getSelectionModel().getSelectedItem().getId() == this.idselected) {// si correcto
				this.ubicacionDTO.setDescripcion(edditedCell.getNewValue().toString());
			}
		}
	}

	@FXML
	/**
	 * editEdificio si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editEdificio(CellEditEvent<?, ?> edditedCell) {
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
	/**
	 * editPlanta si se ha hecho doble click en una celda
	 *
	 * @param edditedCell celda editada por el usuario al hacer doble click
	 */
	public void editPlanta(CellEditEvent<?, ?> edditedCell) {
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

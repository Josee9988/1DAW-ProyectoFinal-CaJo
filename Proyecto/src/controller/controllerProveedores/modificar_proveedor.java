package controller.controllerProveedores;

import java.sql.SQLException;

import dto.proveedorDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.jdbcProveedoresDAO;

public class modificar_proveedor {
	
	@FXML
	private TextField nombre;
	@FXML
	private TextField contacto;
	@FXML
	private ComboBox<Integer> valoracion;
	@FXML
	private TextField direccion;
	@FXML
	private Button modificarProveedor;
	@FXML
	private Text textoError;

	private Stage stage;
	private int id;
	private proveedorDTO proveedorDTO;
	private jdbcProveedoresDAO jdbcProveedoresDAO;

	/**
	 * agregar_proveedor constructor defult que inicializa el controller
	 * consultar_proveedores
	 */
	public modificar_proveedor() {
		this.proveedorDTO = new proveedorDTO();
		this.jdbcProveedoresDAO = new jdbcProveedoresDAO();

	}

	/**
	 * inicializar método que inicializa los ComboBox
	 */
	public void inicializar(proveedorDTO p) {
		ObservableList<Integer> valoraciones = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		this.valoracion.setItems(valoraciones);
		this.valoracion.setEditable(false);
		this.valoracion.getSelectionModel().select(0);
		this.valoracion.getStyleClass().add("center-aligned");// clase del css para centrar combobox
		this.nombre.setText(p.getNombre());
		this.contacto.setText(p.getContacto());
		this.direccion.setText(p.getDireccion());
		this.id = p.getId();
	}

	@FXML
	/**
	 * agregarproveedor método que a través de los TextFields recibidos rellenados
	 * por el usuario rellena un objeto proveedorDTO y lo manda a la base de datos
	 *
	 * @throws SQLException por is ha habido una excepción SQL
	 */
	public void modificarProveedor() throws SQLException {
		this.proveedorDTO.setNombre(this.nombre.getText());
		this.proveedorDTO.setContacto(this.contacto.getText());
		this.proveedorDTO.setDireccion(this.direccion.getText());
		this.proveedorDTO.setValoracion(this.valoracion.getValue());
		this.proveedorDTO.setId(this.id);
		// cerrar la ventana:
		this.stage = (Stage) this.modificarProveedor.getScene().getWindow(); // seleccionamos la escena actual

		if (this.nombre.getText().isEmpty() || this.contacto.getText().isEmpty()
				|| this.direccion.getText().isEmpty()) {
			this.textoError.setText("Rellena todos los campos");

		} else {
			this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
			this.jdbcProveedoresDAO.modificarProveedor(this.proveedorDTO);
		}
	}

	
}

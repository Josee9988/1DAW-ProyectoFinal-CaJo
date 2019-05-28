package controller.controllerUbicaciones;

import java.sql.SQLException;

import dto.ubicacionDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.jdbcUbicacionDAO;

public class modificar_ubicacion {
	@FXML
	private TextField nombre;
	@FXML
	private TextField descripcion;
	@FXML
	private TextField edificio;
	@FXML
	private TextField planta;
	@FXML
	private Button modificarUbicacion;
	@FXML
	private Text textoError;

	private Stage stage;
	private ubicacionDTO ubicacionDTO;
	private jdbcUbicacionDAO jdbcUbicacionDAO;
	private int id;
	
	public modificar_ubicacion() {
		this.ubicacionDTO = new ubicacionDTO();
		this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
	}
	
	/**
	 * agregar_ubicacion constructor default que inicializa la clase
	 * consultar_ubicaciones
	 */
	public void inicializar(ubicacionDTO u) {
		this.id = u.getId();
		this.nombre.setText(u.getNombre());
		this.descripcion.setText(u.getDescripcion());
		this.edificio.setText(u.getEdificio());
		this.planta.setText(u.getPlanta());
	}

	@FXML
	/**
	 * agregarUbicacion método que a través de los TextFields rellanados por el
	 * usuario rellena un objeto ubicacionDTO y lo manda a la base de datos
	 *
	 * @throws SQLException por si ha habido una excepción SQL
	 */
	public void modificarUbicacion() throws SQLException {
		this.ubicacionDTO.setNombre(this.nombre.getText());
		this.ubicacionDTO.setDescripcion(this.descripcion.getText());
		this.ubicacionDTO.setEdificio(this.edificio.getText());
		this.ubicacionDTO.setPlanta(this.planta.getText());
		this.ubicacionDTO.setId(this.id);
		// cerrar la ventana:
		this.stage = (Stage) this.modificarUbicacion.getScene().getWindow(); // seleccionamos la escena actual

		if (this.nombre.getText().isEmpty() || this.descripcion.getText().isEmpty() || this.edificio.getText().isEmpty()
				|| this.planta.getText().isEmpty()) {
			this.textoError.setText("Rellena todos los campos");
		} else {
			this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
			this.jdbcUbicacionDAO.modificarUbicacion(this.ubicacionDTO);
		}
	}

}

/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerUbicaciones;

import java.sql.SQLException;

import dto.ubicacionDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.jdbcUbicacionDAO;

public class agregar_ubicacion {
	@FXML
	private TextField nombre;
	@FXML
	private TextField descripcion;
	@FXML
	private TextField edificio;
	@FXML
	private TextField planta;
	@FXML
	private Button agregarUbicacion;
	@FXML
	private Text textoError;
	@FXML
	private Text textoTop;

	private Stage stage;
	private ubicacionDTO ubicacionDTO;
	private jdbcUbicacionDAO jdbcUbicacionDAO;
	private int id;

	/**
	 * agregar_ubicacion constructor default que inicializa la clase
	 * consultar_ubicaciones
	 */
	public agregar_ubicacion() {
		this.ubicacionDTO = new ubicacionDTO();
		this.jdbcUbicacionDAO = new jdbcUbicacionDAO();
		this.id = 0;

	}

	/**
	 * agregar_ubicacion constructor default que inicializa la clase
	 * consultar_ubicaciones
	 *
	 * @param u ubicacionDTO objeto de tipo ubicacionDTO que hemos seleccionado que
	 *          se utiliza para modificar los datos.
	 */
	public void inicializar(ubicacionDTO u) {
		this.id = u.getId();
		this.textoTop.setText("Modificar ubicacion");
		this.agregarUbicacion.setText("Modificar ubicación");
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
	public void agregarUbicacion() throws SQLException {
		this.ubicacionDTO.setNombre(this.nombre.getText());
		this.ubicacionDTO.setDescripcion(this.descripcion.getText());
		this.ubicacionDTO.setEdificio(this.edificio.getText());
		this.ubicacionDTO.setPlanta(this.planta.getText());

		// cerrar la ventana:
		this.stage = (Stage) this.agregarUbicacion.getScene().getWindow(); // seleccionamos la escena actual

		if (this.nombre.getText().isEmpty() || this.descripcion.getText().isEmpty() || this.edificio.getText().isEmpty()
				|| this.planta.getText().isEmpty()) {
			this.textoError.setText("Rellena todos los campos");
		} else {
			if (this.id != 0) { // si es una modificacion
				this.ubicacionDTO.setId(this.id);
				this.jdbcUbicacionDAO.modificarUbicacion(this.ubicacionDTO);
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente

			} else {
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
				this.jdbcUbicacionDAO.agregarUbicacion(this.ubicacionDTO);
			}
		}
	}

}

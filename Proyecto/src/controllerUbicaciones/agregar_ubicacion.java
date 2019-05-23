/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package controllerUbicaciones;

import java.sql.SQLException;

import dto.ubicacionDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

	private Stage stage;
	private ubicacionDTO ubicacionDTO;
	private jdbcUbicacionDAO jdbcUbicacionDAO;
	/**
	 * agregar_ubicacion constructor default que inicializa la clase
	 * consultar_ubicaciones
	 */
	public agregar_ubicacion() {
		this.ubicacionDTO = new ubicacionDTO();
		this.jdbcUbicacionDAO = new jdbcUbicacionDAO();

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
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.jdbcUbicacionDAO.agregarUbicacion(this.ubicacionDTO);

	}

}

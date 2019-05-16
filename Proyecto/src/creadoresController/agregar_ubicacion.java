/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 15, 2019
 * @param args Recibe los datos del programa
 */
package creadoresController;

import java.sql.SQLException;

import controller.consultar_ubicaciones;
import dto.ubicacionDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

	private consultar_ubicaciones consultar;

	public agregar_ubicacion() {
		this.consultar = new consultar_ubicaciones();
	}

	public void inicializar() {

	}

	@FXML
	public void agregarUbicacion() throws SQLException {
		ubicacionDTO ubicacionDTO = new ubicacionDTO();
		ubicacionDTO.setNombre(this.nombre.getText());
		ubicacionDTO.setDescripcion(this.descripcion.getText());
		ubicacionDTO.setEdificio(this.edificio.getText());
		ubicacionDTO.setPlanta(this.planta.getText());

		// cerrar la ventana:
		this.stage = (Stage) this.agregarUbicacion.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.consultar.agregarUbicacionEnBD(ubicacionDTO);

	}

}

/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 12, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.sql.SQLException;

import dto.proveedorDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregar_proveedor {
	@FXML
	private TextField nombre;
	@FXML
	private TextField contacto;
	@FXML
	private ComboBox valoracion;
	@FXML
	private TextField direccion;
	@FXML
	private Button agregarproveedor;

	private Stage stage;

	private consultar_proveedores consultar;

	public agregar_proveedor() {
		this.consultar = new consultar_proveedores();
	}

	public void inicializar() {
		ObservableList<Integer> valoraciones = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		this.valoracion.setItems(valoraciones);
		this.valoracion.setEditable(false);
		this.valoracion.getSelectionModel().select(0);
		this.valoracion.getStyleClass().add("center-aligned");// clase del css para centrar combobox
	}

	@FXML
	public void agregarproveedor() throws SQLException {
		proveedorDTO proveedorDTO = new proveedorDTO();
		proveedorDTO.setNombre(this.nombre.getText());
		proveedorDTO.setContacto(this.contacto.getText());
		proveedorDTO.setDireccion(this.direccion.getText());
		proveedorDTO.setValoracion((int) this.valoracion.getValue());
		// cerrar la ventana:
		this.stage = (Stage) this.agregarproveedor.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.consultar.agregarProveedorEnBD(proveedorDTO);
	}

}

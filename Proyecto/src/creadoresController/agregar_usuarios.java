/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package creadoresController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import controller.consultar_usuarios;
import dto.usuarioDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class agregar_usuarios {

	@FXML
	private TextField usuario;
	@FXML
	private TextField password;
	@FXML
	private TextField nombre;
	@FXML
	private TextField direccion;
	@FXML
	private TextField apellidos;
	@FXML
	private TextField telefono;
	@FXML
	private ComboBox rol;
	@FXML
	private Button agregarusuario;

	private consultar_usuarios consultar_usuarios;
	private Stage stage;

	public agregar_usuarios() {
		this.consultar_usuarios = new consultar_usuarios();
		this.stage = null;
	}

	public void inicializar() {
		ObservableList<String> roles = FXCollections.observableArrayList("Profesor", "Jefe Dpto.", "Mantenimiento",
				"Admin");
		this.rol.setItems(roles);
		this.rol.setEditable(false);
		this.rol.getSelectionModel().select(0);
		this.rol.getStyleClass().add("center-aligned");// clase del css para centrar combobox

	}

	@FXML
	public void agregarusuario() throws SQLException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		usuarioDTO usuarioDTO = new usuarioDTO();
		usuarioDTO.setUser(this.usuario.getText());
		usuarioDTO.setPassword(this.password.getText());
		usuarioDTO.setNombre(this.nombre.getText());
		usuarioDTO.setDireccion(this.direccion.getText());
		usuarioDTO.setTelefono(this.telefono.getText());
		usuarioDTO.setTelefono(this.telefono.getText());
		usuarioDTO.setRol(this.traducirComboBox());
		this.stage = (Stage) this.agregarusuario.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.consultar_usuarios.agregarEnBaseDatos(usuarioDTO);
	}

	/**
	 * traducirComboBox Según el valor que tenga el ComboBox seleccionado, se
	 * traduce a entero para guardarlo en la base de datos
	 *
	 * @return resultado entero que contiene el valor entre 1 y 4 referido a los
	 *         roles
	 */
	public int traducirComboBox() {
		String seleccionado = (String) this.rol.getValue();
		int resultado = 0;
		switch (seleccionado) {
		case "Profesor":
			resultado = 1;
			break;
		case "Jefe Dpto.":
			resultado = 2;
			break;
		case "Mantenimiento":
			resultado = 3;
			break;
		case "Admin":
			resultado = 4;
			break;

		default:
			break;
		}
		return resultado;
	}

}

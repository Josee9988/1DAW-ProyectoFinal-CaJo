/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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

	consultar_usuarios consultar_usuarios;
	Stage stage;


	public agregar_usuarios() {
		this.consultar_usuarios = new consultar_usuarios();
		this.stage = null;
	}

	public void inicializar() {
		ObservableList<Integer> roles = FXCollections.observableArrayList(1,2,3,4);
		this.rol.setItems(roles);
		this.rol.setEditable(false);
		this.rol.getSelectionModel().select(3);
		this.rol.getStyleClass().add("center-aligned");//clase del css para centrar combobox

	}

	@FXML
	public void agregarusuario() throws SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		usuarioDTO usuarioDTO = new usuarioDTO();
		usuarioDTO.setUser(this.usuario.getText());
		usuarioDTO.setPassword(this.password.getText());
		usuarioDTO.setNombre(this.nombre.getText());
		usuarioDTO.setDireccion(this.direccion.getText());
		usuarioDTO.setTelefono(this.telefono.getText());
		usuarioDTO.setTelefono(this.telefono.getText());
		usuarioDTO.setRol((int) this.rol.getValue());
		this.stage = (Stage) this.agregarusuario.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
		this.consultar_usuarios.agregarEnBaseDatos(usuarioDTO);

	}



}

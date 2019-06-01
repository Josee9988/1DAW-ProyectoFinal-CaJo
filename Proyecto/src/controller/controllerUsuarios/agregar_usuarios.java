/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller.controllerUsuarios;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.jdbcUsuarioDAO;

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
	private ComboBox<String> rol;
	@FXML
	private Button agregarusuario;
	@FXML
	private Text textoError;
	@FXML
	private Text labelTop;

	private boolean agregacion;

	private Stage stage;
	private usuarioDTO usuarioDTO;
	private jdbcUsuarioDAO jdbcUsuarioDAO;

	/**
	 * agregar_usuarios constructor default que inicializa variables simples.
	 */
	public agregar_usuarios() {
		this.usuarioDTO = new usuarioDTO();
		this.jdbcUsuarioDAO = new jdbcUsuarioDAO();
		this.agregacion = false;
	}

	/**
	 * inicializar método que inicializa los ComboBox
	 */
	public void inicializar() {
		this.agregacion = true;
		ObservableList<String> roles = FXCollections.observableArrayList("Profesor", "Jefe Dpto.", "Mantenimiento",
				"Admin");
		this.rol.setItems(roles);
		this.rol.setEditable(false);
		this.rol.getSelectionModel().select(0);
	}

	/**
	 * inicializar para modificar
	 *
	 * @param u recibe el objeto usuarioDTO seleccionado
	 */
	public void inicializar(usuarioDTO u) {
		this.labelTop.setText("Modificar usuario");
		this.agregarusuario.setText("Modificar usuario");
		ObservableList<String> roles = FXCollections.observableArrayList("Profesor", "Jefe Dpto.", "Mantenimiento",
				"Admin");
		this.rol.setItems(roles);
		this.rol.setEditable(false);
		this.rol.getSelectionModel().select(0);
		this.agregacion = false;
		this.usuarioDTO.setId(u.getId());
		this.usuario.setText(u.getUser());
		this.password.setText(u.getPassword());
		this.nombre.setText(u.getNombre());
		this.apellidos.setText(u.getApellidos());
		this.direccion.setText(u.getDireccion());
		this.telefono.setText(u.getTelefono());
	}

	@FXML
	/**
	 * agregarusuario agrega todos los valores recibidos de los TextField a un
	 * objeto usuarioDTO que se mandará a la base de datos para agregarlo
	 *
	 * @throws SQLException             si ha habido una excepción SQL
	 * @throws BadPaddingException      por si el formato no es el correcto
	 * @throws InvalidKeyException      si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException   por si el formateo de la key no es correcta
	 */
	public void agregarusuario() throws SQLException, InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		this.usuarioDTO.setUser(this.usuario.getText());
		this.usuarioDTO.setPassword(this.password.getText());
		this.usuarioDTO.setNombre(this.nombre.getText());
		this.usuarioDTO.setDireccion(this.direccion.getText());
		this.usuarioDTO.setTelefono(this.telefono.getText());
		this.usuarioDTO.setApellidos(this.apellidos.getText());
		this.usuarioDTO.setRol(this.traducirComboBox(rol.getValue()));
		this.stage = (Stage) this.agregarusuario.getScene().getWindow(); // seleccionamos la escena actual

		// si los campos importantes no están vacios lo hará, de otra manera no lo
		// agregará
		if (!(this.usuario.getText().isEmpty() || this.password.getText().isEmpty() || this.nombre.getText().isEmpty()
				|| this.apellidos.getText().isEmpty())) {
			if (this.agregacion == false) {// si es una modificación
				if (this.password.getText().length() < 6) {
					this.textoError.setText("La contraseña debe superar los 6 carácteres");
				} else if (this.usuario.getText().equals("root")) {
					this.textoError.setText("El usuario root no se puede modificar");
				} else {
					this.jdbcUsuarioDAO.modificarUsuario(this.usuarioDTO);// lo agrega en la base de datos
					this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
				}
			} else { // si es para agregar
				if (this.jdbcUsuarioDAO.userEncontrado(this.usuarioDTO.getUser())) {
					this.textoError.setText("ERROR!: Ese usuario ya existe, escoja uno distinto...");
				} else {
					if (this.password.getText().length() < 6) {
						this.textoError.setText("La contraseña debe superar los 6 carácteres");
					} else {
						this.jdbcUsuarioDAO.crearUsuario(this.usuarioDTO);// lo agrega en la base de datos
						this.stage.close(); // cerramos la ventana actual para pasar a la siguiente

					}
				}
			}

		} else {
			this.textoError.setText("ERROR!: Rellene todos los campos");
		}

	}

	/**
	 * traducirComboBox Según el valor que tenga el ComboBox seleccionado, se
	 * traduce a entero para guardarlo en la base de datos
	 *
	 * @param valor para que se traduzca. valor a traducir
	 * @return resultado entero que contiene el valor entre 1 y 4 referido a los
	 *         roles7
	 */
	public int traducirComboBox(String valor) {
		int resultado = 0;
		switch (valor) {
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
			resultado = 1;
			break;
		}
		return resultado;
	}

}

/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 11, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import dto.usuarioDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.jdbcUsuarioDAO;

public class Login_Controller {

	private jdbcUsuarioDAO users;

	private Stage profesor;
	private Stage jefe_departamento;
	private Stage mantenimiento;
	private Stage admin;

	private FXMLLoader fxmlLoaderProfesor;
	private FXMLLoader fxmlLoaderJefeDepartamento;
	private FXMLLoader fxmlLoaderMantenimiento;
	private FXMLLoader fxmlLoaderAdmin;

	private Home_Prof_Controller controllerProfesor;
	private Home_Jefe_Controller controllerJefeDepartamento;
	private Home_Mantenimiento_Controller controllerMantenimiento;
	private Home_Admin_Controller controllerAdmin;

	private Parent root1;
	private Parent root2;
	private Parent root3;
	private Parent root4;

	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;

	@FXML
	private TextField user;
	@FXML
	private TextField laberError;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Text area2;
	@FXML
	private javafx.scene.control.Button iniciar; // botón iniciar del login

	private Image icon;
	private Stage stage;
	private crypto_controller crypto;

	public Login_Controller() throws IOException {

		this.users = new jdbcUsuarioDAO();

		this.profesor = new Stage();
		this.jefe_departamento = new Stage();
		this.mantenimiento = new Stage();
		this.admin = new Stage();

		this.fxmlLoaderProfesor = new FXMLLoader(this.getClass().getResource("/view/home_prof_page.fxml"));
		this.fxmlLoaderJefeDepartamento = new FXMLLoader(this.getClass().getResource("/view/home_jefe_page.fxml"));
		this.fxmlLoaderMantenimiento = new FXMLLoader(
				this.getClass().getResource("/view/home_mantenimiento_page.fxml"));
		this.fxmlLoaderAdmin = new FXMLLoader(this.getClass().getResource("/view/home_admin_page.fxml"));

		this.root1 = (Parent) this.fxmlLoaderProfesor.load();
		this.root2 = (Parent) this.fxmlLoaderJefeDepartamento.load();
		this.root3 = (Parent) this.fxmlLoaderMantenimiento.load();
		this.root4 = (Parent) this.fxmlLoaderAdmin.load();

		this.controllerProfesor = this.fxmlLoaderProfesor.<Home_Prof_Controller>getController();
		this.controllerJefeDepartamento = this.fxmlLoaderJefeDepartamento.<Home_Jefe_Controller>getController();
		this.controllerMantenimiento = this.fxmlLoaderMantenimiento.<Home_Mantenimiento_Controller>getController();
		this.controllerAdmin = this.fxmlLoaderAdmin.<Home_Admin_Controller>getController();

		this.scene1 = new Scene(this.root1);
		this.scene2 = new Scene(this.root2);
		this.scene3 = new Scene(this.root3);
		this.scene4 = new Scene(this.root4);
		this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png")); // ruta icono

		this.stage = null;
		this.crypto = new crypto_controller();

	}

	@FXML
	private void iniciarSesion(ActionEvent event) throws IOException, SQLException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String passwordencriptada = "";

		// vemos si están vacios los campos o no.
		if (!this.user.getText().isEmpty() && !this.passwordField.getText().isEmpty()) {
			// encriptamos el texto que ha escrito el usuario
			passwordencriptada = this.crypto.encrypt(this.passwordField.getText());
			switch (this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada))) {

			case 1: // profesor
				this.controllerProfesor.recibirParametros(
						this.users.devolverNombre(new usuarioDTO(this.user.getText(), passwordencriptada)),
						this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada)));
				this.profesor.setScene(this.scene1);

				this.profesor.getIcons().add(this.icon); // agregamos el icono
				this.profesor.setTitle("Menú de Profesor"); // ponemos el título de la ventana
				// cogemos la escena que tenemos y la cerramos en el momento que se activa el
				// botón "iniciar"
				this.stage = (Stage) this.iniciar.getScene().getWindow(); // seleccionamos la escena actual
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
				this.profesor.show();
				break;

			case 2: // jefedepartamento
				this.controllerJefeDepartamento.recibirParametros(
						this.users.devolverNombre(new usuarioDTO(this.user.getText(), passwordencriptada)),
						this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada)));
				this.jefe_departamento.setScene(this.scene2);

				this.jefe_departamento.getIcons().add(this.icon); // agregamos el icono
				this.jefe_departamento.setTitle("Menú de Jefe de Departamento"); // ponemos el título de la ventana
				// cogemos la escena que tenemos y la cerramos en el momento que se activa el
				// botón "iniciar"
				this.stage = (Stage) this.iniciar.getScene().getWindow(); // seleccionamos la escena actual
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
				this.jefe_departamento.show();
				break;

			case 3: // mantenimiento
				this.controllerMantenimiento.recibirParametros(
						this.users.devolverNombre(new usuarioDTO(this.user.getText(), passwordencriptada)),
						this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada)));
				this.mantenimiento.setScene(this.scene3);

				this.mantenimiento.getIcons().add(this.icon); // agregamos el icono
				this.mantenimiento.setTitle("Menú de Mantenimiento"); // ponemos el título de la ventana
				// cogemos la escena que tenemos y la cerramos en el momento que se activa el
				// botón "iniciar"
				this.stage = (Stage) this.iniciar.getScene().getWindow(); // seleccionamos la escena actual
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
				this.mantenimiento.show();
				break;

			case 4: // admin
				this.controllerAdmin.recibirParametros(
						this.users.devolverNombre(new usuarioDTO(this.user.getText(), passwordencriptada)),
						this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada)));
				this.admin.setScene(this.scene4);

				this.admin.getIcons().add(this.icon); // agregamos el icono
				this.admin.setTitle("Menú de Administrador"); // ponemos el título de la ventana
				// cogemos la escena que tenemos y la cerramos en el momento que se activa el
				// botón "iniciar"

				this.stage = (Stage) this.iniciar.getScene().getWindow(); // seleccionamos la escena actual
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente

				this.admin.show();
				break;

			default:
				this.area2.setText("No encontrado");
				break;
			}
		} else {
			this.resultadoIncorrecto();
		}
	}

	public void resultadoIncorrecto() {
		this.area2.setText("Campo(s) vacios");
	}

}

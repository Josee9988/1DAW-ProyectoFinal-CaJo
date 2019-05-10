/**
 * @author Jose_Gracia_Berenguer, Carlos_Robles
 * @version May 10, 2019
 * @param args Recibe los datos del programa
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Login_Controller {

	// private bdController users;

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
	private TextField area2;
	private javafx.scene.control.Button iniciar; // botón iniciar del login
	private Image icon;
	private Stage stage;

	public Login_Controller() throws IOException {

		// this.users = bdController.getInstance();

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
		this.icon = null;
		this.stage = null;

	}

	@FXML
	private void iniciarSesion(ActionEvent event) throws IOException, SQLException {
		if (!this.user.getText().isEmpty() && !this.passwordField.getText().isEmpty()) {
			switch (this.users.ComprobarExistencia(this.user.getText(), this.passwordField.getText())) {
			case 1:
				this.controllerProfesor.recibirParametros(
						this.users.NombreApellidos(this.user.getText(), this.passwordField.getText()),
						this.users.ComprobarExistencia(this.user.getText(), this.passwordField.getText()));
				this.profesor.setScene(this.scene1);
				this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png")); // decimos dónde
																									// está el icono
				this.profesor.getIcons().add(this.icon); // agregamos el icono
				this.profesor.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
				// cogemos la escena que tenemos y la cerramos en el momento que se activa el
				// botón "iniciar"
				this.stage = (Stage) this.iniciar.getScene().getWindow(); // seleccionamos la escena actual
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
				this.profesor.show();
				break;
			case 2:
				this.controllerJefeDepartamento.recibirParametros(
						this.users.NombreApellidos(this.user.getText(), this.passwordField.getText()),
						this.users.ComprobarExistencia(this.user.getText(), this.passwordField.getText()));
				this.jefe_departamento.setScene(this.scene2);

				this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png")); // decimos dónde
																									// está el icono
				this.jefe_departamento.getIcons().add(this.icon); // agregamos el icono
				this.jefe_departamento.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
				// cogemos la escena que tenemos y la cerramos en el momento que se activa el
				// botón "iniciar"
				this.stage = (Stage) this.iniciar.getScene().getWindow(); // seleccionamos la escena actual
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
				this.jefe_departamento.show();
				break;
			case 3:
				this.controllerMantenimiento.recibirParametros(
						this.users.NombreApellidos(this.user.getText(), this.passwordField.getText()),
						this.users.ComprobarExistencia(this.user.getText(), this.passwordField.getText()));
				this.mantenimiento.setScene(this.scene3);
				this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png")); // decimos dónde
																									// está el icono
				this.mantenimiento.getIcons().add(this.icon); // agregamos el icono
				this.mantenimiento.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
				// cogemos la escena que tenemos y la cerramos en el momento que se activa el
				// botón "iniciar"
				this.stage = (Stage) this.iniciar.getScene().getWindow(); // seleccionamos la escena actual
				this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
				this.mantenimiento.show();
				break;
			case 4:
				this.controllerAdmin.recibirParametros(
						this.users.NombreApellidos(this.user.getText(), this.passwordField.getText()),
						this.users.ComprobarExistencia(this.user.getText(), this.passwordField.getText()));
				this.admin.setScene(this.scene4);
				this.icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png")); // decimos dónde
																									// está el icono
				this.admin.getIcons().add(this.icon); // agregamos el icono
				this.admin.setTitle("Proyecto Jose Carlos"); // ponemos el título de la ventana
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

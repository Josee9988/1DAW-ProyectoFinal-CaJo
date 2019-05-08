package login_controller;

import java.io.IOException;
import java.sql.SQLException;

import Controller.bdController;
import home_controller.Home_Admin_Controller;
import home_controller.Home_Jefe_Controller;
import home_controller.Home_Mantenimiento_Controller;
import home_controller.Home_Prof_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login_Controller {

	private bdController users;

	private Stage profesor;
	private Stage jefe_departamento;
	private Stage matenimiento;
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

	public Login_Controller() throws IOException {
		
		this.users = bdController.getInstance();

		this.profesor = new Stage();
		this.jefe_departamento = new Stage();
		this.matenimiento = new Stage();
		this.admin = new Stage();

		this.fxmlLoaderProfesor = new FXMLLoader(getClass().getResource("/view/home_prof_page.fxml"));
		this.fxmlLoaderJefeDepartamento = new FXMLLoader(getClass().getResource("/view/home_jefe_page.fxml"));
		this.fxmlLoaderMantenimiento = new FXMLLoader(getClass().getResource("/view/home_mantenimiento_page.fxml"));
		this.fxmlLoaderAdmin = new FXMLLoader(getClass().getResource("/view/home_admin_page.fxml"));

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
	
	}

	@FXML
	private void iniciarSesion(ActionEvent event) throws IOException, SQLException {
		if (!this.user.getText().isEmpty() && !this.passwordField.getText().isEmpty()) {
			switch (users.ComprobarExistencia(this.user.getText(), this.passwordField.getText())) {
			case 1:
				this.controllerProfesor.recibirParametros(
						users.NombreApellidos(this.user.getText(), this.passwordField.getText()),
						users.ComprobarExistencia(this.user.getText(), this.passwordField.getText()));
				this.profesor.setScene(this.scene1);
				this.profesor.show();
				break;
			case 2:
				this.controllerJefeDepartamento.recibirParametros(
						users.NombreApellidos(this.user.getText(), this.passwordField.getText()),
						users.ComprobarExistencia(this.user.getText(), this.passwordField.getText()));
				this.jefe_departamento.setScene(this.scene2);
				this.jefe_departamento.show();
				break;
			case 3:
				this.controllerMantenimiento.recibirParametros(
						users.NombreApellidos(this.user.getText(), this.passwordField.getText()),
						users.ComprobarExistencia(this.user.getText(), this.passwordField.getText()));
				this.matenimiento.setScene(this.scene3);
				this.matenimiento.show();
				break;
			case 4:
				this.controllerAdmin.recibirParametros(
						users.NombreApellidos(this.user.getText(), this.passwordField.getText()),
						users.ComprobarExistencia(this.user.getText(), this.passwordField.getText()));
				this.admin.setScene(this.scene4);
				this.admin.show();
				break;
			default:
				this.area2.setText("No encontrado");
				break;
			}
		} else {
			resultadoIncorrecto();
		}
	}

	public void resultadoIncorrecto() {
		this.area2.setText("Campo(s) vacios");
	}

}

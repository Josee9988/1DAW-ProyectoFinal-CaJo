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

import controllerUtilidades.crypto_controller;
import dto.usuarioDTO;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.jdbcUsuarioDAO;

public class Login_Controller {

	private jdbcUsuarioDAO users;

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

	@FXML
	private AnchorPane anchorLogin;
	@FXML
	private StackPane stackLogin;
	@FXML
	private Label topText;

	private Timeline timeline;
	private KeyValue kv;
	private KeyFrame kf;

	private crypto_controller crypto_controller;

	/**
	 * Login_Controller constructor default que inicializa variables de escenas y
	 * fxmls.
	 *
	 * @throws IOException si ha habido una excepción de tipo IO
	 */
	public Login_Controller() throws IOException {

		this.users = new jdbcUsuarioDAO();

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
		this.timeline = new Timeline();

		this.crypto_controller = new crypto_controller();
	}

	@FXML
	/**
	 * iniciarSesion Método que inicia la sesión a través de usuario y contraseña,
	 * según el rol del usuario logeado le mandará a su respectiva view Si ha habido
	 * un error mostrará por texto que no existe el usuario o que no se han
	 * rellenado los campos
	 *
	 * @param event evento ActionEvent que recibe
	 * @throws IOException               excepción IO
	 * @throws SQLException              excepción SQL
	 * @throws IllegalBlockSizeException por si el tamaño no es el correcto (será
	 *                                   siempre 32)
	 * @throws BadPaddingException       por si el formato no es el correcto
	 * @throws InvalidKeyException       si la key de la encriptación falla
	 * @throws NoSuchAlgorithmException  si no existe el algoritmo seleccionado
	 * @throws NoSuchPaddingException    por si el formateo de la key no es correcta
	 */
	private void iniciarSesion(ActionEvent event) throws IOException, SQLException, InvalidKeyException,
	NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		String passwordencriptada = "";

		// vemos si están vacios los campos o no.
		if (!this.user.getText().isEmpty() && !this.passwordField.getText().isEmpty()) {
			passwordencriptada = this.crypto_controller.encrypt(this.passwordField.getText());
			// encriptamos el texto que ha escrito el usuario
			switch (this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada))) {

			case 1: // profesor
				this.controllerProfesor.recibirParametros(
						this.users.devolverNombre(new usuarioDTO(this.user.getText(), passwordencriptada)),
						this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada)));
				this.scene1 = this.iniciar.getScene();
				this.root1.translateYProperty().set(this.scene1.getHeight());
				this.stackLogin.getChildren().add(this.root1);
				this.kv = new KeyValue(this.root1.translateYProperty(), 0, Interpolator.EASE_IN);
				this.kf = new KeyFrame(Duration.seconds(0.75), this.kv);
				this.timeline.getKeyFrames().add(this.kf);
				this.timeline.setOnFinished(t -> {
					this.stackLogin.getChildren().remove(this.anchorLogin);
				});
				this.timeline.play();
				break;

			case 2: // jefedepartamento
				this.controllerJefeDepartamento.recibirParametros(
						this.users.devolverNombre(new usuarioDTO(this.user.getText(), passwordencriptada)),
						this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada)));

				this.scene2 = this.iniciar.getScene();
				this.root2.translateYProperty().set(this.scene2.getHeight());
				this.stackLogin.getChildren().add(this.root2);
				this.kv = new KeyValue(this.root2.translateYProperty(), 0, Interpolator.EASE_IN);
				this.kf = new KeyFrame(Duration.seconds(0.75), this.kv);
				this.timeline.getKeyFrames().add(this.kf);
				this.timeline.setOnFinished(t -> {
					this.stackLogin.getChildren().remove(this.anchorLogin);
				});
				this.timeline.play();
				break;

			case 3: // mantenimiento
				this.controllerMantenimiento.recibirParametros(
						this.users.devolverNombre(new usuarioDTO(this.user.getText(), passwordencriptada)),
						this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada)));

				this.scene3 = this.iniciar.getScene();
				this.root3.translateYProperty().set(this.scene3.getHeight());
				this.stackLogin.getChildren().add(this.root3);
				this.kv = new KeyValue(this.root3.translateYProperty(), 0, Interpolator.EASE_IN);
				this.kf = new KeyFrame(Duration.seconds(0.75), this.kv);
				this.timeline.getKeyFrames().add(this.kf);
				this.timeline.setOnFinished(t -> {
					this.stackLogin.getChildren().remove(this.anchorLogin);
				});
				this.timeline.play();
				break;

			case 4: // admin
				this.controllerAdmin.recibirParametros(
						this.users.devolverNombre(new usuarioDTO(this.user.getText(), passwordencriptada)),
						this.users.comprobarExistencia(new usuarioDTO(this.user.getText(), passwordencriptada)));

				this.scene4 = this.iniciar.getScene();
				this.root4.translateYProperty().set(this.scene4.getHeight());
				this.stackLogin.getChildren().add(this.root4);
				this.kv = new KeyValue(this.root4.translateYProperty(), 0, Interpolator.EASE_IN);
				this.kf = new KeyFrame(Duration.seconds(0.75), this.kv);
				this.timeline.getKeyFrames().add(this.kf);
				this.timeline.setOnFinished(t -> {
					this.stackLogin.getChildren().remove(this.anchorLogin);
				});
				this.timeline.play();
				break;

			default:
				this.area2.setText("No encontrado");
				break;
			}
		} else if (this.user.getText().isEmpty() && this.passwordField.getText().isEmpty() == false) {
			this.area2.setText("Rellene el usuario");
		} else if (this.user.getText().isEmpty() == false && this.passwordField.getText().isEmpty()) {
			this.area2.setText("Rellene la contraseña");
		} else {
			this.area2.setText("Rellene los campos");
		}
	}
}

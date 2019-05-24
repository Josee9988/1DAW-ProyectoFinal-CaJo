/**
 * @author Jose_Gracia, Carlos_Robles
 * @version May 24, 2019
 * @param args Recibe los datos del programa
 */

package controllerUtilidades;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import controllerIncidencias.consultar_incidencias;
import controllerMensajes.consultar_mensajes;
import controllerProveedores.consultar_proveedores;
import controllerUbicaciones.consultar_ubicaciones;
import controllerUsuarios.consultar_usuarios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class confirmar_controller {
	@FXML
	private Button eliminar;
	private Stage stage;
	private int clase;
	private int idSeleccionada;

	private consultar_usuarios consultar_usuarios;
	private consultar_ubicaciones consultar_ubicaciones;
	private consultar_proveedores consultar_proveedores;
	private consultar_mensajes consultar_mensajes;
	private consultar_incidencias consultar_incidencias;

	public confirmar_controller() {
		this.clase = 0;
		this.idSeleccionada = 0;
	}

	/**
	 * inicializar guarda el valor recibido el cuál referenciará a una clase en una
	 * variable global 0 = usuarios; 1 = ubicaciones; 2 = proveedores; 3 = mensajes;
	 * 4 = incidencias;
	 *
	 * @param clase          recibe un número entero refiriendose a la clase que es
	 *                       para que sepamos a qué métodos llamar
	 * @param idSeleccionada se nos pasa la id seleccionada en ese momento, ya que
	 *                       si cambiamos de view perdemos el focus seleccionado y
	 *                       no podemos ver a cuál estábamos seleccionando, por ello
	 *                       lo pasamos para poder utilizarlo al elimianr
	 */
	public void inicializar(int clase, int idSeleccionada) {
		this.clase = clase;
		this.idSeleccionada = idSeleccionada;
	}

	@FXML
	public void eliminar() throws SQLException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
			NoSuchAlgorithmException, NoSuchPaddingException {
		if (this.clase == 0) { // usuario
			this.consultar_usuarios = new consultar_usuarios();
			this.consultar_usuarios.eliminarUsuarioBD(this.idSeleccionada);
		} else if (this.clase == 1) { // ubicaciones
			this.consultar_ubicaciones = new consultar_ubicaciones();
		} else if (this.clase == 2) { // proveedores
			this.consultar_proveedores = new consultar_proveedores();
		} else if (this.clase == 3) { // mensajes
			this.consultar_mensajes = new consultar_mensajes();
		} else { // incidencias
			this.consultar_incidencias = new consultar_incidencias();
		}



		this.stage = (Stage) this.eliminar.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
	}

	@FXML
	/**
	 * si pulsa el botón cancelar símplemente se cerrará la ventana y nada pasará
	 */
	public void cancelar() {
		this.stage = (Stage) this.eliminar.getScene().getWindow(); // seleccionamos la escena actual
		this.stage.close(); // cerramos la ventana actual para pasar a la siguiente
	}

}

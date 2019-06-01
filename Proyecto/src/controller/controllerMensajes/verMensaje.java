package controller.controllerMensajes;

import java.sql.SQLException;

import dto.mensajesDTO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import model.jdbcUsuarioDAO;

public class verMensaje {

	@FXML
	TitledPane visionMensaje;
	@FXML
	TextArea cuerpo;
	@FXML
	TextArea emisor;
	@FXML
	TextArea receptor;
	@FXML
	TextArea asunto;

	private jdbcUsuarioDAO bdusuario;

	/**
	 * contructor que inicializa el jdbc de mensajes
	 */
	public verMensaje() {
		this.bdusuario = new jdbcUsuarioDAO();
	}

	/**
	 * se visializa un mensaje que se recibe por parametro
	 * 
	 * @param m mensaje a visualizar
	 * @throws SQLException
	 */
	public void inicializar(mensajesDTO m) throws SQLException {
		this.cuerpo.setEditable(false);
		this.emisor.setEditable(false);
		this.receptor.setEditable(false);
		this.asunto.setEditable(false);
		this.visionMensaje
				.setText("Viendo mensaje: " + m.getAsunto() + " de " + this.bdusuario.devolverNombre(m.getEmisor())
						+ " para " + this.bdusuario.devolverNombre(m.getReceptor()));
		this.cuerpo.setText(m.getCuerpo());
		this.asunto.setText(m.getAsunto());
		this.emisor.setText(m.getEmisorS());
		this.receptor.setText(m.getReceptorS());
	}
}

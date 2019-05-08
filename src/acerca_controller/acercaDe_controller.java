package acerca_controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class acercaDe_controller {
	
	@FXML
	private TextField campo1;
	@FXML
	private TextField campo2;
	@FXML
	private TextField campo3;
	@FXML
	private TextField campo4;
	@FXML
	private TextField campo5;
	
	public acercaDe_controller() {

	}
	
	public void inicializar() {
		this.campo1.setEditable(false);
		this.campo2.setEditable(false);
		this.campo3.setEditable(false);
		this.campo4.setEditable(false);
		this.campo5.setEditable(false);
	}
}


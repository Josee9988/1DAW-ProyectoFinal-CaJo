import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application {

	@Override
	public void start(Stage primaryStage) {
		Parent root;
		try {
			root = FXMLLoader.load(this.getClass().getResource("/view/login.fxml"));
			primaryStage.setScene(new Scene(root));

			Image icon = new Image(this.getClass().getResourceAsStream("/view/jc-favicon.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Proyecto Jose Carlos");
			primaryStage.show();

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

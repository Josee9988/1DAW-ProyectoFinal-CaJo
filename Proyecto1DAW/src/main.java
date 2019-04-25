import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class main extends Application {

	@Override
	public void start(Stage primaryStage) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ui2.fxml"));
			primaryStage.setScene(new Scene(root));
			Image icon = new Image(getClass().getResourceAsStream("/jc-favicon.png"));
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Componentes");
			primaryStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main.java
 * JavaFX entry point. Launches the GUI window.
 * Accepts optional CLI arg for startup CSV path.
 * @author [Name] [Date] T10
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// getResource path starts from resources/ root
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/view/MainView.fxml")
		);
		Scene scene = new Scene(loader.load(), 900, 600);
		primaryStage.setTitle("CPSC 219 - Movie & Series Database");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


}
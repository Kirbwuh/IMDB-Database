package main.java;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * Main launcher class.
 * Keeps JavaFX app in same file (no extra files).
 * @author [Name] [Date] T10
 */
public class Main {

	public static void main(String[] args) {
		Application.launch(JavaFxApp.class, args);
	}

	public static class JavaFxApp extends Application {

		@Override
		public void start(Stage primaryStage) throws Exception {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/view/MainView.fxml")
			);
			Scene scene = new Scene(loader.load(), 900, 600);

			MainController mainController = loader.getController();
			List<String> args = getParameters().getRaw();
			if (args.contains("--load")) {
				mainController.setLoadCsvOnStartup(true);
			}
			mainController.initializeStartupData();

			primaryStage.setTitle("CPSC 219 - Movie & Series Database");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
}
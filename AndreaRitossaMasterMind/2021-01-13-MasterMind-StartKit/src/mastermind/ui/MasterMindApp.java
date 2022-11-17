package mastermind.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mastermind.persistence.BadFileFormatException;
import mastermind.persistence.ConfigReader;
import mastermind.persistence.MyConfigReader;

public class MasterMindApp extends Application {

	private Controller controller;

	public void start(Stage stage) {
		try {
			ConfigReader configReader = new MyConfigReader(new FileReader("config.txt"));
			controller = new MyController(configReader.numeroTentativi(), configReader.dimensioneCodice(), "gameover.txt");
			System.out.println("Configuration read");
		} catch (FileNotFoundException | BadFileFormatException e) {
			controller = new MyController(5,4,"gameover.txt");
			System.out.println("Configuration not read, using defaults");
		}
		if (controller == null) {
			Controller.alert("Errore irrecuperabile", "Errore nella creazione del controller", "Addio");
			System.exit(1);
		}
		stage.setTitle("Master Mind");
		MasterMindPane root = new MasterMindPane(controller, stage);
		
		Scene scene = new Scene(root, 650, 300, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}


	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

}

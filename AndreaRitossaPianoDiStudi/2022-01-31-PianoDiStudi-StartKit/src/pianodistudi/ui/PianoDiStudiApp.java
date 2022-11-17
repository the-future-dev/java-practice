package pianodistudi.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pianodistudi.controller.Controller;
import pianodistudi.model.AttivitaFormativa;
import pianodistudi.model.CfuRange;
import pianodistudi.model.Ordinamento;
import pianodistudi.model.Ssd;
import pianodistudi.model.Tipologia;
import pianodistudi.persistence.MyAttivitaFormativeReader;


public class PianoDiStudiApp extends Application {
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Compilazione Piano di Studi");
		try {
				Map<String,AttivitaFormativa> mappaAF = new MyAttivitaFormativeReader().recuperaElenco(new FileReader("AttivitaFormative.txt"));
				//
				Controller controller = new Controller(ordinamento9254(), mappaAF);
				MainPane mainPanel = new MainPane(controller);
				//
				Scene scene = new Scene(mainPanel, 830, 700, Color.AQUAMARINE);
				stage.setScene(scene);
				stage.show();
				
		} catch (IOException e) {
			Controller.alert(
					"ERRORE DI I/O",
					"Errore di lettura: impossibile leggere i dati",
					"Dettagli: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			Controller.alert(
					"ERRORE DI I/O",
					"Formato dei file errato: impossibile leggere i dati",
					"Dettagli: " + e.getMessage());
		}
	}

	private Ordinamento ordinamento9254() {
		Ordinamento ord = new Ordinamento();
		ord.addItem(Tipologia.A1, new CfuRange(39, 51), Optional.of(List.of(
				Ssd.INF01, Ssd.INGINF05, Ssd.MAT02, Ssd.MAT03, Ssd.MAT05, Ssd.MAT06, Ssd.MAT08)));
		ord.addItem(Tipologia.A2, new CfuRange(9, 18), Optional.of(List.of(
				Ssd.FIS01, Ssd.FIS03, Ssd.CHIM07)));
		ord.addItem(Tipologia.B1, new CfuRange(6, 15), Optional.of(List.of(
				Ssd.INGINF01, Ssd.INGINF02, Ssd.INGINF07)));
		ord.addItem(Tipologia.B2, new CfuRange(48, 66), Optional.of(List.of(
				Ssd.INGINF05, Ssd.INGINF04)));
		ord.addItem(Tipologia.B3, new CfuRange(9, 15), Optional.of(List.of(
				Ssd.INGINF02, Ssd.INGINF03)));
		ord.addItem(Tipologia.C, new CfuRange(18, 30), Optional.of(List.of(
				Ssd.INGIND31, Ssd.INGIND35, Ssd.IUS14, Ssd.MAT07, Ssd.MAT09)));
		ord.addItem(Tipologia.D, new CfuRange(12, 18), Optional.empty());
		ord.addItem(Tipologia.E1, new CfuRange(3, 9), Optional.of(List.of(
				Ssd.PROVAFINALE)));
		ord.addItem(Tipologia.E2, new CfuRange(6, 6), Optional.of(List.of(
				Ssd.LINGUASTRANIERA)));
		ord.addItem(Tipologia.F, new CfuRange(6, 6), Optional.empty());
		
		return ord;
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}

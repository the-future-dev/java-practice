package pianodistudi.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pianodistudi.controller.Controller;
import pianodistudi.model.AttivitaFormativa;
import pianodistudi.model.CfuRange;
import pianodistudi.model.Ordinamento;
import pianodistudi.model.Ssd;
import pianodistudi.model.Tipologia;

public class PianoDiStudiAppMock extends Application {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Compilazione Piano di Studi - MOCK");

		Controller controller = new Controller(ordinamento9254(), creaMappaAF());
		MainPane mainPanel = new MainPane(controller);
		//
		Scene scene = new Scene(mainPanel, 830, 700, Color.AQUAMARINE);
		stage.setScene(scene);
		stage.show();
	}
	
	private Map<String, AttivitaFormativa> creaMappaAF() {
		// necessario suddividere in tre blocchi perché Map.of accetta al massimo 10 argomenti
		Map<String,AttivitaFormativa> mappaAF_anno1 = Map.of(
				"ANALISI MATEMATICA T-1", new AttivitaFormativa("ANALISI MATEMATICA T-1", Ssd.MAT05, 9),
				"ANALISI MATEMATICA T-2", new AttivitaFormativa("ANALISI MATEMATICA T-2", Ssd.MAT05, 6),
				"FONDAMENTI DI INFORMATICA T-1", new AttivitaFormativa("FONDAMENTI DI INFORMATICA T-1", Ssd.INGINF05, 12),
				"FONDAMENTI DI INFORMATICA T-2", new AttivitaFormativa("FONDAMENTI DI INFORMATICA T-2", Ssd.INGINF05, 12),
				"RETI LOGICHE T", new AttivitaFormativa("RETI LOGICHE T", Ssd.INGINF05, 6),		
				"GEOMETRIA E ALGEBRA T", new AttivitaFormativa("GEOMETRIA E ALGEBRA T", Ssd.MAT03, 6),
				"LINGUA INGLESE B-2", new AttivitaFormativa("LINGUA INGLESE B-2", Ssd.QUALSIASI, 6));
		Map<String,AttivitaFormativa> mappaAF_anno2 = Map.of(
				"FISICA GENERALE T", new AttivitaFormativa("FISICA GENERALE T", Ssd.FIS01, 9),
				"MATEMATICA APPLICATA T", new AttivitaFormativa("MATEMATICA Applicata T", Ssd.MAT07, 6),
				"CALCOLATORI ELETTRONICI T", new AttivitaFormativa("CALCOLATORI ELETTRONICI T", Ssd.INGINF05, 6),
				"SISTEMI INFORMATIVI T", new AttivitaFormativa("SISTEMI INFORMATIVI T", Ssd.INGINF05, 9),
				"SISTEMI OPERATIVI T", new AttivitaFormativa("SISTEMI OPERATIVI T", Ssd.INGINF05, 9),
				"FONDAMENTI DI TELECOMUNICAZIONI T", new AttivitaFormativa("FONDAMENTI DI TELECOMUNICAZIONI T", Ssd.INGINF03, 9),
				"ELETTROTECNICA T", new AttivitaFormativa("ELETTROTECNICA T", Ssd.INGIND31, 6),
				"ECONOMIA E ORGANIZZAZIONE AZIENDALE T", new AttivitaFormativa("ECONOMIA E ORGANIZZAZIONE AZIENDALE T", Ssd.INGIND35, 6));
		Map<String,AttivitaFormativa> mappaAF_anno3 = Map.of(
				"ELETTRONICA T", new AttivitaFormativa("ELETTRONICA T", Ssd.INGINF01, 6),
				"CONTROLLI AUTOMATICI T", new AttivitaFormativa("CONTROLLI AUTOMATICI T", Ssd.INGINF04, 9),
				"RETI DI CALCOLATORI T", new AttivitaFormativa("RETI DI CALCOLATORI T", Ssd.INGINF05, 9),
				"TECNOLOGIE WEB T", new AttivitaFormativa("TECNOLOGIE WEB T", Ssd.INGINF05, 9),
				"INGEGNERIA DEL SOFTWARE T", new AttivitaFormativa("INGEGNERIA DEL SOFTWARE T", Ssd.INGINF05, 9),
				//
				"TIROCINIO T", new AttivitaFormativa("TIROCINIO T", Ssd.QUALSIASI, 6),
				"PROVA FINALE T", new AttivitaFormativa("PROVA FINALE T", Ssd.QUALSIASI, 3));
		Map<String,AttivitaFormativa> mappaAF_scelte = Map.of(
				"AMMINISTRAZIONE DI SISTEMI T", new AttivitaFormativa("AMMINISTRAZIONE DI SISTEMI T", Ssd.INGINF05, 6),
				"DIRITTO DELL'INFORMATICA T", new AttivitaFormativa("DIRITTO DELL'INFORMATICA T", Ssd.IUS14, 6),
				"AFFIDABILITA' E CONTROLLO DELLA QUALITA' T", new AttivitaFormativa("AFFIDABILITA' E CONTROLLO DELLA QUALITA' T", Ssd.INGINF07, 6),
				"AMMINISTRAZIONE DI SISTEMI E SICUREZZA T", new AttivitaFormativa("AMMINISTRAZIONE DI SISTEMI E SICUREZZA T", Ssd.INGINF05, 12),
				"LABORATORIO DI SICUREZZA INFORMATICA T", new AttivitaFormativa("LABORATORIO DI SICUREZZA INFORMATICA T", Ssd.INGINF05, 6),
				"PROGETTAZIONE DI APPLICAZIONI WEB T", new AttivitaFormativa("PROGETTAZIONE DI APPLICAZIONI WEB T", Ssd.INGINF05, 6)
				);
		Map<String,AttivitaFormativa> mappaAF = new HashMap<>();
		mappaAF.putAll(mappaAF_anno1);
		mappaAF.putAll(mappaAF_anno2);
		mappaAF.putAll(mappaAF_anno3);
		mappaAF.putAll(mappaAF_scelte);
		return mappaAF;
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
				Ssd.QUALSIASI)));
		ord.addItem(Tipologia.E2, new CfuRange(6, 6), Optional.of(List.of(
				Ssd.QUALSIASI)));
		ord.addItem(Tipologia.F, new CfuRange(6, 6), Optional.empty());
		
		return ord;
	}

	public static void main(String[] args) {
		launch(args);
	}
	

}

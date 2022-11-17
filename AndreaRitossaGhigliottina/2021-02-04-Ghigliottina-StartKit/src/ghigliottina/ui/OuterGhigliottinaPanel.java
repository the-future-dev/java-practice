package ghigliottina.ui;

import java.text.NumberFormat;

import ghigliottina.model.Ghigliottina;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class OuterGhigliottinaPanel extends BorderPane {
 
	private GhigliottinaPanel gPanel;
	private TextField txtRispostaUtente, txtRispostaEsatta;
	private Label rightLabel, leftLabel;
	private Button svela;
	private String rispostaEsatta;
	private Controller controller;
	private Ghigliottina gh;
	private int montepremi;
	
	public OuterGhigliottinaPanel(int montepremi, Controller controller) {
		this.controller=controller;
		this.montepremi=montepremi;
		setupGhigliottinaPanel();
		rispostaEsatta = gh.getRispostaEsatta().toString();
		//
		HBox revealBox = new HBox();
		revealBox.setAlignment(Pos.CENTER);
		revealBox.setStyle("-fx-background-color: blue;");
		
		VBox parola = new VBox();
			rightLabel = new Label("La parola segreta:");
			rightLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");
//			rightLabel.setAlignment(Pos.CENTER);
			txtRispostaEsatta = new TextField();
			txtRispostaEsatta.setDisable(true);
		parola.getChildren().addAll(rightLabel, txtRispostaEsatta);
		
		VBox risposta = new VBox();
			leftLabel = new Label("La tua risposta");
			leftLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ffffff;");
//			leftLabel.setAlignment(Pos.CENTER);
			txtRispostaUtente = new TextField();
			risposta.getChildren().addAll(leftLabel, txtRispostaUtente);
		svela = new Button("SVELA");
		svela.setOnAction(this::unveils);
		svela.setStyle("-fx-background-color: #ffffff; -fx-font-weight: bold; -fx-text-fill: red");
		revealBox.getChildren().addAll(parola, risposta, svela);
		this.setTop(revealBox);
	}
	
	private void setupGhigliottinaPanel() {
		// initial setup
		gh = controller.sorteggiaGhigliottina();
		this.rispostaEsatta=gh.getRispostaEsatta();
		gPanel = new GhigliottinaPanel(montepremi, gh.getTerne());
		this.setBottom(gPanel);
	}
	
	private void reset() {
		setupGhigliottinaPanel();
		txtRispostaUtente.setText("");
		txtRispostaEsatta.setText("");
	}
	
	private void unveils(ActionEvent e) {
		String rispostaUtente = txtRispostaUtente.getText();
		if (rispostaUtente == null)
			GhigliottinaPanel.alert("Nessuna risposta?", "NON HAI INSERITO NESSUNA RISPOSTA", "sparala a caso, � meglio!");
		else {
			boolean indovinato = gh.getRispostaEsatta().toUpperCase().equals(rispostaUtente.toUpperCase());
			if (indovinato) {
				alert("Hai vinto!", "Montepremi: "+montepremiAsString(), "");
			} else {
				txtRispostaEsatta.setText(rispostaEsatta);
				alert("Risposta Sbagliata", "Carissimo, hai perso: "+montepremiAsString(), "La parola era: "+rispostaEsatta);
			}
			reset();
		}
	}
	
	private String montepremiAsString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		formatter.setMaximumFractionDigits(0);
		return formatter.format(montepremi);
	}
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public static void info(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

}

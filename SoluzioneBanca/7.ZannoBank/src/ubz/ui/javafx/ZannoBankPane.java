package ubz.ui.javafx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ubz.model.ImpossibleWithdrawException;
import ubz.model.Prelievo;
import ubz.model.Taglio;
import ubz.ui.controller.Controller;

public class ZannoBankPane extends BorderPane {

	private Controller controller;
	private TextField[] labels, labels2, disponibilit‡Fields, prelievoFields;
	private TextField importoField;
	private Button conferma;

	public ZannoBankPane(Controller controller) {
		this.controller = controller;
		Taglio[] tagli = Taglio.values();
		int tagliCount = tagli.length;
		labels = new TextField[tagliCount];
		labels2 = new TextField[tagliCount];
		disponibilit‡Fields = new TextField[tagliCount];
		prelievoFields = new TextField[tagliCount];
		importoField = new TextField();
		importoField.setAlignment(Pos.CENTER);
		importoField.setFont(Font.font(importoField.getFont().getFamily(), FontWeight.BOLD, 16));
		conferma = new Button("Preleva");
		
		// -- predisposizione textfields e labels
		for (int i = 0; i < tagliCount; ++i){
			Taglio t = tagli[i];
			labels[i] = new TextField("Ä " + t.getValore());
			labels[i].setPrefWidth(60);
			labels[i].setAlignment(Pos.BASELINE_CENTER);
			labels[i].setEditable(false);
			disponibilit‡Fields[i] = new TextField();
			disponibilit‡Fields[i].setPrefWidth(60);
			disponibilit‡Fields[i].setAlignment(Pos.BASELINE_CENTER);
			disponibilit‡Fields[i].setEditable(false);
			//
			labels2[i] = new TextField("Ä " + t.getValore());
			labels2[i].setPrefWidth(60);
			labels2[i].setAlignment(Pos.BASELINE_CENTER);
			labels2[i].setEditable(false);
			prelievoFields[i] = new TextField(); 
			prelievoFields[i].setPrefWidth(60);
			prelievoFields[i].setAlignment(Pos.BASELINE_CENTER);
			prelievoFields[i].setEditable(false);
		}
		VBox cassaPane = new VBox();
		TilePane labelsPane = new TilePane(); labelsPane.setPrefRows(Taglio.values().length);
		TilePane dispoPane = new TilePane();  dispoPane.setPrefRows(Taglio.values().length);
		labelsPane.getChildren().addAll(labels);
		dispoPane.getChildren().addAll(disponibilit‡Fields);
		cassaPane.getChildren().addAll(
				new Label("Tagli banconote e monete"), 
				labelsPane,
				new Label("Quantit‡ disponibili in cassa"),
				dispoPane);
		this.setTop(cassaPane);
		aggiornaDisponibilit‡();
		
		HBox importoPane = new HBox();
		importoPane.setAlignment(Pos.CENTER);
		importoPane.setPrefHeight(USE_COMPUTED_SIZE*2);
		importoPane.getChildren().add(new Label("Importo da prelevare"));
		importoPane.getChildren().addAll(importoField);
		importoPane.getChildren().add(conferma);
		this.setCenter(importoPane);
		
		VBox prelievoPane = new VBox();
		TilePane labelsPane2 = new TilePane(); labelsPane2.setPrefRows(Taglio.values().length);
		TilePane prelPane = new TilePane();  prelPane.setPrefRows(Taglio.values().length);
		labelsPane2.getChildren().addAll(labels2);
		prelPane.getChildren().addAll(prelievoFields);
		prelievoPane.getChildren().addAll(
				new Label("Tagli banconote e monete"), 
				labelsPane2,
				new Label("Prelievo"),
				prelPane);
		this.setBottom(prelievoPane);		
		
		conferma.setOnAction(event -> {
				try {
					Prelievo p = controller.preleva( Integer.parseInt(importoField.getText()) );
					aggiornaPrelievo(p);
				}
				catch(ImpossibleWithdrawException e){
					controller.alert("Errore", "Prelievo non possibile", e.getMessage());
				}
				aggiornaDisponibilit‡();
			});
	}
		

	private void aggiornaPrelievo(Prelievo prelievo) {
		for (Taglio t : Taglio.values()){
			prelievoFields[t.ordinal()].setText(""+prelievo.getQuantit‡(t));
		}
	}


	private void aggiornaDisponibilit‡(){
		for (Taglio t : Taglio.values()){
			disponibilit‡Fields[t.ordinal()].setText(""+controller.getDisponibilit‡Attuale(t));
		}
	}

}

package rfd.ui;

import java.util.List;

//import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import rfd.controller.Controller;
import rfd.model.Route;


public class MainPane extends BorderPane {
	
	private TextArea outputArea; 
	private ComboBox<String> comboFrom, comboTo;
	private Controller controller;
	private CheckBox senzaCambi, unCambio;

	public MainPane(Controller controller) {
		this.controller=controller;
		HBox topBox = new HBox();
		topBox.setPrefHeight(40);
		// populate combos
		comboFrom = new ComboBox<>(); populateCombo(comboFrom);
		comboTo   = new ComboBox<>(); populateCombo(comboTo);
		comboFrom.setOnAction(this::reset);
		comboTo.setOnAction(this::reset);
		topBox.getChildren().addAll(new Label("Partenza "), comboFrom, new Label("  Arrivo "), comboTo);
		this.setTop(topBox);
		//
		HBox centerBox = new HBox();
		centerBox.setPrefHeight(80);
		centerBox.setAlignment(Pos.CENTER);
		senzaCambi = new CheckBox("senza cambi");
		unCambio = new CheckBox("con un cambio");
		centerBox.getChildren().addAll(new Label("Mostra soluzioni:    "), senzaCambi, new Label("   "), unCambio);
		this.setCenter(centerBox);
		senzaCambi.setOnAction(this::myHandle); 
		unCambio.setOnAction(this::myHandle); 
		outputArea = new TextArea();
		outputArea.setPrefSize(500,300);
		outputArea.setFont(Font.font("Courier New", 12));
		this.setBottom(outputArea);
	}

	private void populateCombo(ComboBox<String> combo) {
		// DA FARE l’elenco alfabetico ordinato di tutte le stazioni
		combo.setItems(
				FXCollections.observableArrayList(
						controller.getStationNames()));
		combo.autosize();
	}
	
	
	private void reset(ActionEvent event) {
		outputArea.setText("");
		senzaCambi.setSelected(false);
		unCambio.setSelected(false);
	}
	
	protected void myHandle(ActionEvent event) {
		String from = comboFrom.getValue();
		String to = comboTo.getValue();
		outputArea.setText("");
		if (senzaCambi.isSelected()) {
			findDirectRoutes(from, to);
		}
		if (unCambio.isSelected()) {
			findIndirectRoutes(from, to);
		}
	}
	
	protected void findDirectRoutes(String from, String to) {
		List<Route> lr = controller.getDirectRoutes(from, to);
		if (lr.size() != 0) {
			String str = outputArea.getText()+"\n PERCORSI DIRETTI SENZA CAMBI";
			for (Route r: lr) {
				str+= "\n"+r.toString();
			}
			outputArea.setText(str);
		}
	}
	
	protected void findIndirectRoutes(String from, String to) {
		List<Route> lr = controller.getIndirectRoutes(from, to);
		if (lr.size() != 0) {
			String str = outputArea.getText()+"\n PERCORSI INDIRETTI CON CAMBI";
			for (Route r: lr) {
				str+= "\n"+r.toString();
			}
			outputArea.setText(str);
		}
	}

}

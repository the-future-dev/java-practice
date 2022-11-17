package bussy.ui;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.SortedSet;

import bussy.model.Percorso;
import bussy.ui.controller.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainPane extends BorderPane {
	
	private Button button;
	
	private ComboBox<String> comboA;
	private ComboBox<String> comboDa;
	private Label a, da;
	
	private Controller controller;
	private TextArea output;
	
	public MainPane(Controller controller) {
		this.controller = controller;
		
		button = new Button("Cerca Percorso");
		button.setOnAction(this::myHandle);
		
		a = new Label("A");
		da = new Label("Da");
		comboDa = new ComboBox<>(controller.getNomiFermate());
		comboA = new ComboBox<>(controller.getNomiFermate());
		HBox top = new HBox();
		top.getChildren().addAll(da, comboDa, a, comboA);
		
		output = new TextArea();
		
		this.setTop(top);
		this.setCenter(output);
		this.setBottom(button);
	}
	
	public void myHandle(ActionEvent e) {
		
		Optional<String> a = Optional.of(comboA.getValue());
		Optional<String> da = Optional.of(comboDa.getValue());
		String display = "";
		if (a.isEmpty() || da.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			display = "";
		} else {
			SortedSet<Percorso> p = controller.cercaPercorsi(da.get(), a.get(), OptionalInt.of(7200));
			if (p.isEmpty()) {
				display = "Nessun percorso trovato";
			} else {
				for(Percorso w : p) {
					display += w.toString()+"\n";
				}
			}
		}
		output.setText(display);
	}
}

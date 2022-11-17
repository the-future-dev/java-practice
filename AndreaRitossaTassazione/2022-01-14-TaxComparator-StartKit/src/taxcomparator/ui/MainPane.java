package taxcomparator.ui;

import java.text.DecimalFormat;
//import java.text.NumberFormat;
import java.text.ParseException;
//import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import taxcomparator.controller.Controller;
import taxcomparator.model.Fasce;


public class MainPane extends BorderPane {
	private DecimalFormat df = new DecimalFormat("¤ #,##0.##");
	// DA COMPLETARE

	private CurrencyTextField reddito, imposta;
	private TextArea dettaglio;
	private Button calcola;
	private ComboBox<Fasce> combo;
	private Controller controller;
	
	public MainPane(Controller controller) {
		this.controller=controller;
		VBox topBox = new VBox();
		reddito = new CurrencyTextField();
		HBox buttonBox = new HBox();
		combo = new ComboBox<>();
		combo.setItems(FXCollections.observableArrayList(controller.getListaFasceDisponibili()));
		combo.getSelectionModel().selectFirst();
		calcola = new Button("Calcola");
		calcola.setOnAction(this::calcolaImposta);
		buttonBox.getChildren().addAll(combo, calcola);
		buttonBox.setAlignment(Pos.CENTER);
		topBox.getChildren().addAll(new Label("Reddito imponibile: "), reddito, buttonBox);
		
		VBox center = new VBox();
		imposta = new CurrencyTextField();
		imposta.setEditable(false);
		dettaglio = new TextArea();
		dettaglio.setEditable(false);
		center.getChildren().addAll(
				new Label("Imposta Dovuta"),
				imposta,
				new Label("Dettaglio"), 
				dettaglio);
		
		this.setTop(topBox);
		this.setCenter(center);
		
		// AGGANCIO GESTIONE EVENTO DA FARE
	}

	//
	// METODO DI GESTIONE DELL'EVENTO
	//
	public void calcolaImposta(ActionEvent e) {	
		try {
//			System.out.println(reddito.getText());
			Number cashToTax= df.parse(reddito.getText());
//			System.out.println(" "+cashToTax.doubleValue());
			controller.setFasce(combo.getValue());
			double imposte = controller.calcolaImposte(cashToTax.doubleValue());
			String log = controller.getLog();
		
			this.imposta.setText(df.format(imposte));
			this.dettaglio.setText(log);
		}catch (ParseException exception) {
				TaxComparatorApp.alert("", "no bro", "");
		}
	}
}

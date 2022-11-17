package ubz.ui.javafx;

import java.text.NumberFormat;
//import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import ubz.model.ImpossibleWithdrawException;
import ubz.model.Prelievo;
import ubz.model.Taglio;
import ubz.ui.controller.Controller;

public class ZannoBankPane extends BorderPane {
	private final NumberFormat fV = NumberFormat.getCurrencyInstance(Locale.ITALY);;
	
	private Controller controller;
	private HBox cashInBank;
	private TextField daPrelevare;
	private Button outTheMoney;
	private TextField [] prelievoEffettivo;
	private Prelievo thePrelievo;
	
	private HBox populateDispCash() {
		HBox cash = new HBox();
		for (Taglio t : Taglio.values()) {
			TextField f = new TextField("");
			f.setEditable(false);
			f.setText(""+controller.getDisponibilit�Attuale(t));
			f.setPrefSize(50, 30);
			cash.getChildren().add(f);
		}
		return cash;
	}
	private void populatePrelievo() {
		HBox cash = new HBox();
		for (Taglio t : Taglio.values()) {
			TextField f = new TextField("");
			f.setEditable(false);
			f.setText(""+thePrelievo.getQuantit�(t));
			f.setPrefSize(50, 30);
			cash.getChildren().add(f);
		}
		return cash;
	}
	
	public ZannoBankPane(Controller c) {
		this.controller = c;
		fV.setMaximumFractionDigits(0);
		
		Map<Taglio, Integer> w = new TreeMap<>();
		for (Taglio t : Taglio.values()){
			w.put(t, 0);
		}
		thePrelievo = new Prelievo(w);
		
		TilePane topPanel = new TilePane();
		HBox tagliContainerTop = new HBox();
		for (Taglio t : Taglio.values()) {
			TextField f = new TextField("");
			f.setEditable(false);
			f.setText(fV.format(t.getValore()));
			f.setPrefSize(50, 30);
			tagliContainerTop.getChildren().add(f);
		}
		cashInBank = populateDispCash();
		topPanel.getChildren().addAll(
				new Label("Tagli banconote e monete"),
				tagliContainerTop,
				new Label("Quantit� disponibili in cassa"),
				cashInBank);
		
//		 Al centro, un campo di testo (con testo centrato e font bold 16
//		punti) consente di inserire l�importo da prelevare
		HBox mid = new HBox();
		daPrelevare = new TextField();
		daPrelevare.setPrefSize(100, 40);
		outTheMoney = new Button("Preleva");
		
		outTheMoney.setOnAction(arg0 -> {
			try {
				MyHandlerPrelievo(arg0);
			} catch (ImpossibleWithdrawException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		mid.getChildren().addAll(
				new Label("Importo da prelevare: \t"),
				daPrelevare,
				outTheMoney);
		mid.setAlignment(Pos.CENTER);
		
//		....
		TilePane botPanel = new TilePane();
		HBox tagliContainerBot = new HBox();
		for (Taglio t : Taglio.values()) {
			TextField f = new TextField("");
			f.setEditable(false);
			f.setText(fV.format(t.getValore()));
			f.setPrefSize(50, 30);
			tagliContainerBot.getChildren().add(f);
		}
		TilePane prelievo = new TilePane();
		prelievoEffettivo
		
		botPanel.getChildren().addAll(
				new Label("Tagli banconote e monete"),
				tagliContainerBot,
				new Label("Prelievo"),
				prelievo);
		
		this.setTop(topPanel);
		this.setCenter(mid);
		this.setBottom(botPanel);
	}
	
	public void MyHandlerPrelievo(ActionEvent e) throws ImpossibleWithdrawException {
		try {
			int money = Integer.parseInt(daPrelevare.getText());
//			Prelievo p = controller.preleva(money);
			cashInBank = populateDispCash();
			controller.preleva(money);
			
		} catch(NumberFormatException error1) {
			this.alert("", error1.getLocalizedMessage(), "");
		}
//		catch(ImpossibleWithdrawException error3) {
//			this.alert("Error",error3.getLocalizedMessage(), "");
//		}
		
	}
	
	public void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}

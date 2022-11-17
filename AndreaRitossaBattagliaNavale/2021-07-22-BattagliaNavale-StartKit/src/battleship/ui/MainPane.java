package battleship.ui;

import battleship.controller.Controller;
import battleship.model.ShipItem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainPane extends BorderPane {
	private ComboBox<ShipItem> combo;
	private Controller controller;
	private BattleshipGrid grid;
	private Button verifica;
	
	public MainPane(Controller controller, Stage stage) {
		if (controller == null)
			throw new IllegalArgumentException("nullable controller");
		
		this.controller = controller;
		
		TilePane left = new TilePane();

		combo = new ComboBox<>();
		combo.setItems(
				FXCollections.observableArrayList(ShipItem.values())
			);
		Label lCombo = new Label("Scegli un elemento della combo e premi il pulsante della griglia");
		Label lButton = new Label("Elementi da inserire: \n ... \n ... \n");
		verifica = new Button("VERIFICA");
		verifica.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		verifica.setOnAction(this::myHandle);
		left.getChildren().addAll(lCombo, combo, lButton, verifica);
		
		grid = new BattleshipGrid(controller, combo);
		
		this.setCenter(grid);
		this.setLeft(left);
	}
	
	public void myHandle(ActionEvent e) {
		int wrong = controller.verify();
		if (wrong != 0)
			Controller.alert("Hei!", "Ci sono "+wrong+" celle errate!", "");
		else if(wrong == 0 && controller.isGameOver()) {
			Controller.alert("Game over", "The game is over","");			
		} else
			Controller.alert("", "Tutte le celle sono corrette","");
		
		
	}
}

package minesweeper.ui.javafx;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import minesweeper.ui.controller.Controller;

public class MinesSweeperPane extends BorderPane{
	private Controller controller;
	private MinesSweeperGrid grid;
	private TextArea output;
	private Button restart;
	private Button save;
	
	public MinesSweeperPane(Controller c, Stage s) {
		controller = c;
		
		Label nMines = new Label("Mines: "+controller.getMinesNumber());
		
		output = new TextArea();
		
		grid = new MinesSweeperGrid(controller, output);
		
		HBox bottom = new HBox();
		restart = new Button("Restart");
		restart.setOnAction(this::restarter);
		save = new Button("Save status");
		save.setOnAction(this::saver);
		bottom.getChildren().addAll(save, restart);
		
		this.setTop(nMines);
		
		this.setRight(output);
		this.setCenter(grid);
		
		this.setBottom(bottom);
		
	}
	
	private void restarter(ActionEvent e) {
		controller.restart();
		output.setText("");
		grid = new MinesSweeperGrid(controller, output);
		this.setCenter(grid);
	}
	
	private void saver(ActionEvent e) {
		controller.save();
		
	}
}

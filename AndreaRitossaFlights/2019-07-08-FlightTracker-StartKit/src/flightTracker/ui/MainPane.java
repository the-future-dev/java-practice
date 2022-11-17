package flightTracker.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.Reader;
import java.util.Random;

import flightTracker.model.Flight;
import flightTracker.model.Point;
import flightTracker.persistence.BadFileFormatException;
//import flightTracker.persistence.FlightReader;
import flightTracker.ui.controller.Controller;
//import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainPane extends BorderPane {
	private GeoCanvas canvas;
	private CheckBox checkbox;
	private Controller controller;
	private ComboBox<String> flightCombo;
	
	public MainPane(GeoMap g, Controller c) {
		this.canvas = new GeoCanvas(g);
		this.controller = c;
		
		HBox top = new HBox();
		flightCombo = new ComboBox<>();
		flightCombo.setItems(
				controller.getFlights()
			);
		flightCombo.setOnAction(this::myHandler);
		flightCombo.setTooltip(new Tooltip("Scegliere il volo da graficare"));
		checkbox = new CheckBox();
		top.getChildren().addAll(new Label("Voli Disponibili: "), flightCombo, checkbox, new Label("Voli multipli"));
		Point p1;
		try {
			p1 = controller.load(controller.getFlights().get(0), new FileReader(controller.getFlights().get(0))).getPositions().get(0).getPosition();
			canvas.drawParallel((int) p1.getY());
		} catch (IOException | BadFileFormatException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		this.setCenter(canvas);
		this.setTop(top);
	}
	public void myHandler(ActionEvent e) {
		if (!checkbox.isSelected()) {
			canvas.redrawMap();
		} else {
			Random r = new Random();
			Color c = new Color(r.nextDouble(), r.nextDouble(), r.nextDouble(), 1);
			canvas.setDrawingColor(c);
		}
		FileReader r;
		Flight f = null;
		try {
			r = new FileReader(flightCombo.getValue());
		
			f = controller.load(flightCombo.getValue(), r);
		
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadFileFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (f != null) {
			canvas.drawPoints(controller.getPoints(f));
		}
		
	}
	
//	private void plotFlight(Flight f) {
//		
//	}
}

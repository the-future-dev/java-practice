package elections.ui.javafx;

import java.io.IOException;

import elections.model.Elezioni;
import elections.model.Partito;
import elections.ui.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ElectionPane extends BorderPane {

	private Slider sbarramento;
	private TextArea electionTable;
	private Controller controller;
	private Button calcola, salva;
	private FlowPane rightPane;
	private PieChart chart;

	public ElectionPane(Controller controller) {

		this.controller = controller;

		electionTable = new TextArea();
		electionTable.setEditable(false);
		electionTable.setPrefWidth(500);
		electionTable.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		electionTable.setText("");
		this.setLeft(electionTable);

		HBox sbarramentoPane = new HBox();
		sbarramentoPane.setAlignment(Pos.CENTER_LEFT);
		sbarramentoPane.getChildren().add(new Label("Sbarramento %"));
		sbarramento = new Slider(0, 10, 0);
		sbarramento.setPrefWidth(300);
		sbarramento.setShowTickMarks(true);
		sbarramento.setShowTickLabels(true);
		sbarramento.setMajorTickUnit(1);
		sbarramento.setSnapToTicks(true);
		sbarramento.setBlockIncrement(0.5);
		sbarramentoPane.getChildren().add(sbarramento);
		this.setTop(sbarramentoPane);

		calcola = new Button("Calcola");
		calcola.setOnAction(this::calcolaSeggi);
		TilePane commandPane = new TilePane();
		commandPane.setAlignment(Pos.CENTER);
		salva = new Button("Salva");
		salva.setDisable(true);
		salva.setOnAction(this::salva);
		commandPane.getChildren().addAll(calcola, salva);
		this.setBottom(commandPane);
		rightPane = new FlowPane();
		this.setRight(rightPane);
		
		electionTable.setText(controller.getElezioni().toString());
	}

	private void calcolaSeggi(ActionEvent event) {
		Elezioni elezioni = controller.ricalcola(sbarramento.getValue() / 100.0);
		electionTable.setText(elezioni.toString());
		salva.setDisable(false);
		ridisegnaGrafico(elezioni);
	}

	private void ridisegnaGrafico(Elezioni elezioni) {
		ObservableList<PieChart.Data> dati = FXCollections.observableArrayList();
		for (Partito p : elezioni.getPartiti()) {
			long seggi = elezioni.getRisultato().getSeggi(p);
			if (seggi > 0) {
				dati.add(new PieChart.Data(p.getNome(), seggi));
			}
		}
		rightPane.getChildren().remove(chart);
		chart = new PieChart(dati);
		chart.setLabelsVisible(false);
		chart.setTitle("Distribuizione seggi");
		chart.setPrefWidth(60);
		rightPane.getChildren().add(chart);
	}

	private void salva(ActionEvent event) {
		try {
			controller.salvaSuFile("Proporzionale con sbarramento del " + sbarramento.getValue() + "%");
			salva.setDisable(true);
		} catch (IOException e1) {
			controller.alert("Errore nel salvataggio", "Impossibile scrivere su file",
					"Non è stato possibile aprire il file o scrivere su di esso");
			System.err.println(e1);
			return;
		}
	}

}

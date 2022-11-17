package dentinia.governor.ui.javafx;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;

import dentinia.governor.model.Elezioni;
import dentinia.governor.model.Partito;
import dentinia.governor.model.RisultatoElezioni;
import dentinia.governor.ui.controller.Controller;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ElectionPane extends BorderPane {
	private Button calcola;
	private PieChart chart;
	private Controller controller;
	private TextArea electionTable;
	private TextArea percentageTable;
	private FlowPane rightPane;
	private Button salva;
	private Slider sbarramento;
	
	public ElectionPane(Controller c) {
		this.controller= c;
		
		HBox top = new HBox();
		Label topL = new Label("Sbarramento %	");
		sbarramento = new Slider(0, 10, 0);
		sbarramento.setPrefWidth(100);
		sbarramento.setShowTickMarks(true);
		sbarramento.setShowTickLabels(true);
		sbarramento.setMajorTickUnit(1);
		sbarramento.setSnapToTicks(true);
		sbarramento.setBlockIncrement(0.5);

		//		sbarramento.valueProperty().addListener(this::calcolaSeggi);
		top.getChildren().addAll(topL, sbarramento);
		top.setAlignment(Pos.CENTER_LEFT);
		
		rightPane = new FlowPane();
		chart = new PieChart();
		disegnaGrafico(controller.getElezioni());
		
		FlowPane mid = new FlowPane();
		electionTable = new TextArea();
		percentageTable = new TextArea();
		electionTable.setPrefWidth(300);
		percentageTable.setPrefWidth(300);
		
		percentageTable.setEditable(false);
		electionTable.setEditable(false);
		
		electionTable.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		percentageTable.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		
		percentageTable.setText(calcolaPercentuali(controller.getElezioni()));
		electionTable.setText(controller.getElezioni().toString());
		
		mid.getChildren().addAll(electionTable, percentageTable);
		
		HBox bottom = new HBox();
		calcola = new Button("Calcola");
		calcola.setOnAction(this::calcolaSeggi);
		salva = new Button("Salva");
		salva.setDisable(true);
		salva.setOnAction(this::salva);
		bottom.getChildren().addAll(calcola, salva);
		bottom.setAlignment(Pos.CENTER);
		
		this.setBottom(bottom);
		this.setCenter(mid);
		this.setRight(rightPane);
		this.setTop(sbarramento);
		
		disegnaGrafico(controller.getElezioni());
	}
	
	private void salva(ActionEvent e) {


	}
	
	private void disegnaGrafico(Elezioni elezioni) {
		ObservableList<PieChart.Data> dati = FXCollections.observableArrayList();
		for (Partito p : elezioni.getPartiti()) {
			long seggi = elezioni.getRisultato().getSeggi(p);
			if (seggi > 0) {
				dati.add(
					new PieChart.Data(p.getNome(), seggi)
				);
			}
		}
		rightPane.getChildren().remove(chart);
		chart = new PieChart(dati);
		chart.setLabelsVisible(false);
		chart.setTitle("Distribuizione seggi");
		rightPane.getChildren().add(chart);
	}
	
	public void calcolaSeggi(ActionEvent e) {
		Elezioni elezioni = controller.ricalcola(sbarramento.getValue() / 100.0);
		electionTable.setText(elezioni.toString());
		percentageTable.setText(calcolaPercentuali(elezioni));
		salva.setDisable(false);
		disegnaGrafico(elezioni);
	}

	private String calcolaPercentuali(Elezioni elezioni) {
		RisultatoElezioni re = elezioni.getRisultato();
		Long totSeggi = re.getSeggiTotali();
		Long totVoti = elezioni.getVotiTotali();
		NumberFormat formatter = NumberFormat.getPercentInstance(Locale.ITALY);
		String str = "Valutazione in percentuale\n";
		
		for (Partito p : re.getPartiti()) {
			Long votiP = elezioni.getVoti(p);
			Long seggiP = re.getSeggi(p);
			double voti = votiP.doubleValue()/totVoti.doubleValue();
			double seggi = seggiP.doubleValue()/totSeggi.doubleValue();
			str+= p.getNome()+"\t\tVoti: "+formatter.format(voti)+"\t\t\t"+"Seggi: "+formatter.format(seggi)+"\n";
		}
		
		return str;
	}
}

package qa.ui.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import qa.model.Checker;
import qa.model.Misura;
import qa.model.WeightChecker;
import qa.persistence.BadFileFormatException;
import qa.persistence.MisureReader;

public class Controller {
	private List<Misura> list;
	private Checker checker;
	private DialogManager dialogManager;

	public Controller(MisureReader myReader, Reader reader, DialogManager dialogManager) throws IOException, BadFileFormatException {
		list = myReader.leggiMisure(reader);
		checker = new WeightChecker(list);
		this.dialogManager = dialogManager;
	}

	public Checker getChecker() {
		return checker;
	}

	public void alert(String title, String header, String content) {
		dialogManager.alert(title, header, content);
	}

	public ObservableList<String> getDescrizioni() {
		return FXCollections.observableList(
				checker.getTabellaMisure().keySet().stream().collect(Collectors.toList()));	
	}
	
	public Map<String, Double> getTabellaPercentuali() {
		return checker.getTabellaPercentuali();
	}

	public String printTabellaPercentuali() {
		return checker.printTabellaPercentuali();
	}

	public List<Misura> getMisure(String descrizione) {
		return checker.getMisure(descrizione);
	}
	
	public List<Misura> getListaProdottiEntroRange(String descrizione){
		return checker.getListaMisureInRange(descrizione);
	}
	
	public double getPercentualeProdottiEntroRange(String descrizione){
		return checker.getPercentualeMisureInRange(descrizione);
	}
	
	public void addMisura(String descrizione, double pesoReale){
		List<Misura> misure= checker.getTabellaMisure().get(descrizione);
		misure.add(new Misura(descrizione, misure.get(0).getExpected(), pesoReale));
		//checker.getTabellaMisure().put(descrizione, misure);
	}
}

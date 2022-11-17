package qa.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import qa.persistence.BadFileFormatException;
import qa.persistence.MyMisureReader;
import qa.persistence.MisureReader;

public abstract class Checker {

	private Map<String, List<Misura>> mappa;
	private List<Misura> elencoMisure;
	private int totaleMisure;

	public Checker(List<Misura> elencoMisure) {
		if (elencoMisure == null || elencoMisure.size() == 0)
			throw new IllegalArgumentException("elenco misure nullo o vuoto");

		this.elencoMisure = elencoMisure;
		this.mappa = new HashMap<>();
		totaleMisure = 0;
		popolaMappa();
	}

	private void popolaMappa() {
		for (Misura misura : elencoMisure) {
			String key = misura.getName();
			List<Misura> lista = mappa.get(key);
			if (lista == null) {
				lista = new ArrayList<>();
				mappa.put(key, lista);
			}
			lista.add(misura);
			totaleMisure++;
		}
	}

	protected List<Misura> getElencoMisure() {
		return Collections.unmodifiableList(elencoMisure);
	}

	public Map<String, List<Misura>> getTabellaMisure() {
		return Collections.unmodifiableMap(mappa);
	}

	public List<Misura> getMisure(String descrizione) {
		if (descrizione == null || descrizione.isEmpty())
			throw new IllegalArgumentException("descrizione nulla o vuota");
		List<Misura> list = mappa.get(descrizione);
		if (list == null)
			throw new IllegalArgumentException("descrizione non presente");
		return Collections.unmodifiableList(list);
	}

	public int getNumeroMisure() {
		return totaleMisure;
	}
	
	public boolean verificaScostamentoPercentuale(double expected, double real, double percentuale){
		return Double.compare(expected-real,  percentuale*expected/100) <= 0;
		// restituisce true se tutto ok, false se critica
		// per "critica" si intende fuori range per difetto: prodotto in eccesso invece è sempre ok
	}

	public boolean verificaScostamentoAssoluto(double expected, double real, double delta){
		return Double.compare(expected-real,  delta) <= 0;
		// restituisce true se tutto ok, false se critica
		// per "critica" si intende fuori range per difetto: prodotto in eccesso invece è sempre ok
	}

	// quick test
	public static void main(String args[]) throws IOException, BadFileFormatException {
		try (Reader r = new FileReader("Misure.txt")) {
			MisureReader vReader = new MyMisureReader();
			List<Misura> listaMisure = vReader.leggiMisure(r);
			Checker checker = new WeightChecker(listaMisure);
			System.out.println("Misure lette: " + checker.getNumeroMisure());
			for (String descrizione : checker.getTabellaMisure().keySet()) {
				System.out.println(descrizione + "(" + checker.getMisure(descrizione).size() + ")");
				Misura m0 = checker.getMisure(descrizione).get(0);
				System.out.println("Misura selezionata: " + m0);
				System.out.println("Verifica scostamento percentuale: " + checker.verificaScostamentoPercentuale(m0.getExpected(),m0.getReal(), 3.0));
				System.out.println("Verifica scostamento assoluto:    " + checker.verificaScostamentoAssoluto(m0.getExpected(),m0.getReal(), 5));				
			}
		}
	}

	public abstract Map<String, Double> getTabellaPercentuali();

	public abstract String printTabellaPercentuali();

	public abstract List<Misura> getListaMisureInRange(String descrizione) ;

	public abstract double getPercentualeMisureInRange(String descrizione) ;

}

package pianodistudi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pianodistudi.model.AttivitaFormativa;
import pianodistudi.model.Ordinamento;
import pianodistudi.model.PianoDiStudi;
import pianodistudi.model.Ssd;
import pianodistudi.model.Tipologia;

public class Controller {

	public static void alert(String title, String headerMessage, String contentMessage) {
		javafx.scene.control.Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	//--------------
	
	private Ordinamento ord;
	private Map<String,AttivitaFormativa> mappaAF;
	private PianoDiStudi piano;

	public Controller(Ordinamento ord, Map<String,AttivitaFormativa> mappaAF) {
		this.ord=ord;
		this.mappaAF=mappaAF;
		this.piano = new PianoDiStudi();
	}
	
	public Ordinamento getOrdinamento() {
		return this.ord;
	}
	
	public SortedSet<String> getListaAF() {
		return new TreeSet<String>(mappaAF.keySet());
	}
	
	public AttivitaFormativa getAttivitaFormativaPerNome(String afName) {
		return mappaAF.get(afName);
	}

	public void inserisci(AttivitaFormativa af, Tipologia t) {
		piano.inserisci(af, t);
	}

	public void rimuovi(AttivitaFormativa af, Tipologia t) {
		piano.rimuovi(af, t);
	}

	public int getCfu(Tipologia t) {
		return piano.getCfu(t);
	}
	
	public int getCfuTot() {
		return piano.getCfuTot();
	}
	
	public boolean rispettaOrdinamento() {	
		return piano.verificaOrdinamento(ord);
	}
	
	public String getLogVerificaOrdinamento() {
		return piano.logVerifica();
	}
	
	public List<AttivitaFormativa> getAttivitaFormativePerTipologia(Tipologia t){
		return piano.getAttivitaFormative(t);
	}

	public Map<Tipologia, List<AttivitaFormativa>> getPianoDidatticoStandard(){
		Map<Tipologia, List<AttivitaFormativa>> mappaPianoStandard = new HashMap<>();
		mappaPianoStandard.put(Tipologia.A1, List.of(
				new AttivitaFormativa("ANALISI MATEMATICA T-1", Ssd.MAT05, 9),
				new AttivitaFormativa("ANALISI MATEMATICA T-2", Ssd.MAT05, 6),
				new AttivitaFormativa("FONDAMENTI DI INFORMATICA T-1", Ssd.INGINF05, 12),
				new AttivitaFormativa("FONDAMENTI DI INFORMATICA T-2", Ssd.INGINF05, 12),
				new AttivitaFormativa("GEOMETRIA E ALGEBRA T", Ssd.MAT03, 6)
				));
		mappaPianoStandard.put(Tipologia.A2, List.of(
				new AttivitaFormativa("FISICA GENERALE T", Ssd.FIS01, 9)));
		mappaPianoStandard.put(Tipologia.B1, List.of(
				new AttivitaFormativa("ELETTRONICA T", Ssd.INGINF01, 6)));
		mappaPianoStandard.put(Tipologia.B2, List.of(
				new AttivitaFormativa("RETI LOGICHE T", Ssd.INGINF05, 6),
				new AttivitaFormativa("CALCOLATORI ELETTRONICI T", Ssd.INGINF05, 6), 
				new AttivitaFormativa("SISTEMI INFORMATIVI T", Ssd.INGINF05, 9),
				new AttivitaFormativa("SISTEMI OPERATIVI T", Ssd.INGINF05, 9),
				new AttivitaFormativa("CONTROLLI AUTOMATICI T", Ssd.INGINF04, 9),
				new AttivitaFormativa("RETI DI CALCOLATORI T", Ssd.INGINF05, 9), 
				new AttivitaFormativa("TECNOLOGIE WEB T", Ssd.INGINF05, 9), 
				new AttivitaFormativa("INGEGNERIA DEL SOFTWARE T", Ssd.INGINF05, 9)
				));
		mappaPianoStandard.put(Tipologia.B3, List.of(
				new AttivitaFormativa("FONDAMENTI DI TELECOMUNICAZIONI T", Ssd.INGINF03, 9)));
		mappaPianoStandard.put(Tipologia.C, List.of(
				new AttivitaFormativa("MATEMATICA APPLICATA T", Ssd.MAT07, 6),
				new AttivitaFormativa("ELETTROTECNICA T", Ssd.INGIND31, 6),
				new AttivitaFormativa("ECONOMIA E ORGANIZZAZIONE AZIENDALE T", Ssd.INGIND35, 6)
				));
		mappaPianoStandard.put(Tipologia.D, List.of(
				new AttivitaFormativa("AMMINISTRAZIONE DI SISTEMI T", Ssd.INGINF05, 6), 
				new AttivitaFormativa("DIRITTO DELL'INFORMATICA T", Ssd.IUS14, 6)
				));
		mappaPianoStandard.put(Tipologia.E1, List.of(
				new AttivitaFormativa("PROVA FINALE T", Ssd.PROVAFINALE, 3)));
		mappaPianoStandard.put(Tipologia.E2, List.of(
				new AttivitaFormativa("LINGUA INGLESE B-2", Ssd.LINGUASTRANIERA, 6)));
		mappaPianoStandard.put(Tipologia.F, List.of(
				new AttivitaFormativa("TIROCINIO T", Ssd.QUALSIASI, 6)));
		
		return mappaPianoStandard;
	}
}

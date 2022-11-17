package pianodistudi.model;

import java.util.Optional;

public enum Tipologia {

	A1("A", "di base", "Matematica informatica e statistica"), 
	A2("A", "di base", "Fisica e chimica"),
	B1("B", "caratterizzanti", "Ingegneria elettronica"), 
	B2("B", "caratterizzanti", "Ingegneria informatica"), 
	B3("B", "caratterizzanti", "Ingegneria delle telecomunicazioni"), 
	C("C", "affini o integrative"),
	D("D", "a scelta dello studente"), 
	E1("E", "prova finale e lingue straniere", "prova finale"),
	E2("E", "prova finale e lingue straniere", "lingue straniere"),
	F("F", "altre");

	private String sigla, descrizione;
	private Optional<String> sottoAmbito;

	private Tipologia(String sigla, String descrizione, String sottoAmbito) {
		this.sigla= sigla;
		this.descrizione = descrizione;
		this.sottoAmbito = Optional.of(sottoAmbito);
	}
	private Tipologia(String sigla, String descrizione) {
		this.sigla= sigla;
		this.descrizione = descrizione;
		this.sottoAmbito = Optional.empty();
	}

	public String getSigla() {
		return sigla;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public Optional<String> getSottoAmbito() {
		return sottoAmbito;
	}
	
	public static Tipologia of(String sigla, String sottoAmbito) {
		for (Tipologia t : Tipologia.values()) {
			if (t.getSottoAmbito().isPresent()) {
				if (t.getSigla().equals(sigla) && !sottoAmbito.equals("") && t.getSottoAmbito().get().equals(sottoAmbito))
					return t;
			} 
			else {
				if (t.getSigla().equals(sigla) && sottoAmbito.equals(""))
					return t;
			}
		}
		throw new IllegalArgumentException("sigla o sottoambito errati per " + sigla + ", " + sottoAmbito);
	}
	
	public static Tipologia of(String sigla) {
		return of(sigla, "");
	}
	
	@Override 
	public String toString() {
		return "Tipologia " + this.getSigla() + " (materie " + this.getDescrizione() + ")" + 
				(this.getSottoAmbito().isPresent() 
				? ", sotto-ambito " + this.getSottoAmbito().get() : "");
	}
}

package pianodistudi.model;

import java.util.List;

public interface Piano {
	public static final int CFULT = 180;
	
	public int getCfu(Tipologia t);
	public int getCfuTot();
	public List<AttivitaFormativa> getAttivitaFormative(Tipologia t);
	
	public static Piano getPianoStandard() {
		return new Piano() {
			
			@Override
			public int getCfuTot() { return CFULT; }
			
			@Override
			public int getCfu(Tipologia t) {
				return switch(t) {
				case A1 -> 45;
				case A2 -> 9;
				case B1 -> 6;
				case B2 -> 66;
				case B3 -> 9;
				case C  -> 18;
				case D  -> 12;
				case E1 -> 3;
				case E2 -> 6;
				case F  -> 6;
				};
			}
			
			@Override
			public List<AttivitaFormativa> getAttivitaFormative(Tipologia t) {
				return switch(t) {
					case A1 -> List.of(	new AttivitaFormativa("Analisi matematica T-1", Ssd.MAT05, 9),
										new AttivitaFormativa("Analisi matematica T-2", Ssd.MAT05, 6),
										new AttivitaFormativa("Fondamenti di Informatica T-1", Ssd.INGINF05, 12),
										new AttivitaFormativa("Fondamenti di Informatica T-2", Ssd.INGINF05, 12),
										new AttivitaFormativa("Geometria e algebra T", Ssd.MAT03, 6));
					case A2 -> List.of(	new AttivitaFormativa("Fisica generale T", Ssd.FIS01, 9));
					case B1 -> List.of(	new AttivitaFormativa("Elettronica T", Ssd.INGINF01, 6));
					case B2 -> List.of(	new AttivitaFormativa("Reti logiche T", Ssd.INGINF05, 6),
										new AttivitaFormativa("Calcolatori elettronici T", Ssd.INGINF05, 6),
										new AttivitaFormativa("Sistemi informativi T", Ssd.INGINF05, 9),
										new AttivitaFormativa("Sistemi operativi T", Ssd.INGINF05, 9),
										new AttivitaFormativa("Controlli automatici T", Ssd.INGINF04, 9),
										new AttivitaFormativa("Reti di calcolatori T", Ssd.INGINF05, 9),
										new AttivitaFormativa("Tecnologie web T", Ssd.INGINF05, 9),
										new AttivitaFormativa("Ingegneria del software T", Ssd.INGINF05, 9));
					case B3 -> List.of(	new AttivitaFormativa("Fondamenti di telecomunicazioni T", Ssd.INGINF03, 9));
					case C  -> List.of(	new AttivitaFormativa("Matematica applicata T", Ssd.MAT07, 6),
										new AttivitaFormativa("Elettrotecnica T", Ssd.INGIND31, 6),
										new AttivitaFormativa("Economia e organizzazione aziendale T", Ssd.INGIND35, 6));
					case D  -> List.of(	new AttivitaFormativa("Amministrazione di sistemi T", Ssd.INGINF05, 6),
										new AttivitaFormativa("Diritto dell'inforamtica T", Ssd.IUS14, 6));
					case E1 -> List.of(	new AttivitaFormativa("Prova finale T", Ssd.QUALSIASI, 3));
					case E2 -> List.of(	new AttivitaFormativa("Lingua inglese B-2", Ssd.QUALSIASI, 6));
					case F  -> List.of(	new AttivitaFormativa("Tirocinio T", Ssd.QUALSIASI, 6));
					};
			}
		};
	}
}

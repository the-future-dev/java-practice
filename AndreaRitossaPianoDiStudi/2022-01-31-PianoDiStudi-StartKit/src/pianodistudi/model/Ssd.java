package pianodistudi.model;

public enum Ssd {
	 
	CHIM07("Chim/07"), FIS01("Fis/01"), FIS03("Fis/03"), 
	INF01("Inf/01"), 
	INGIND31("Ing-Ind/31"), INGIND35("Ing-Ind/35"), 
	INGINF01("Ing-Inf/01"), INGINF02("Ing-Inf/02"), INGINF03("Ing-Inf/03"), INGINF04("Ing-Inf/04"), 
	INGINF05("Ing-Inf/05"), INGINF07("Ing-Inf/07"),   
	IUS14("Ius/14"),
	MAT01("Mat/01"), MAT02("Mat/02"), MAT03("Mat/03"), MAT05("Mat/05"), MAT06("Mat/06"), MAT07("Mat/07"), MAT08("Mat/08"), MAT09("Mat/09"), 
	QUALSIASI("qualsiasi"), PROVAFINALE("prova finale"), LINGUASTRANIERA("lingua straniera");
	
	private Ssd(String s) {
		value=s;
	}
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	public static Ssd of(String s) {
		// se stringa illegale, lancia IEA
		return Ssd.valueOf(s.replace("-", "").replace("/", "").replace(" ", "").toUpperCase());
	}
}

package ubz.model;

public enum Taglio  {
	CINQUECENTO(500), DUECENTO(200), CENTO(100), CINQUANTA(50), VENTI(20), DIECI(10), CINQUE(5), DUE(2), UNO(1); 

	private Taglio(int valore){
		this.valore=valore;
	}
	private int valore;
	public int getValore() { return valore; }
	
}

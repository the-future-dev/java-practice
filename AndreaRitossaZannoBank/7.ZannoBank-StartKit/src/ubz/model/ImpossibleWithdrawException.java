package ubz.model;

public class ImpossibleWithdrawException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private int importo, importoResiduo;
	
	public ImpossibleWithdrawException(int importo, int importoResiduo){
		this.importo=importo;
		this.importoResiduo=importoResiduo;
	}
	
	public String getMessage(){
		return "Impossibile prelevare € " + importo + " causa € " + importoResiduo + " non erogabili";
	}
}
